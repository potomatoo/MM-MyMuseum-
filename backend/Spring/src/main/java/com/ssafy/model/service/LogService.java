package com.ssafy.model.service;

import org.springframework.stereotype.Service;

import com.ssafy.model.dto.LogDto;

@Service
public interface LogService {
	public boolean SaveLog(LogDto log);
}
