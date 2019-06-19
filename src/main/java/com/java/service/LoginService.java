package com.java.service;

import org.springframework.stereotype.Service;

import com.java.dto.User;

public interface LoginService {
	
	public User login(String username, String password);

}
