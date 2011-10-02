package com.bis.sales.repository;


import com.bis.domain.SalesTransaction;
import com.bis.domain.SalesTransaction;
import com.bis.repository.BaseRepository;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class SalesRepository extends BaseRepository<SalesTransaction>{

    @Autowired
    public SalesRepository(@Qualifier("sessionFactory") SessionFactory sessionFactory) {
        super(SalesTransaction.class);
        setSessionFactory(sessionFactory);
    }
}
