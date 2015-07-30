package com.oocl.kk.server.action;

import java.net.Socket;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.oocl.kary.pojo.Packet;
import com.oocl.kary.pojo.User;

public interface Action {
	public static final String LOGIN = "login";
	public static final String GET_USER = "getuser";
	public static final String CHAT = "chat";
	public static final String GROUP_CHAT = "groupchat";
	public static final String SHAKE = "shake";
	public static final String LOGOUT = "logout";
	
	
	public static final String ADMIN_LOGIN = "adminlogin";

	public static final Gson GSON = new Gson();

	public void execute(Packet packet, Socket socket, List<User> users,
			Map<Integer, Socket> session);
}
