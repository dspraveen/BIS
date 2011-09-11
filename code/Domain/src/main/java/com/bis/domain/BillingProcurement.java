package com.bis.domain;
import java.util.Date;

public class BillingProcurement implements java.io.Serializable {

	private Integer billId;
	private int vendorId;
	private Date startDate;
	private Date endDate;
	private Float balanceAmount;

	public BillingProcurement() {
	}

	public BillingProcurement(int vendorId) {
		this.vendorId = vendorId;
	}

	public BillingProcurement(int vendorId, Date startDate, Date endDate,
			Float balanceAmount) {
		this.vendorId = vendorId;
		this.startDate = startDate;
		this.endDate = endDate;
		this.balanceAmount = balanceAmount;
	}

	public Integer getBillId() {
		return this.billId;
	}

	public void setBillId(Integer billId) {
		this.billId = billId;
	}

	public int getVendorId() {
		return this.vendorId;
	}

	public void setVendorId(int vendorId) {
		this.vendorId = vendorId;
	}

	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Float getBalanceAmount() {
		return this.balanceAmount;
	}

	public void setBalanceAmount(Float balanceAmount) {
		this.balanceAmount = balanceAmount;
	}

}
