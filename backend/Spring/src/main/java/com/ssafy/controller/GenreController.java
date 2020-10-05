package com.ssafy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ssafy.model.response.BasicResponse;
import com.ssafy.model.service.GenreService;

@Controller
public class GenreController {
	@Autowired
	private GenreService genreService;

	@GetMapping("/api/public/genre/list")
	public Object getAllGenre() {
		BasicResponse response = new BasicResponse();

		response.data = genreService.getGenrelist();
		if (response.data != null) {
			response.status = true;
			response.message = "조회에 성공하였습니다.";
		} else {
			response.status = false;
			response.message = "조회에 실패하였습니다.";
		}

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/api/public/genre/find")
	public Object findGenreByName(@RequestParam String GenreName) {
		BasicResponse response = new BasicResponse();

		response.data = genreService.findGenreByNameLike(GenreName);
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
