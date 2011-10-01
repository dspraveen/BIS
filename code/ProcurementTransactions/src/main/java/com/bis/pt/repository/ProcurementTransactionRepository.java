package com.bis.pt.repository;

import com.bis.domain.ProcurementTransaction;
import com.bis.repository.BaseRepository;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
public class ProcurementTransactionRepository extends BaseRepository<ProcurementTransaction>{

    @Autowired
    public ProcurementTransactionRepository(@Qualifier("sessionFactory") SessionFactory sessionFactory) {
        super(ProcurementTransaction.class);
        setSessionFactory(sessionFactory);
    }
}
