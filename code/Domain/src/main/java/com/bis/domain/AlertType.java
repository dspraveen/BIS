package com.bis.domain;

import java.util.Date;

public class AlertType implements java.io.Serializable {

	private Integer alertTypeId;
	private String alertName;
	private String alertMessage;
    private Date lastRunTime;

	public AlertType() {
	}

	public AlertType(String alertName, String alertMessage) {
		this.alertName = alertName;
		this.alertMessage = alertMessage;
	}

	public Integer getAlertTypeId() {
		return this.alertTypeId;
	}

	public void setAlertTypeId(Integer alertTypeId) {
		this.alertTypeId = alertTypeId;
	}

	public String getAlertName() {
		return this.alertName;
	}

	public void setAlertName(String alertName) {
		this.alertName = alertName;
	}

	public String getAlertMessage() {
		return this.alertMessage;
	}

	public void setAlertMessage(String alertMessage) {
		this.alertMessage = alertMessage;
	}

    public Date getLastRunTime() {
        return lastRunTime;
    }

    public void setLastRunTime(Date lastRunTime) {
        this.lastRunTime = lastRunTime;
    }
}
