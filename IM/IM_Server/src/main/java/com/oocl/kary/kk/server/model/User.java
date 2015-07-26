package com.oocl.kary.kk.server.model;

import java.io.Serializable;

public class User implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String username;
	private String state;
	private String password;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public User() {

	}

	public User(String id) {
		this.id = id;
	}

	public User(String id, String username) {
		this.id = id;
		this.username = username;
	}

	public User(String id, String username, String password) {
		this.id = id;
		this.username = username;
		this.password = password;
	}
}
