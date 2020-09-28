package com.ssafy.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ssafy.model.dto.MuseumDto;

@Repository
public interface MuseumRepository extends JpaRepository<MuseumDto, String> {

	@Query(value = "select * from ssafy.museum limit :start ,16", nativeQuery = true)
	public List<MuseumDto> findMuseumlimit(@Param("start") Integer start);

	@Query(value = "select * from ssafy.museum where museum_name like :museum_name limit :start ,16", nativeQuery = true)
	public List<MuseumDto> findMuseumByMuseumNamelimit(@Param("museum_name") String museumName,
			@Param("start") Integer start);
}
