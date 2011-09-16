package com.bis.web.controller;

import com.bis.core.services.ItemMasterService;
import com.bis.domain.Item;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ItemsControllerTest {

    @Mock
    private ItemMasterService itemMasterService;
    @Mock
    private BindingResult bindingResult;
    @Mock
    private Model model;
    private ItemsController controller;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
        controller = new ItemsController(itemMasterService);
    }

    @Test
    public void shouldGetItemModelAndViewForGivenItemCode(){
        int itemCode = 12;
        Item item = new Item();
        when(itemMasterService.get(itemCode)).thenReturn(item);
        ModelAndView modelAndView = controller.show(itemCode);
        Assert.assertEquals(item,modelAndView.getModel().get("item"));
        Assert.assertEquals("item/show",modelAndView.getViewName());
    }

    @Test
    public void shouldGetCreateItemForm(){
        ModelAndView modelAndView = controller.createForm();
        Item item = (Item) modelAndView.getModel().get("item");
        Assert.assertNull(item.getItemCode());
        Assert.assertEquals("item/createForm",modelAndView.getViewName());
    }

    @Test
    public void shouldGetUpdateItemForm(){
        int itemCode = 12;
        Item item = new Item();
        when(itemMasterService.get(itemCode)).thenReturn(item);
        ModelAndView modelAndView = controller.updateForm(itemCode);
        Assert.assertEquals(item,modelAndView.getModel().get("item"));
        Assert.assertEquals("item/updateForm",modelAndView.getViewName());
    }

    @Test
    public void shouldCreateNewItem(){
        Item item = new Item();
        item.setItemCode(12);
        String result = controller.create(item, bindingResult,model);
        Assert.assertEquals("redirect:/item/show/12",result);
        verify(itemMasterService).create(item);
    }

    @Test
    public void shouldUpdateItem(){
        Item item = new Item();
        item.setItemCode(12);
        String result = controller.update(item, bindingResult,model);
        Assert.assertEquals("redirect:/item/show/12",result);
        verify(itemMasterService).update(item);
    }
}
