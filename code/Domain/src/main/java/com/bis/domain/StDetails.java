package com.bis.domain;


import com.bis.common.MathUtils;

import java.util.Date;

public class StDetails implements java.io.Serializable {

    private Integer detailsId;
    private int transactionId;
    private Date dateOfPublishing;
    private Integer quantity;
    private Float mrp;
    private Float netPrice;
    private Item item = new Item();

    public StDetails() {
    }

    public Integer getDetailsId() {
        return this.detailsId;
    }

    public void setDetailsId(Integer detailsId) {
        this.detailsId = detailsId;
    }

    public int getTransactionId() {
        return this.transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public Date getDateOfPublishing() {
        return this.dateOfPublishing;
    }

    public void setDateOfPublishing(Date dateOfPublishing) {
        this.dateOfPublishing = dateOfPublishing;
    }

    public Integer getQuantity() {
        return this.quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Float getAmount() {
        if (quantity == null || netPrice == null) return null;
        return (float) MathUtils.roundTwoDecimals(quantity * netPrice);
    }

    public Float getMrp() {
        return mrp;
    }

    public void setMrp(Float mrp) {
        this.mrp = mrp;
    }

    public Float getNetPrice() {
        return netPrice;
    }

    public void setNetPrice(Float netPrice) {
        this.netPrice = netPrice;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }
}
