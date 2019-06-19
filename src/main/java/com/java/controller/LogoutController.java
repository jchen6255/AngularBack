package com.java.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/logout.do")
@CrossOrigin(origins="*")
public class LogoutController {
	
	@GetMapping
	public String logout(HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		session.invalidate();
		return "login";
	}
}
