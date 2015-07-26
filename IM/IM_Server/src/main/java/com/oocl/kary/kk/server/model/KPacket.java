package com.oocl.kary.kk.server.model;

public class KPacket {
	public String type;
	public User from;
	public User[] to;
	public Object body;
	public String token;
	public String stamp;

	public KPacket() {
		
	}

	public KPacket(String type) {
		this.type = type;
	}

	public KPacket(String type, User user) {
		this.type = type;
		this.body = user;
	}

	public KPacket(String type, LoginBody loginBody) {
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
