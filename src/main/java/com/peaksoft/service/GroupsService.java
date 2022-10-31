package com.peaksoft.service;

import com.peaksoft.entity.Groups;

import java.util.List;

public interface GroupsService {

    List<Groups> getAllGroups();

    void addGroups(Groups group,Long courseId);

    Groups getGroupById(Long id);

    void updateGroup(Groups group,Long courseId);

    void deleteGroup(Groups group);

}
