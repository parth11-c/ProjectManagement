package com.data.service;


import org.springframework.stereotype.Service;

import com.data.entity.Users;
import com.data.repository.URepo;
 
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

@Service
public class UService {

    @Autowired
    private URepo Urepo;

     public Users saveOrUpdateUsers(Users Users) {
        return Urepo.save(Users);
    }

     public List<Users> getAllUserss() {
        return Urepo.findAll();
    }
     
 
     public Optional<Users> getUsersById(String id) {
         return Urepo.findById(id);  // Example: Using Spring Data JPA's findById method
     }


     public Optional<Users> getUsersByUsersname(String Usersname) {
        return Urepo.findByUsername(Usersname);
    }

     public Optional<Users> getUsersById1(String id) {
        return Urepo.findById(id);
    }

     public Optional<Users> updateUsersByUsername(String name, Users updatedUsers) {
    	    return Urepo.findByUsername(name).map(existingUser -> {
    	        existingUser.setPassword(updatedUsers.getPassword());
    	        existingUser.setEmail(updatedUsers.getEmail());
    	        existingUser.setName(updatedUsers.getUsername());
    	        existingUser.setName(updatedUsers.getName());
    	        
    	        return Urepo.save(existingUser);
    	    });
    	}


   public void deleteUsersByUsersname(String Usersname) {
        Urepo.deleteByUsername(Usersname);
    }

    // Delete a Users by ID
    public void deleteUsersById(String id) {
        Urepo.deleteById(id);
    }
}