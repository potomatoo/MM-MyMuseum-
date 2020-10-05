package com.ssafy.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.model.dto.GenreDto;
import com.ssafy.model.repository.GenreRepository;

@Service
public class GenreServiceImpl implements GenreService {
	@Autowired
	private GenreRepository genreRepository;

	@Override
	public List<GenreDto> getGenrelist() {
		return genreRepository.findAll();
	}

	@Override
	public List<GenreDto> findGenreByNameLike(String GenreName) {
		// TODO Auto-generated method stub
		return genreRepository.findByGenreNameLike('%'+GenreName+'%');
	}

}
