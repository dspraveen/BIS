package com.bis.pb.repository;


import com.bis.domain.BillingProcurement;
import com.bis.repository.BaseRepository;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Qualifier;

public class ProcurementBillingRepository extends BaseRepository<BillingProcurement>{
    protected ProcurementBillingRepository(@Qualifier("sessionFactory") SessionFactory sessionFactory) {
        super(BillingProcurement.class);
        setSessionFactory(sessionFactory);
    }
}
