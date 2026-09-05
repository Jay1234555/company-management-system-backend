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

        String name = group.getName() == null
                ? ""
                : group.getName().trim();

        // Empty name validation
        if (name.isEmpty()) {
            throw new IllegalArgumentException("Group name cannot be empty");
        }

        // Duplicate name validation
        if (groupRepository.existsByNameIgnoreCaseAndIsDeletedFalse(name)) {
            throw new IllegalArgumentException("Group name already exists");
        }

        group.setName(name);

        // New group is active
        group.setDeleted(false);

        return groupRepository.save(group);
    }

    // Get All Active Groups
    public List<Group> getAllGroups() {

        List<Group> groups = groupRepository.findAll();

        System.out.println("ALL GROUPS IN DATABASE: " + groups);

        return groupRepository.findByIsDeletedFalse();
    }
    // Get Group By ID
    public Group getGroupById(Long id) {

        return groupRepository.findByIdAndIsDeletedFalse(id)
                .orElseThrow(() -> new RuntimeException("Group not found"));
    }

    // Update Group
    public Group updateGroup(Long id, Group group) {

        String name = group.getName() == null
                ? ""
                : group.getName().trim();

        // Empty name validation
        if (name.isEmpty()) {
            throw new IllegalArgumentException("Group name cannot be empty");
        }

        // Find active group
        Group existingGroup = groupRepository
                .findByIdAndIsDeletedFalse(id)
                .orElseThrow(() -> new RuntimeException("Group not found"));

        // Duplicate name validation
        if (groupRepository
                .existsByNameIgnoreCaseAndIsDeletedFalseAndIdNot(name, id)) {

            throw new IllegalArgumentException("Group name already exists");
        }

        existingGroup.setName(name);
        existingGroup.setDescription(group.getDescription());

        return groupRepository.save(existingGroup);
    }

    // Soft Delete Group
    public void deleteGroup(Long id) {

        Group existingGroup = groupRepository
                .findByIdAndIsDeletedFalse(id)
                .orElseThrow(() -> new RuntimeException("Group not found"));

        // Soft delete
        existingGroup.setDeleted(true);

        groupRepository.save(existingGroup);
        
    }
}