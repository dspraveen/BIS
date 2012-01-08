package com.bis.domain;

public class AlertConfig implements java.io.Serializable {

	private Integer alertConfigId;
	private AlertType alertType;
	private String alertParameters;
	private String alertConfigName;
	private Character defaultConfig;

    public Integer getAlertConfigId() {
		return this.alertConfigId;
	}

	public void setAlertConfigId(Integer alertConfigId) {
		this.alertConfigId = alertConfigId;
	}

	public AlertType getAlertType() {
		return this.alertType;
	}

	public void setAlertType(AlertType alertType) {
		this.alertType = alertType;
	}

	public String getAlertParameters() {
		return this.alertParameters;
	}

	public void setAlertParameters(String alertParameters) {
		this.alertParameters = alertParameters;
	}

    public String getAlertConfigName() {
        return alertConfigName;
    }

    public void setAlertConfigName(String alertConfigName) {
        this.alertConfigName = alertConfigName;
    }

    public Character getDefaultConfig() {
		return this.defaultConfig;
	}

	public void setDefaultConfig(Character defaultConfig) {
		this.defaultConfig = defaultConfig;
	}

}
