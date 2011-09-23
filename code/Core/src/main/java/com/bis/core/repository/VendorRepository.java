package com.bis.core.repository;

import com.bis.domain.Vendor;
import com.bis.repository.BaseRepository;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
public class VendorRepository  extends BaseRepository<Vendor>{

    @Autowired
    public VendorRepository(@Qualifier("sessionFactory") SessionFactory sessionFactory) {
        super(Vendor.class);
        setSessionFactory(sessionFactory);
    }
}
