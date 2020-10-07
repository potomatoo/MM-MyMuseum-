package com.ssafy.controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URLDecoder;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.social.google.connect.GoogleConnectionFactory;
import org.springframework.social.oauth2.GrantType;
import org.springframework.social.oauth2.OAuth2Operations;
import org.springframework.social.oauth2.OAuth2Parameters;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.ssafy.config.GoogleLoginConfig;
import com.ssafy.model.dto.UserDto;
import com.ssafy.model.response.BasicResponse;
import com.ssafy.model.service.UserService;

@Controller
public class GoogleLoginController {
	@Autowired
	private GoogleConnectionFactory googleConnectionFactory;
	@Autowired
	private OAuth2Parameters oAuth2Parameters;

	@Autowired
	private UserService userService;

	@GetMapping("/api/public/google")
	public Object googleSignOnButton() throws URISyntaxException, MalformedURLException, IOException {
		ResponseEntity response = null;
		BasicResponse result = new BasicResponse();

		OAuth2Operations oAuth2Operations = googleConnectionFactory.getOAuthOperations();
		result.data = oAuth2Operations.buildAuthorizeUrl(GrantType.AUTHORIZATION_CODE, oAuth2Parameters);
		result.status = (result.data != null) ? true : false;
		if (result.status) {
			result.message = "Google Login code";
			response = new ResponseEntity(result, HttpStatus.OK);
		} else {
			result.message = "Fall Create Googl Login Code";
			response = new ResponseEntity(result, HttpStatus.BAD_REQUEST);
		}
		return response;
	}

	@GetMapping("/api/public/google/redirect")
	public Object googleSignOnButtonRedirect(HttpServletRequest request, RedirectAttributes rediAttributes) {

		MultiValueMap<String, String> parma = new LinkedMultiValueMap<String, String>();
		parma.add("code", request.getParameter("code"));
		parma.add("client_id", GoogleLoginConfig.GOOGLE_CLIENT_ID);
		parma.add("client_secret", GoogleLoginConfig.GOOGLE_SECRIT_ID);
		parma.add("redirect_uri", "http://j3b205.p.ssafy.io/api/public/google/redirect");
		parma.add("grant_type", "authorization_code");

		HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
		factory.setReadTimeout(5000);
		factory.setConnectTimeout(3000);
		HttpClient httpClient = HttpClientBuilder.create().setMaxConnTotal(100).setMaxConnPerRoute(5).build();
		factory.setHttpClient(httpClient);

		RestTemplate restTemplate = new RestTemplate(factory);
		String url = "https://accounts.google.com/o/oauth2/token";
		String obj = restTemplate.postForEntity(url, parma, String.class).getBody();

		JsonParser jsonParser = new JsonParser();
		JsonObject result = jsonParser.parse(obj).getAsJsonObject();

		String id_token = result.get("id_token").getAsString();
		String access_token = result.get("access_token").getAsString();

		DecodedJWT decodedJwt = JWT.decode(id_token);
		String email = decodedJwt.getClaim("email").asString();

		UserDto user = userService.GoogleLogin(email);

		if (user == null) {
			BasicResponse basicResponse = new BasicResponse();
			basicResponse.data = null;
			basicResponse.status = false;
			basicResponse.message = "이미 가입된 이메일 입니다.";

			return new ResponseEntity<>(basicResponse, HttpStatus.BAD_REQUEST);
		}

		rediAttributes.addAttribute("userId", user.getUserId());
		rediAttributes.addAttribute("userPassword", user.getUserId());
		rediAttributes.addAttribute("type", user.getUserType());

		String redirectUrl = "redirect:/api/public/login";

		return redirectUrl;
	}

	@PostMapping("/api/public/google/login")
	public Object googleLogin(@RequestBody String authToken, RedirectAttributes rediAttributes) {
		MultiValueMap<String, String> parma = new LinkedMultiValueMap<String, String>();
		System.out.println(URLDecoder.decode(authToken));

		parma.add("code", URLDecoder.decode(authToken));
		parma.add("client_id", GoogleLoginConfig.GOOGLE_CLIENT_ID);
		parma.add("client_secret", GoogleLoginConfig.GOOGLE_SECRIT_ID);
		parma.add("redirect_uri", "postmessage");
		parma.add("grant_type", "authorization_code");
		System.out.println(authToken);
		HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
		factory.setReadTimeout(5000);
		factory.setConnectTimeout(3000);
		HttpClient httpClient = HttpClientBuilder.create().setMaxConnTotal(100).setMaxConnPerRoute(5).build();
		factory.setHttpClient(httpClient);

		RestTemplate restTemplate = new RestTemplate(factory);
		String url = "https://accounts.google.com/o/oauth2/token";
		String obj = restTemplate.postForEntity(url, parma, String.class).getBody();

		JsonParser jsonParser = new JsonParser();
		JsonObject result = jsonParser.parse(obj).getAsJsonObject();

		String id_token = result.get("id_token").getAsString();
		String access_token = result.get("access_token").getAsString();

		DecodedJWT decodedJwt = JWT.decode(id_token);
		String email = decodedJwt.getClaim("email").asString();

		UserDto user = userService.GoogleLogin(email);

		if (user == null) {
			BasicResponse basicResponse = new BasicResponse();
			basicResponse.data = null;
			basicResponse.status = false;
			basicResponse.message = "이미 가입된 이메일 입니다.";

			return new ResponseEntity<>(basicResponse, HttpStatus.BAD_REQUEST);
		}

		rediAttributes.addAttribute("userId", user.getUserId());
		rediAttributes.addAttribute("userPassword", user.getUserId() + "google");

		String redirectUrl = "redirect:/api/public/login";

		return redirectUrl;
	}
}
