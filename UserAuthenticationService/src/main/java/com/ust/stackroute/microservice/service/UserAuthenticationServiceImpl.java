package com.ust.stackroute.microservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ust.stackroute.microservice.exception.UserAlreadyExistsException;
import com.ust.stackroute.microservice.exception.UserNotFoundException;
import com.ust.stackroute.microservice.model.User;
import com.ust.stackroute.microservice.repository.UserAutheticationRepository;



/*
* Service classes are used here to implement additional business logic/validation 
* This class has to be annotated with @Service annotation.
* @Service - It is a specialization of the component annotation. It doesn't currently 
* provide any additional behavior over the @Component annotation, but it's a good idea 
* to use @Service over @Component in service-layer classes because it specifies intent 
* better. Additionally, tool support and additional behavior might rely on it in the 
* future.
* */

@Service
public class UserAuthenticationServiceImpl
		implements
			UserAuthenticationService {

	/*
	 * Autowiring should be implemented for the UserAuthenticationRepository.
	 * (Use Constructor-based autowiring) Please note that we should not create
	 * any object using the new keyword.
	 */

	@Autowired
	private UserAutheticationRepository autheticationRepository;

	/*
	 * This method should be used to validate a user using userId and password.
	 * Call the corresponding method of Respository interface.
	 * 
	 */
	@Override
	public User findByUserIdAndPassword(String userId, String password)
			throws UserNotFoundException {

		User flag = autheticationRepository.findByUserIdAndUserPassword(userId,
				password);
		if (flag != null)
			return flag;
		else {
			throw new UserNotFoundException("User Not Found");
		}

	}

	/*
	 * This method should be used to save a new user.Call the corresponding
	 * method of Respository interface.
	 */

	@Override
	public boolean saveUser(User user) throws UserAlreadyExistsException {

		boolean u = autheticationRepository.findById(user.getUserId())
				.isPresent();
		if (u) {

			throw new UserAlreadyExistsException("User Found ");

		} else {
			User flag = autheticationRepository.save(user);
			if (flag != null)
				return true;
			else
				return false;
		}

	}
}
