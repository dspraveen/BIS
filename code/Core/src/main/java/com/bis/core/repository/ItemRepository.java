package com.bis.core.repository;

import com.bis.common.DateUtils;
import com.bis.domain.Item;
import com.bis.domain.ItemPrice;
import com.bis.repository.BaseRepository;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public class ItemRepository extends BaseRepository<Item>{
    @Autowired
    public ItemRepository(@Qualifier("sessionFactory") SessionFactory sessionFactory) {
        super(Item.class);
        setSessionFactory(sessionFactory);
    }

    public float getPrice(int itemCode){
        ItemPrice itemPrice = (ItemPrice) getSession().createCriteria(ItemPrice.class)
                .add(Restrictions.eq("itemCode", itemCode))
                .add(Restrictions.isNull("endTime")).list().get(0);
        return itemPrice.getPrice();
    }

    public float setItemPrice(int itemCode, float price){
        Date date = DateUtils.currentDate();
        List list = getSession().createCriteria(ItemPrice.class)
        .add(Restrictions.eq("itemCode", itemCode))
        .add(Restrictions.isNull("endTime")).list();
        if(!list.isEmpty()){
            ItemPrice currentPrice = (ItemPrice) list.get(0);
            currentPrice.setEndTime(date);
            getHibernateTemplate().update(currentPrice);
        }
        ItemPrice itemPrice = new ItemPrice();
        itemPrice.setItemCode(itemCode);
        itemPrice.setPrice(price);
        itemPrice.setStartDate(DateUtils.addMinutes(date, 1));
        itemPrice.setEndTime(null);
        getHibernateTemplate().save(itemPrice);
        return itemPrice.getPrice();
    }
}
