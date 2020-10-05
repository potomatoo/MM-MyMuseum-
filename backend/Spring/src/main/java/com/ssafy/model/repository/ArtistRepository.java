package com.ssafy.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ssafy.model.dto.ArtistDto;
import com.ssafy.model.dto.MuseumDto;

@Repository
public interface ArtistRepository extends JpaRepository<ArtistDto, String> {
	@Query(value = "select * from ssafy.artist limit :start ,16", nativeQuery = true)
	public List<ArtistDto> findArtistlimit(@Param("start") Integer start);

	@Query(value = "select * from ssafy.artist where artist_name like :artist_name limit :start ,16", nativeQuery = true)
	public List<ArtistDto> findArtistByArtistNamelimit(@Param("artist_name") String artistName,
			@Param("start") Integer start);
}
