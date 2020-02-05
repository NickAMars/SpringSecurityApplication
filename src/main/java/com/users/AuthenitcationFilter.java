package com.users;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.users.models.LoginRequestModel;
import com.users.models.UserDto;
import com.users.service.UserService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class AuthenitcationFilter extends UsernamePasswordAuthenticationFilter{

	UserService userService;
	Environment environment;
	
	@Autowired
	public AuthenitcationFilter(	UserService userService,
			Environment environment, AuthenticationManager authenticationManager){
		super.setAuthenticationManager(authenticationManager);
		this.userService = userService;
		this.environment = environment;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		
		try {
			LoginRequestModel creds = new ObjectMapper().readValue(request.getInputStream(), LoginRequestModel.class);
			return  getAuthenticationManager().authenticate(
					new UsernamePasswordAuthenticationToken(
					creds.getUsername(),
					creds.getPassword(),
					new ArrayList<>()
					));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return super.attemptAuthentication(request, response);
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		// TODO Auto-generated method stub
		String username = authResult.getName();
		UserDto userDetails = userService.getUserByUserName(username);
		String token = Jwts.builder()
				.setSubject(userDetails.getUserid())
				.setExpiration(new Date(System.currentTimeMillis() + Long.parseLong(environment.getProperty("token.expiration_time"))))
				.signWith(SignatureAlgorithm.HS512, environment.getProperty("token.secret"))
				.compact();

		
		response.addHeader("Authorization" , token);
//		res.addHeader("userId", userDetails.getUserId());
	}
	
	
}
