package com.bis.procurement.repository;

import com.bis.domain.ProcurementTransaction;
import com.bis.repository.BaseRepository;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public class ProcurementTransactionRepository extends BaseRepository<ProcurementTransaction>{

    @Autowired
    public ProcurementTransactionRepository(@Qualifier("sessionFactory") SessionFactory sessionFactory) {
        super(ProcurementTransaction.class);
        setSessionFactory(sessionFactory);
    }

    public List<ProcurementTransaction> getProcurementTransactions(Integer vendorId, Date fromDate, Date toDate) {
        return getSession().createCriteria(ProcurementTransaction.class)
                .add(Restrictions.eq("vendor.vendorId", vendorId))
                .add(Restrictions.ge("date", fromDate))
                .add(Restrictions.le("date", toDate)).list();
    }

    public List<ProcurementTransaction> getProcurementTransactions( Date fromDate, Date toDate) {
        return getSession().createCriteria(ProcurementTransaction.class)
                .add(Restrictions.ge("date", fromDate))
                .add(Restrictions.le("date", toDate)).list();
    }
}
