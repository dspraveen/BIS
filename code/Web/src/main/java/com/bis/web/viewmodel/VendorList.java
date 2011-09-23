package com.bis.web.viewmodel;

import com.bis.domain.Vendor;

import java.util.List;

public class VendorList {
    private int selectedVendorId;
    private List<Vendor> vendors;

    public VendorList(int selectedVendorId, List<Vendor> vendors) {
        this.selectedVendorId = selectedVendorId;
        this.vendors = vendors;
    }

    public int getSelectedVendorId() {
        return selectedVendorId;
    }

    public void setSelectedVendorId(int selectedVendorId) {
        this.selectedVendorId = selectedVendorId;
    }

    public List<Vendor> getVendors() {
        return vendors;
    }

    public Vendor getSelectedVendor() {
        for (Vendor vendor : vendors) {
            if (vendor.getVendorId().equals(selectedVendorId)) {
                return vendor;
            }
        }
        return vendors.get(0);
    }

    public int getCount() {
        return vendors.size();
    }
}
