package com.users;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import io.jsonwebtoken.Jwts;

public class AuthorizationFilter extends BasicAuthenticationFilter {
	private Environment environment;
	
	public AuthorizationFilter(AuthenticationManager authenticationManager, Environment environment) {
		super(authenticationManager);
		// TODO Auto-generated constructor stub
		this.environment = environment;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		String authoriztionHeader = req.getHeader(environment.getProperty("authorization.token.header.name"));
		
		if(authoriztionHeader == null || !authoriztionHeader.startsWith(environment.getProperty("authorization.token.header.prefix"))) {
//			chain.doFilter(req, res);
			res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			return;
		}
		UsernamePasswordAuthenticationToken authentication = getAuthentication(req);
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		chain.doFilter(req, res);
	}
	
	
	
	private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest req) {
		String authorizationHeader = req.getHeader(environment.getProperty("authorization.token.header.name"));
		
		if(authorizationHeader == null)
			return null;
		// get the token 
		String token = authorizationHeader.replace(environment.getProperty("authorization.token.header.prefix"), "");
		System.out.println("get token" +token);
		
		// get the userId  which we place for the subject
		String userId = Jwts.parser()
				.setSigningKey(environment.getProperty("token.secret"))
				.parseClaimsJws(token)
				.getBody()
				.getSubject();
		System.out.println("valid token");
		
		
		if(userId == null) {
			return null;
		}
		return new UsernamePasswordAuthenticationToken(userId, null, new ArrayList<>());
	}
}
