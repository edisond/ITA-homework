package com.oocl.kk.pojo;

public class Packet {
	
	private String type;
	private User from;
	private User[] to;
	private Object body;
	private String stamp;
	private Object subject;
	private boolean read;
	private String token;
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public User getFrom() {
		return from;
	}

	public void setFrom(User from) {
		this.from = from;
	}

	public User[] getTo() {
		return to;
	}

	public void setTo(User[] to) {
		this.to = to;
	}

	public Object getBody() {
		return body;
	}

	public void setBody(Object body) {
		this.body = body;
	}

	public String getStamp() {
		return stamp;
	}

	public void setStamp(String stamp) {
		this.stamp = stamp;
	}

	public Object getSubject() {
		return subject;
	}

	public void setSubject(Object subject) {
		this.subject = subject;
	}

	public boolean isRead() {
		return read;
	}

	public void setRead(boolean read) {
		this.read = read;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

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
