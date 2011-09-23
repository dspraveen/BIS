package com.bis.core.services;


import com.bis.domain.Vendor;

import java.util.List;

public interface VendorMasterService {

    void create(Vendor vendor);
    void update(Vendor vendor);
    Vendor get(int vendorCode);
    List<Vendor> getAll();
}
