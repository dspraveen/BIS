package com.bis.web.controller;

import com.bis.core.services.HawkerMasterService;
import com.bis.domain.BillingCycle;
import com.bis.domain.Hawker;
import com.bis.web.viewmodel.HawkerList;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.*;

@Controller
@RequestMapping("/hawker")
public class HawkersController {

    protected final Logger logger = Logger.getLogger(getClass());

    private HawkerMasterService hawkerMasterService;

    protected HawkersController() {
    }

    @Autowired
    public HawkersController(HawkerMasterService hawkerMasterService) {
         this.hawkerMasterService = hawkerMasterService;
    }

    @RequestMapping(value = "/show/{id}",method = RequestMethod.GET)
    public ModelAndView show(@PathVariable("id") int hawkerId) {
        Hawker hawker= hawkerMasterService.get(hawkerId);
        return new ModelAndView("hawker/show", "hawker", hawker);
    }

    @RequestMapping(value = "/createForm",method = RequestMethod.GET)
    public ModelAndView createForm() {
        Hawker hawker = new Hawker();
        hawker.setBillingCycle('W');
        return new ModelAndView("hawker/createForm", "hawker", hawker);
    }
    @RequestMapping(value = "/updateForm/{id}",method = RequestMethod.GET)
    public ModelAndView updateForm(@PathVariable("id") int hawkerId) {
        Hawker hawker= hawkerMasterService.get(hawkerId);
        return new ModelAndView("hawker/updateForm", "hawker", hawker);
    }

    @Transactional
    @RequestMapping(value = "/create",method = RequestMethod.POST)
    public String create(@Valid Hawker hawker, BindingResult bindingResult, Model uiModel) {
        uiModel.asMap().clear();
        hawkerMasterService.create(hawker);
        return "redirect:/hawker/show/"+hawker.getHawkerId();
    }


    @Transactional
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public String update(@Valid Hawker hawker, BindingResult bindingResult, Model uiModel) {
        uiModel.asMap().clear();
        hawkerMasterService.update(hawker);
        return "redirect:/hawker/show/"+hawker.getHawkerId();
    }

    @RequestMapping(value = "/list")
    public ModelAndView list(@RequestParam( value = "selectedHawkerId",defaultValue = "0",required = false) int selectedHawkerId) {
        List<Hawker> all = hawkerMasterService.getAll();
        Collections.sort(all,new Comparator<Hawker>() {
            @Override
            public int compare(Hawker o1, Hawker o2) {
                return o1.getHawkerName().compareToIgnoreCase(o2.getHawkerName());
            }
        });
        return new ModelAndView("hawker/list","hawkerList",new HawkerList(selectedHawkerId,all));
    }

    @ModelAttribute("billingCycles")
    public Map<Character,String> billingCycles(){
        Map<Character,String> billingCycles = new HashMap<Character,String>();
        for(BillingCycle billingCycle: BillingCycle.values()){
            billingCycles.put(billingCycle.getCode(), billingCycle.toString());
        }
        return billingCycles;
    }
}
