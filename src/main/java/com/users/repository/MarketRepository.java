package com.users.repository;

import org.springframework.data.repository.CrudRepository;

import com.users.entity.MarketEntity;

public interface MarketRepository  extends CrudRepository<MarketEntity, Long>  {
	MarketEntity findByname(String name);
}
