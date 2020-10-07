package com.ssafy.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.model.dto.MuseumDto;

@Service
public interface MuseumService {
	public List<MuseumDto> findMuseumlimit(int start);

	public List<MuseumDto> findMuseumByMuseumNamelimit(String museumName, int start);

	public MuseumDto findMuseumDetail(String museumName);
}
