package com.bis.core.repository;

import com.bis.domain.Hawker;
import com.bis.repository.BaseRepository;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
public class HawkerRepository extends BaseRepository<Hawker>{
    @Autowired
    public HawkerRepository(@Qualifier("sessionFactory") SessionFactory sessionFactory) {
        super(Hawker.class);
        setSessionFactory(sessionFactory);
    }
}
