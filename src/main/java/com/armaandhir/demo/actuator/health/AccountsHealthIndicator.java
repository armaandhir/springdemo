package com.armaandhir.demo.actuator.health;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.stereotype.Component;

import com.armaandhir.demo.model.UserAccount;
import com.armaandhir.demo.service.UserAccountService;

/**
 * A custom Accounts spring boot actuator.
 * Are invoked when the Actuator 'health' endpoint is invoked. Each HealthIndicator class assesses some
 * portion of the application's health, returing a Health object which indicates that status and, optionally,
 * additional health attributes.
 * 
 * @author armaan
 *
 * reference: https://github.com/leanstacks/spring-boot-fundamentals/blob/security/src/main/java/org/example/ws/actuator/health/GreetingHealthIndicator.java
 */
@Component
public class AccountsHealthIndicator {
	
	@Autowired
	UserAccountService userAccountService;
	
	public Health health() 
	{
		Collection<UserAccount> accounts = userAccountService.findAll();
		if (accounts == null || accounts.size() == 0)
		{
			return Health.down().withDetail("count: ", 0).build();
		}
		return Health.up().withDetail("count", accounts.size()).build();
	}
}
