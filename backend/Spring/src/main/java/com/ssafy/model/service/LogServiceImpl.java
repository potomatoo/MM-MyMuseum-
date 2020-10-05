package com.ssafy.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.model.dto.LogDto;
import com.ssafy.model.repository.LogRepository;

@Service
public class LogServiceImpl implements LogService {

	@Autowired
	private LogRepository logRepository;

	public boolean SaveLog(LogDto log) {
		LogDto result = logRepository.save(log);
		if (result != null)
			return true;
		else
			return false;
	}
}
