package com.users.service;

import java.util.ArrayList;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.users.models.UserDto;

public interface UserService 
extends UserDetailsService{
	public UserDto createUser(UserDto userDto);
	public UserDto getUserByUserName(String username);
	public ArrayList<UserDto> getUsers();

}
