package com.ssafy.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.social.google.connect.GoogleConnectionFactory;
import org.springframework.social.oauth2.OAuth2Parameters;

@Configuration
public class GoogleLoginConfig {
	public static final String GOOGLE_CLIENT_ID = "110473550923-l77b5sv8d1vh1mnuvbrsrljqc3ttgd6u.apps.googleusercontent.com";
	public static final String GOOGLE_SECRIT_ID = "WjZFPZx5DqT7nab5-JCLxNWC";

	@Bean
	GoogleConnectionFactory googleConnectionFactory() {
		return new GoogleConnectionFactory(GOOGLE_CLIENT_ID, GOOGLE_SECRIT_ID);
	}

	@Bean
	OAuth2Parameters oAuth2Parameters() {
		OAuth2Parameters oAuth2Parameters = new OAuth2Parameters();
		oAuth2Parameters.setRedirectUri("http://j3b205.p.ssafy.io/api/public/google/redirect");
		oAuth2Parameters.setScope(
				"https://www.googleapis.com/auth/userinfo.profile https://www.googleapis.com/auth/userinfo.email openid");
		return oAuth2Parameters;
	}

}
