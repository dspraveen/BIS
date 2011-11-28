package com.bis.web.controller;

import com.bis.core.services.ItemMasterService;
import com.bis.domain.Item;
import com.bis.domain.ItemCycle;
import com.bis.domain.ItemReturnType;
import com.bis.testcommon.ItemBuilder;
import com.bis.web.viewmodel.ItemList;
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
        ModelAndView modelAndView = controller.show(itemCode);
        Item itemModel = (Item) modelAndView.getModel().get("item");
        Assert.assertEquals(item, itemModel);
        assertEquals("item/show", modelAndView.getViewName());
    }

    @Test
    public void shouldGetCreateItemForm() {
        ModelAndView modelAndView = controller.createForm();
        Item item = (Item) modelAndView.getModel().get("item");
        Assert.assertNull(item.getItemCode());
        assertEquals("item/createForm", modelAndView.getViewName());
    }

    @Test
    public void shouldGetUpdateItemForm() {
        int itemCode = 12;
        Item item = new Item();
        item.setItemCode(itemCode);
        when(itemMasterService.get(itemCode)).thenReturn(item);
        ModelAndView modelAndView = controller.updateForm(itemCode);
        Item itemModel = (Item) modelAndView.getModel().get("item");
        assertEquals(item, itemModel);
        assertEquals("item/updateForm", modelAndView.getViewName());
    }

    @Test
    public void shouldCreateNewItem() {
        Item item = new Item();
        item.setItemCode(12);
        String result = controller.create(item, bindingResult, model);
        assertEquals("redirect:/item/show/12", result);
        verify(itemMasterService).create(item);
    }

    @Test
    public void shouldUpdateItem() {
        Item item = new Item();
        item.setItemCode(12);
        String result = controller.update(item, bindingResult, model);
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
        assertEquals(itemTwo.getItemName(), itemList.getItems().get(0).getItemName());
        assertEquals(itemOne.getItemName(), itemList.getItems().get(1).getItemName());
        assertEquals(itemTwo, itemList.getSelectedItem());
    }

    @Test
    public void shouldGetItemPriceJson() throws IOException {
        MockHttpServletResponse response = new MockHttpServletResponse();
        Item item = new Item();
        item.setDefaultPrice(10f);
        when(itemMasterService.get(1)).thenReturn(item);
        controller.getItemPrice(1,response);
        Assert.assertEquals("{\"price\":10.0}",response.getContentAsString());
    }
}
