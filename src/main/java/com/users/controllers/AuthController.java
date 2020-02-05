package com.users.controllers;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.users.entity.UserEntity;
import com.users.models.CreateUserRequestModel;
import com.users.models.UserDto;
import com.users.service.UserService;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:4000")
public class AuthController {
	
	@Autowired
	UserService userService;

	
	@GetMapping("")
	public String check() {
		return "check server";
	}
	@PostMapping("/signup")
	public ResponseEntity<String> CreateUser(@RequestBody CreateUserRequestModel createUserRequestModel) {
		
		ModelMapper modelMapper  = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		UserDto userDto =  modelMapper.map(createUserRequestModel, UserDto.class);
		userService.createUser(userDto);
		return  ResponseEntity.status(HttpStatus.OK).body(" it works");
	}
}
