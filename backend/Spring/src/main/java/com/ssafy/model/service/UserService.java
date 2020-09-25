package com.ssafy.model.service;

import org.springframework.stereotype.Service;

import com.ssafy.model.dto.UserDto;

@Service
public interface UserService {
	public UserDto Signup(UserDto user);
}
