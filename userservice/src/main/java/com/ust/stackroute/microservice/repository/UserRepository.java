package com.ust.stackroute.microservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.ust.stackroute.microservice.model.User;

/*
* This class is implementing the MongoRepository interface for User.
* Annotate this class with @Repository annotation
* */
@Repository
public interface UserRepository extends MongoRepository<User, String> {

}
