package com.ssafy.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ssafy.model.dto.LogDto;

@Repository
public interface LogRepository extends JpaRepository<LogDto, Integer> {

}
