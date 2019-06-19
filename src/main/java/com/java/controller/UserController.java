package com.java.controller;

import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.java.dto.User;
import com.java.service.UserService;
import com.java.util.UploadUtil;

@RestController
@CrossOrigin(origins = "*")
public class UserController {

	@Autowired
	UserService service;
	@Autowired
	UploadUtil util;

	@GetMapping("/getUser.do")
	public User getUser(@RequestParam String username) {
		User user = null;
		user = service.getUser(username);
		if (user != null)
			return user;
		else
			return new User();
	}

	@GetMapping("/getUsers.do")
	public List<User> getUsers() {
		List<User> list = null;
		list = service.getUsers();
		return list;
	}

	// Test this method
	@PostMapping("/updateUser.do")
	public User updateUser(@RequestParam(required = false) MultipartFile file, @ModelAttribute User user,
			HttpServletRequest request) {
		System.out.println(user);
		if (file != null)
			user.setProfilePic(util.uploadFile(file));

		return service.updateUser(user);
	}

	@GetMapping("/searchUsers.do")
	public Set<User> searchUsers(@RequestParam String username) {
		return service.searchUsers(username);
	}
}
