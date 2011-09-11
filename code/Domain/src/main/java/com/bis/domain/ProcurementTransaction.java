package com.bis.domain;

import java.util.Date;

public class ProcurementTransaction implements java.io.Serializable {

	private Integer transactionId;
	private int vendorId;
	private Date date;
	private Character transactionType;
	private Float totalAmount;

	public ProcurementTransaction() {
	}

	public ProcurementTransaction(int vendorId) {
		this.vendorId = vendorId;
	}

	public ProcurementTransaction(int vendorId, Date date,
			Character transactionType, Float totalAmount) {
		this.vendorId = vendorId;
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

	public int getVendorId() {
		return this.vendorId;
	}

	public void setVendorId(int vendorId) {
		this.vendorId = vendorId;
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

}
