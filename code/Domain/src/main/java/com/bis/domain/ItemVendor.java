package com.bis.domain;

public class ItemVendor implements java.io.Serializable {

	private Integer itemVendorId;
	private int itemCode;
	private int vendorId;

	public ItemVendor() {
	}

	public ItemVendor(int itemCode, int vendorId) {
		this.itemCode = itemCode;
		this.vendorId = vendorId;
	}

	public Integer getItemVendorId() {
		return this.itemVendorId;
	}

	public void setItemVendorId(Integer itemVendorId) {
		this.itemVendorId = itemVendorId;
	}

	public int getItemCode() {
		return this.itemCode;
	}

	public void setItemCode(int itemCode) {
		this.itemCode = itemCode;
	}

	public int getVendorId() {
		return this.vendorId;
	}

	public void setVendorId(int vendorId) {
		this.vendorId = vendorId;
	}

}
