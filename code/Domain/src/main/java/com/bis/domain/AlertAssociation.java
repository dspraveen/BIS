package com.bis.domain;

public class AlertAssociation implements java.io.Serializable {

	private Integer alertAssociationId;
	private Group group;
	private AlertConfig alertConfig;

	public Integer getAlertAssociationId() {
		return this.alertAssociationId;
	}

	public void setAlertAssociationId(Integer alertAssociationId) {
		this.alertAssociationId = alertAssociationId;
	}

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public AlertConfig getAlertConfig() {
        return alertConfig;
    }

    public void setAlertConfig(AlertConfig alertConfig) {
        this.alertConfig = alertConfig;
    }
}
