package com.ssafy.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.model.dto.UserDto;
import com.ssafy.model.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDto Signup(UserDto user) {
		// TODO Auto-generated method stub
		System.out.println(user.toString());
		return userRepository.save(user);
	}

}
