package com.oocl.kk.server.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.oocl.kary.pojo.Packet;
import com.oocl.kary.pojo.User;
import com.oocl.kk.server.action.*;

/**
 * 服务器子线程
 * 
 * @author edisond@qq.com
 * 
 */
public class Response implements Runnable {
	private Socket socket;
	private List<User> users;
	Map<Integer, Socket> session;
	private static final Gson gson = new Gson();
	private boolean isLogout;
	@SuppressWarnings("rawtypes")
	private Map<String, Class> actionMap;

	/**
	 * 构造方法
	 * 
	 * @param socket
	 * @param users
	 */
	@SuppressWarnings("rawtypes")
	public Response(Socket socket, List<User> users, Map<Integer, Socket> session) {
		this.socket = socket;
		this.users = users;
		this.session = session;
		isLogout = false;
		this.actionMap = new HashMap<String, Class>();
		actionMap.put(Action.CHAT, ChatAction.class);
		actionMap.put(Action.GROUP_CHAT, GroupChatAction.class);
		actionMap.put(Action.SHAKE, ShakeAction.class);
		actionMap.put(Action.GET_USER, GetUserAction.class);
		actionMap.put(Action.LOGIN, LoginAction.class);
		actionMap.put(Action.LOGOUT, LogoutAction.class);
	}

	/**
	 * 线程启动
	 */
	public void run() {
		BufferedReader in = null;
		PrintWriter out = null;
		String json = null;
		Packet packet = null;

		try {
			/**
			 * 初始化读写流
			 */
			in = new BufferedReader(new InputStreamReader(
					socket.getInputStream()));
			out = new PrintWriter(socket.getOutputStream());

			/**
			 * 长连接，在注销时断开
			 */
			while (!isLogout) {

				/**
				 * 读取客户端发送的JSON请求包，并转化为对象
				 */
				json = in.readLine();

				if (json == null) {
					break;
				}

				System.out.println("GET:\t" + json);

				if (json.equals(Action.ADMIN_LOGIN)) {
					out.println("admin login");
					out.flush();
				} else {
					packet = gson.fromJson(json, Packet.class);

					/**
					 * 根据请求包的类型做出不同响应
					 */
					Class<?> actionClass = actionMap.get(packet.getType());
					Action action = (Action) actionClass.newInstance();
					action.execute(packet, socket, users, session);
				}

			}

		} catch (SocketException e) {
			/**
			 * 将用户注销的信息发送给所有客户端，以更新用户在线列表
			 */
			Class<?> actionClass = actionMap.get(Action.LOGOUT);
			Action action;
			try {
				action = (Action) actionClass.newInstance();
				action.execute(packet, socket, users, session);
			} catch (InstantiationException | IllegalAccessException e1) {
				e1.printStackTrace();
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			System.out.println("Disconnected:\t" + socket.toString()
					+ "\tOnline num: " + session.size());

			try {
				in.close();
				out.close();
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
