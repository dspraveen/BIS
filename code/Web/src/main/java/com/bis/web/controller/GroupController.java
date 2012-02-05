package com.bis.web.controller;

import com.bis.core.services.HawkerMasterService;
import com.bis.core.services.ItemMasterService;
import com.bis.core.services.VendorMasterService;
import com.bis.domain.*;
import com.bis.reporting.services.GroupService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/group")
public class GroupController {

    protected final Logger logger = Logger.getLogger(getClass());

    private GroupService groupService;
    private VendorMasterService vendorMasterService;
    private HawkerMasterService hawkerMasterService;
    private ItemMasterService itemMasterService;
    private Group groupDetails = new Group();

    protected GroupController() {
    }

    @Autowired
    public GroupController(VendorMasterService vendorMasterService, HawkerMasterService hawkerMasterService, GroupService groupService, ItemMasterService itemMasterService) {
        this.groupService = groupService;
        this.vendorMasterService = vendorMasterService;
        this.hawkerMasterService = hawkerMasterService;
        this.itemMasterService = itemMasterService;
    }

    @RequestMapping(value = "/showGroups", method = RequestMethod.GET)
    public ModelAndView showGroups() {
        List<Group> groupList = groupService.getAll();
        return new ModelAndView("group/showGroups", "groupList", groupList);
    }

    @RequestMapping(value = "/createGroup", method = RequestMethod.GET)
    public ModelAndView createForm() {
        Group group = new Group();
        this.groupDetails.getGroupItems().clear();
        this.groupDetails.setGroupId(null);
        this.groupDetails.setGroupName(null);
        this.groupDetails.setGroupText(null);
        return new ModelAndView("group/createGroup", "group", group);
    }

    @Transactional
    @RequestMapping(value = "/createNewGroup", method = RequestMethod.POST)
    public String create(@Valid Group group, BindingResult bindingResult, Model uiModel) {
        uiModel.asMap().clear();
        this.groupDetails.setGroupName(group.getGroupName());
        this.groupDetails.setGroupText(group.getGroupText());
        groupService.save(groupDetails);
        return "redirect:/group/showGroups";
    }

    @RequestMapping(value = "/updateGroup/{id}", method = RequestMethod.GET)
    public ModelAndView updateForm(@PathVariable("id") int groupId) {
        Group group = groupService.getGroup(groupId);
        return new ModelAndView("group/updateGroup", "group", group);
    }

    @Transactional
    @RequestMapping(value = "/updateGroup", method = RequestMethod.POST)
    public String update(@Valid Group group, BindingResult bindingResult, Model uiModel) {
        uiModel.asMap().clear();
        groupService.update(group);
        return "redirect:/group/showGroups";
    }

    @RequestMapping(value = "/addNewItem", method = RequestMethod.GET)
    public ModelAndView addNewItem(@RequestParam(value = "itemCode", required = true, defaultValue = "-1") int itemCode) {
        Item item = itemMasterService.get(itemCode);
        groupService.addItemToGroup(groupDetails, item);
        Group group = new Group();
        group = groupDetails;
        return new ModelAndView("group/showGroup", "group", group);
    }

    @RequestMapping(value = "/removeItem/{id}", method = RequestMethod.GET)
    public ModelAndView removeItem(@PathVariable("id") int itemCode) {
        Item item = itemMasterService.get(itemCode);
        groupService.removeItemFromGroup(groupDetails, item);
        Group group = new Group();
        group = groupDetails;
        return new ModelAndView("group/showGroup", "group", group);
    }

    @RequestMapping(value = "/addNewVendor", method = RequestMethod.GET)
    public ModelAndView addNewVendor(@RequestParam(value = "vendorId", required = true, defaultValue = "-1") int vendorId) {
        Vendor vendor = vendorMasterService.get(vendorId);
        groupService.addVendorToGroup(groupDetails, vendor);
        Group group = new Group();
        group = groupDetails;
        return new ModelAndView("group/showGroup", "group", group);
    }

    @RequestMapping(value = "/removeVendor/{id}", method = RequestMethod.GET)
    public ModelAndView removeVendor(@PathVariable("id") int vendorId) {
        Vendor vendor = vendorMasterService.get(vendorId);
        groupService.removeVendorFromGroup(groupDetails, vendor);
        Group group = new Group();
        group = groupDetails;
        return new ModelAndView("group/showGroup", "group", group);
    }

    @RequestMapping(value = "/addNewHawker", method = RequestMethod.GET)
    public ModelAndView addNewHawker(@RequestParam(value = "hawkerId", required = true, defaultValue = "-1") int hawkerId) {
        Hawker hawker = hawkerMasterService.get(hawkerId);
        groupService.addHawkerToGroup(groupDetails, hawker);
        Group group = new Group();
        group = groupDetails;
        return new ModelAndView("group/showGroup", "group", group);
    }

    @RequestMapping(value = "/removeHawker/{id}", method = RequestMethod.GET)
    public ModelAndView removeHawker(@PathVariable("id") int hawkerId) {
        Hawker hawker = hawkerMasterService.get(hawkerId);
        groupService.removeHawkerFromGroup(groupDetails, hawker);
        Group group = new Group();
        group = groupDetails;
        return new ModelAndView("group/showGroup", "group", group);
    }

    @ModelAttribute("items")
    public List<Item> items() {
        return itemMasterService.getAll();
    }

    @ModelAttribute("vendors")
    public List<Vendor> vendors() {
        return vendorMasterService.getAll();
    }

    @ModelAttribute("hawkers")
    public List<Hawker> hawkers() {
        return hawkerMasterService.getAll();
    }
}
