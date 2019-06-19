package com.java.controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.java.dto.User;
import com.java.service.UserService;

@RestController
@RequestMapping("/reset_request.do")
@CrossOrigin(origins = "*")
public class ResetController {
	
	static Logger logger = Logger.getLogger(ResetController.class);
	@Autowired
	private UserService service;

	@PostMapping(consumes={MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE},produces="application/json")
	public String checkUser(@RequestBody User user, HttpServletResponse response) {
		User u= null;
		
		//Email and Username passed in
		// validate email
		Pattern p = Pattern.compile(
				"[a-zA-Z0-9[!#$%&'()*+,/\\-_\\.\"]]+@[a-zA-Z0-9[!#$%&'()*+,/\\-_\"]]+\\.[a-zA-Z0-9[!#$%&'()*+,/\\-_\"\\.]]+");
		Matcher m = p.matcher(user.getEmail());
		if (!m.matches()) {
			return "{\"Status\":\"Fail\"}";
		}

		u= service.getUser(user.getUsername());
		
		
		//Valid user
		if(u!=null && u.getEmail().equals(user.getEmail())) {
			logger.info("Password Reset");
			//set temporary password
			PasswordGenerator passwordGenerator = new PasswordGenerator.PasswordGeneratorBuilder()
			        .useDigits(true)
			        .useLower(true)
			        .useUpper(true)
			        .usePunctuation(true)
			        .build();
			String password = passwordGenerator.generate(8);
			System.out.println(password);
			u.setPassword(password);
			//update user password
			service.updateUser(u);
			
			AmazonSES ases= new AmazonSES();
//			update email send instruction
			ases.setResetEmail(u);
			try {
				ases.sendResetEmail();
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				System.out.println("Messaging exception");
			}
			
			return "{\"Status\":\"Processing Request...\"}";
		}
		
		logger.info("Password Reset Fail");
		return "{\"Status\":\"Processing Request...\"}";
	}
}
