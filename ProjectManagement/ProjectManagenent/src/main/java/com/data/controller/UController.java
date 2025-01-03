package com.data.controller;

import com.data.entity.Users;
import com.data.service.UService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@CrossOrigin(origins = "http://localhost:3000")  
@RestController
@RequestMapping("/api/")  // Standardize the API path
public class UController {

    @Autowired
    private UService usersService;

    // Save or update a user
    @PostMapping("/save")
    public ResponseEntity<Users> saveOrUpdateUser(@RequestBody Users user) {
        Users savedUser = usersService.saveOrUpdateUsers(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);  // Return 201 for creation
    }

    // Get all users
    @GetMapping("/")
    public ResponseEntity<List<Users>> getAllUsers() {
        List<Users> users = usersService.getAllUserss();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    // Get user by ID
    @GetMapping("user/{id}")
    public ResponseEntity<Users> getUserById(@PathVariable String id) {
        Optional<Users> userOpt = usersService.getUsersById(id);

        if (userOpt.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);  // Return 404 if user not found
        }

        return new ResponseEntity<>(userOpt.get(), HttpStatus.OK);  // Return user with 200 OK status
    }

    // Delete user by ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable String id) {
        Optional<Users> userOpt = usersService.getUsersById(id);
        
        if (userOpt.isEmpty()) {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);  // Return 404 if user not found
        }

        usersService.deleteUsersById(id);
        return new ResponseEntity<>("User deleted successfully!", HttpStatus.OK);
    }
    
    
    @PutMapping("/update/{id}")
    public ResponseEntity<Users> updateUser(@PathVariable String id, @RequestBody Users user) {
        Optional<Users> existingUserOpt = usersService.getUsersById(id);

        if (existingUserOpt.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);  // Return 404 if user not found
        }

         Users existingUser = existingUserOpt.get();
        existingUser.setName(user.getName());
        existingUser.setEmail(user.getEmail());
        existingUser.setUsername(user.getUsername());
        existingUser.setPassword(user.getPassword());

        // Save the updated user
        Users updatedUser = usersService.saveOrUpdateUsers(existingUser);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);  // Return updated user with 200 OK status
    }
    
    
    
    
    
}
