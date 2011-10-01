package com.bis.repository;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.io.Serializable;
import java.util.List;

public abstract class BaseRepository<T> extends HibernateDaoSupport {

    private Class<T> type;

    protected BaseRepository(Class<T> type) {
        this.type = type;
    }

    public void save(T object) {
        getHibernateTemplate().save(object);
    }

    public T get(Serializable id) {
        return (T) getHibernateTemplate().get(type, id);
    }

    public void delete(T object) {
        getHibernateTemplate().delete(object);
    }

    public void update(T object) {
        getHibernateTemplate().update(object);
    }

    public List<T> getAll(){
        return getHibernateTemplate().loadAll(type);
    }
}

