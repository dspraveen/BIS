package com.bis.web.controller;

import com.bis.domain.AlertConfig;
import com.bis.domain.AlertType;
import com.bis.domain.Group;
import com.bis.reporting.services.AlertConfigurationService;
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
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/alerts")
public class AlertConfigController {

    protected final Logger logger = Logger.getLogger(getClass());

    private AlertConfigurationService alertConfigurationService;

    protected AlertConfigController() {

    }

    @Autowired
    public AlertConfigController(AlertConfigurationService alertConfigurationService) {
        this.alertConfigurationService = alertConfigurationService;
    }

    @RequestMapping(value = "/showAlertsConfig", method = RequestMethod.GET)
    public ModelAndView showAlertsConfigs() {
        return new ModelAndView("alerts/showAlertsConfig");
    }

    @ModelAttribute("alertTypes")
    public List<AlertType> alertTypes() {
        return alertConfigurationService.getAlertTypes();
    }

    @RequestMapping(value = "/showAllConfigs", method = RequestMethod.GET)
    public ModelAndView showAllConfigs(@RequestParam(value = "alertTypeId", required = false, defaultValue = "-1") int alertTypeId) {
        List<AlertConfig> alertConfigList = alertConfigurationService.getAlertConfigs(alertTypeId);
        return new ModelAndView("alerts/showAllConfigs", "alertConfigList", alertConfigList);
    }

    @RequestMapping(value = "/newConfig", method = RequestMethod.GET)
    public ModelAndView newConfig(@RequestParam(value = "alertTypeId", required = false, defaultValue = "-1") int alertTypeId) {
        AlertConfigParams alertConfigParams = new AlertConfigParams();
        alertConfigParams.getAlertConfig().setAlertType(alertConfigurationService.getAlertType(alertTypeId));
        return new ModelAndView("alerts/newConfig", "alertConfigParams", alertConfigParams);
    }

    @Transactional
    @RequestMapping(value = "/createConfig", method = RequestMethod.POST)
    public String create(@Valid AlertConfigParams alertConfigParams, BindingResult bindingResult, Model uiModel) {
        uiModel.asMap().clear();
        return "redirect:/alerts/showAllConfigs";
    }

    @RequestMapping(value = "/updateForm/{id}", method = RequestMethod.GET)
    public ModelAndView updateForm(@PathVariable("id") int alertConfigId) {
        AlertConfigParams alertConfigParams = new AlertConfigParams();
        alertConfigParams.setAlertConfig(alertConfigurationService.get(alertConfigId));
        //trim the Parameters value according to the alertType - String parsing.
        return new ModelAndView("alerts/updateForm", "alertConfigParams", alertConfigParams);
    }

    @Transactional
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(@Valid AlertConfigParams alertConfigParams, BindingResult bindingResult, Model uiModel) {
        uiModel.asMap().clear();
        return "redirect:/alerts/showAllConfigs";
    }
}
