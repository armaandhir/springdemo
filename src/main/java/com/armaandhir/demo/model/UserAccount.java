package com.armaandhir.demo.model;

import java.math.BigInteger;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class UserAccount {
	
	@Id
	@GeneratedValue
	private BigInteger id;
	
	private String email;
	
	private String password;
	
	public UserAccount(){
		
	}

	public BigInteger getId() {
		return id;
	}

	public void setId(BigInteger id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String emailId) {
		this.email = emailId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
