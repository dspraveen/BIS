package com.bis.core.services;

import com.bis.common.BisCacheManager;
import com.bis.core.repository.VendorRepository;
import com.bis.domain.Vendor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VendorMasterCacheServiceImpl extends VendorMasterServiceImpl {

    public static final String VENDORS = "vendors";
    private BisCacheManager bisCacheManager;

    @Autowired
    public VendorMasterCacheServiceImpl(VendorRepository vendorRepository, BisCacheManager bisCacheManager) {
        super(vendorRepository);
        this.bisCacheManager = bisCacheManager;
    }

    @Override
    public void create(Vendor vendor) {
        super.create(vendor);
        bisCacheManager.remove(VENDORS);
    }

    @Override
    public void update(Vendor vendor) {
        super.update(vendor);
        bisCacheManager.remove(VENDORS);
    }

    @Override
    public Vendor get(int vendorCode) {
        if (bisCacheManager.cached(VENDORS)) {
            List<Vendor> vendors = (List<Vendor>) bisCacheManager.get(VENDORS);
            for (Vendor vendor : vendors) {
                if (vendorCode == vendor.getVendorId()) return vendor;
            }
        }
        return super.get(vendorCode);
    }

    @Override
    public List<Vendor> getAll() {
        if (bisCacheManager.notCached(VENDORS)) {
            bisCacheManager.cache(VENDORS, super.getAll());
        }
        return (List<Vendor>) bisCacheManager.get(VENDORS);
    }
}
