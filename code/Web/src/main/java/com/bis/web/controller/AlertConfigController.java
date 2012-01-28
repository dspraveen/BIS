package com.bis.web.controller;

import com.bis.domain.AlertConfig;
import com.bis.domain.AlertType;
import com.bis.domain.Group;
import com.bis.reporting.services.AlertConfigurationService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

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

/*    @ModelAttribute("alertConfigs")
    public List<AlertConfig> alertConfigs() {
        return alertConfigurationService.getAll();
    }  */

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
        AlertConfig alertConfig = new AlertConfig();
        return new ModelAndView("alerts/newConfig", "alertConfig", alertConfig);
    }
}
