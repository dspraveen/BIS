package com.bis.web.viewmodel;

import com.bis.domain.AlertConfig;

public class AlertConfigParams {
    private Integer numOfDays = 0;
    private AlertConfig alertConfig = new AlertConfig();

    public Integer getNumOfDays() {
        return numOfDays;
    }

    public void setNumOfDays(Integer numOfDays) {
        this.numOfDays = numOfDays;
    }

    public AlertConfig getAlertConfig() {
        return alertConfig;
    }

    public void setAlertConfig(AlertConfig alertConfig) {
        this.alertConfig = alertConfig;
    }
}
