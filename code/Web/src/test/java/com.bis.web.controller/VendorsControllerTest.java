package com.bis.web.controller;

import com.bis.core.services.VendorMasterService;
import com.bis.domain.BillingCycle;
import com.bis.domain.Vendor;
import com.bis.testcommon.VendorBuilder;
import com.bis.web.viewmodel.VendorList;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class VendorsControllerTest {

    @Mock
    private VendorMasterService vendorMasterService;
    @Mock
    private BindingResult bindingResult;
    @Mock
    private Model model;
    private VendorsController controller;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        controller = new VendorsController(vendorMasterService);
    }

    @Test
    public void shouldGetVendorModelAndViewForGivenVendorId() {
        int vendorId = 12;
        Vendor vendor = new Vendor();
        when(vendorMasterService.get(vendorId)).thenReturn(vendor);
        ModelAndView modelAndView = controller.show(vendorId);
        assertEquals(vendor, modelAndView.getModel().get("vendor"));
        assertEquals("vendor/show", modelAndView.getViewName());
    }

    @Test
    public void shouldGetCreateVendorForm() {
        ModelAndView modelAndView = controller.createForm();
        Vendor vendor = (Vendor) modelAndView.getModel().get("vendor");
        Assert.assertNull(vendor.getVendorId());
        assertEquals("vendor/createForm", modelAndView.getViewName());
    }

    @Test
    public void shouldGetUpdateVendorForm() {
        int vendorId = 12;
        Vendor vendor = new Vendor();
        when(vendorMasterService.get(vendorId)).thenReturn(vendor);
        ModelAndView modelAndView = controller.updateForm(vendorId);
        assertEquals(vendor, modelAndView.getModel().get("vendor"));
        assertEquals("vendor/updateForm", modelAndView.getViewName());
    }

    @Test
    public void shouldCreateNewVendor() {
        Vendor vendor = new Vendor();
        vendor.setVendorId(12);
        String result = controller.create(vendor, bindingResult, model);
        assertEquals("redirect:/vendor/show/12", result);
        verify(vendorMasterService).create(vendor);
    }

    @Test
    public void shouldUpdateVendor() {
        Vendor vendor = new Vendor();
        vendor.setVendorId(12);
        String result = controller.update(vendor, bindingResult, model);
        assertEquals("redirect:/vendor/show/12", result);
        verify(vendorMasterService).update(vendor);
    }

    @Test
    public void shouldGetListOfBillingCycles() {
        Map<Character, String> billingCycles = controller.billingCycles();
        assertEquals(BillingCycle.WEEKLY.toString(), billingCycles.get(BillingCycle.WEEKLY.getCode()));
        assertEquals(BillingCycle.FORTNIGHT.toString(), billingCycles.get(BillingCycle.FORTNIGHT.getCode()));
        assertEquals(BillingCycle.MONTHLY.toString(), billingCycles.get(BillingCycle.MONTHLY.getCode()));
    }

    @Test
    public void shouldGetListOfVendors() {
        Vendor vendorOne = new VendorBuilder().withDefaults().setVendorId(0).setVendorName("pqr").build();
        Vendor vendorTwo = new VendorBuilder().withDefaults().setVendorId(1).setVendorName("abc").build();
        ArrayList<Vendor> vendors = new ArrayList<Vendor>();
        vendors.add(vendorOne);
        vendors.add(vendorTwo);
        when(vendorMasterService.getAll()).thenReturn(vendors);
        ModelAndView modelAndView = controller.list(1);
        VendorList vendorList = (VendorList) modelAndView.getModel().get("vendorList");
        assertEquals(2, vendorList.getVendors().size());
        assertEquals(1, vendorList.getSelectedVendorId());
        assertEquals(vendorTwo, vendorList.getVendors().get(0));
        assertEquals(vendorOne, vendorList.getVendors().get(1));
        assertEquals(vendorTwo, vendorList.getSelectedVendor());
    }

    @Test
    public void shouldGetItemPriceJson() throws IOException {
        MockHttpServletResponse response = new MockHttpServletResponse();
        Vendor vendor = new Vendor();
        vendor.setVendorDiscount(10f);
        when(vendorMasterService.get(1)).thenReturn(vendor);
        controller.getVendorDiscount(1,response);
        Assert.assertEquals("{\"discount\":10.0}",response.getContentAsString());
    }
}
