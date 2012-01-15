package com.bis.reporting.services;


import com.bis.domain.Group;

import java.util.List;

public interface GroupService {
    void save(Group group);
    void update(Group group);
    void delete(Group group);
    Group getGroup(Integer groupId);
    List<Group> getAll();
}
