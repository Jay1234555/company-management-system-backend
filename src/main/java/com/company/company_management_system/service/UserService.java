
package com.company.company_management_system.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.company.company_management_system.entity.User;
import com.company.company_management_system.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Get all users
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Get user by ID
    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() ->
                    new RuntimeException(
                        "User not found with id: " + id
                    ));
    }

    // Create user
    public User createUser(User user) {
        return userRepository.save(user);
    }

    // Find user by email
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() ->
                    new RuntimeException(
                        "User not found with email: " + email
                    ));
    }

    // Delete user
    public void deleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() ->
                    new RuntimeException(
                        "User not found with id: " + id
                    ));

        userRepository.delete(user);
    }
}
