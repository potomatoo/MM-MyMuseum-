package com.ssafy.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.model.dto.MyArtDto;
import com.ssafy.model.dto.UserDto;

@Service
public interface MyArtService {
	public List<UserDto> findArtistList(int start);

	public MyArtDto saveMyart(MyArtDto myart);
}
