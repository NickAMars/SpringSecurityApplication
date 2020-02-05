package com.users.models;

import java.io.Serializable;

import javax.persistence.Column;

public class MarketDto implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6761282431779020775L;
	//entity does have market id
	private String name;
	private String quantity;
	private String cost;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	public String getCost() {
		return cost;
	}
	public void setCost(String cost) {
		this.cost = cost;
	}

	
	
}
