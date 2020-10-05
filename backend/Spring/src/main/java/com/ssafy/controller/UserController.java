package com.ssafy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import com.ssafy.model.dto.UserDto;
import com.ssafy.model.response.BasicResponse;
import com.ssafy.model.service.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService userService;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private RedisTemplate<String, Object> redisTemplate;

	@PostMapping("/api/public/signup")
	public Object signup(@RequestBody UserDto user) {
		BasicResponse response = new BasicResponse();
		user.setUserPassword(passwordEncoder.encode(user.getUserPassword()));
		UserDto result = userService.Signup(user);
		response.data = result;
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/api/private/user/detail")
	public Object UserInof(@RequestHeader("Authorization") String jwtToken) {
		BasicResponse response = new BasicResponse();

		UserDto user = (UserDto) redisTemplate.opsForValue().get(jwtToken);
		if (user == null) {
			response.status = false;
			response.message = "잘못된 사용자 입니다.";
			return new ResponseEntity<>(response, HttpStatus.OK);
		}

		user.setUserPassword("");
		response.data = user;
		response.status = true;
		response.message = "사용자 정보입니다.";

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/api/public/user/checkemail")
	public Object CheckEmamil(@RequestParam("email") String email) {
		BasicResponse response = new BasicResponse();

		response.status = userService.CheckEmail(email);

		if (response.status) {
			response.message = "사용 가능한 이메일 입니다.";
			return new ResponseEntity<>(response, HttpStatus.OK);
		} else {
			response.message = "중복된 이메일 입니다.";
			return new ResponseEntity<>(response, HttpStatus.OK);

		}
	}

}
