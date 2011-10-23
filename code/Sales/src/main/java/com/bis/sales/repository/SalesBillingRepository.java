package com.bis.sales.repository;


import com.bis.domain.BillingSales;
import com.bis.repository.BaseRepository;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
public class SalesBillingRepository extends BaseRepository<BillingSales>{

    @Autowired
    protected SalesBillingRepository(@Qualifier("sessionFactory") SessionFactory sessionFactory) {
        super(BillingSales.class);
        setSessionFactory(sessionFactory);
    }
}
