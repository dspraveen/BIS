package com.bis.sales.repository;


import com.bis.domain.Hawker;
import com.bis.domain.SalesTransaction;
import com.bis.repository.BaseRepository;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public class SalesTransactionRepository extends BaseRepository<SalesTransaction>{

    @Autowired
    public SalesTransactionRepository(@Qualifier("sessionFactory") SessionFactory sessionFactory) {
        super(SalesTransaction.class);
        setSessionFactory(sessionFactory);
    }

    public List<SalesTransaction> getSalesTransactions(Hawker hawker, Date fromDate, Date toDate){
        return getSession().createCriteria(SalesTransaction.class)
                .add(Restrictions.eq("hawker", hawker))
                .add(Restrictions.ge("date", fromDate))
                .add(Restrictions.le("date", toDate)).list();
    }

    public List<SalesTransaction> getSalesTransactions(Date fromDate, Date toDate){
        return getSession().createCriteria(SalesTransaction.class)
                .add(Restrictions.ge("date", fromDate))
                .add(Restrictions.le("date", toDate)).list();
    }

    public List<SalesTransaction> getSalesTransactions(Hawker hawker, Date tillDate) {
        return getSession().createCriteria(SalesTransaction.class)
                .add(Restrictions.eq("hawker", hawker))
                .add(Restrictions.le("date", tillDate))
                .addOrder(Order.asc("date")).list();
    }
}
