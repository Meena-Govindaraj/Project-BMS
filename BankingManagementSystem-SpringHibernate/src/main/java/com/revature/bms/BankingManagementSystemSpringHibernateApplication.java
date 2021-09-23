package com.revature.bms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BankingManagementSystemSpringHibernateApplication {

	public static void main(String[] args) {

		System.out.println("###Application Started");
		SpringApplication.run(BankingManagementSystemSpringHibernateApplication.class, args);
	}

}