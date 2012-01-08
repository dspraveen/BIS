package com.bis.domain;

public class AlertAssociation implements java.io.Serializable {

	private Integer associationId;
	private AlertConfig alertConfig;
	private Integer itemId;
	private Integer hawkerId;
	private Integer vendorId;

    public Integer getAssociationId() {
		return this.associationId;
	}

	public void setAssociationId(Integer associationId) {
		this.associationId = associationId;
	}

	public AlertConfig getAlertConfig() {
		return this.alertConfig;
	}

	public void setAlertConfig(AlertConfig alertConfig) {
		this.alertConfig = alertConfig;
	}

	public Integer getItemId() {
		return this.itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	public Integer getHawkerId() {
		return this.hawkerId;
	}

	public void setHawkerId(Integer hawkerId) {
		this.hawkerId = hawkerId;
	}

	public Integer getVendorId() {
		return this.vendorId;
	}

	public void setVendorId(Integer vendorId) {
		this.vendorId = vendorId;
	}

}
