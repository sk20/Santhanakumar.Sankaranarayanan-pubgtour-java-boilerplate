package com.ust.stackroute.microservice.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ust.stackroute.microservice.exception.UserAlreadyExistsException;
import com.ust.stackroute.microservice.model.User;
import com.ust.stackroute.microservice.service.UserAuthenticationService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
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
@Api
@CrossOrigin
public class UserAuthenticationController {
	static final long EXPIRATIONTIME = 90000;
	Map<String, String> map = new HashMap<>();
	@Autowired
	private UserAuthenticationService userService;
	@RequestMapping("/")
	public class RootController {
		@RequestMapping(method = RequestMethod.GET)
		public String swaggerUi() {
			return "redirect:/swagger-ui.html";
		}
	}

	@PostMapping("/api/v1/auth/login")
	@ApiOperation(value = "Login to Get JWT Token")
	public ResponseEntity<?> login(@RequestBody User user) throws ServletException {
		String jwtToken = "";
		try {
			jwtToken = getToken(user.getUserId(), user.getUserPassword());
			map.clear();
			map.put("message", "user successfully logged in");
			map.put("token", jwtToken);
		} catch (Exception e) {
			String exceptionMessage = e.getMessage();
			map.clear();
			map.put("token", null);
			map.put("message", exceptionMessage);
			return new ResponseEntity<>(map, HttpStatus.UNAUTHORIZED);
		}
		return new ResponseEntity<>(map, HttpStatus.OK);
	}

	public String getToken(String username, String password) throws Exception {
		if (username == null || password == null) {
			throw new ServletException("Please fill in username and password");
		}
		User user = userService.findByUserIdAndPassword(username, password);
		if (user != null) {
			String jwtToken = Jwts.builder().setSubject(username).setIssuedAt(new Date())
					.setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
					.signWith(SignatureAlgorithm.HS256, "secretkey").compact();
			return jwtToken;
		} else {
			throw new ServletException("Invalid credentials.");
		}
	}

	/*
	 * Autowiring should be implemented for the UserAuthenticationService. (Use
	 * Constructor-based autowiring) Please note that we should not create an
	 * object using the new keyword
	 */
	public UserAuthenticationController(UserAuthenticationService authicationService) {
	}

	/*
	 * Define a handler method which will create a specific user by reading the
	 * Serialized object from request body and save the user details in the
	 * database. This handler method should return any one of the status
	 * messages basis on different situations: 1. 201(CREATED) - If the user
	 * created successfully. 2. 409(CONFLICT) - If the userId conflicts with any
	 * existing user
	 * 
	 * This handler method should map to the URL "/api/v1/auth/register" using
	 * HTTP POST method
	 */
	@ApiOperation(value = "Register New User")
	// @CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
	@RequestMapping(value = "/api/v1/auth/register", method = RequestMethod.POST, headers = "Accept=application/json")
	public ResponseEntity<Void> registerUser(@RequestBody User user) {
		boolean flag;
		try {
			user.setUserAddedDate(new Date());
			flag = userService.saveUser(user);
		} catch (UserAlreadyExistsException e) {
			HttpHeaders headers = new HttpHeaders();
			return new ResponseEntity<Void>(headers, HttpStatus.CONFLICT);
		}
		HttpHeaders headers = new HttpHeaders();
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}
	/*
	 * Define a handler method which will authenticate a user by reading the
	 * Serialized user object from request body containing the username and
	 * password. The username and password should be validated before proceeding
	 * ahead with JWT token generation. The user credentials will be validated
	 * against the database entries. The error should be return if validation is
	 * not successful. If credentials are validated successfully, then JWT token
	 * will be generated. The token should be returned back to the caller along
	 * with the API response. This handler method should return any one of the
	 * status messages basis on different situations: 1. 200(OK) - If login is
	 * successful 2. 401(UNAUTHORIZED) - If login is not successful
	 * 
	 * This handler method should map to the URL "/api/v1/auth/login" using HTTP
	 * POST method
	 */
}
