package com.bis.sales.repository;


import com.bis.domain.SalesTransaction;
import com.bis.repository.BaseRepository;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.Date;
import java.util.List;

public class SalesRepository extends BaseRepository<SalesTransaction>{

    @Autowired
    public SalesRepository(@Qualifier("sessionFactory") SessionFactory sessionFactory) {
        super(SalesTransaction.class);
        setSessionFactory(sessionFactory);
    }

    public List<SalesTransaction> getSalesTransactions(Date fromDate, Date toDate){
        return getSession().createCriteria(SalesTransaction.class)
                .add(Restrictions.ge("date", fromDate))
                .add(Restrictions.le("date", toDate)).list();
    }
 }
