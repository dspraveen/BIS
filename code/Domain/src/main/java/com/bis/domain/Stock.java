package com.bis.domain;

import java.util.Date;

public class Stock implements java.io.Serializable {

	private Integer stockId;
	private Item item;
	private Date dateOfPublishing;
	private Integer quantity;

	public Stock() {
	}

    public Stock(int itemCode, Date dateOfPublishing, Integer quantity) {
		this.item = new Item(itemCode);
		this.dateOfPublishing = dateOfPublishing;
		this.quantity = quantity;
	}

	public Integer getStockId() {
		return this.stockId;
	}

	public void setStockId(Integer stockId) {
		this.stockId = stockId;
	}

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
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

}
