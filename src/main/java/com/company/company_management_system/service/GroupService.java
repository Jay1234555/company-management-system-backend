package com.company.company_management_system.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.company_management_system.entity.Group;
import com.company.company_management_system.repository.GroupRepository;

@Service
public class GroupService {

    @Autowired
    private GroupRepository groupRepository;

    // Create Group
    public Group addGroup(Group group) {
        return groupRepository.save(group);
    }

    // Get All Groups
    public List<Group> getAllGroups() {
        return groupRepository.findAll();
    }

    // Get Group By ID
    public Group getGroupById(Long id) {
        return groupRepository.findById(id).orElse(null);
    }

    // Update Group
    public Group updateGroup(Long id, Group group) {

        Group existingGroup = groupRepository.findById(id).orElse(null);

        if (existingGroup != null) {

            existingGroup.setName(group.getName());
            existingGroup.setDescription(group.getDescription());

            return groupRepository.save(existingGroup);
        }

        return null;
    }

    // Delete Group
    public void deleteGroup(Long id) {
        groupRepository.deleteById(id);
    }
}