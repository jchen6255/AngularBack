package com.java.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.dto.User;
import com.java.repository.UserRepository;

@Service
public class RegisterServiceImpl implements RegisterService{

	@Autowired UserRepository rep;
	@Override
	public User addUser(User user) {
		return rep.registerUser(user);
	}

}
