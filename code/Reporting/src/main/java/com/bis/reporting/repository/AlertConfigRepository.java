package com.bis.reporting.repository;

import com.bis.domain.AlertConfig;
import com.bis.repository.BaseRepository;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
public class AlertConfigRepository extends BaseRepository<AlertConfig> {

    @Autowired
    public AlertConfigRepository(@Qualifier("sessionFactory") SessionFactory sessionFactory) {
        super(AlertConfig.class);
        setSessionFactory(sessionFactory);
    }
}
