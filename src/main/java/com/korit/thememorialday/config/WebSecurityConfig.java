package com.korit.thememorialday.config;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.annotation.web.configurers.HttpBasicConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import com.korit.thememorialday.dto.response.ResponseCode;
import com.korit.thememorialday.dto.response.ResponseMessage;
import com.korit.thememorialday.filter.JwtAuthenticationFilter;
import com.korit.thememorialday.handler.OAuth2SuccessHandler;
import com.korit.thememorialday.service.implement.OAuth2UserServiceImplement;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
//# Spring Web 보안 설정

@Configurable
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

	private final JwtAuthenticationFilter jwtAuthenticationFilter;
	private final OAuth2UserServiceImplement oAuth2UserServiceImplement;
	private final OAuth2SuccessHandler oAuth2SuccessHandler;

	@Bean
	protected SecurityFilterChain configure(HttpSecurity security) throws Exception {
		security
			.httpBasic(HttpBasicConfigurer::disable)
			.sessionManagement(sessionManagement -> sessionManagement
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
			.csrf(CsrfConfigurer::disable)
			// configurationSource 메서드 만든 후 적용
			.cors(cors -> cors.configurationSource(configurationSource()))
			.authorizeHttpRequests(request -> request
				.requestMatchers("/api/v1/auth/**", "/support/notice/**", "/stores/**", "/file/*", "/**").permitAll()
				.anyRequest().authenticated()
			)
			.exceptionHandling(exception -> exception
				// AuthenticationFailEntryPoint 만든 후 적용
				.authenticationEntryPoint(new AuthenticationFailEntryPoint())
			)

			//* OAuth2 로그인 적용
			.oauth2Login(oauth2 -> oauth2
				.redirectionEndpoint(endpoint -> endpoint.baseUri("/oauth2/callback/*"))
				.authorizationEndpoint(endpoint -> endpoint.baseUri("/api/v1/auth/sns-sign-in"))
				// OAuth 서비스 만든 후 적용
				.userInfoEndpoint(endpoint -> endpoint.userService(oAuth2UserServiceImplement))
				.successHandler(oAuth2SuccessHandler)
			)

			.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

			return security.build();
	}

	// CORS 설정
	protected CorsConfigurationSource configurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.addAllowedOrigin("*");
		configuration.addAllowedHeader("*");
		configuration.addAllowedMethod("*");

		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);

		return source;
	}

	// 인증 및 인가 작업 중 발생하는 예외 처리
	class AuthenticationFailEntryPoint implements AuthenticationEntryPoint {

		@Override
		public void commence(HttpServletRequest request, HttpServletResponse response,
				AuthenticationException authException) throws IOException, ServletException {
			
			authException.printStackTrace();
			response.setContentType("application/json");
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			response.getWriter()
					.write("{\"code\": \"" + ResponseCode.AUTHENTICATION_FAIL + "\", \"message\": \"" + ResponseMessage.AUTHENTICATION_FAIL + "\"}");
		}
		
	}	
}

