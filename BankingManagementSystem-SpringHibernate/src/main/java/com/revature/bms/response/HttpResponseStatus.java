package com.revature.bms.response;

import lombok.Data;

@Data
public class HttpResponseStatus {

	private Integer statusCode;
	private String message;
	private Object data;

	public HttpResponseStatus() {
	}

	public HttpResponseStatus(Integer statusCode, String message, Object data) {
		this.statusCode = statusCode;
		this.message = message;
		this.data = data;
	}

	public HttpResponseStatus(Integer statusCode, String message) {
		this.statusCode = statusCode;
		this.message = message;
	}
	
	/*
	 * public HttpResponseStatus(Integer statusCode,Map<String, String> map) {
	 * this.message=map.getOrDefault(map, message); }
	 */
}
