package com.ssafy.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ssafy.model.dto.GenreDto;

@Repository
public interface GenreRepository extends JpaRepository<GenreDto, String> {
	public List<GenreDto> findByGenreNameLike(String GenreName);

}
