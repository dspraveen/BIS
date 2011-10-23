package com.bis.web.controller;

import com.bis.core.services.ItemMasterService;
import com.bis.domain.Item;
import com.bis.domain.ItemCycle;
import com.bis.domain.ItemReturnType;
import com.bis.testcommon.ItemBuilder;
import com.bis.web.viewmodel.ItemList;
import com.bis.web.viewmodel.ItemViewForm;
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
import static org.junit.Assert.assertTrue;
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
    public void setup() {
        MockitoAnnotations.initMocks(this);
        controller = new ItemsController(itemMasterService);
    }

    @Test
    public void shouldGetItemModelAndViewForGivenItemCode() {
        int itemCode = 12;
        Item item = new Item();
        item.setItemCode(itemCode);
        when(itemMasterService.get(itemCode)).thenReturn(item);
        when(itemMasterService.getItemPrice(itemCode)).thenReturn(10f);
        ModelAndView modelAndView = controller.show(itemCode);
        ItemViewForm itemViewForm = (ItemViewForm) modelAndView.getModel().get("itemForm");
        assertEquals(item, itemViewForm.getItem());
        assertTrue(itemViewForm.getItemPrice()==10f);
        assertEquals("item/show", modelAndView.getViewName());
    }

    @Test
    public void shouldGetCreateItemForm() {
        ModelAndView modelAndView = controller.createForm();
        ItemViewForm itemViewForm = (ItemViewForm) modelAndView.getModel().get("itemForm");
        Assert.assertNull(itemViewForm.getItem().getItemCode());
        assertEquals("item/createForm", modelAndView.getViewName());
    }

    @Test
    public void shouldGetUpdateItemForm() {
        int itemCode = 12;
        Item item = new Item();
        item.setItemCode(itemCode);
        when(itemMasterService.get(itemCode)).thenReturn(item);
        ModelAndView modelAndView = controller.updateForm(itemCode);
        ItemViewForm itemViewForm = (ItemViewForm) modelAndView.getModel().get("itemForm");
        assertEquals(item, itemViewForm.getItem());
        assertEquals("item/updateForm", modelAndView.getViewName());
    }

    @Test
    public void shouldCreateNewItem() {
        ItemViewForm itemViewForm = new ItemViewForm(new Item(),0f);
        itemViewForm.getItem().setItemCode(12);
        Item item = itemViewForm.getItem();
        String result = controller.create(itemViewForm, bindingResult, model);
        assertEquals("redirect:/item/show/12", result);
        verify(itemMasterService).create(item);
    }

    @Test
    public void shouldUpdateItem() {
        ItemViewForm itemViewForm = new ItemViewForm(new Item(),0f);
        itemViewForm.getItem().setItemCode(12);
        Item item = itemViewForm.getItem();
        String result = controller.update(itemViewForm, bindingResult, model);
        assertEquals("redirect:/item/show/12", result);
        verify(itemMasterService).update(item);
    }

    @Test
    public void shouldGetListOfItemTypes() {
        Map<Character, String> itemTypes = controller.itemTypes();
        assertEquals(ItemCycle.WEEKLY.toString(), itemTypes.get(ItemCycle.WEEKLY.getCode()));
        assertEquals(ItemCycle.FORTNIGHT.toString(), itemTypes.get(ItemCycle.FORTNIGHT.getCode()));
        assertEquals(ItemCycle.MONTHLY.toString(), itemTypes.get(ItemCycle.MONTHLY.getCode()));
    }

    @Test
    public void shouldGetListOfItemReturnTypes() {
        Map<Character, String> itemReturnTypes = controller.itemReturnTypes();
        assertEquals(ItemReturnType.YES.toString(), itemReturnTypes.get(ItemReturnType.YES.getCode()));
        assertEquals(ItemReturnType.NO.toString(), itemReturnTypes.get(ItemReturnType.NO.getCode()));
    }

    @Test
    public void shouldGetListOfItems() {
        Item itemOne = new ItemBuilder().withDefaults().setItemCode(0).setName("pqr").build();
        Item itemTwo = new ItemBuilder().withDefaults().setItemCode(1).setName("abc").build();
        ArrayList<Item> items = new ArrayList<Item>();
        items.add(itemOne);
        items.add(itemTwo);
        when(itemMasterService.getAll()).thenReturn(items);
        ModelAndView modelAndView = controller.list(1);
        ItemList itemList = (ItemList) modelAndView.getModel().get("itemList");
        assertEquals(2, itemList.getCount());
        assertEquals(1, itemList.getSelectedItemCode());
        assertEquals(itemTwo.getItemName(), itemList.getItemNames().get(itemTwo.getItemCode()));
        assertEquals(itemOne.getItemName(), itemList.getItemNames().get(itemOne.getItemCode()));
        assertEquals(itemTwo, itemList.getSelectedItem());
    }

    @Test
    public void shouldGetItemPriceJson() throws IOException {
        MockHttpServletResponse response = new MockHttpServletResponse();
        when(itemMasterService.getItemPrice(1)).thenReturn(10f);
        controller.getItemPrice(1,response);
        Assert.assertEquals("{\"price\":10.0}",response.getContentAsString());
    }
}
