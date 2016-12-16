package com.armaandhir.demo.service.impl;

import java.math.BigInteger;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.armaandhir.demo.model.UserAccount;
import com.armaandhir.demo.service.UserAccountService;

@Service
public class UserAccountServiceImpl implements UserAccountService {

	private static BigInteger nextId;
	private static Map<BigInteger, UserAccount> userAccountsMap;
	
	/**
	 * This block temporarily fills the collection with temporary accounts
	 */
	static {
		nextId = BigInteger.ZERO; 
		userAccountsMap = new HashMap<BigInteger, UserAccount>();
		
		UserAccount ua0 = new UserAccount();
		ua0.setEmail("dhir.armaan@gmail.com");
		ua0.setPassword("12345a");
		createAccount(ua0);
		
		UserAccount ua1 = new UserAccount();
		ua1.setEmail("dhir.kashish@gmail.com");
		ua1.setPassword("12345k");
		createAccount(ua1);
	}
	
	
	/**
	 * @param 	account			 	
	 * @return	account		created account is returned
	 */
	private static UserAccount createAccount(UserAccount account) {
		account.setId(nextId);
		nextId = nextId.add(BigInteger.ONE);
		userAccountsMap.put(account.getId(), account);
		return account;
	}
	
	/**
	 * @param 	account
	 * @return	
	 */
	private static UserAccount updateAccount(UserAccount account) {
		if (account != null) {
			userAccountsMap.remove(account.getId());
			userAccountsMap.put(account.getId(), account);
		}
		else {
			return null;
		}
		return account;
	}
	
	/**
	 * @param id
	 * @return
	 */
	private static boolean deleteAccount(BigInteger id) {
		UserAccount deletedAccount = userAccountsMap.remove(id);
		if (deletedAccount == null) {
			return false;
		}
		return true;
	}
	
	@Override
	public Collection<UserAccount> findAll() {
		return userAccountsMap.values();
	}

	@Override
	public UserAccount findOne(BigInteger id) {
		UserAccount account = userAccountsMap.get(id);
		return account;
	}

	@Override
	public UserAccount create(UserAccount account) {
		UserAccount createdAccount = createAccount(account);
		return createdAccount;
	}

	@Override
	public UserAccount update(UserAccount account) {
		UserAccount updatedAccount = updateAccount(account);
		return updatedAccount;
	}

	@Override
	public void delete(BigInteger id) {
		deleteAccount(id);
	}

}
