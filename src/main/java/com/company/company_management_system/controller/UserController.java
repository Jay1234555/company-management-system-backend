
package com.company.company_management_system.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.company_management_system.entity.User;
import com.company.company_management_system.service.UserService;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = {
	    "http://localhost:3000",
	    "https://sparkling-communication-production-0a7a.up.railway.app"
	})
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    // GET all users
    @GetMapping
    public List<User> getAllUsers() {

        return userService.getAllUsers();
    }


    // GET user by ID
    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id) {

        return userService.getUserById(id);
    }


    // CREATE user
    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }


    // LOGIN
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User loginUser) {

        try {

            User user =
                    userService.getUserByEmail(loginUser.getEmail());


            // Check password
            if (!user.getPassword().equals(loginUser.getPassword())) {

                return ResponseEntity
                        .status(HttpStatus.UNAUTHORIZED)
                        .body("Invalid email or password");
            }


            // Login successful for both USER and ADMIN
            return ResponseEntity.ok(user);


        } catch (RuntimeException e) {

            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body("Invalid email or password");
        }
    }
}
