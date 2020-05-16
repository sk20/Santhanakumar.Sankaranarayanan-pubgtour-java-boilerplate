package com.ust.stackroute.microservice.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

/*
 * The class "User" will be acting as the data model for the User Table in the database. 
 * Please note that this class is annotated with @Entity annotation. 
 * Hibernate will scan all package for any Java objects annotated with the @Entity annotation. 
 * If it finds any, then it will begin the process of looking through that particular 
 * Java object to recreate it as a table in your database.
 */
@Entity
public class User {

	/*
	 * This class should have five fields (userId,firstName,lastName,
	 * userPassword,userRole,userAddedDate). Out of these five fields, the field
	 * userId should be the primary key. This class should also contain the
	 * getters and setters for the fields, along with the no-arg , parameterized
	 * constructor and toString method.The value of userAddedDate should not be
	 * accepted from the user but should be always initialized with the system
	 * date
	 */

	@Id
	private String userId;
	private String userPassword;
	private String firstName;
	private String lastName;
	private String userMobile;
	private Date userAddedDate;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getUserMobile() {
		return userMobile;
	}
	public void setUserRole(String userMobile) {
		this.userMobile = userMobile;
	}
	public Date getUserAddedDate() {
		return userAddedDate;
	}
	public void setUserAddedDate(Date userAddedDate) {
		this.userAddedDate = userAddedDate;
	}
	public User(String userId, String userPassword, String firstName,
			String lastName, String userMobile, Date userAddedDate) {

		this.userId = userId;
		this.userPassword = userPassword;
		this.firstName = firstName;
		this.lastName = lastName;
		this.userMobile = userMobile;
		this.userAddedDate = userAddedDate;
	}
	public User() {

	}

}
