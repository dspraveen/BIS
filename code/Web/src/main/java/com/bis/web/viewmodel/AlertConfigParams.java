package com.bis.web.viewmodel;

import com.bis.domain.AlertConfig;

public class AlertConfigParams {
    private Integer numOfDays_SUA = 0;
    private Integer numOfDays_SUS = 0;
    private Integer percentStock_SUS = 0;
    private AlertConfig alertConfig = new AlertConfig();

    public Integer getNumOfDays_SUA() {
        return numOfDays_SUA;
    }

    public void setNumOfDays_SUA(Integer numOfDays_SUA) {
        this.numOfDays_SUA = numOfDays_SUA;
    }

    public AlertConfig getAlertConfig() {
        return alertConfig;
    }

    public void setAlertConfig(AlertConfig alertConfig) {
        this.alertConfig = alertConfig;
    }

    public Integer getNumOfDays_SUS() {
        return numOfDays_SUS;
    }

    public void setNumOfDays_SUS(Integer numOfDays_SUS) {
        this.numOfDays_SUS = numOfDays_SUS;
    }

    public Integer getPercentStock_SUS() {
        return percentStock_SUS;
    }

    public void setPercentStock_SUS(Integer percentStock_SUS) {
        this.percentStock_SUS = percentStock_SUS;
    }
}
