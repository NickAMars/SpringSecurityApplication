package com.users.service;

import java.util.ArrayList;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.users.entity.MarketEntity;
import com.users.entity.UserEntity;
import com.users.models.MarketDto;
import com.users.repository.MarketRepository;
import com.users.repository.UsersRepository;


@Service
public class MarketServiceImpl implements MarketService{

	
	private ModelMapper modelMapper ;
	MarketRepository marketRepository;
	private ArrayList<MarketDto> allItems;
	
	//Constructor injection 
	@Autowired
	public MarketServiceImpl(MarketRepository marketRepository) {
		this.marketRepository = marketRepository;
		
		this.modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
	}
	
	@Override
	public ArrayList<MarketDto> getMarketItems() {
		allItems = new ArrayList<MarketDto>();
		Iterable<MarketEntity> iterator =  marketRepository.findAll();
		iterator.forEach(action-> allItems.add( modelMapper.map(action, MarketDto.class)) );
		return allItems;
	}

	@Override
	public MarketDto getMarketItem(String MarketName) {
		// TODO Auto-generated method stub
		
//		  modelMapper.map(marketRepository.findByname(MarketName), MarketDto.class);
		return modelMapper.map(marketRepository.findByname(MarketName), MarketDto.class);
	}

	@Override
	public void insertMarketItem(MarketDto marketDto) {
		// TODO Auto-generated method stub
		marketRepository.save(modelMapper.map(marketDto, MarketEntity.class));
		
	}

	@Override
	public void updateMarketItem(MarketDto marketDto) {
		// TODO Auto-generated method stub
		MarketEntity DatabaseEntity  = marketRepository.findByname(marketDto.getName());
		MarketEntity SaveEntity = modelMapper.map(marketDto, MarketEntity.class);
		SaveEntity.setId(DatabaseEntity.getId());
		marketRepository.save(SaveEntity);
	}
//	public MarketDto getMarketItems(); v
}
