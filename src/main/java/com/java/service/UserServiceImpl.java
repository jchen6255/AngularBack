package com.java.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.dto.User;
import com.java.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{

	@Autowired UserRepository rep;
	@Override
	public User getUser(String username) {
		return rep.getUserByUsername(username);
	}

	@Override
	public List<User> getUsers() {
		return rep.getUsers();
	}

	@Override
	public User updateUser(User user) {
		return rep.updateUser(user);
	}

	@Override
	public Set<User> searchUsers(String username) {
		List<User> list = rep.getUserslikeUsername(username);
		return list.stream().collect(Collectors.toCollection(HashSet::new));
	}

}
