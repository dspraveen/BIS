package com.bis.domain;


import java.util.Date;

public class Alert implements java.io.Serializable {

	private Integer alertNum;
	private AlertType alertType;
	private String alertText;
	private Character alertStatus;
	private Integer snoozeTime;
	private Date alertTime;

    public Integer getAlertNum() {
		return this.alertNum;
	}

	public void setAlertNum(Integer alertNum) {
		this.alertNum = alertNum;
	}

	public AlertType getAlertType() {
		return this.alertType;
	}

	public void setAlertType(AlertType alertType) {
		this.alertType = alertType;
	}

	public String getAlertText() {
		return this.alertText;
	}

	public void setAlertText(String alertText) {
		this.alertText = alertText;
	}

	public Character getAlertStatus() {
		return this.alertStatus;
	}

	public void setAlertStatus(Character alertStatus) {
		this.alertStatus = alertStatus;
	}

	public Integer getSnoozeTime() {
		return this.snoozeTime;
	}

	public void setSnoozeTime(Integer snoozeTime) {
		this.snoozeTime = snoozeTime;
	}

	public Date getAlertTime() {
		return this.alertTime;
	}

	public void setAlertTime(Date alertTime) {
		this.alertTime = alertTime;
	}

}
