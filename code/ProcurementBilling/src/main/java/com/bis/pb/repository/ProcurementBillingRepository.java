package com.bis.pb.repository;


import com.bis.domain.BillingProcurement;
import com.bis.repository.BaseRepository;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
public class ProcurementBillingRepository extends BaseRepository<BillingProcurement>{
    @Autowired
    protected ProcurementBillingRepository(@Qualifier("sessionFactory") SessionFactory sessionFactory) {
        super(BillingProcurement.class);
        setSessionFactory(sessionFactory);
    }
}
