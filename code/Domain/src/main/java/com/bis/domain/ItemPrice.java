package com.bis.domain;

import java.util.Date;

public class ItemPrice implements java.io.Serializable {

	private Integer priceId;
	private int itemCode;
	private float price;
	private Date startDate;
	private Date endTime;

	public ItemPrice() {
	}

	public ItemPrice(int itemCode, float price, Date startDate) {
		this.itemCode = itemCode;
		this.price = price;
		this.startDate = startDate;
	}

	public ItemPrice(int itemCode, float price, Date startDate, Date endTime) {
		this.itemCode = itemCode;
		this.price = price;
		this.startDate = startDate;
		this.endTime = endTime;
	}

	public Integer getPriceId() {
		return this.priceId;
	}

	public void setPriceId(Integer priceId) {
		this.priceId = priceId;
	}

	public int getItemCode() {
		return this.itemCode;
	}

	public void setItemCode(int itemCode) {
		this.itemCode = itemCode;
	}

	public float getPrice() {
		return this.price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndTime() {
		return this.endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

}
