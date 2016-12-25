package com.armaandhir.demo.service;

import java.util.Collection;

import com.armaandhir.demo.model.UserAccount;

/**
 * @author Armaan Dhir
 *
 */
public interface UserAccountService {

	/**
	 * @return
	 */
	Collection<UserAccount> findAll();
	
	/**
	 * @param id
	 * @return
	 */
	UserAccount findOne(Long id);
	
	/**
	 * @param account
	 * @return
	 */
	UserAccount create(UserAccount account);
	
	/**
	 * @param account
	 * @return
	 */
	UserAccount update (UserAccount account);
	
	/**
	 * @param id
	 */
	void delete (Long id);
}
