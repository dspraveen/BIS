package com.bis.domain;

import java.util.Date;

public class PaymentHistorySales implements java.io.Serializable {

	private Integer paymentId;
	private int hawkerId;
	private Date date;
	private Float amount;
	private String receiptNum;
	private Character mode;
	private String remarks;

	public PaymentHistorySales() {
	}

	public PaymentHistorySales(int hawkerId) {
		this.hawkerId = hawkerId;
	}

	public PaymentHistorySales(int hawkerId, Date date, Float amount,
			String receiptNum, Character mode, String remarks) {
		this.hawkerId = hawkerId;
		this.date = date;
		this.amount = amount;
		this.receiptNum = receiptNum;
		this.mode = mode;
		this.remarks = remarks;
	}

	public Integer getPaymentId() {
		return this.paymentId;
	}

	public void setPaymentId(Integer paymentId) {
		this.paymentId = paymentId;
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

	public Float getAmount() {
		return this.amount;
	}

	public void setAmount(Float amount) {
		this.amount = amount;
	}

	public String getReceiptNum() {
		return this.receiptNum;
	}

	public void setReceiptNum(String receiptNum) {
		this.receiptNum = receiptNum;
	}

	public Character getMode() {
		return this.mode;
	}

	public void setMode(Character mode) {
		this.mode = mode;
	}

	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

}
