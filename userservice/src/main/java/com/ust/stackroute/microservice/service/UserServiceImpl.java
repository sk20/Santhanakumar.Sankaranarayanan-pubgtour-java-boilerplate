package com.ust.stackroute.microservice.service;

import java.util.Optional;
import org.springframework.stereotype.Service;

import com.ust.stackroute.microservice.exception.UserAlreadyExistsException;
import com.ust.stackroute.microservice.exception.UserNotFoundException;
import com.ust.stackroute.microservice.model.User;
import com.ust.stackroute.microservice.repository.UserRepository;


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
public class UserServiceImpl implements UserService {

	/*
	 * Autowiring should be implemented for the UserRepository. (Use
	 * Constructor-based autowiring) Please note that we should not create any
	 * object using the new keyword.
	 */
	private UserRepository userRepository;

	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	/*
	 * This method should be used to save a new user.Call the corresponding method
	 * of Respository interface.
	 */

	public User registerUser(User user) throws UserAlreadyExistsException {
		User currentUser = this.userRepository.insert(user);
		if (currentUser != null) {
			return currentUser;
		} else {
			throw new UserAlreadyExistsException("User was already found");
		}
	}

	/*
	 * This method should be used to update a existing user.Call the corresponding
	 * method of Respository interface.
	 */

	public User updateUser(String userId,User user) throws UserNotFoundException {
		userRepository.save(user);
		return userRepository.findById(userId).get();
	}

	/*
	 * This method should be used to delete an existing user. Call the corresponding
	 * method of Respository interface.
	 */

	public boolean deleteUser(String userId) throws UserNotFoundException {
		User currentUser = this.getUserById(userId);
		if (currentUser != null) {
			try {
				this.userRepository.delete(currentUser);
				return true;
			} catch (Exception e) {
				throw e;
			}
		} else {
			throw new UserNotFoundException("User was not found");
		}
	}

	/*
	 * This method should be used to get a user by userId.Call the corresponding
	 * method of Respository interface.
	 */

	public User getUserById(String userId) throws UserNotFoundException {
		Optional<User> result = this.userRepository.findById(userId);
		return result.orElseThrow(() -> new UserNotFoundException(userId));
	}

}
