package com.bis.sales.repository;


import com.bis.domain.BillingSales;
import com.bis.domain.Hawker;
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
public class SalesBillingRepository extends BaseRepository<BillingSales> {

    @Autowired
    protected SalesBillingRepository(@Qualifier("sessionFactory") SessionFactory sessionFactory) {
        super(BillingSales.class);
        setSessionFactory(sessionFactory);
    }

    public List<BillingSales> getSalesBillList(Date fromDate, Date toDate) {
        return getSession().createCriteria(BillingSales.class)
                .add(Restrictions.ge("date", fromDate))
                .add(Restrictions.le("date", toDate)).list();
    }

    public Date getLastBillEndDate(Hawker hawker) {
        return (Date) getSession().createCriteria(BillingSales.class)
                .add(Restrictions.eq("hawkerId", hawker.getHawkerId()))
                .addOrder(Order.desc("endDate")).list().get(0);
    }
}
