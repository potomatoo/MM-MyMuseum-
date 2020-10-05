package com.ssafy.model.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ssafy.model.dto.UserDto;
import com.ssafy.model.repository.UserRepository;

import ch.qos.logback.core.encoder.Encoder;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PasswordEncoder encoder;

	@Override
	public UserDto Signup(UserDto user) {
		// TODO Auto-generated method stub
		return userRepository.save(user);
	}

	@Override
	public UserDto GoogleLogin(String userId) {
		// TODO Auto-generated method stub
		Optional<UserDto> user = userRepository.findById(userId);

		if (user.isPresent())
			return user.get();
		else {
			UserDto newUser = new UserDto();

			newUser.setUserId(userId);
			newUser.setUserPassword(encoder.encode(userId + "google"));
			newUser.setUserName(userId.split("@")[0]);
			newUser.setUserType(1);

			return userRepository.save(newUser);
		}
	}

	@Override
	public boolean CheckEmail(String email) {
		// TODO Auto-generated method stub

		Optional<UserDto> user = userRepository.findById(email);

		return !user.isPresent();
	}

	@Override
	public UserDto UpdateUserName(String userId, String userName) {
		// TODO Auto-generated method stub
		int result = userRepository.updateUserName(userId, userName);
		if (result != 0) {
			UserDto user = userRepository.findById(userId).get();
			user.setUserPassword("");
			return user;
		}
		return null;
	}

}
