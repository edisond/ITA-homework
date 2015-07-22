package com.oocl.kary.kk.model;

public class KPacket {
	private String type;
	private User from;
	private User[] to;
	private Object body;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

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


