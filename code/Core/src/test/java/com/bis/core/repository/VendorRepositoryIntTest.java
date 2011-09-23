package com.bis.core.repository;

import com.bis.domain.Vendor;
import com.bis.testcommon.BaseIntTest;
import com.bis.testcommon.VendorBuilder;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class VendorRepositoryIntTest extends BaseIntTest {

    @Autowired
    private VendorRepository vendorRepository;

    @Test
    public void shouldPerformCRUDOnOItem(){
        Vendor vendor = new VendorBuilder().withDefaults().build();
        vendorRepository.save(vendor);

        Vendor dbVendor = vendorRepository.get(vendor.getVendorId());
        assertEquals(vendor, dbVendor);

        dbVendor.setPhoneNumber("newPhone");
        vendorRepository.update(dbVendor);

        Vendor updatedVendor = vendorRepository.get(vendor.getVendorId());
        assertEquals("newPhone", updatedVendor.getPhoneNumber());

        vendorRepository.delete(vendor);
        assertNull(vendorRepository.get(vendor.getVendorId()));
    }

    @Test
    public void shouldGetAllItems(){
        Vendor vendorOne = new VendorBuilder().withDefaults().build();
        Vendor vendorTwo = new VendorBuilder().withDefaults().build();
        vendorRepository.save(vendorOne);
        vendorRepository.save(vendorTwo);
        List<Vendor> all = vendorRepository.getAll();
        assertTrue(all.contains(vendorOne));
        assertTrue(all.contains(vendorTwo));
        vendorRepository.delete(vendorOne);
        vendorRepository.delete(vendorTwo);
    }

}
