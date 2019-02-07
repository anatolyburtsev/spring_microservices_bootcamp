package com.mylnikov.HibernatePerfTest;

import com.mylnikov.HibernatePerfTest.entity.Account;
import com.mylnikov.HibernatePerfTest.entity.SimpleUser;
import com.mylnikov.HibernatePerfTest.repo.AccountRepository;
import com.mylnikov.HibernatePerfTest.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.LinkedList;

@SpringBootApplication
public class HibernatePerfTestApplication {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private AccountRepository accountRepository;

	@PostConstruct
	public void test() {
		SimpleUser simpleUser = new SimpleUser();
		simpleUser.name = "Name";
		simpleUser.email = "email";
		Account account = new Account();
		account.user = simpleUser;
		simpleUser.accounts = new LinkedList<>();
		simpleUser.accounts.add(account);
		userRepository.save(simpleUser);

	}

	public static void main(String[] args) {
		SpringApplication.run(HibernatePerfTestApplication.class, args);
	}

}

