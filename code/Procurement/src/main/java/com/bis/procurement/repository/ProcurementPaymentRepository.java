package com.bis.procurement.repository;


import com.bis.domain.PaymentHistoryProcurement;
import com.bis.repository.BaseRepository;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public class ProcurementPaymentRepository extends BaseRepository<PaymentHistoryProcurement>{
    @Autowired
    protected ProcurementPaymentRepository(@Qualifier("sessionFactory") SessionFactory sessionFactory) {
        super(PaymentHistoryProcurement.class);
        setSessionFactory(sessionFactory);
    }

    public List<PaymentHistoryProcurement> getProcurementPayments(Date fromDate, Date toDate) {
        return getSession().createCriteria(PaymentHistoryProcurement.class)
                .add(Restrictions.ge("date",fromDate))
                .add(Restrictions.le("date", toDate)).list();
    }


    public List<PaymentHistoryProcurement> getProcurementPayments(int vendorId, Date fromDate, Date toDate) {
        return getSession().createCriteria(PaymentHistoryProcurement.class)
                .add(Restrictions.eq("vendorId",vendorId))
                .add(Restrictions.ge("date", fromDate))
                .add(Restrictions.le("date",toDate)).list();
    }


}
