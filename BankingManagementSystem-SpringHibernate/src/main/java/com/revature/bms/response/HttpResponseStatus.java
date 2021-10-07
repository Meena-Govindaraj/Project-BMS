package com.revature.bms.response;

import java.util.HashMap;
import java.util.Map;

import org.springframework.validation.FieldError;import com.sun.xml.bind.v2.util.QNameMap.Entry;

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
