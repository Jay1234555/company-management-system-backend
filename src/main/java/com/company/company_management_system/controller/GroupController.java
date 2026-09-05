package com.company.company_management_system.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.company_management_system.entity.Group;
import com.company.company_management_system.service.GroupService;

@RestController
@RequestMapping("/api/groups")
@CrossOrigin(origins = {
	    "http://localhost:3000",
	    "https://sparkling-communication-production-0a7a.up.railway.app"
	})
public class GroupController {
	

    @Autowired
    private GroupService groupService;

    @PostMapping
    public Group addGroup(@RequestBody Group group) {
        return groupService.addGroup(group);
    }

    @GetMapping
    public List<Group> getAllGroups() {
        return groupService.getAllGroups();
    }

    @GetMapping("/{id}")
    public Group getGroupById(@PathVariable Long id) {
        return groupService.getGroupById(id);
    }

    @PutMapping("/{id}")
    public Group updateGroup(@PathVariable Long id,
                             @RequestBody Group group) {
        return groupService.updateGroup(id, group);
    }

    @DeleteMapping("/{id}")
    public String deleteGroup(@PathVariable Long id) {
        groupService.deleteGroup(id);
        return "Group deleted successfully";
    }
}
