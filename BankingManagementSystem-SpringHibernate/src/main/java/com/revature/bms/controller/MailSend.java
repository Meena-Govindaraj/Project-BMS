package com.revature.bms.controller;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class MailSend {

	private static final Logger logger = LogManager.getLogger(MailSend.class.getName());

	private static JavaMailSender javaMailSender;

	@Autowired
	public MailSend(JavaMailSender s) {
		this.javaMailSender = s;
	}

	private static final int NO_OF_QUICK_SERVICE_THREADS = 5;

	/**
	 * this statement create a thread pool of five threads here we are assigning
	 * send mail task using ScheduledExecutorService.submit();
	 */
	private static ScheduledExecutorService quickService = Executors
			.newScheduledThreadPool(NO_OF_QUICK_SERVICE_THREADS); 
	
	public static void sendMail(String toReceiver, String subject, String message) {

		SimpleMailMessage msg = new SimpleMailMessage();

		msg.setTo(toReceiver);
		msg.setSubject(subject);
		msg.setText(message);

		quickService.submit(new Runnable() {
			@Override
			public void run() {
				try {
					javaMailSender.send(msg);
				} catch (Exception e) {
					logger.error("Exception occur while send a mail : ", e);
				}
			}
		});
		

	}

}