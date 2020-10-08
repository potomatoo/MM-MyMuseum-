package com.ssafy.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.model.dto.GenreDto;

@Service
public interface GenreService {
	public List<GenreDto> getGenrelist();

	public List<GenreDto> findGenreByNameLike(String GenreName);

	public GenreDto findGenreDetail(String genreName);
}
