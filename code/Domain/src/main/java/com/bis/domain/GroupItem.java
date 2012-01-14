package com.bis.domain;

public class GroupItem implements java.io.Serializable {

    private Integer groupItemId;
    private Integer itemId;
    private Integer vendorId;
    private Integer hawkerId;

    public Integer getGroupItemId() {
        return this.groupItemId;
    }

    public void setGroupItemId(Integer groupItemId) {
        this.groupItemId = groupItemId;
    }

    public Integer getItemId() {
        return this.itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public Integer getVendorId() {
        return this.vendorId;
    }

    public void setVendorId(Integer vendorId) {
        this.vendorId = vendorId;
    }

    public Integer getHawkerId() {
        return this.hawkerId;
    }

    public void setHawkerId(Integer hawkerId) {
        this.hawkerId = hawkerId;
    }

}
