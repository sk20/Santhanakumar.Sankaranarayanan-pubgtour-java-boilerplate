package com.ust.stackroute.microservice.exception;

public class UserAlreadyExistsException extends Exception {

	public UserAlreadyExistsException(String message) {
		super(message);
	}
}
