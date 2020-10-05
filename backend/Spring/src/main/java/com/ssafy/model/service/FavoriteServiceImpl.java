package com.ssafy.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.model.dto.FavoriteDto;
import com.ssafy.model.repository.FavoriteRepository;

@Service
public class FavoriteServiceImpl implements FavoriteService {

	@Autowired
	private FavoriteRepository favoriteRepository;

	@Override
	public List<FavoriteDto> findFavorityById(String userId, int start) {
		// TODO Auto-generated method stub
		int index = start * 16 ;

		return favoriteRepository.findFavoriteByUserlimit(userId, index);
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

}
