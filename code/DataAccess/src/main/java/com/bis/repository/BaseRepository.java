package com.bis.repository;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.io.Serializable;

public abstract class BaseRepository<T> extends HibernateDaoSupport {

    private Class<T> type;

    protected BaseRepository(Class<T> type) {
        this.type = type;
    }

    public void save(T object) {
        getSession().save(object);
    }

    public T get(Serializable id) {
        return (T) getSession().get(type,id);
    }

    public void delete(T object) {
        getSession().delete(object);
    }

    public void update(T object) {
        getSession().update(object);
    }
}

