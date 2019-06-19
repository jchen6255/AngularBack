package com.java.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.java.dto.User;
import com.java.main.Project2;
import com.java.service.LoginService;

@RestController
@CrossOrigin(origins="*")
public class LoginController {
	static Logger logger= Logger.getLogger(LoginController.class);
	@Autowired
	private LoginService service;

	@PostMapping(value = "/login.do")
	public User login(HttpServletRequest request, HttpServletResponse response, @RequestBody User user) {
		User currUser = null;
		currUser = service.login(user.getUsername(), user.getPassword());
		
		if(currUser!=null) {
			System.out.println("currUser = "+ currUser.getUsername());
		}
		System.out.println("user = "+ user.getUsername());
		MD5 encryption= new MD5();
		if (currUser != null && user.getUsername().equals(currUser.getUsername())
				&& encryption.encrypt(user.getPassword()).equals(currUser.getPassword())
				) {
			request.getSession().setAttribute("User", currUser);
			logger.info(currUser.getUsername()+" logged in!");
			return currUser;
		}else {
			logger.info("Invalid login");
			return new User();
		}
		//return null;
	}
}
