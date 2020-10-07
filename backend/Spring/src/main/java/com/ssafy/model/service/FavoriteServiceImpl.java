package com.ssafy.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.model.dto.ArtDto;
import com.ssafy.model.dto.FavoriteDto;
import com.ssafy.model.repository.ArtRepository;
import com.ssafy.model.repository.FavoriteRepository;

@Service
public class FavoriteServiceImpl implements FavoriteService {

	@Autowired
	private FavoriteRepository favoriteRepository;
	@Autowired
	private ArtRepository ArtRepository;

	@Override
	public List<ArtDto> findFavorityById(String userId, int start) {
		// TODO Auto-generated method stub
		int index = start * 16;

		return ArtRepository.findArtByFavorite(userId, start);
	}

	@Override
	public FavoriteDto saveFavorite(FavoriteDto favorite) {
		// TODO Auto-generated method stub

		return favoriteRepository.save(favorite);
	}

	@Override
	public void deleteFavorite(String userId, int artNo) {
		// TODO Auto-generated method stub
		favoriteRepository.deleteFavorite(userId, artNo);
	}

	@Override
	public boolean checkFavorite(String userId, int artNo) {
		// TODO Auto-generated method stub
		FavoriteDto favorite = favoriteRepository.checkFavorite(userId, artNo);

		if (favorite == null)
			return false;
		else
			return true;
	}

}
