package com.ssafy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ssafy.model.response.BasicResponse;
import com.ssafy.model.service.MuseumService;

@Controller
public class MuseumController {
	@Autowired
	private MuseumService museumService;

	@GetMapping("/api/public/museum/list")
	public Object getMuseumList(@RequestParam int start) {
		BasicResponse response = new BasicResponse();

		response.data = museumService.findMuseumlimit(start);
		if (response.data != null) {
			response.status = true;
			response.message = "조회에 성공하였습니다.";
		} else {
			response.status = false;
			response.message = "조회에 실패하였습니다.";
		}

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/api/public/museum/find")
	public Object getMuseumList(@RequestParam String museumName, @RequestParam int start) {
		BasicResponse response = new BasicResponse();

		response.data = museumService.findMuseumByMuseumNamelimit('%' + museumName + '%', start);
		if (response.data != null) {
			response.status = true;
			response.message = "조회에 성공하였습니다.";
		} else {
			response.status = false;
			response.message = "조회에 실패하였습니다.";
		}

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/api/public/museum/detail")
	public Object FindMuseumDetail(@RequestParam String museumName) {
		BasicResponse response = new BasicResponse();

		response.data = museumService.findMuseumDetail(museumName);
		response.status = (response.data != null) ? true : false;

		if (response.status) {
			response.message = "조회에 성공하였습니다.";
			return new ResponseEntity<>(response, HttpStatus.OK);
		} else {
			response.message = "조회에 실패하였습니다.";
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
	}
}
