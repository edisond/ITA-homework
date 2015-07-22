package com.oocl.kary.kk.model;

public class KPacket {
	public String type;
	public User from;
	public User[] to;
	public Object body;


	public KPacket(String type, User user) {
		if (type.equals("login")) {
			this.type = type;
			this.body = user;
		}
	}

	public KPacket(String type, LoginBody loginBody) {
		if (type.equals("login")) {
			this.type = type;
			this.body = loginBody;
		}
	}
}


