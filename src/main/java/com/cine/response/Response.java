package com.cine.response;

import java.io.Serializable;

public class Response implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -249341137333400241L;
	private int code;
	private String message;
	
	public Response(int code,String message) {
		this.code=code;
		this.message=message;
	}
	
	public Response() {
		
	}
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
