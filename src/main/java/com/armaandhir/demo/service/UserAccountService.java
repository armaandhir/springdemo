package com.armaandhir.demo.service;

import java.util.Collection;

import com.armaandhir.demo.model.UserAccount;

public interface UserAccountService {

	Collection<UserAccount> findAll();
	
	UserAccount findOne(Long id);
	
	UserAccount create(UserAccount account);
	
	UserAccount update (UserAccount account);
	
	void delete (Long id);
}
