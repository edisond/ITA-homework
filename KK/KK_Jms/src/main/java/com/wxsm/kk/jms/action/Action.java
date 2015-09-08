package com.wxsm.kk.jms.action;

import com.oocl.kary.pojo.Packet;

public interface Action {
	public static final String LOGIN = "login";
	public static final String GET_USER = "getuser";
	public static final String CHAT = "chat";
	public static final String GROUP_CHAT = "groupchat";
	public static final String SHAKE = "shake";
	public static final String LOGOUT = "logout";

	public void execute(Packet packet);
}
