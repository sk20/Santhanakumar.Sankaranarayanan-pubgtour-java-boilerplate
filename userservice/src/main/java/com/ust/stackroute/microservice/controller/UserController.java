package com.ust.stackroute.microservice.controller;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ust.stackroute.microservice.exception.UserAlreadyExistsException;
import com.ust.stackroute.microservice.exception.UserNotFoundException;
import com.ust.stackroute.microservice.model.User;
import com.ust.stackroute.microservice.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


/*
 * As in this assignment, we are working on creating RESTful web service, hence annotate
 * the class with @RestController annotation. A class annotated with the @Controller annotation
 * has handler methods which return a view. However, if we use @ResponseBody annotation along
 * with @Controller annotation, it will return the data directly in a serialized 
 * format. Starting from Spring 4 and above, we can use @RestController annotation which 
 * is equivalent to using @Controller and @ResposeBody annotation
 */
@RestController
@RequestMapping("/api/v1")
@Api
@CrossOrigin
public class UserController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	/*
	 * Autowiring should be implemented for the UserService. (Use Constructor-based
	 * autowiring) Please note that we should not create an object using the new
	 * keyword
	 */
	@Autowired
	private UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	/*
	 * Define a handler method which will create a specific user by reading the
	 * Serialized object from request body and save the user details in the
	 * database. This handler method should return any one of the status messages
	 * basis on different situations: 1. 201(CREATED) - If the user created
	 * successfully. 2. 409(CONFLICT) - If the userId conflicts with any existing
	 * user
	 * 
	 * This handler method should map to the URL "/user" using HTTP POST method
	 */
	@ApiOperation(value = "Create User ")
	@RequestMapping(value="/user", method = RequestMethod.POST, headers ="Accept=application/json")
	public ResponseEntity<?> registerUser(@RequestBody User user) {
		logger.info("registerUser : STARTED");
		HttpHeaders headers = new HttpHeaders();
		try {
			user.setUserAddedDate(new Date());
			User userCreated = userService.registerUser(user);
			logger.info("userCreated: " + userCreated);
			if (userCreated != null) {
				return new ResponseEntity<>(headers, HttpStatus.CREATED);
			}
		} catch (UserAlreadyExistsException e) {
			e.printStackTrace();
			return new ResponseEntity<>(headers, HttpStatus.CONFLICT);
		}
		logger.info("registerUser : ENDED");
		return new ResponseEntity<>(headers, HttpStatus.CREATED);
	}

	/*
	 * Define a handler method which will update a specific user by reading the
	 * Serialized object from request body and save the updated user details in a
	 * database. This handler method should return any one of the status messages
	 * basis on different situations: 1. 200(OK) - If the user updated successfully.
	 * 2. 404(NOT FOUND) - If the user with specified userId is not found.
	 * 
	 * This handler method should map to the URL "/api/v1/user/{id}" using HTTP PUT
	 * method.
	 */
	@ApiOperation(value = "Update User ")
	@RequestMapping(value = "/user/{userId}", method = RequestMethod.PUT, headers = "Accept=application/json")
	public ResponseEntity<?> updateUser(@PathVariable("id") String id, @RequestBody User user) {
		logger.info("updateUser : STARTED");
		HttpHeaders headers = new HttpHeaders();
		try {
			if (userService.updateUser(id, user) != null) {
				return new ResponseEntity<>(headers, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(headers, HttpStatus.NOT_FOUND);
			}
		} catch (UserNotFoundException e) {
			e.printStackTrace();
		}
		logger.info("updateUser : ENDED");
		return new ResponseEntity<>(headers, HttpStatus.NOT_FOUND);
	}

	/*
	 * Define a handler method which will delete a user from a database. This
	 * handler method should return any one of the status messages basis on
	 * different situations: 1. 200(OK) - If the user deleted successfully from
	 * database. 2. 404(NOT FOUND) - If the user with specified userId is not found.
	 *
	 * This handler method should map to the URL "/api/v1/user/{id}" using HTTP
	 * Delete method" where "id" should be replaced by a valid userId without {}
	 */
	@ApiOperation(value = "Delete User ")
	@RequestMapping(value = "/user/{userId}", method = RequestMethod.DELETE, headers = "Accept=application/json")
	public ResponseEntity<?> deleteUser(@PathVariable("id") String id) {
		logger.info("deleteUser : STARTED");
		User user;
		try {
			user = userService.getUserById(id);
			logger.info("User details: "+user);
		} catch (com.ust.stackroute.microservice.exception.UserNotFoundException e) {
			// TODO Auto-generated catch block
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}

		boolean status = false;
		try {
			status = userService.deleteUser(id);
		} catch (com.ust.stackroute.microservice.exception.UserNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logger.info("deleteUser : ENDED");
		if (status)
			return new ResponseEntity<User>(HttpStatus.OK);
		else
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		
	}

	/*
	 * Define a handler method which will show details of a specific user. This
	 * handler method should return any one of the status messages basis on
	 * different situations: 1. 200(OK) - If the user found successfully. 2. 404(NOT
	 * FOUND) - If the user with specified userId is not found. This handler method
	 * should map to the URL "/api/v1/user/{id}" using HTTP GET method where "id"
	 * should be replaced by a valid userId without {}
	 */
	@ApiOperation(value = "Get User By Id")
	@RequestMapping(value = "/user/{userId}", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<User> getUserById(@PathVariable("userId") String userId) {
		logger.info("getUserById : STARTED");
		HttpHeaders headers = new HttpHeaders();
		try {
			User user = userService.getUserById(userId);
			if (user != null) {
				return new ResponseEntity<User>(headers, HttpStatus.OK);
			}

		} catch (UserNotFoundException e) {
			e.printStackTrace();
			return new ResponseEntity<User>(headers, HttpStatus.NOT_FOUND);
		}
		logger.info("getUserById : ENDED");
		return new ResponseEntity<User>(headers, HttpStatus.NOT_FOUND);
	}
}
