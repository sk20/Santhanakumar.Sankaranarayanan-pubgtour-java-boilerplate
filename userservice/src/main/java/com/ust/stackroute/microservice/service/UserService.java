package com.ust.stackroute.microservice.service;

import com.ust.stackroute.microservice.exception.UserAlreadyExistsException;
import com.ust.stackroute.microservice.exception.UserNotFoundException;
import com.ust.stackroute.microservice.model.User;

public interface UserService {

	/*
	 * Should not modify this interface. You have to implement these methods in
	 * corresponding Impl classes
	 */

	User registerUser(User user) throws UserAlreadyExistsException;

	User updateUser(String userId, User user) throws UserNotFoundException;

	boolean deleteUser(String userId) throws UserNotFoundException;

	User getUserById(String userId) throws UserNotFoundException;
}
