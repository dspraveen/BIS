package com.bis.reporting.repository;

import com.bis.domain.Alert;
import com.bis.repository.BaseRepository;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
public class AlertsRepository extends BaseRepository<Alert> {

    @Autowired
    public AlertsRepository(@Qualifier("sessionFactory") SessionFactory sessionFactory) {
        super(Alert.class);
        setSessionFactory(sessionFactory);
    }
}
