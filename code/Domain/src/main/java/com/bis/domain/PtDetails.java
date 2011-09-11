package com.bis.domain;

import java.util.Date;

public class PtDetails implements java.io.Serializable {

	private Integer detailsId;
	private int transactionId;
	private int itemCode;
	private Date dateOfPublishing;
	private Integer quantity;
	private Float amount;

	public PtDetails() {
	}

	public PtDetails(int transactionId, int itemCode, Date dateOfPublishing) {
		this.transactionId = transactionId;
		this.itemCode = itemCode;
		this.dateOfPublishing = dateOfPublishing;
	}

	public PtDetails(int transactionId, int itemCode, Date dateOfPublishing,
			Integer quantity, Float amount) {
		this.transactionId = transactionId;
		this.itemCode = itemCode;
		this.dateOfPublishing = dateOfPublishing;
		this.quantity = quantity;
		this.amount = amount;
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

	public int getItemCode() {
		return this.itemCode;
	}

	public void setItemCode(int itemCode) {
		this.itemCode = itemCode;
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

}
