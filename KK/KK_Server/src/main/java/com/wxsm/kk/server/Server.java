package com.wxsm.kk.server;

import java.io.*;
import java.net.*;
import java.util.*;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.oocl.kary.pojo.Packet;
import com.oocl.kary.pojo.User;
import com.wxsm.kk.server.service.Response;
import com.wxsm.kk.server.util.JmsUtil;

/**
 * 服务器主线程
 * 
 * @author edisond@qq.com
 * 
 */
public class Server {

	private static final Gson GSON = new Gson();

	public static void main(String[] args) throws Exception {

		final String SERVER_TOKEN = new Double(Math.random()).toString();

		System.out.println(new Date().toString() + "\tLoading config.ini...");
		Properties config = new Properties();
		config.load(Server.class.getResourceAsStream("config.ini"));
		System.out.println(new Date().toString()
				+ "\tLoad config.ini successed...");

		System.out.println(new Date().toString()
				+ "\tLoading user list from JMC...");
		Packet packet = new Packet("getuser");
		packet.setToken(SERVER_TOKEN);
		String json = GSON.toJson(packet, packet.getClass());
		JmsUtil.produce(json);
		json = JmsUtil.consume(SERVER_TOKEN);
		packet = GSON.fromJson(json, packet.getClass());
		@SuppressWarnings("unchecked")
		List<User> users = (LinkedList<User>) GSON.fromJson(packet.getBody()
				.toString(), new TypeToken<LinkedList<User>>() {
		}.getType());
		System.out.println(new Date().toString()
				+ "\tLoad user list from JMC successed...");

		for (User user : users) {
			user.setState("offline");
		}

		/**
		 * 程序Session集，用来保存已登陆的用户以及其对应的Socket
		 */
		Map<Integer, Socket> session = new LinkedHashMap<Integer, Socket>();

		System.out.println(new Date().toString() + "\tStarting server...");
		@SuppressWarnings("resource")
		ServerSocket server = new ServerSocket(Integer.parseInt(config
				.getProperty("port")));
		System.out.println(new Date().toString()
				+ "\tStart server successed...");

		while (true) {
			try {
				Socket socket = server.accept();
				System.out.println("Connected:\t" + socket.toString());
				new Thread(new Response(socket, users, session)).start();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
