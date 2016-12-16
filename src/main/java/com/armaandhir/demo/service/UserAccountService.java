package com.armaandhir.demo.service;

import java.math.BigInteger;
import java.util.Collection;

import com.armaandhir.demo.model.UserAccount;

public interface UserAccountService {

	Collection<UserAccount> findAll();
	
	UserAccount findOne(BigInteger id);
	
	UserAccount create(UserAccount account);
	
	UserAccount update (UserAccount account);
	
	void delete (UserAccount account);
}
