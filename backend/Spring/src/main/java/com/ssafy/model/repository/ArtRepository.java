package com.ssafy.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ssafy.model.dto.ArtDto;

@Repository
public interface ArtRepository extends JpaRepository<ArtDto, Integer> {

	@Query(value = "select * from ssafy.art where art_title like :title limit :start ,16", nativeQuery = true)
	public List<ArtDto> findArtByTitlelimit(@Param("title") String title, @Param("start") Integer start);

	@Query(value = "select * from ssafy.art where art_artist = :artist limit :start ,16", nativeQuery = true)
	public List<ArtDto> findArtByArtistlimit(@Param("artist") String artist, @Param("start") Integer start);

	@Query(value = "select * from ssafy.art where art_museum = :museum limit :start ,16", nativeQuery = true)
	public List<ArtDto> findArtByMuseumlimit(@Param("museum") String museum, @Param("start") Integer start);

	@Query(value = "select * from ssafy.art where art_genre = :genre limit :start ,16", nativeQuery = true)
	public List<ArtDto> findArtByGenrelimit(@Param("genre") String genre, @Param("start") Integer start);

	@Query(value = "select * from ssafy.art where art_no in (select art_no from ssafy.favorite where user_id = :user_id) limit :start ,16", nativeQuery = true)
	public List<ArtDto> findArtByFavorite(@Param("user_id") String userId, @Param("start") Integer start);

}
