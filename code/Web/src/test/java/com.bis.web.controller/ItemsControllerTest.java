package com.bis.web.controller;

import com.bis.core.services.ItemMasterService;
import com.bis.domain.Item;
import com.bis.domain.ItemCycle;
import com.bis.domain.ItemReturnType;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

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
        when(itemMasterService.get(itemCode)).thenReturn(item);
        ModelAndView modelAndView = controller.show(itemCode);
        assertEquals(item, modelAndView.getModel().get("item"));
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
        when(itemMasterService.get(itemCode)).thenReturn(item);
        ModelAndView modelAndView = controller.updateForm(itemCode);
        assertEquals(item, modelAndView.getModel().get("item"));
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
        /*Item itemOne = new ItemBuilder().withDefaults().setName("pqr").build();
        Item itemTwo = new ItemBuilder().withDefaults().setName("abc").build();
        ArrayList<Item> itemList = new ArrayList<Item>();
        itemList.add(itemOne);
        itemList.add(itemTwo);
        when(itemMasterService.getAll()).thenReturn(itemList);
        List<Item> items = controller.list();
        assertEquals(2, items.size());
        assertEquals(itemTwo, items.get(0));
        assertEquals(itemOne, items.get(1));*/
    }
}
