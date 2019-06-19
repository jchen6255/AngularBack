package com.java.repository;

import java.util.List;

import com.java.dto.User;

public interface UserRepository {
	public User getUserByUsername(String usrname/*, String hashedPassword*/);
	public User getUserById(int userId);
	public List<User> getUsers();
	public User registerUser(User newUser);
	public User updateUser(User currUser);
	public List<User> getUserslikeUsername(String usrname);
}
