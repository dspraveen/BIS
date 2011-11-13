package com.bis.procurement.repository;


import com.bis.domain.BillingProcurement;
import com.bis.domain.Vendor;
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
public class ProcurementBillingRepository extends BaseRepository<BillingProcurement>{
    @Autowired
    protected ProcurementBillingRepository(@Qualifier("sessionFactory") SessionFactory sessionFactory) {
        super(BillingProcurement.class);
        setSessionFactory(sessionFactory);
    }

    public List<BillingProcurement> getProcurementBillList(Date fromDate, Date toDate) {
        return getSession().createCriteria(BillingProcurement.class)
                .add(Restrictions.ge("date", fromDate))
                .add(Restrictions.le("date", toDate)).list();
    }

    public BillingProcurement getLastBill(Vendor vendor) {

        List<BillingProcurement> billingProcurements = getSession().createCriteria(BillingProcurement.class)
                .add(Restrictions.eq("vendor", vendor))
                .addOrder(Order.desc("endDate")).list();

        if(billingProcurements.isEmpty()){
            return null;
        }
        else{
            return billingProcurements.get(0);
        }
    }
}
