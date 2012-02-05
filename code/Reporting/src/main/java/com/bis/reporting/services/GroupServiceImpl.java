package com.bis.reporting.services;

import com.bis.domain.*;
import com.bis.reporting.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupServiceImpl implements GroupService {

    private GroupRepository groupRepository;

    @Autowired
    public GroupServiceImpl(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    @Override
    public void save(Group group) {
        groupRepository.save(group);
    }

    @Override
    public void update(Group group) {
        groupRepository.update(group);
    }

    @Override
    public void delete(Group group) {
        groupRepository.delete(group);
    }

    @Override
    public Group getGroup(Integer groupId) {
        return groupRepository.getGroup(groupId);
    }

    @Override
    public List<Group> getAll() {
        return groupRepository.getAll();
    }

    public void addItemToGroup(Group group, Item item) {
        List<GroupItem> groupItems = group.getGroupItems();
        boolean flag = true;
        if (groupItems.size() > 0) {
            for (GroupItem groupItem : groupItems) {
                if (groupItem.getItem() == null) {
                    groupItem.setItem(item);
                    flag = false;
                    break;
                }
            }
        }
        if (flag) {
            GroupItem groupItem = new GroupItem();
            groupItem.setItem(item);
            group.getGroupItems().add(groupItem);
        }
    }

    public void addVendorToGroup(Group group, Vendor vendor) {
        List<GroupItem> groupItems = group.getGroupItems();
        boolean flag = true;
        if (groupItems.size() > 0) {
            for (GroupItem groupItem : groupItems) {
                if (groupItem.getVendor() == null) {
                    groupItem.setVendor(vendor);
                    flag = false;
                    break;
                }
            }
        }
        if (flag) {
            GroupItem groupItem = new GroupItem();
            groupItem.setVendor(vendor);
            group.getGroupItems().add(groupItem);
        }
    }

    public void addHawkerToGroup(Group group, Hawker hawker) {
        List<GroupItem> groupItems = group.getGroupItems();
        boolean flag = true;
        if (groupItems.size() > 0) {
            for (GroupItem groupItem : groupItems) {
                if (groupItem.getHawker() == null) {
                    groupItem.setHawker(hawker);
                    flag = false;
                    break;
                }
            }
        }
        if (flag) {
            GroupItem groupItem = new GroupItem();
            groupItem.setHawker(hawker);
            group.getGroupItems().add(groupItem);
        }
    }

    public void removeItemFromGroup(Group group, Item item) {
        List<GroupItem> groupItems = group.getGroupItems();
        for (GroupItem groupItem : groupItems) {
            if (groupItem.getItem().getItemCode() == item.getItemCode()) {
                if (groupItem.getVendor().getVendorId() == null && groupItem.getHawker().getHawkerId() == null) {
                    groupItems.remove(groupItem);
                } else {
                    groupItem.setItem(null);
                }
                break;
            }
        }
    }

    public void removeVendorFromGroup(Group group, Vendor vendor) {
        List<GroupItem> groupItems = group.getGroupItems();
        for (GroupItem groupItem : groupItems) {
            if (groupItem.getVendor().getVendorId() == vendor.getVendorId()) {
                if (groupItem.getItem().getItemCode() == null && groupItem.getHawker().getHawkerId() == null) {
                    groupItems.remove(groupItem);
                } else {
                    groupItem.setVendor(null);
                }
                break;
            }
        }
    }

    public void removeHawkerFromGroup(Group group, Hawker hawker) {
        List<GroupItem> groupItems = group.getGroupItems();
        for (GroupItem groupItem : groupItems) {
            if (groupItem.getHawker().getHawkerId() == hawker.getHawkerId()) {
                if (groupItem.getItem().getItemCode() == null && groupItem.getVendor().getVendorId() == null) {
                    groupItems.remove(groupItem);
                } else {
                    groupItem.setHawker(null);
                }
                break;
            }
        }
    }
}

