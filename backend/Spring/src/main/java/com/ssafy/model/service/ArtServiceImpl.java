package com.ssafy.model.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.model.dto.ArtDto;
import com.ssafy.model.repository.ArtRepository;

@Service
public class ArtServiceImpl implements ArtService {

	@Autowired
	private ArtRepository artRepository;

	@Override
	public List<ArtDto> findArtByTitlelimit(String title, Integer start) {
		int index = start * 16;

		return artRepository.findArtByTitlelimit(title, index);
	}

	@Override
	public List<ArtDto> findArtByArtistlimit(String artist, Integer start) {
		int index = start * 16;

		return artRepository.findArtByArtistlimit(artist, index);
	}

	@Override
	public List<ArtDto> findArtByMuseumlimit(String museum, Integer start) {
		int index = start * 16;

		return artRepository.findArtByMuseumlimit(museum, index);
	}

	@Override
	public List<ArtDto> findArtByGenrelimit(String genre, Integer start) {
		int index = start * 16;

		return artRepository.findArtByGenrelimit(genre, index);
	}

	@Override
	public ArtDto findArtByArtNo(int artNo) {
		// TODO Auto-generated method stub
		Optional<ArtDto> result = artRepository.findById(artNo);

		if (result.isPresent())
			return result.get();
		else
			return null;
	}

}
