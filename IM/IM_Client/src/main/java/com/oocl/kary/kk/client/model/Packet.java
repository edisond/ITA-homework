package com.oocl.kary.kk.client.model;

public class Packet {
	public String type;
	public User from;
	public User[] to;
	public Object body;
	public Boolean read;
	public String token;
	public String stamp;

	public Packet() {
		
	}

	public Packet(String type) {
		this.type = type;
	}

	public Packet(String type, User user) {
		this.type = type;
		this.body = user;
	}

	public Packet(String type, LoginBody loginBody) {
		this.type = type;
		this.body = loginBody;
	}

	public class LoginBody {
		public String message;

		public LoginBody(String message) {
			this.message = message;
		}
	}
}
