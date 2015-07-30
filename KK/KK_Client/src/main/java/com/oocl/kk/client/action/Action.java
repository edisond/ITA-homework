package com.oocl.kk.client.action;

import com.google.gson.Gson;
import com.oocl.kary.pojo.Packet;
import com.oocl.kk.client.ui.MainFrame;

public interface Action {
	public static final String LOGIN = "login";
	public static final String GET_USER = "getuser";
	public static final String CHAT = "chat";
	public static final String GROUP_CHAT = "groupchat";
	public static final String SHAKE = "shake";
	public static final String LOGOUT = "logout";

	public static final Gson GSON = new Gson();

	public void execute(Packet packet, final MainFrame frame);
}
