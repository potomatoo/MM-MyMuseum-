package com.ssafy.model.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ssafy.model.dto.MyArtDto;
import com.ssafy.model.dto.UserDto;

@Repository
public interface MyArtRepository extends JpaRepository<MyArtDto, Integer> {

	@Query(value = "select * from ssafy.myart where user_id = :user_id limit :start,16", nativeQuery = true)
	List<MyArtDto> findByUserId(@Param("user_id") String UserId, @Param("start") int start);

	@Query(value = "select myart_no,myart_title,(select user_name from ssafy.user where user_id = a.user_id) as user_id, myart_age,myart_type,myart_url,myart_description  from ssafy.myart a where myart_no = :myart_no", nativeQuery = true)
	public Optional<MyArtDto> findMyartDetail(@Param("myart_no") int myartNo);

}
