package com.data.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.data.entity.Users;

public interface URepo extends MongoRepository<Users, String> {
    Optional<Users> findByUsername(String username);

    void deleteByUsername(String username);
}
