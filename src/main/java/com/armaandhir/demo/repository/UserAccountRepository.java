package com.armaandhir.demo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.armaandhir.demo.model.UserAccount;

@Repository
public interface UserAccountRepository extends CrudRepository<UserAccount, Long> {
	
}
