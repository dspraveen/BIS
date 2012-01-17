package com.bis.web.controller;

import com.bis.core.services.HawkerMasterService;
import com.bis.core.services.VendorMasterService;
import com.bis.domain.Group;
import com.bis.domain.Hawker;
import com.bis.domain.Vendor;
import com.bis.reporting.services.GroupService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

    protected GroupController() {
    }

    @Autowired
    public GroupController(VendorMasterService vendorMasterService, HawkerMasterService hawkerMasterService, GroupService groupService) {
        this.groupService = groupService;
        this.vendorMasterService = vendorMasterService;
        this.hawkerMasterService = hawkerMasterService;
    }

    @RequestMapping(value = "/showGroups", method = RequestMethod.GET)
    public ModelAndView showGroups() {
        List<Group> groupList = groupService.getAll();
        return new ModelAndView("reporting/showGroups", "groupList", groupList);
    }

    @RequestMapping(value = "/createGroup", method = RequestMethod.GET)
    public ModelAndView createForm() {
        Group group = new Group();
        return new ModelAndView("reporting/createGroup", "group", group);
    }

    @Transactional
    @RequestMapping(value = "/createGroup", method = RequestMethod.POST)
    public String create(@Valid Group group, BindingResult bindingResult, Model uiModel) {
        uiModel.asMap().clear();
        groupService.save(group);
        return "redirect:/reporting/showGroups";
    }

    @RequestMapping(value = "/updateGroup/{id}", method = RequestMethod.GET)
    public ModelAndView updateForm(@PathVariable("id") int groupId) {
        Group group = groupService.getGroup(groupId);
        return new ModelAndView("reporting/updateGroup", "Group", group);
    }

    @Transactional
    @RequestMapping(value = "/updateGroup", method = RequestMethod.POST)
    public String update(@Valid Group group, BindingResult bindingResult, Model uiModel) {
        uiModel.asMap().clear();
        groupService.update(group);
        return "redirect:/reporting/showGroups";
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
