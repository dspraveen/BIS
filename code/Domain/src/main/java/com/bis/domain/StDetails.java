package com.bis.domain;


import java.util.Date;

public class StDetails implements java.io.Serializable {

    private Integer detailsId;
    private int transactionId;
    private Date dateOfPublishing;
    private Integer quantity;
    private Float amount;
    private Item item;

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
        return this.amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Float getPricePerItem() {
        if (quantity == null || amount == null) return null;
        return quantity != 0 ? amount / quantity : 0;
    }
}
