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

    public Stock getStock(int itemCode, Date dateOfPublishing) {
        List stock = getSession().createCriteria(Stock.class)
                .add(Restrictions.eq("item.itemCode", itemCode))
                .add(Restrictions.eq("dateOfPublishing", dateOfPublishing))
                .list();
        return stock.isEmpty() ? null : (Stock) stock.get(0);
    }

    public List<Stock> getAllStock() {
        return getSession().createCriteria(Stock.class)
                .add(Restrictions.gt("quantity", 0)).list();
    }

    public List<Stock> getItemStock(int itemCode, Date fromDateOfPublish, Date endDateOfPublish) {
        return getSession().createCriteria(Stock.class)
                .add(Restrictions.eq("item.itemCode", itemCode))
                .add(Restrictions.between("dateOfPublishing", fromDateOfPublish, endDateOfPublish))
                .list();
    }
}
    