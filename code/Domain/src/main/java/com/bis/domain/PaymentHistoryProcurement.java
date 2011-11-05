package com.bis.domain;

import java.util.Date;

public class PaymentHistoryProcurement implements java.io.Serializable {

	private Integer paymentId;
	private Date date;
	private Float amount;
	private String receiptNum;
	private Character mode;
	private String remarks;
    private Vendor vendor = new Vendor();

	public PaymentHistoryProcurement() {
	}

	public Integer getPaymentId() {
		return this.paymentId;
	}

	public void setPaymentId(Integer paymentId) {
		this.paymentId = paymentId;
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

    public Vendor getVendor() {
        return vendor;
    }

    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
    }
}
