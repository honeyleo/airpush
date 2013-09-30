package com.w.core.exception;

public class AppException extends Exception {

	private static final long serialVersionUID = 8633328680035195765L;
	
	private String code;
	private String msg;

	public AppException(String code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public String getMessage() {
		return this.msg;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}
