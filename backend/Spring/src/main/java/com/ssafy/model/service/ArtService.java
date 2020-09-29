package com.ssafy.model.service;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import com.ssafy.model.dto.ArtDto;

@Service
public interface ArtService {

	public List<ArtDto> findArtByTitlelimit(String title, Integer start);

	public List<ArtDto> findArtByArtistlimit(String artist, Integer start);

	public List<ArtDto> findArtByMuseumlimit(String museum, Integer start);

	public List<ArtDto> findArtByGenrelimit(String genre, Integer start);
}
