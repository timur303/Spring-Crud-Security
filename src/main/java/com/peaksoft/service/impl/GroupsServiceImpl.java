package com.peaksoft.service.impl;

import com.peaksoft.dao.GroupDAO;
import com.peaksoft.entity.Groups;
import com.peaksoft.service.GroupsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class GroupsServiceImpl implements GroupsService {

    private final GroupDAO groupDAO;

    @Autowired
    public GroupsServiceImpl(GroupDAO groupDAO) {
        this.groupDAO = groupDAO;
    }

    @Override
    public List<Groups> getAllGroups() {
        return groupDAO.getAllGroups();
    }

    @Override
    public void addGroups(Groups group,Long courseId) {
        groupDAO.addGroups(group,courseId);
    }

    @Override
    public Groups getGroupById(Long id) {
        return groupDAO.getGroupById(id);
    }

    @Override
    public void updateGroup(Groups group,Long courseId) {
        groupDAO.updateGroup(group,courseId);
    }

    @Override
    public void deleteGroup(Groups group) {
        groupDAO.deleteGroup(group);
    }
}
