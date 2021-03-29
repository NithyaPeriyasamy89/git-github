package com.sample.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.sample.request.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
}