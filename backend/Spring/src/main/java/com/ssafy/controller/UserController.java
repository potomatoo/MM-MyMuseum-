package com.ssafy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.ssafy.model.dto.UserDto;
import com.ssafy.model.response.BasicResponse;
import com.ssafy.model.service.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService userService;
	@Autowired
	private PasswordEncoder passwordEncoder;

	@PostMapping("/api/public/signup")
	public Object signup(@RequestBody UserDto user) {
		BasicResponse response = new BasicResponse();
		user.setUserPassword(passwordEncoder.encode(user.getUserPassword()));
		UserDto result = userService.Signup(user);
		response.data = result;
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
