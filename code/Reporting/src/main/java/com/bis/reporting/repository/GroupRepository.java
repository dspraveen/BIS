package com.bis.reporting.repository;

import com.bis.domain.Group;
import com.bis.repository.BaseRepository;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
public class GroupRepository extends BaseRepository<Group> {

    @Autowired
    public GroupRepository(@Qualifier("sessionFactory") SessionFactory sessionFactory) {
        super(Group.class);
        setSessionFactory(sessionFactory);
    }

    public Group getGroup(Integer groupId) {
        return (Group)getSession().createCriteria(Group.class)
                .add(Restrictions.eq("groupId",groupId)).list().get(0);
    }
}
