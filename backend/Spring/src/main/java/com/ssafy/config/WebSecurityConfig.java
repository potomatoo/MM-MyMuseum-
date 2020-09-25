package com.ssafy.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.ssafy.filter.JwtAuthenticationFilter;
import com.ssafy.filter.JwtAuthorizationFilter;
import com.ssafy.model.repository.UserRepository;
import com.ssafy.security.UserPrincipalDetailService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private UserPrincipalDetailService userPrincipalDetailsService;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RedisTemplate<String, Object> redisTemplate;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
	}

	@Bean
	DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
		daoAuthenticationProvider.setUserDetailsService(this.userPrincipalDetailsService);

		return daoAuthenticationProvider;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// @formatter:off
		http.formLogin().disable();
		http.csrf().disable()// rest api이므로 csrf보안이 필요없음
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)// jwt 토큰 사용으로 세션이 필요 없음
				.and().cors().and().addFilterAt(customfilter(), UsernamePasswordAuthenticationFilter.class)
				.addFilter(new JwtAuthorizationFilter(authenticationManager(), this.userRepository)).authorizeRequests()
				.antMatchers("/swagger-ui.html").permitAll().antMatchers("/swagger-resources/**").permitAll()
				.antMatchers("/v2/api-docs").permitAll().antMatchers("/webjars/**").permitAll()
				.antMatchers("/swagger/**").permitAll().antMatchers("/api/public/**").permitAll().anyRequest()
				.authenticated();

		// 로그아웃 처리
		http.logout().invalidateHttpSession(true).clearAuthentication(true)
				.logoutRequestMatcher(new AntPathRequestMatcher("/api/user/logout")).logoutSuccessUrl("/index")
				.permitAll();
		// @formatter:on
	}

	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();

		configuration.addAllowedOriginPattern("*");

		configuration.addAllowedHeader("*");
		configuration.addAllowedMethod("*");
		configuration.setAllowCredentials(true);

		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}

	protected JwtAuthenticationFilter customfilter() {
		JwtAuthenticationFilter authfilter = new JwtAuthenticationFilter();

		try {
			authfilter.setFilterProcessesUrl("/api/public/login");
			authfilter.setAuthenticationManager(this.authenticationManagerBean());
			authfilter.setRedisTemplate(this.redisTemplate);
			authfilter.setUsernameParameter("userId");
			authfilter.setPasswordParameter("userPassword");
		} catch (Exception e) {
			// TODO: handle exception
		}

		return authfilter;
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
