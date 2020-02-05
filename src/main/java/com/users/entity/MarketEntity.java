package com.users.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="market")
public class MarketEntity  implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4517531570047893638L;
	
	@Id
	@GeneratedValue(strategy= GenerationType.SEQUENCE, generator="seq")
	private long id;
	@Column(name = "item_name", unique=true)
	private String name;
	@Column(name = "item_quantity")
	private String quantity;
	@Column(name = "item_cost")
	private String cost;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
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
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
}
