package com.bis.domain;

public class GroupItem implements java.io.Serializable {

    private Integer groupItemId;
    private Item item = new Item();
    private Vendor vendor = new Vendor();
    private Hawker hawker = new Hawker();

    public Integer getGroupItemId() {
        return this.groupItemId;
    }

    public void setGroupItemId(Integer groupItemId) {
        this.groupItemId = groupItemId;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Vendor getVendor() {
        return vendor;
    }

    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
    }

    public Hawker getHawker() {
        return hawker;
    }

    public void setHawker(Hawker hawker) {
        this.hawker = hawker;
    }
}
