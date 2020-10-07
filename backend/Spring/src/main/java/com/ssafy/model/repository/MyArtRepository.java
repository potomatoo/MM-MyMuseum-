package com.ssafy.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ssafy.model.dto.MyArtDto;

@Repository
public interface MyArtRepository extends JpaRepository<MyArtDto, Integer> {

	@Query(value = "select * from ssafy.myart where user_id = :user_id limit :start,16", nativeQuery = true)
	List<MyArtDto> findByUserId(@Param("user_id") String UserId, @Param("start") int start);
}
