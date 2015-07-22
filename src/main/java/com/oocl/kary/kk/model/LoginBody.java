package com.oocl.kary.kk.model;

public class LoginBody {
	private String message;

	public LoginBody(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
