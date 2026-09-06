package com.company.company_management_system.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    // Create Group
    @PostMapping
    public ResponseEntity<?> addGroup(@RequestBody Group group) {

        try {

            Group savedGroup = groupService.addGroup(group);

            return new ResponseEntity<>(savedGroup, HttpStatus.CREATED);

        } catch (IllegalArgumentException e) {

            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.BAD_REQUEST
            );
        }
    }

    // Get All Active Groups
    @GetMapping
    public ResponseEntity<List<Group>> getAllGroups() {

        return ResponseEntity.ok(groupService.getAllGroups());
    }

    // Get Group By ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getGroupById(@PathVariable Long id) {

        try {

            return ResponseEntity.ok(groupService.getGroupById(id));

        } catch (RuntimeException e) {

            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.NOT_FOUND
            );
        }
    }

    // Update Group
    @PutMapping("/{id}")
    public ResponseEntity<?> updateGroup(
            @PathVariable Long id,
            @RequestBody Group group) {

        try {

            Group updatedGroup = groupService.updateGroup(id, group);

            return ResponseEntity.ok(updatedGroup);

        } catch (IllegalArgumentException e) {

            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.BAD_REQUEST
            );

        } catch (RuntimeException e) {

            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.NOT_FOUND
            );
        }
    }

    // Soft Delete Group
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteGroup(@PathVariable Long id) {

        try {

            groupService.deleteGroup(id);

            return ResponseEntity.ok("Group deleted successfully");

        } catch (RuntimeException e) {

            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.NOT_FOUND
            );
        }
    }
}