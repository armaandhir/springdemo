package com.armaandhir.demo.service.impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.armaandhir.demo.model.UserAccount;
import com.armaandhir.demo.repository.UserAccountRepository;
import com.armaandhir.demo.service.UserAccountService;

/**
 * @author Armaan Dhir
 * 
 * Implementation of the methods defined the in the UserAccountService interface.
 * Uses UserAccountRepository to perform CRUD operations for UserAccount'
 */
@Service
public class UserAccountServiceImpl implements UserAccountService {

	/**
	 * Using the UserAccountRepository which will be used to perform CRUD operations.
	 */
	@Autowired
	private UserAccountRepository userAccountRepository;
	
	
	@Override
	public Collection<UserAccount> findAll() {
		return (Collection<UserAccount>) userAccountRepository.findAll();
	}

	@Override
	public UserAccount findOne(Long id) {
		UserAccount account = userAccountRepository.findOne(id);
		return account;
	}

	@Override
	public UserAccount create(UserAccount account) {
		if(account.getId() != null) {
			//cannot create account with specified id value
			return null;
		}
		UserAccount createdAccount = userAccountRepository.save(account);
		return createdAccount;
	}

	@Override
	public UserAccount update(UserAccount account) {
		UserAccount persistedAccount = userAccountRepository.findOne(account.getId());
		if (persistedAccount == null) {
			// cannot update the account that has not been created
			return null;
		}
		UserAccount updatedAccount = userAccountRepository.save(account);
		return updatedAccount;
	}

	@Override
	public void delete(Long id) {
		userAccountRepository.delete(id);
	}

}
