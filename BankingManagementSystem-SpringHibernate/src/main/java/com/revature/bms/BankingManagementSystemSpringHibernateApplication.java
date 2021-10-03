package com.revature.bms;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BankingManagementSystemSpringHibernateApplication {

	private static final Logger logger = LogManager
			.getLogger(BankingManagementSystemSpringHibernateApplication.class.getName());

	public static void main(String[] args) {

		logger.info("###Application Started");
		SpringApplication.run(BankingManagementSystemSpringHibernateApplication.class, args);
	}

}