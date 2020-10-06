package com.ssafy.model.service;

import org.springframework.stereotype.Service;

import com.ssafy.model.dto.UserDto;

@Service
public interface UserService {
	public UserDto Signup(UserDto user);

	public UserDto GoogleLogin(String email);

	public boolean CheckEmail(String email);

	public UserDto UpdateUserName(String userId, String userName);

	public UserDto RegistArtist(String userId);
}
