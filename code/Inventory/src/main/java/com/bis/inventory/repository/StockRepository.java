/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bis.inventory.repository;

import com.bis.domain.Stock;
import com.bis.repository.BaseRepository;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public class StockRepository extends BaseRepository<Stock> {
    @Autowired
    public StockRepository(@Qualifier("sessionFactory") SessionFactory sessionFactory) {
        super(Stock.class);
        setSessionFactory(sessionFactory);
    }

    public List<Stock> getAllStock(int itemCode) {
        return getSession().createCriteria(Stock.class).add(Restrictions.eq("itemCode", itemCode)).list();
    }

    public Stock getStock(int itemCode, Date dateOfPublishing) {
        return (Stock) getSession().createCriteria(Stock.class)
                .add(Restrictions.eq("itemCode", itemCode))
                .add(Restrictions.eq("dateOfPublishing", dateOfPublishing))
                .list().get(0);

    }
}
    