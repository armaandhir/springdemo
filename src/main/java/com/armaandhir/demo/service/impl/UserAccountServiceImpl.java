package com.armaandhir.demo.service.impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.armaandhir.demo.model.UserAccount;
import com.armaandhir.demo.repository.UserAccountRepository;
import com.armaandhir.demo.service.UserAccountService;

/**
 * @author Armaan Dhir
 * 
 * Implementation of the methods defined the in the UserAccountService interface.
 * Transactional annotation at class level sets transactional boundaries to all public methods.
 * If applied to method, then it overrides the class level transactional configuration. 
 */
@Service
@Transactional(
		propagation = Propagation.SUPPORTS,
		readOnly = true)
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
	@Transactional(
			propagation = Propagation.REQUIRES_NEW,
			readOnly = false)
	public UserAccount create(UserAccount account) {
		if(account.getId() != null) {
			//cannot create account with specified id value
			return null;
		}
		UserAccount createdAccount = userAccountRepository.save(account);
		return createdAccount;
	}

	@Override
	@Transactional(
			propagation = Propagation.REQUIRES_NEW,
			readOnly = false)
	public UserAccount update(UserAccount account) {
		UserAccount persistedAccount = userAccountRepository.findOne(account.getId());
		if (persistedAccount == null) {
			// cannot update the account that has not been created
			return null;
		}
		// for deliberate roll back use
		String pass = persistedAccount.getPassword();
		
		UserAccount updatedAccount = userAccountRepository.save(account);
		
		System.out.println("pass= " + pass);
		System.out.println("updated= " + updatedAccount.getPassword());
		// Deliberate rollback to illustrate functionality
		if (updatedAccount.getPassword().equals(pass)){
			System.out.println("In");
			throw new RuntimeException("Deliberate Rollback");
		}
		return updatedAccount;
	}

	@Override
	@Transactional(
			propagation = Propagation.REQUIRES_NEW,
			readOnly = false)
	public void delete(UserAccount account) {
		userAccountRepository.delete(account);
	}

}
