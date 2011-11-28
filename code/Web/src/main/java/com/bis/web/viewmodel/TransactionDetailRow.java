package com.bis.web.viewmodel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TransactionDetailRow {
    private Float mrp;
    private Float discount;
    private Float pricePerItem;
    private Integer detailsId;
    private int transactionId;
    private Integer itemCode;
    private Date dateOfPublishing;
    private Integer quantity;
    private Float amount;
    private boolean checked;
    List<ListElement> issueDates = new ArrayList<ListElement>();

    public TransactionDetailRow() {
    }

    public Float getMrp() {
        return mrp;
    }

    public void setMrp(Float mrp) {
        this.mrp = mrp;
    }

    public Float getDiscount() {
        return discount;
    }

    public void setDiscount(Float discount) {
        this.discount = discount;
    }

    public Float getPricePerItem() {
        return pricePerItem;
    }

    public void setPricePerItem(Float pricePerItem) {
        this.pricePerItem = pricePerItem;
    }

    public Integer getDetailsId() {
        return detailsId;
    }

    public void setDetailsId(Integer detailsId) {
        this.detailsId = detailsId;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public Integer getItemCode() {
        return itemCode;
    }

    public void setItemCode(Integer itemCode) {
        this.itemCode = itemCode;
    }

    public Date getDateOfPublishing() {
        return dateOfPublishing;
    }

    public void setDateOfPublishing(Date dateOfPublishing) {
        this.dateOfPublishing = dateOfPublishing;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public void updateVendorDiscount(Float vendorDiscount) {
        discount = vendorDiscount;
        if(itemCode != null && itemCode > 0){
            pricePerItem = (100 - discount) * mrp / 100;
            if (quantity != null) {
            amount = pricePerItem * quantity;
            }
        }
    }

    public void updateItemPrice(float itemPrice) {
        this.mrp = itemPrice;
        if (discount != null) {
            pricePerItem = (100 - discount) * mrp / 100;
        }
        if (pricePerItem != null && quantity != null) {
            amount = pricePerItem * quantity;
        }
    }

    public void updateDiscount() {
        pricePerItem = (100 - discount) * mrp / 100;
        if (quantity != null) {
            amount = pricePerItem * quantity;
        }
    }

    public void updatePricePerItem() {
        this.discount = (mrp - pricePerItem) / mrp * 100;
        if (quantity != null) {
            amount = pricePerItem * quantity;
        }
    }

    public void updateQuantity() {
        if (pricePerItem != null) {
            amount = pricePerItem * quantity;
        }
    }

    public List<ListElement> getIssueDates() {
        return issueDates;
    }

    public void setIssueDates(List<ListElement> issueDates) {
        this.issueDates = issueDates;
    }
}
