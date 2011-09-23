package com.bis.testcommon;

import com.bis.domain.Vendor;

public class VendorBuilder {
    private Vendor vendor = new Vendor();

    public Vendor build(){
        return this.vendor;
    }

    public VendorBuilder withDefaults(){
        vendor.setVendorName("Times");
        vendor.setVendorDiscount(10.0F);
        vendor.setBillingCycle('W');
        vendor.setAddress("Address");
        vendor.setPhoneNumber("phone1");
        vendor.setAlternatePhone("altPhone");
        return this;
    }

    public VendorBuilder setVendorId(int id) {
        vendor.setVendorId(id);
        return this;
    }

    public VendorBuilder setVendorName(String name) {
        vendor.setVendorName(name);
        return this;
    }
}
