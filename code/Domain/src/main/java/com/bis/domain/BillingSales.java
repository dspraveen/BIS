package com.bis.domain;

import java.util.Date;

public class BillingSales implements java.io.Serializable {

	private Integer billId;
	private int hawkerId;
	private Date startDate;
	private Date endDate;
	private Float balanceAmount;

	public BillingSales() {
	}

	public BillingSales(int hawkerId) {
		this.hawkerId = hawkerId;
	}

	public BillingSales(int hawkerId, Date startDate, Date endDate,
			Float balanceAmount) {
		this.hawkerId = hawkerId;
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

	public int getHawkerId() {
		return this.hawkerId;
	}

	public void setHawkerId(int hawkerId) {
		this.hawkerId = hawkerId;
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
