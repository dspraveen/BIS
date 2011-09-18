package com.bis.web.controller;

import com.bis.core.services.ItemMasterService;
import com.bis.domain.Item;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/item")
public class ItemsController {

    private ItemMasterService itemMasterService;

    @ModelAttribute("itemTypes")
    public Map<Character,String> itemTypes(){
        Map<Character,String> itemTypes = new HashMap<Character,String>();
        itemTypes.put('W',"WEEKLY");
        itemTypes.put('F',"FORTNIGHT");
        itemTypes.put('M',"MONTHLY");
        return itemTypes;
    }

    @ModelAttribute("itemReturnTypes")
    public Map<Character,String> itemReturnTypes(){
        Map<Character,String> itemReturnTypes = new HashMap<Character,String>();
        itemReturnTypes.put('Y',"YES");
        itemReturnTypes.put('N',"NO");
        return itemReturnTypes;
    }

    @Autowired
    public ItemsController(ItemMasterService itemMasterService) {
         this.itemMasterService = itemMasterService;
    }

    protected final Logger logger = Logger.getLogger(getClass());

    @RequestMapping(value = "/show/{id}",method = RequestMethod.GET)
    public ModelAndView show(@PathVariable("id") int itemCode) {
        Item item= itemMasterService.get(itemCode);
        return new ModelAndView("item/show", "item", item);
    }

    @RequestMapping(value = "/createForm",method = RequestMethod.GET)
    public ModelAndView createForm() {
        Item item = new Item();
        item.setReturnable('Y');
        item.setItemLife('W');
        return new ModelAndView("item/createForm", "item", item);
    }
    @RequestMapping(value = "/updateForm/{id}",method = RequestMethod.GET)
    public ModelAndView updateForm(@PathVariable("id") int itemCode) {
        Item item= itemMasterService.get(itemCode);
        return new ModelAndView("item/updateForm", "item", item);
    }

    @RequestMapping(value = "/create",method = RequestMethod.POST)
    public String create(@Valid Item item, BindingResult bindingResult, Model uiModel) {
        uiModel.asMap().clear();
        itemMasterService.create(item);
        return "redirect:/item/show/"+item.getItemCode();
    }

    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public String update(@Valid Item item, BindingResult bindingResult, Model uiModel) {
        uiModel.asMap().clear();
        itemMasterService.update(item);
        return "redirect:/item/show/"+item.getItemCode();
    }
}
