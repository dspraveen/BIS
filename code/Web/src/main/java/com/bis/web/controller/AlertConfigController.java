package com.bis.web.controller;

import com.bis.domain.*;
import com.bis.reporting.services.AlertConfigurationService;
import com.bis.reporting.services.GroupService;
import com.bis.web.viewmodel.AlertConfigParams;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/alerts")
public class AlertConfigController {

    protected final Logger logger = Logger.getLogger(getClass());

    private AlertConfigurationService alertConfigurationService;
    private GroupService groupService;
    private AlertConfigParams alertConfigParams = new AlertConfigParams();

    protected AlertConfigController() {
    }

    @Autowired
    public AlertConfigController(AlertConfigurationService alertConfigurationService, GroupService groupService) {
        this.alertConfigurationService = alertConfigurationService;
        this.groupService = groupService;
    }

    @RequestMapping(value = "/showAlertsConfig", method = RequestMethod.GET)
    public ModelAndView showAlertsConfigs() {
        return new ModelAndView("alerts/showAlertsConfig");
    }

    @ModelAttribute("alertTypes")
    public List<AlertType> alertTypes() {
        return alertConfigurationService.getAlertTypes();
    }

    @RequestMapping(value = "/addGroup/{id}", method = RequestMethod.GET)
    public ModelAndView addGroup(@PathVariable("id") int groupId) {
        Group group = groupService.getGroup(groupId);
        AlertAssociation alertAssociation = new AlertAssociation();
        alertAssociation.setGroup(group);
        alertConfigParams.getAlertConfig().getAlertAssociations().add(alertAssociation);
        AlertConfigParams alertConfigParams1 = alertConfigParams;
        return new ModelAndView("alerts/showGroupList", "alertConfigParams", alertConfigParams1);
    }

    @RequestMapping(value = "/removeGroup/{id}", method = RequestMethod.GET)
    public ModelAndView removeGroup(@PathVariable("id") int groupId) {
        List<AlertAssociation> alertAssociations = alertConfigParams.getAlertConfig().getAlertAssociations();
        for (AlertAssociation alertAssociation : alertAssociations) {
            if (alertAssociation.getGroup().getGroupId() == groupId) {
                alertAssociations.remove(alertAssociations.indexOf(alertAssociation));
                break;
            }
        }
        AlertConfigParams alertConfigParams1 = alertConfigParams;
        return new ModelAndView("alerts/showGroupList", "alertConfigParams", alertConfigParams1);
    }

    @RequestMapping(value = "/showAllConfigs", method = RequestMethod.GET)
    public ModelAndView showAllConfigs(@RequestParam(value = "alertTypeId", required = false, defaultValue = "-1") int alertTypeId) {
        List<AlertConfig> alertConfigList = alertConfigurationService.getAlertConfigs(alertTypeId);
        return new ModelAndView("alerts/showAllConfigs", "alertConfigList", alertConfigList);
    }

    @RequestMapping(value = "/showConfig", method = RequestMethod.GET)
    public ModelAndView showConfig(@RequestParam(value = "alertTypeId", required = false, defaultValue = "-1") int alertTypeId) {
        List<AlertConfig> alertConfigList = alertConfigurationService.getAlertConfigs(alertTypeId);
        return new ModelAndView("alerts/showConfigs", "alertConfigList", alertConfigList);
    }

    @RequestMapping(value = "/newConfig", method = RequestMethod.GET)
    public ModelAndView newConfig(@RequestParam(value = "alertTypeId", required = false, defaultValue = "-1") int alertTypeId) {
        this.alertConfigParams.getAlertConfig().getAlertAssociations().clear();
        this.alertConfigParams.getAlertConfig().setAlertConfigId(null);
        this.alertConfigParams.getAlertConfig().setAlertConfigName(null);
        this.alertConfigParams.getAlertConfig().setAlertParameters(null);
        AlertConfigParams alertConfigParams1 = new AlertConfigParams();
        alertConfigParams.getAlertConfig().setAlertType(alertConfigurationService.getAlertType(alertTypeId));
        if (alertTypeId == 1) {
            return new ModelAndView("alerts/newConfig_SUA", "alertConfigParams", alertConfigParams1);
        }
        if (alertTypeId == 2) {
            return new ModelAndView("alerts/newConfig_SUS", "alertConfigParams", alertConfigParams1);
        }
        return null;
    }

