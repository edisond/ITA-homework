package com.wxsm.o2o.pojo;

public class Image {
	private String id;
	private byte[] body;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Image(String id, byte[] body) {
		super();
		this.id = id;
		this.body = body;
	}

	public byte[] getBody() {
		return body;
	}

	public void setBody(byte[] body) {
		this.body = body;
	}

}
