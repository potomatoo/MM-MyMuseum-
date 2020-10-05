package com.ssafy.model.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.model.dto.MuseumDto;
import com.ssafy.model.repository.MuseumRepository;

@Service
public class MuseumServiceImpl implements MuseumService {

	@Autowired
	private MuseumRepository museumRepository;

	@Override
	public List<MuseumDto> findMuseumlimit(int start) {
		// TODO Auto-generated method stub
		int index = start * 16;

		return museumRepository.findMuseumlimit(index);
	}

	@Override
	public List<MuseumDto> findMuseumByMuseumNamelimit(String museumName, int start) {
		// TODO Auto-generated method stub
		int index = start * 16;

		return museumRepository.findMuseumByMuseumNamelimit(museumName, index);
	}

	@Override
	public MuseumDto findMuseumDetail(String museumName) {
		// TODO Auto-generated method stub

		Optional<MuseumDto> museum = museumRepository.findById(museumName);

		if (museum.isPresent())
			return museum.get();
		else
			return null;
	}

}
