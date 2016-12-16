package com.armaandhir.demo.api;

import java.math.BigInteger;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.armaandhir.demo.model.UserAccount;

/**
 * @author	armaandhir
 * @date
 * @reference	https://github.com/leanstacks/spring-boot-fundamentals/tree/restws-2    
 */
@RestController
public class SpringDemoController {
	
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
	private static UserAccount update(UserAccount account) {
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
	private static boolean delete(BigInteger id) {
		UserAccount deletedAccount = userAccountsMap.remove(id);
		if (deletedAccount == null) {
			return false;
		}
		return true;
	}
	
	
	/* ---Controller methods from here--- */
	
	
	/**
	 * @return
	 */
	@RequestMapping(
			value="/springdemo/api/accounts",
			method=RequestMethod.GET,
			produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<UserAccount>> getAllAccounts() {
		return new ResponseEntity<Collection<UserAccount>>(userAccountsMap.values(), HttpStatus.OK);
	}
	
	/**
	 * Gets the account information by id
	 * @param id
	 * @return
	 */
	@RequestMapping(
			value="/springdemo/api/account/{id}",
			method=RequestMethod.GET,
			produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserAccount> getAccount(@PathVariable("id") BigInteger id) {
		UserAccount account = userAccountsMap.get(id);
		if (userAccountsMap.get(id) == null) {
			return new ResponseEntity<UserAccount>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<UserAccount>(account, HttpStatus.OK);
		
	}
	
	/**
	 * Creates a new account
	 * @param account	A json object
	 * @return createdAccount
	 */
	@RequestMapping(
			value="/springdemo/api/signup",
			method=RequestMethod.POST,
			consumes=MediaType.APPLICATION_JSON_VALUE,
			produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserAccount> addAccount(@RequestBody UserAccount account) {
		UserAccount createdAccount = createAccount(account);
		return new ResponseEntity<UserAccount>(createdAccount, HttpStatus.OK);
	}
	
	/**
	 * @param account
	 * @return
	 */
	@RequestMapping(
			value="/springdemo/api/update",
			method=RequestMethod.PUT,
			consumes=MediaType.APPLICATION_JSON_VALUE,
			produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserAccount> updateAccount(@RequestBody UserAccount account) {
		UserAccount updatedAccount = update(account);
		if(updatedAccount == null) {
			return new ResponseEntity<UserAccount>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<UserAccount>(updatedAccount, HttpStatus.OK);
	}
	
	/**
	 * @param id
	 * @param account
	 * @return
	 */
	@RequestMapping(
			value="/springdemo/api/delete{id}",
			method=RequestMethod.DELETE,
			consumes=MediaType.APPLICATION_JSON_VALUE,
			produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserAccount> deleteAccount(
			@PathVariable("id") BigInteger id, @RequestBody UserAccount account) {
		boolean deleted = delete(id);
		if(!deleted) {
			return new ResponseEntity<UserAccount>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<UserAccount>(HttpStatus.NO_CONTENT);
	}
	

// end of class	
}
