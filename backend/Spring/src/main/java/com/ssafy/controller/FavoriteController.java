package com.ssafy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import com.ssafy.model.dto.FavoriteDto;
import com.ssafy.model.dto.UserDto;
import com.ssafy.model.response.BasicResponse;
import com.ssafy.model.service.FavoriteService;

@Controller
public class FavoriteController {
	@Autowired
	private FavoriteService favoriteService;

	@Autowired
	private RedisTemplate<String, Object> redisTemplate;

	@GetMapping("/api/private/favorite/list")
	public Object FindFavorite(@RequestHeader("Authorization") String jwtToken, @RequestParam("start") int start) {
		BasicResponse response = new BasicResponse();

		UserDto user = (UserDto) redisTemplate.opsForValue().get(jwtToken);
		if (user == null) {
			response.status = false;
			response.message = "잘못된 사용자 입니다.";
			return new ResponseEntity<>(response, HttpStatus.OK);
		}

		response.data = favoriteService.findFavorityById(user.getUserId(), start);

		if (response.data != null) {
			response.status = true;
			response.message = "조회에 성공하였습니다.";
		} else {
			response.status = false;
			response.message = "조회에 실패하였습니다.";
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping("/api/private/favorite/save")
	public Object SaveFavorite(@RequestHeader("Authorization") String jwtToken, @RequestBody FavoriteDto favorite) {
		BasicResponse response = new BasicResponse();

		UserDto user = (UserDto) redisTemplate.opsForValue().get(jwtToken);
		if (user == null) {
			response.status = false;
			response.message = "잘못된 사용자 입니다.";
			return new ResponseEntity<>(response, HttpStatus.OK);
		}

		favorite.setUserId(user.getUserId());
		response.data = favoriteService.saveFavorite(favorite);
		if (response.data != null) {
			response.status = true;
			response.message = "저장에 성공하였습니다.";
		} else {
			response.status = false;
			response.message = "저장에 실패하였습니다.";
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@DeleteMapping("/api/private/favorite/delete")
	public Object DeleteFavorite(@RequestHeader("Authorization") String jwtToken, @RequestParam("artNo") int artNo) {
		BasicResponse response = new BasicResponse();

		UserDto user = (UserDto) redisTemplate.opsForValue().get(jwtToken);
		if (user == null) {
			response.status = false;
			response.message = "잘못된 사용자 입니다.";
			return new ResponseEntity<>(response, HttpStatus.OK);
		}

		favoriteService.deleteFavorite(user.getUserId(), artNo);

		response.status = true;
		response.message = "삭제에 성공하였습니다.";
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/api/private/favorite/check")
	public Object CheckFavorite(@RequestHeader("Authorization") String jwtToken, @RequestParam("artNo") int artNo) {
		BasicResponse response = new BasicResponse();

		UserDto user = (UserDto) redisTemplate.opsForValue().get(jwtToken);
		if (user == null) {
			response.status = false;
			response.message = "잘못된 사용자 입니다.";
			return new ResponseEntity<>(response, HttpStatus.OK);
		}

		response.data = favoriteService.checkFavorite(user.getUserId(), artNo);

		response.status = true;
		response.message = "조회에 성공하였습니다.";
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
