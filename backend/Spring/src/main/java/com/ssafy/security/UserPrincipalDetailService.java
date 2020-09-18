package com.ssafy.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ssafy.model.dto.UserDto;
import com.ssafy.model.repository.UserRepository;

@Service
public class UserPrincipalDetailService implements UserDetailsService {
	private UserRepository userRepository;

	public UserPrincipalDetailService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		UserDto user = this.userRepository.findById(username).get();
		UserPrincipal userPrincipal = new UserPrincipal(user);

		return userPrincipal;
	}

}
