package ru.java.mentor.hibernate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableScheduling
@EnableTransactionManagement
@SpringBootApplication
public class HibernateApplication {
	public static void main(String[] args) {
		SpringApplication.run(HibernateApplication.class, args);
	}

}

