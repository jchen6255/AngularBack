package com.java.service;

import java.util.List;
import java.util.Set;

import com.java.dto.User;

public interface UserService {
	public User getUser(String username);
	public List<User> getUsers();
	public User updateUser(User user);
	public Set<User> searchUsers(String username);
}
