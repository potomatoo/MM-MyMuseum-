package com.ssafy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ssafy.model.response.BasicResponse;
import com.ssafy.model.service.ArtService;

@Controller
public class ArtController {
	@Autowired
	private ArtService artService;

	@GetMapping("/api/public/art/title")
	public Object findArtByTitlelimit(@RequestParam String title, @RequestParam int start) {
		BasicResponse response = new BasicResponse();

		response.data = artService.findArtByTitlelimit('%' + title + '%', start);
		if (response.data != null) {
			response.status = true;
			response.message = "조회에 성공하였습니다.";
		} else {
			response.status = false;
			response.message = "조회에 실패하였습니다.";
		}

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/api/public/art/artist")
	public Object findArtByArtistlimit(@RequestParam String artist, @RequestParam Integer start) {
		BasicResponse response = new BasicResponse();

		response.data = artService.findArtByArtistlimit(artist, start);
		if (response.data != null) {
			response.status = true;
			response.message = "조회에 성공하였습니다.";
		} else {
			response.status = false;
			response.message = "조회에 실패하였습니다.";
		}

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/api/public/art/museum")
	public Object findArtByMuseumlimit(@RequestParam String museum, @RequestParam Integer start) {
		BasicResponse response = new BasicResponse();

		response.data = artService.findArtByMuseumlimit(museum, start);
		if (response.data != null) {
			response.status = true;
			response.message = "조회에 성공하였습니다.";
		} else {
			response.status = false;
			response.message = "조회에 실패하였습니다.";
		}

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/api/public/art/genre")
	public Object findArtByGenrelimit(@RequestParam String genre, @RequestParam Integer start) {
		BasicResponse response = new BasicResponse();

		response.data = artService.findArtByGenrelimit(genre, start);
		if (response.data != null) {
			response.status = true;
			response.message = "조회에 성공하였습니다.";
		} else {
			response.status = false;
			response.message = "조회에 실패하였습니다.";
		}

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/api/public/art/detail")
	public Object findArtByGenrelimit(@RequestParam int artNo) {
		BasicResponse response = new BasicResponse();

		response.data = artService.findArtByArtNo(artNo);
		if (response.data != null) {
			response.status = true;
			response.message = "조회에 성공하였습니다.";
		} else {
			response.status = false;
			response.message = "조회에 실패하였습니다.";
		}

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}
