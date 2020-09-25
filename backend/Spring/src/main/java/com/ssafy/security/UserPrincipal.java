package com.ssafy.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.ssafy.model.dto.UserDto;

public class UserPrincipal implements UserDetails {
	private UserDto user;

	public UserPrincipal(UserDto user) {
		this.user = user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub\
		// 계정이 가진 권한 목록 반환
		List<GrantedAuthority> authorites = new ArrayList<>();

		authorites.add(new SimpleGrantedAuthority("USER"));

		return authorites;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		// 계정 비밀번호 반환
		return this.user.getUserPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		// 계정 이름 반환
		return this.user.getUserId();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		// 계정 만료되어있는지 반환 (true: 만료 안됨)
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		// 계정 잠겨있는지 반환 (true: 잠기지 않음 )
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		// 계정의 비밀번호 만료상태 반환 (true: 만료 안됨)
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		// 계정이 활성화 되어있는지 반환 (true: 활성화 되어있음)
		return true;
	}

	public int getUserType() {
		return this.user.getUserType();
	}

	public UserDto getUser() {
		return this.user;
	}
}
