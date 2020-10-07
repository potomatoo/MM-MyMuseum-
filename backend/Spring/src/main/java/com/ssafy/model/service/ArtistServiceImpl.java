package com.ssafy.model.service;

import java.util.List;
import java.util.Optional;

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
		int index = start * 16;

		return artistRepository.findArtistlimit(index);
	}

	@Override
	public List<ArtistDto> findArtistByArtistNamelimit(String artistName, Integer start) {
		int index = start * 16;

		return artistRepository.findArtistByArtistNamelimit(artistName, index);
	}

	@Override
	public ArtistDto findArtistDetail(String artistName) {
		// TODO Auto-generated method stub
		Optional<ArtistDto> artist = artistRepository.findById(artistName);

		if (artist.isPresent())
			return artist.get();
		else
			return null;
	}

}
