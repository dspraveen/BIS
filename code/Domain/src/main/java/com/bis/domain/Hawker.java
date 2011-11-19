package com.bis.domain;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

public class Hawker implements java.io.Serializable {

    private Integer hawkerId;
    private String hawkerName;
    private String address;
    private String phoneNumber;
    private Float hawkerDiscount;
    private Character billingCycle;
    private String alternatePhone;

    public Hawker() {
    }

    public Hawker(String address) {
        this.address = address;
    }

    public Hawker(String hawkerName, String address, String phoneNumber,
                  Float hawkerDiscount, Character billingCycle, String alternatePhone) {
        this.hawkerName = hawkerName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.hawkerDiscount = hawkerDiscount;
        this.billingCycle = billingCycle;
        this.alternatePhone = alternatePhone;
    }

    public Integer getHawkerId() {
        return this.hawkerId;
    }

    public void setHawkerId(Integer hawkerId) {
        this.hawkerId = hawkerId;
    }

    public String getHawkerName() {
        return this.hawkerName;
    }

    public void setHawkerName(String hawkerName) {
        this.hawkerName = hawkerName;
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

    public Float getHawkerDiscount() {
        return this.hawkerDiscount;
    }

    public void setHawkerDiscount(Float hawkerDiscount) {
        this.hawkerDiscount = hawkerDiscount;
    }

    public Character getBillingCycle() {
        return this.billingCycle;
    }

    public String getBillingCycleDescription() {
        return BillingCycle.getNameByCode(this.billingCycle);
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
