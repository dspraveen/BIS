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
                .add(Restrictions.ge("startDate", fromDate))
                .add(Restrictions.le("endDate", toDate)).list();
    }

    public BillingSales getLastBill(Hawker hawker) {
        List<BillingSales> billingSales = getSession().createCriteria(BillingSales.class)
                .add(Restrictions.eq("hawker", hawker))
                .addOrder(Order.desc("endDate")).list();

        if(billingSales.isEmpty()){
            return null;
        }
        else{
            return billingSales.get(0);
        }
    }

    public List<BillingSales> getSalesBillList(Hawker hawker, Date fromDate, Date toDate) {
        return getSession().createCriteria(BillingSales.class)
                .add(Restrictions.eq("hawker",hawker))
                .add(Restrictions.ge("startDate", fromDate))
                .add(Restrictions.le("endDate", toDate)).list();
    }
}
