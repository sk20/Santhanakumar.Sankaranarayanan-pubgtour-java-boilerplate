package com.ust.stackroute.microservice.service;

import com.ust.stackroute.microservice.exception.*;

import com.ust.stackroute.microservice.model.*;

public interface UserAuthenticationService {

	/*
	 * Should not modify this interface. You have to implement these methods in
	 * corresponding Impl classes
	 */

	public User findByUserIdAndPassword(String userId, String password)
			throws UserNotFoundException;

	boolean saveUser(User user) throws UserAlreadyExistsException;
}
