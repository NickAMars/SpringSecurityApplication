package com.users.controllers;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.users.models.MarketDto;
import com.users.service.MarketService;


@RestController
@RequestMapping("/market")
@CrossOrigin(origins = "http://localhost:4000")
public class MaketFood {
	
	
	
	@Autowired
	MarketService marketService;
	
	
	@GetMapping("/item")
	public MarketDto getMarketItem(@RequestParam String marketItem) {
		return marketService.getMarketItem(marketItem);
	}
	
	@GetMapping("/items")
	public ArrayList<MarketDto> getMarketItems( ) {
		System.out.println("in method returning result");
		return marketService.getMarketItems();
	}
	
	@PostMapping("/insert")
	public ResponseEntity<String> insertMarketItem(@RequestBody MarketDto marketDto) {
		marketService.insertMarketItem(marketDto);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body("you have inserted a fruit");
	}
	@PutMapping("/update")
	public ResponseEntity<String> updateMarketItem(@RequestBody MarketDto marketDto) {
		marketService.updateMarketItem(marketDto);
		return  ResponseEntity.status(HttpStatus.ACCEPTED).body("you have updated a fruit");
	}
}
