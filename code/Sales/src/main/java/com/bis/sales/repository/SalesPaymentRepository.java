package com.bis.sales.repository;

import com.bis.domain.PaymentHistorySales;
import com.bis.repository.BaseRepository;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public class SalesPaymentRepository extends BaseRepository<PaymentHistorySales>{

    @Autowired
    public SalesPaymentRepository(@Qualifier("sessionFactory") SessionFactory sessionFactory) {
        super(PaymentHistorySales.class);
        setSessionFactory(sessionFactory);
    }

    public List<PaymentHistorySales> getSalesPayments(Date fromDate, Date toDate) {
        return getSession().createCriteria(PaymentHistorySales.class)
                .add(Restrictions.ge("date", fromDate))
                .add(Restrictions.le("date", toDate)).list();
    }

    public List<PaymentHistorySales> getSalesPayments(int hawkerId, Date fromDate, Date toDate) {
        return getSession().createCriteria(PaymentHistorySales.class)
                .add(Restrictions.eq("hawkerId",hawkerId))
                .add(Restrictions.ge("date",fromDate))
                .add(Restrictions.le("date", toDate)).list();
    }
}