    @Transactional
    @RequestMapping(value = "/createConfig", method = RequestMethod.POST)
    public String create(@Valid AlertConfigParams alertConfigParams, BindingResult bindingResult, Model uiModel) {
        uiModel.asMap().clear();
        if (this.alertConfigParams.getAlertConfig().getAlertType().getAlertTypeId() == 1) {
            this.alertConfigParams.getAlertConfig().setAlertConfigName(alertConfigParams.getAlertConfig().getAlertConfigName());
            this.alertConfigParams.getAlertConfig().setAlertParameters("Days:" + alertConfigParams.getNumOfDays_SUA().toString());
            alertConfigurationService.save(this.alertConfigParams.getAlertConfig());
        }
        if (this.alertConfigParams.getAlertConfig().getAlertType().getAlertTypeId() == 2) {
            this.alertConfigParams.getAlertConfig().setAlertConfigName(alertConfigParams.getAlertConfig().getAlertConfigName());
            String params = "Days:" + alertConfigParams.getNumOfDays_SUS().toString() + " " + "Percentage:" + alertConfigParams.getPercentStock_SUS().toString();
            this.alertConfigParams.getAlertConfig().setAlertParameters(params);
            alertConfigurationService.save(this.alertConfigParams.getAlertConfig());
        }
        return "redirect:/alerts/showConfig?alertTypeId=" + this.alertConfigParams.getAlertConfig().getAlertType().getAlertTypeId();
    }

    @RequestMapping(value = "/updateForm/{id}", method = RequestMethod.GET)
    public ModelAndView updateForm(@PathVariable("id") int alertConfigId) {
        AlertConfigParams alertConfigParams1 = new AlertConfigParams();
        alertConfigParams1.setAlertConfig(alertConfigurationService.get(alertConfigId));
        if (alertConfigParams1.getAlertConfig().getAlertType().getAlertTypeId() == 1) {
            String[] params = alertConfigParams1.getAlertConfig().getAlertParameters().split("[ :]");
            alertConfigParams1.setNumOfDays_SUA(Integer.parseInt(params[1]));
            this.alertConfigParams = alertConfigParams1;
            return new ModelAndView("alerts/updateConfig_SUA", "alertConfigParams", alertConfigParams1);
        }
        if (alertConfigParams1.getAlertConfig().getAlertType().getAlertTypeId() == 2) {
            String[] params = alertConfigParams1.getAlertConfig().getAlertParameters().split("[ :]");
            alertConfigParams1.setNumOfDays_SUS(Integer.parseInt(params[1]));
            alertConfigParams1.setPercentStock_SUS(Integer.parseInt(params[3]));
            this.alertConfigParams = alertConfigParams1;
            return new ModelAndView("alerts/updateConfig_SUS", "alertConfigParams", alertConfigParams1);
        }
        return null;
    }

    @Transactional
    @RequestMapping(value = "/updateConfig", method = RequestMethod.POST)
    public String update(@Valid AlertConfigParams alertConfigParams, BindingResult bindingResult, Model uiModel) {
        uiModel.asMap().clear();
        if (this.alertConfigParams.getAlertConfig().getAlertType().getAlertTypeId() == 1) {
            this.alertConfigParams.getAlertConfig().setAlertParameters("Days:" + alertConfigParams.getNumOfDays_SUA().toString());
            alertConfigurationService.update(this.alertConfigParams.getAlertConfig());
        }
        if (this.alertConfigParams.getAlertConfig().getAlertType().getAlertTypeId() == 2) {
            String params = "Days:" + alertConfigParams.getNumOfDays_SUS().toString() + " " + "Percentage:" + alertConfigParams.getPercentStock_SUS().toString();
            this.alertConfigParams.getAlertConfig().setAlertParameters(params);
            alertConfigurationService.update(this.alertConfigParams.getAlertConfig());
        }
        return "redirect:/alerts/showConfig?alertTypeId=" + this.alertConfigParams.getAlertConfig().getAlertType().getAlertTypeId();
    }

    @ModelAttribute("groups")
    public List<Group> hawkers() {
        return groupService.getAll();
    }
}
