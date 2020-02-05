package com.users.service;

import java.util.ArrayList;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.users.entity.UserEntity;
import com.users.models.UserDto;
import com.users.repository.UsersRepository;



@Service
public class UserServiceImpl  implements UserService{
	
    private ModelMapper modelMapper ;
	// DI fields here 
    UsersRepository usersRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	
	//Constructor injection 
	@Autowired
	public UserServiceImpl(UsersRepository usersRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.usersRepository = usersRepository;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
		
		this.modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
	}
		
	
//	UserServiceImpl(){
//		this.modelMapper = new ModelMapper();
//		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
//	}

	@Override
	public UserDto createUser(UserDto userDto) {
	
		userDto.setUserid(UUID.randomUUID().toString());
		UserEntity userEntity =  modelMapper.map(userDto, UserEntity.class);
		userEntity.setEncryptedPassword(bCryptPasswordEncoder.encode((userDto.getPassword())));
		userEntity.setRole("USER");
		userEntity.setAllowed(true);
		usersRepository.save(userEntity);
		
		return null;
	}

	@Override
	public ArrayList<UserDto> getUsers() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserEntity userEntity = usersRepository.findByusername(username);
		if(userEntity == null ) throw new UsernameNotFoundException(username);
		
		return new User(userEntity.getUsername(),
				userEntity.getEncryptedPassword(),
				true,true,true,true, new ArrayList<>()
				);
	}


	@Override
	public UserDto getUserByUserName(String username) {
		UserEntity userEntity = usersRepository.findByusername(username);
		if(userEntity == null ) throw new UsernameNotFoundException(username);
		// TODO Auto-generated method stub
		return  modelMapper.map(userEntity, UserDto.class);
	}

}
