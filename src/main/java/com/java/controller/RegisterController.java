package com.java.controller;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.java.dto.User;
import com.java.service.RegisterService;

@RestController
//@RequestMapping("register.do")
@CrossOrigin(origins="*")
public class RegisterController {

	static Logger logger = Logger.getLogger(RegisterController.class);
	@Autowired
	private RegisterService service;

	@PostMapping("register.do")
	public String register(@RequestBody User user, HttpServletResponse response) {
		System.out.println("xdd");
		return "{ii : ss}";
	}
	
	
	
//	@PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE,
//			MediaType.APPLICATION_XML_VALUE }, produces = "application/json")
//	public String registerUser(@RequestBody User user, HttpServletResponse response) {
//
//		// validate email
//		Pattern p = Pattern.compile(
//				"[a-zA-Z0-9[!#$%&'()*+,/\\-_\\.\"]]+@[a-zA-Z0-9[!#$%&'()*+,/\\-_\"]]+\\.[a-zA-Z0-9[!#$%&'()*+,/\\-_\"\\.]]+");
//		Matcher m = p.matcher(user.getEmail());
//		if (!m.matches()) {
//			return "{\"Status\":\"Fail\"}";
//		}
//
//		// validate password
//		if (user.getPassword().length() < 8 || user.getPassword().length() > 30) {
//			return "{\"Status\":\"Fail\"}";
//		}
//		User newUser = service.addUser(user);
//		if (newUser != null) {
//			logger.info("Register successful");
//			return "{\"Status\":\"Success\"}";
//		}
//		else {
//			logger.info("Register fail");
//			return "{\"Status\":\"Fail\"}";
//		}
//	}

}
