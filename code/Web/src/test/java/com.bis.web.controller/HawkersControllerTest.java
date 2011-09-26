package com.bis.web.controller;

import com.bis.core.services.HawkerMasterService;
import com.bis.domain.BillingCycle;
import com.bis.domain.Hawker;
import com.bis.testcommon.HawkerBuilder;
import com.bis.web.viewmodel.HawkerList;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class HawkersControllerTest {

    @Mock
    private HawkerMasterService hawkerMasterService;
    @Mock
    private BindingResult bindingResult;
    @Mock
    private Model model;
    private HawkersController controller;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        controller = new HawkersController(hawkerMasterService);
    }

    @Test
    public void shouldGetHawkerModelAndViewForGivenHawkerId() {
        int hawkerId = 12;
        Hawker hawker = new Hawker();
        when(hawkerMasterService.get(hawkerId)).thenReturn(hawker);
        ModelAndView modelAndView = controller.show(hawkerId);
        assertEquals(hawker, modelAndView.getModel().get("hawker"));
        assertEquals("hawker/show", modelAndView.getViewName());
    }

    @Test
    public void shouldGetCreateHawkerForm() {
        ModelAndView modelAndView = controller.createForm();
        Hawker hawker = (Hawker) modelAndView.getModel().get("hawker");
        Assert.assertNull(hawker.getHawkerId());
        assertEquals("hawker/createForm", modelAndView.getViewName());
    }

    @Test
    public void shouldGetUpdateHawkerForm() {
        int hawkerId = 12;
        Hawker hawker = new Hawker();
        when(hawkerMasterService.get(hawkerId)).thenReturn(hawker);
        ModelAndView modelAndView = controller.updateForm(hawkerId);
        assertEquals(hawker, modelAndView.getModel().get("hawker"));
        assertEquals("hawker/updateForm", modelAndView.getViewName());
    }

    @Test
    public void shouldCreateNewHawker() {
        Hawker hawker = new Hawker();
        hawker.setHawkerId(12);
        String result = controller.create(hawker, bindingResult, model);
        assertEquals("redirect:/hawker/show/12", result);
        verify(hawkerMasterService).create(hawker);
    }

    @Test
    public void shouldUpdateHawker() {
        Hawker hawker = new Hawker();
        hawker.setHawkerId(12);
        String result = controller.update(hawker, bindingResult, model);
        assertEquals("redirect:/hawker/show/12", result);
        verify(hawkerMasterService).update(hawker);
    }

    @Test
    public void shouldGetListOfBillingCycles() {
        Map<Character, String> billingCycles = controller.billingCycles();
        assertEquals(BillingCycle.WEEKLY.toString(), billingCycles.get(BillingCycle.WEEKLY.getCode()));
        assertEquals(BillingCycle.FORTNIGHT.toString(), billingCycles.get(BillingCycle.FORTNIGHT.getCode()));
        assertEquals(BillingCycle.MONTHLY.toString(), billingCycles.get(BillingCycle.MONTHLY.getCode()));
    }

    @Test
    public void shouldGetListOfHawkers() {
        Hawker hawkerOne = new HawkerBuilder().withDefaults().setHawkerId(0).setName("pqr").build();
        Hawker hawkerTwo = new HawkerBuilder().withDefaults().setHawkerId(1).setName("abc").build();
        ArrayList<Hawker> hawkers = new ArrayList<Hawker>();
        hawkers.add(hawkerOne);
        hawkers.add(hawkerTwo);
        when(hawkerMasterService.getAll()).thenReturn(hawkers);
        ModelAndView modelAndView = controller.list(1);
        HawkerList hawkerList = (HawkerList) modelAndView.getModel().get("hawkerList");
        assertEquals(2, hawkerList.getHawkers().size());
        assertEquals(1, hawkerList.getSelectedHawkerId());
        assertEquals(hawkerTwo, hawkerList.getHawkers().get(0));
        assertEquals(hawkerOne, hawkerList.getHawkers().get(1));
        assertEquals(hawkerTwo, hawkerList.getSelectedHawker());
    }
}
