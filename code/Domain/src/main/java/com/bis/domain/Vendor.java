package com.bis.domain;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

public class Vendor implements java.io.Serializable {

	private Integer vendorId;
	private String vendorName;
	private String address;
	private String phoneNumber;
	private Float vendorDiscount;
	private Character billingCycle;
	private String alternatePhone;

	public Vendor() {
	}

	public Vendor(String vendorName) {
		this.vendorName = vendorName;
	}

	public Vendor(String vendorName, String address, String phoneNumber,
			Float vendorDiscount, Character billingCycle, String alternatePhone) {
		this.vendorName = vendorName;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.vendorDiscount = vendorDiscount;
		this.billingCycle = billingCycle;
		this.alternatePhone = alternatePhone;
	}

	public Integer getVendorId() {
		return this.vendorId;
	}

	public void setVendorId(Integer vendorId) {
		this.vendorId = vendorId;
	}

	public String getVendorName() {
		return this.vendorName;
	}

	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Float getVendorDiscount() {
		return this.vendorDiscount;
	}

	public void setVendorDiscount(Float vendorDiscount) {
		this.vendorDiscount = vendorDiscount;
	}

	public Character getBillingCycle() {
		return this.billingCycle;
	}

	public void setBillingCycle(Character billingCycle) {
		this.billingCycle = billingCycle;
	}

	public String getAlternatePhone() {
		return this.alternatePhone;
	}

	public void setAlternatePhone(String alternatePhone) {
		this.alternatePhone = alternatePhone;
	}

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj);
    }

}
