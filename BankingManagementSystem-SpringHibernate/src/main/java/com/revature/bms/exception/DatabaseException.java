package com.revature.bms.exception;

public class DatabaseException extends RuntimeException{
	
	private static final long serialVersionUID = -550105279094709335L;

	public DatabaseException(String msg) {
		super(msg);
	}
}