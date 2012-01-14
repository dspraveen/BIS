package com.bis.domain;

import java.util.ArrayList;
import java.util.List;

public class Group implements java.io.Serializable {

	private Integer groupId;
	private String groupName;
	private String groupText;
    List<GroupItem> groupItems = new ArrayList<GroupItem>();

	public Integer getGroupId() {
		return this.groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

	public String getGroupName() {
		return this.groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getGroupText() {
		return this.groupText;
	}

	public void setGroupText(String groupText) {
		this.groupText = groupText;
	}

    public List<GroupItem> getGroupItems() {
        return groupItems;
    }

    public void setGroupItems(List<GroupItem> groupItems) {
        this.groupItems = groupItems;
    }
}
