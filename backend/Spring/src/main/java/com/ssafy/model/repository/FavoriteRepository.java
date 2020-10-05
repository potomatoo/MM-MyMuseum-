package com.ssafy.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.model.dto.FavoriteDto;

@Repository
public interface FavoriteRepository extends JpaRepository<FavoriteDto, Integer> {
	@Query(value = "select * from ssafy.favorite where user_id=:user_id limit :start ,16", nativeQuery = true)
	public List<FavoriteDto> findFavoriteByUserlimit(@Param("user_id") String user_id, @Param("start") Integer start);

	@Modifying
	@Transactional
	@Query(value = "delete from ssafy.favorite where user_id=:user_id and art_no = :art_no", nativeQuery = true)
	public void deleteFavorite(@Param("user_id") String user_id, @Param("art_no") Integer art_no);

}
