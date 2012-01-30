package com.bis.reporting.services;


import com.bis.domain.Group;
import com.bis.domain.Hawker;
import com.bis.domain.Item;
import com.bis.domain.Vendor;

import java.util.List;

public interface GroupService {
    void save(Group group);
    void update(Group group);
    void delete(Group group);
    Group getGroup(Integer groupId);
    List<Group> getAll();
    void addItemToGroup(Group group, Item item);
    void addVendorToGroup(Group group, Vendor vendor);
    void addHawkerToGroup(Group group, Hawker hawker);
}
