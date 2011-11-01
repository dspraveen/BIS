package com.bis.web.controller;

import com.bis.core.services.ItemMasterService;
import com.bis.domain.Item;
import com.bis.domain.ItemCycle;
import com.bis.domain.ItemReturnType;
import com.bis.web.viewmodel.ItemList;
import com.bis.web.viewmodel.ItemViewForm;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.*;

@Controller
@RequestMapping("/item")
public class ItemsController extends BaseController{

    protected final Logger logger = Logger.getLogger(getClass());

    private ItemMasterService itemMasterService;

    @Autowired
    public ItemsController(ItemMasterService itemMasterService) {
        this.itemMasterService = itemMasterService;
    }

    @RequestMapping(value = "/show/{id}", method = RequestMethod.GET)
    public ModelAndView show(@PathVariable("id") int itemCode) {
        Item item = itemMasterService.get(itemCode);
        float itemPrice = itemMasterService.getItemPrice(item.getItemCode());
        return new ModelAndView("item/show", "itemForm", new ItemViewForm(item, itemPrice));
    }

    @RequestMapping(value = "/createForm", method = RequestMethod.GET)
    public ModelAndView createForm() {
        Item item = new Item();
        item.setReturnable('Y');
        item.setItemLife('W');
        return new ModelAndView("item/createForm", "itemForm", new ItemViewForm(item, 0f));
    }

    @RequestMapping(value = "/updateForm/{id}", method = RequestMethod.GET)
    public ModelAndView updateForm(@PathVariable("id") int itemCode) {
        Item item = itemMasterService.get(itemCode);
        float itemPrice = itemMasterService.getItemPrice(item.getItemCode());
        return new ModelAndView("item/updateForm", "itemForm", new ItemViewForm(item, itemPrice));
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(@Valid ItemViewForm itemForm, BindingResult bindingResult, Model uiModel) {
        uiModel.asMap().clear();
        Item item = itemForm.getItem();
        itemMasterService.create(item);
        itemMasterService.setItemPrice(item.getItemCode(), itemForm.getItemPrice());
        return "redirect:/item/show/" + item.getItemCode();
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(@Valid ItemViewForm itemForm, BindingResult bindingResult, Model uiModel) {
        uiModel.asMap().clear();
        Item item = itemForm.getItem();
        itemMasterService.update(item);
        itemMasterService.setItemPrice(item.getItemCode(), itemForm.getItemPrice());
        return "redirect:/item/show/" + item.getItemCode();
    }

    @RequestMapping(value = "/list")
    public ModelAndView list(@RequestParam(value = "selectedItemCode", defaultValue = "0", required = false) int selectedItemCode) {
        List<Item> all = itemMasterService.getAll();
        if(all.isEmpty()){
            return new ModelAndView("item/list", "itemList", new ItemList(selectedItemCode, new ArrayList<ItemViewForm>()));
        }
        Collections.sort(all, new Comparator<Item>() {
            @Override
            public int compare(Item o1, Item o2) {
                return o1.getItemName().compareToIgnoreCase(o2.getItemName());
            }
        });
        if(selectedItemCode == 0){
            selectedItemCode = all.get(0).getItemCode();
        }
        List<ItemViewForm> itemViewForms = new ArrayList<ItemViewForm>();
        for (Item item : all) {
            float itemPrice = 0f;
            if (selectedItemCode == item.getItemCode()) {
                itemPrice = itemMasterService.getItemPrice(item.getItemCode());
            }
            itemViewForms.add(new ItemViewForm(item, itemPrice));
        }
        return new ModelAndView("item/list", "itemList", new ItemList(selectedItemCode, itemViewForms));
    }

    @RequestMapping(value = "/price", method = RequestMethod.GET)
    public ModelAndView getItemPrice(@RequestParam(value = "selectedItemCode", defaultValue = "0", required = false) int selectedItemCode, HttpServletResponse response) {
        float itemPrice = itemMasterService.getItemPrice(selectedItemCode);
        Map<String, Float> price = new HashMap<String, Float>();
        price.put("price", itemPrice);
        return json(price,response);
    }

    @ModelAttribute("itemTypes")
    public Map<Character, String> itemTypes() {
        Map<Character, String> itemCycles = new HashMap<Character, String>();
        for (ItemCycle itemCycle : ItemCycle.values()) {
            itemCycles.put(itemCycle.getCode(), itemCycle.toString());
        }
        return itemCycles;
    }

    @ModelAttribute("itemReturnTypes")
    public Map<Character, String> itemReturnTypes() {
        Map<Character, String> itemReturnTypes = new HashMap<Character, String>();
        for (ItemReturnType itemReturnType : ItemReturnType.values()) {
            itemReturnTypes.put(itemReturnType.getCode(), itemReturnType.toString());
        }
        return itemReturnTypes;
    }
}
