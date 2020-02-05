package com.users;

import java.util.Arrays;

import org.aspectj.weaver.ast.And;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer.FrameOptionsConfig;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.users.service.UserService;



@Configuration
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {
	private Environment  environment;
	private UserService userService;
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	public WebSecurity(Environment environment,
			UserService userService,
			BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.environment = environment;
		this.userService= userService;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userService).passwordEncoder(bCryptPasswordEncoder);
	}
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
//		super.configure(http);
		http.csrf().disable();
		http.headers().frameOptions().disable();
		 http.cors().configurationSource(corsConfigurationSource()).and().authorizeRequests()
		.antMatchers("/auth/**").permitAll()
		.and()
		.addFilter( getAuthenticationFilter() )
		.addFilter(new AuthorizationFilter(authenticationManager(), environment));
		
	}
	
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
    	
        CorsConfiguration configuration = new CorsConfiguration();

        configuration.applyPermitDefaultValues();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE"));
        configuration.setAllowedHeaders(Arrays.asList("Authorization", "content-type", "x-auth-token"));
        configuration.setExposedHeaders(Arrays.asList("Authorization"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

	private AuthenitcationFilter getAuthenticationFilter() throws Exception {
		AuthenitcationFilter authenticationFilter =new AuthenitcationFilter(
			userService, environment, authenticationManager()
				);
		authenticationFilter.setFilterProcessesUrl(environment.getProperty("login.url.path"));
//		authenticationFilter.setFilterProcessesUrl(environment.getProperty("login.url.path"));
		return authenticationFilter;
	}
	
}
