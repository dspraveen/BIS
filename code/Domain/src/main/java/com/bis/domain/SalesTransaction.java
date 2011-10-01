package com.bis.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SalesTransaction implements java.io.Serializable {

	private Integer transactionId;
	private int hawkerId;
	private Date date;
	private Character transactionType;
	private Float totalAmount;
    private List<StDetails> transactionDetails = new ArrayList<StDetails>();

	public SalesTransaction() {
	}

	public SalesTransaction(int hawkerId) {
		this.hawkerId = hawkerId;
	}

	public SalesTransaction(int hawkerId, Date date, Character transactionType,
			Float totalAmount) {
		this.hawkerId = hawkerId;
		this.date = date;
		this.transactionType = transactionType;
		this.totalAmount = totalAmount;
	}

	public Integer getTransactionId() {
		return this.transactionId;
	}

	public void setTransactionId(Integer transactionId) {
		this.transactionId = transactionId;
	}

	public int getHawkerId() {
		return this.hawkerId;
	}

	public void setHawkerId(int hawkerId) {
		this.hawkerId = hawkerId;
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
}
