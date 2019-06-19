package com.java.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.dto.User;
import com.java.repository.UserRepository;

@Service
public class LoginServiceImpl implements LoginService{

	@Autowired UserRepository rep;
	@Override
	public User login(String username, String password) {
		return rep.getUserByUsername(username);
	}

}
