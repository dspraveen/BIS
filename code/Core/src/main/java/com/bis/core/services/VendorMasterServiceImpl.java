package com.bis.core.services;

import com.bis.core.repository.VendorRepository;
import com.bis.domain.Vendor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class VendorMasterServiceImpl implements VendorMasterService {

    private VendorRepository vendorRepository;

    @Autowired
    public VendorMasterServiceImpl(VendorRepository vendorRepository) {
        this.vendorRepository = vendorRepository;
    }

    @Override
    public void create(Vendor vendor) {
        vendorRepository.save(vendor);
    }

    @Override
    public void update(Vendor vendor) {
        vendorRepository.update(vendor);
    }

    @Override
    public Vendor get(int vendorCode) {
        return vendorRepository.get(vendorCode);
    }

    @Override
    public List<Vendor> getAll() {
        return vendorRepository.getAll();
    }
}
