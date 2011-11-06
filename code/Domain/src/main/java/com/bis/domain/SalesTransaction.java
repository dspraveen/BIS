package com.bis.domain;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SalesTransaction implements java.io.Serializable {

    private Integer transactionId;
    private Hawker hawker = new Hawker();
    private Date date;
    private Character transactionType;
    private Float totalAmount;
    private List<StDetails> transactionDetails = new ArrayList<StDetails>();

    public SalesTransaction() {
    }

    public Integer getTransactionId() {
        return this.transactionId;
    }

    public void setTransactionId(Integer transactionId) {
        this.transactionId = transactionId;
    }

    public Hawker getHawker() {
        return hawker;
    }

    public void setHawker(Hawker hawker) {
        this.hawker = hawker;
    }

    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Character getTransactionType() {
        return this.transactionType;
    }

    public void setTransactionType(Character transactionType) {
        this.transactionType = transactionType;
    }

    public Float getTotalAmount() {
        return this.totalAmount;
    }

    public void setTotalAmount(Float totalAmount) {
        this.totalAmount = totalAmount;
    }

    public List<StDetails> getTransactionDetails() {
        return transactionDetails;
    }

    public void setTransactionDetails(List<StDetails> transactionDetails) {
        this.transactionDetails = transactionDetails;
    }

    public void calculateAndSetTotalAmount() {
        List<StDetails> transactionDetails = this.getTransactionDetails();
        float totalAmount = 0;
        for (StDetails details : transactionDetails) {
            totalAmount += details.getAmount();
        }
        this.setTotalAmount(totalAmount);
    }

    public StDetails getStDetails(final Integer transactionDetailId) {
        if (transactionDetailId == null) return null;
        return (StDetails) CollectionUtils.find(transactionDetails, new Predicate() {
            @Override
            public boolean evaluate(Object object) {
                StDetails details = (StDetails) object;
                if (details.getDetailsId() == null) return false;
                return details.getDetailsId().intValue() == transactionDetailId.intValue();
            }
        });
    }
}
