package com.bis.domain;
import java.util.Date;

public class BillingProcurement implements java.io.Serializable {

	private Integer billId;
	private Date startDate;
	private Date endDate;
	private Float procurementAmount;
	private Float balanceAmount;
    private Vendor vendor = new Vendor();

    public BillingProcurement() {
    }

    public BillingProcurement(Date startDate, Date endDate, Float balanceAmount, Vendor vendor, Float procurementAmount) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.balanceAmount = balanceAmount;
        this.vendor = vendor;
        this.procurementAmount = procurementAmount;
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

    public Vendor getVendor() {
        return vendor;
    }

    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
    }

    public Float getProcurementAmount() {
        return procurementAmount;
    }

    public void setProcurementAmount(Float procurementAmount) {
        this.procurementAmount = procurementAmount;
    }
}
