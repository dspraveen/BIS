package com.bis.reporting.repository;

import com.bis.domain.AlertAssociation;
import com.bis.repository.BaseRepository;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
public class AlertAssociationRepository extends BaseRepository<AlertAssociation> {

    @Autowired
    public AlertAssociationRepository(@Qualifier("sessionFactory") SessionFactory sessionFactory) {
        super(AlertAssociation.class);
        setSessionFactory(sessionFactory);
    }
}
