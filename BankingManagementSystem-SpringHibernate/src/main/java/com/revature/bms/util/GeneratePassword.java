package com.revature.bms.util;

import java.util.Random;

public class GeneratePassword {

	public static String generatePassword() {
		// It will generate 6 digit random Number.
		// from 0 to 999999
		Random rnd = new Random();
		int number = rnd.nextInt(999999);

		// this will convert any number sequence into 6 character.
		return String.format("%06d", number);

	}

	public static String generateAccountNo() {

		String number = "60";
		for (int i = 0; i < 10; i++) {
			Random rand = new Random();
			int n = rand.nextInt(10) + 0;
			number += Integer.toString(n);
		}
		System.out.println("Generated Account No: " + number);
		return number;

	}

}
