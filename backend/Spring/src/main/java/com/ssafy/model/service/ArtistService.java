package com.ssafy.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.model.dto.ArtistDto;

@Service
public interface ArtistService {
	public List<ArtistDto> findArtistlimit(Integer start);

	public List<ArtistDto> findArtistByArtistNamelimit(String artistName, Integer start);
}
