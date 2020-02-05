package com.users.entity;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="users")
public class UserEntity implements Serializable{
	
	
	private static final long serialVersionUID = 2985064685549929046L;
	@Id
	@GeneratedValue
	private long id;
	@Column(nullable=false, unique=true)
	private String userid;
	@Column(nullable=false, length= 50)
	private String username;
	@Column(nullable=false, unique=true)
	private String encryptedPassword;
	@Column(nullable=false)
	private String role;
	@Column(nullable=false)
	private boolean allowed;
	@Column(unique=true)
	private String email;
	
	
	
	
	
	
	
	
	
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public boolean isAllowed() {
		return allowed;
	}
	public void setAllowed(boolean allowed) {
		this.allowed = allowed;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEncryptedPassword() {
		return encryptedPassword;
	}
	public void setEncryptedPassword(String encryptedPassword) {
		this.encryptedPassword = encryptedPassword;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}

	
	
	
}
