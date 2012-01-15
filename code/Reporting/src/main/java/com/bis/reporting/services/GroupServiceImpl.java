package com.bis.reporting.services;

import com.bis.domain.Group;
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
}
