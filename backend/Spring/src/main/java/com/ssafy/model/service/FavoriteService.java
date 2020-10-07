package com.ssafy.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.model.dto.ArtDto;
import com.ssafy.model.dto.FavoriteDto;

@Service
public interface FavoriteService {
	public List<ArtDto> findFavorityById(String userId, int start);

	public FavoriteDto saveFavorite(FavoriteDto favorite);

	public void deleteFavorite(String userID, int artNo);

	public boolean checkFavorite(String userId, int artNo);
}
