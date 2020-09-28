package com.ssafy.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.model.dto.ArtistDto;
import com.ssafy.model.repository.ArtistRepository;

@Service
public class ArtistServiceImpl implements ArtistService {

	@Autowired
	private ArtistRepository artistRepository;

	@Override
	public List<ArtistDto> findArtistlimit(Integer start) {
		int index = start * 16 + 1;

		return artistRepository.findArtistlimit(index);
	}

	@Override
	public List<ArtistDto> findArtistByArtistNamelimit(String artistName, Integer start) {
		int index = start * 16 + 1;

		return artistRepository.findArtistByArtistNamelimit(artistName, index);
	}

}
