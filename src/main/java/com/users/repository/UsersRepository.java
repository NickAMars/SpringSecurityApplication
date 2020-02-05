package com.users.repository;

import org.springframework.data.repository.CrudRepository;

import com.users.entity.UserEntity;

public interface UsersRepository extends CrudRepository<UserEntity, Long> {
	UserEntity findByusername(String username);
}
