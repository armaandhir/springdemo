package com.armaandhir.demo.api;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.armaandhir.demo.model.UserAccount;
import com.armaandhir.demo.service.UserAccountService;

/**
 * @author	armaandhir
 * @date
 * @reference	https://github.com/leanstacks/spring-boot-fundamentals/tree/restws-2    
 */
@RestController
public class SpringDemoController {
	
	/**
	 * Always use Interface type for dependency injection rather than Implementation class.
	 * This follows programming by contract-model so that only public methods are exposed to service client.
	 */
	@Autowired
	private UserAccountService userAccountService;
	
	/**
	 * @return
	 */
	@RequestMapping(
			value="/springdemo/api/accounts",
			method=RequestMethod.GET,
			produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<UserAccount>> getAllAccounts() {
		return new ResponseEntity<Collection<UserAccount>>(userAccountService.findAll(), HttpStatus.OK);
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
	public ResponseEntity<UserAccount> getAccount(@PathVariable("id") Long id) {
		UserAccount account = userAccountService.findOne(id);
		if (account == null) {
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
		UserAccount createdAccount = userAccountService.create(account);
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
		UserAccount updatedAccount = userAccountService.update(account);
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
			@PathVariable("id") Long id, @RequestBody UserAccount account) {
		userAccountService.delete(id);
		return new ResponseEntity<UserAccount>(HttpStatus.NO_CONTENT);
	}
	

// end of class	
}
