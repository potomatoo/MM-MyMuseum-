package com.ssafy.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.model.dto.UserDto;

@Repository
public interface UserRepository extends JpaRepository<UserDto, String> {
	@Modifying
	@Transactional
	@Query(value = "update ssafy.user set user_name = :user_name where user_id = :user_id", nativeQuery = true)
	public int updateUserName(@Param("user_id") String userId, @Param("user_name") String userName);

	@Query(value = "select * from ssafy.user where user_artist = 3 limit :start,16", nativeQuery = true)
	public List<UserDto> findMyArtist(@Param("start") int start);

	@Modifying
	@Transactional
	@Query(value = "update ssafy.user set user_artist = 3 where user_id = :user_id", nativeQuery = true)
	public int registArtist(@Param("user_id") String userId);
}
