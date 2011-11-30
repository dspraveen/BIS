package com.bis.domain;

import java.util.Date;

public class BillingSales implements java.io.Serializable {

	private Integer billId;
	private Date startDate;
	private Date endDate;
	private Float salesAmount;
	private Float balanceAmount;
    private Hawker hawker = new Hawker();

    public BillingSales(Date startDate, Date endDate, Float balanceAmount, Hawker hawker, Float salesAmount) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.balanceAmount = balanceAmount;
        this.hawker = hawker;
        this.salesAmount = salesAmount;
    }

    public BillingSales() {
    }

	public Integer getBillId() {
		return this.billId;
	}

	public void setBillId(Integer billId) {
		this.billId = billId;
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

    public Hawker getHawker() {
        return hawker;
    }

    public void setHawker(Hawker hawker) {
        this.hawker = hawker;
    }

    public Float getSalesAmount() {
        return salesAmount;
    }

    public void setSalesAmount(Float salesAmount) {
        this.salesAmount = salesAmount;
    }
}
