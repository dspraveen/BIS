package com.bis.reporting.repository;

import com.bis.domain.AlertConfig;
import com.bis.domain.AlertType;
import com.bis.repository.BaseRepository;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AlertConfigRepository extends BaseRepository<AlertConfig> {

    @Autowired
    public AlertConfigRepository(@Qualifier("sessionFactory") SessionFactory sessionFactory) {
        super(AlertConfig.class);
        setSessionFactory(sessionFactory);
    }

    public List<AlertConfig> getAlertConfigs(AlertType alertTypeId) {
        return getSession().createCriteria(AlertConfig.class)
                .add(Restrictions.eq("alertType",alertTypeId)).list();
    }
}
