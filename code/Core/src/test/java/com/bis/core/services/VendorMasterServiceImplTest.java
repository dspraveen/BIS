package com.bis.core.services;

import com.bis.core.repository.VendorRepository;
import com.bis.domain.Vendor;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

import static org.mockito.Mockito.verify;

public class VendorMasterServiceImplTest {
    @Mock
    private VendorRepository vendorRepository;
    private VendorMasterServiceImpl vendorMasterService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        vendorMasterService = new VendorMasterServiceImpl(vendorRepository);
    }

    @Test
    public void shouldCreateNewVendor() throws Exception {
        Vendor vendor = new Vendor();
        vendorMasterService.create(vendor);
        verify(vendorRepository).save(vendor);
    }

    @Test
    public void shouldUpdateVendor() throws Exception {
        Vendor vendor = new Vendor();
        vendorMasterService.update(vendor);
        verify(vendorRepository).update(vendor);
    }

    @Test
    public void shouldGetVendor() throws Exception {
        Vendor item = new Vendor();
        Mockito.when(vendorRepository.get(12)).thenReturn(item);
        Assert.assertEquals(item,vendorMasterService.get(12));
    }

    @Test
    public void shouldGetAllVendors() throws Exception {
        ArrayList<Vendor> vendors = new ArrayList<Vendor>();
        Mockito.when(vendorRepository.getAll()).thenReturn(vendors);
        Assert.assertEquals(vendors,vendorMasterService.getAll());
    }
}
