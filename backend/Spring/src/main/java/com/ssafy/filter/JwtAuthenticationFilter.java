package com.ssafy.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.google.gson.Gson;
import com.ssafy.config.JwtProperties;
import com.ssafy.model.dto.LoginRequestDto;
import com.ssafy.model.dto.UserDto;
import com.ssafy.model.response.BasicResponse;
import com.ssafy.security.UserPrincipal;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	RedisTemplate<String, Object> redisTemplate;

	public void setRedisTemplate(RedisTemplate<String, Object> redisTemplate) {
		this.redisTemplate = redisTemplate;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		// TODO Auto-generated method stub

		LoginRequestDto loginreq = new LoginRequestDto();

		String id = request.getParameter("userId");
		String password = request.getParameter("userPassword");

		if (id == null)
			id = "";
		if (password == null)
			password = "";

		loginreq.setUserId(id);
		loginreq.setPassword(password);
		
		System.out.println(loginreq.toString());
		
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
				loginreq.getUserId(), loginreq.getPassword(), new ArrayList<>());

		return this.getAuthenticationManager().authenticate(authenticationToken);
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		// TODO Auto-generated method stub
		
		UserPrincipal principal = (UserPrincipal) authResult.getPrincipal();
		String token = JWT.create().withSubject(principal.getUsername())
				.withExpiresAt(new Date(System.currentTimeMillis() + JwtProperties.EXPIRATION_TIME))
				.sign(Algorithm.HMAC512(JwtProperties.SECRET.getBytes()));

		response.addHeader(JwtProperties.HEADER_STRING, JwtProperties.TOKEN_PREFIX + token);
		this.redisTemplate.opsForValue().set(JwtProperties.TOKEN_PREFIX + token, principal.getUser());
		// @formatter:off
		BasicResponse result = new BasicResponse();

		result.status = true;
		result.message = "login success";

		UserDto user = principal.getUser();
		user.setUserPassword(JwtProperties.TOKEN_PREFIX + token);

		result.data = user;

		response.getWriter().write(new Gson().toJson(result));

		// @formatter:on
		response.getWriter().flush();
		response.getWriter().close();
	}

}
