package com.ssafy.controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.model.dto.MyArtDto;
import com.ssafy.model.dto.UserDto;
import com.ssafy.model.response.BasicResponse;
import com.ssafy.model.service.MyArtService;

@Controller
public class MyArtController {

	@Autowired
	private MyArtService myartService;
	@Autowired
	private RedisTemplate<String, Object> redisTemplate;

	private String[] allowFile = { "jpg", "png" };

	@GetMapping("/api/public/myart/artist")
	public Object FindMyArtistList(@RequestParam int start) {
		BasicResponse response = new BasicResponse();

		response.data = myartService.findArtistList(start);
		if (response.data != null) {
			response.status = true;
			response.message = "조회에 성공하였습니다.";
		} else {
			response.status = false;
			response.message = "조회에 실패하였습니다.";
		}

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping("/api/private/myart/upload")
	public Object UploadFile(@RequestHeader("Authorization") String jwtToken, @RequestPart("file") MultipartFile file,
			@RequestPart String title, @RequestPart String type, @RequestPart String description) {
		BasicResponse response = new BasicResponse();

		UserDto user = (UserDto) redisTemplate.opsForValue().get(jwtToken);
		if (user == null) {
			response.status = false;
			response.message = "잘못된 사용자 입니다.";
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}

		if (user.getUserArtist() != 3) {
			response.status = false;
			response.message = "아트스트로 등록되지 않았습니다.";
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}

		String fileName = UUID.randomUUID().toString();
		String fileExtension = FilenameUtils.getExtension(file.getOriginalFilename());

		boolean allow = false;

		for (int i = 0; i < allowFile.length; i++) {
			if (fileExtension.equals(allowFile[i]))
				allow = true;
		}

		if (!allow) {
			response.status = false;
			response.message = "허용되지 않은 파일 확장자 입니다.(사용가능 확장자 png,jpg)";
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
		String path = fileName + '.' + fileExtension;
		try {
			file.transferTo(new File("/mymuseum/" + path));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response.status = false;
			response.message = "파일 업로드에 실패하였습니다.";
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}

		MyArtDto myart = new MyArtDto();

		myart.setMyartTitle(title);
		myart.setUserId(user.getUserId());
		myart.setMyartAge(new Date());
		myart.setMyartDescription(description);
		myart.setMyartType(type);
		myart.setMyartUrl("http://j3b205.p.ssafy.io/file/" + path);

		response.data = myartService.saveMyart(myart);
		response.status = (response.data != null) ? true : false;
		if (response.status) {
			response.message = "그림 등록에 성공하였습니다.";
			return new ResponseEntity<>(response, HttpStatus.OK);
		} else {
			response.message = "그림 등록에 실패하였습니다.";
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}

	}

}
