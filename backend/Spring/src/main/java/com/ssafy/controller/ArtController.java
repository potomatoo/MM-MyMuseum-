package com.ssafy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import com.ssafy.model.dto.LogDto;
import com.ssafy.model.dto.UserDto;
import com.ssafy.model.response.BasicResponse;
import com.ssafy.model.service.ArtService;
import com.ssafy.model.service.LogService;

@Controller
public class ArtController {
	@Autowired
	private ArtService artService;
	@Autowired
	private LogService logService;
	@Autowired
	private RedisTemplate<String, Object> redisTemplate;

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

//	@GetMapping("/api/public/art/detail")
//	public Object findArtDetail(@RequestParam int artNo) {
//		BasicResponse response = new BasicResponse();
//
//		response.data = artService.findArtByArtNo(artNo);
//		if (response.data != null) {
//			response.status = true;
//			response.message = "조회에 성공하였습니다.";
//		} else {
//			response.status = false;
//			response.message = "조회에 실패하였습니다.";
//		}
//
//		return new ResponseEntity<>(response, HttpStatus.OK);
//	}

	@GetMapping("/api/public/art/detail")
	public Object findArtDetail(@RequestHeader("Authorization") String jwtToken, @RequestParam int artNo,
			@RequestParam int type) {
		BasicResponse response = new BasicResponse();

		UserDto user = (UserDto) redisTemplate.opsForValue().get(jwtToken);
		if (user == null) {
			System.out.println("사용자 정보가 없습니다.");
		} else {
			LogDto log = new LogDto();

			log.setUser_id(user.getUserId());
			log.setType(type);
			log.setArtNo(artNo);

			logService.SaveLog(log);
		}

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
