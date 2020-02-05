package com.users.service;

import java.util.ArrayList;

import com.users.models.MarketDto;

public interface MarketService {
	public ArrayList<MarketDto> getMarketItems();
	public MarketDto getMarketItem(String MarketName); 
	public void insertMarketItem(MarketDto marketDto);
	public void updateMarketItem(MarketDto marketDto);
	
}
