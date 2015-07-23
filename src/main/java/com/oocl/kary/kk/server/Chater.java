package com.oocl.kary.kk.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.oocl.kary.kk.model.KPacket;
import com.oocl.kary.kk.model.LoginBody;
import com.oocl.kary.kk.model.User;

/**
 * 服务器子线程
 * 
 * @author edisond@qq.com
 * 
 */
public class Chater implements Runnable {
	private Socket socket;
	private List<User> users;
	Map<String, Socket> session;
	private static final Gson gson = new Gson();
	private boolean isLogout;

	/**
	 * 构造方法
	 * 
	 * @param socket
	 * @param users
	 */
	public Chater(Socket socket, List<User> users, Map<String, Socket> session) {
		this.socket = socket;
		this.users = users;
		this.session = session;
		isLogout = false;
	}

	/**
	 * 线程启动
	 */
	public void run() {
		BufferedReader in = null;
		PrintWriter out = null;

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
				String json = in.readLine();
				KPacket packet = gson.fromJson(json, KPacket.class);

				/**
				 * 根据请求包的类型做出不同响应
				 */
				switch (packet.type) {
				case "login": {

					/**
					 * 登录请求
					 */
					User user = gson.fromJson(packet.body.toString(),
							User.class);
					String msg = "fail";

					/**
					 * 寻找匹配用户ID与密码
					 */
					for (User u : users) {
						if (u.getId().equals(user.getId())
								&& u.getPassword().equals(user.getPassword())) {
							msg = "success";

							/**
							 * 将新用户登陆的信息发送给所有其他客户端，以更新用户在线列表
							 */
							KPacket brocastPacket = new KPacket("getuser");
							brocastPacket.body = users;
							for (String key : session.keySet()) {
								// System.out.println("Login brocast to " +
								// key);
								new Thread(new ServerOut(session.get(key),
										brocastPacket)).start();
							}

							u.setState("online");
							synchronized (session) {
								session.put(user.getId(), socket);
							}
							break;
						}
					}

					KPacket resultPacket = new KPacket("login", new LoginBody(
							msg));
					if (msg.equals("success")) {
						resultPacket.token = socket.toString();
						System.out.println("Logined:\t" + socket.toString()
								+ "\tOnline num: " + session.size());
					}

					/**
					 * 为Socket用户发送返回包
					 */
					new Thread(new ServerOut(socket, resultPacket)).start();
					break;
				}

				case "getuser": {
					KPacket resultPacket = new KPacket("getuser");
					resultPacket.body = users;
					new Thread(new ServerOut(socket, resultPacket)).start();
					break;
				}
				case "chat": {
					for (String key : session.keySet()) {
						if (key.equals(packet.to[0].getId())) {
							new Thread(new ServerOut(session.get(key), json))
									.start();
							break;
						}
					}

					break;
				}
				case "shake": {
					for (String key : session.keySet()) {
						if (key.equals(packet.to[0].getId())) {
							new Thread(new ServerOut(session.get(key), json))
									.start();
							break;
						}
					}
					break;
				}
				case "groupchat": {
					for (User u : packet.to) {
						if (session.containsKey(u.getId())
								&& !session.get(u.getId()).equals(socket)) {
							new Thread(new ServerOut(session.get(u.getId()),
									json)).start();
						}
					}
					break;
				}
				default:
					break;
				}
			}

		} catch (SocketException e) {

			System.out.println("Disconnected:\t" + socket.toString()
					+ "\tOnline num: " + session.size());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			/**
			 * 将用户注销的信息发送给所有客户端，以更新用户在线列表
			 */
			String userId = null;
			synchronized (session) {
				for (String key : session.keySet()) {
					if (session.get(key).equals(socket)) {
						session.remove(key);
						userId = key;
						break;
					}
				}
			}

			for (User u : users) {
				if (u.getId().equals(userId)) {
					u.setState("offline");
					break;
				}
			}

			KPacket brocastPacket = new KPacket("getuser");
			brocastPacket.body = users;
			for (String key : session.keySet()) {
				// System.out.println("Logout brocast to " + key);
				new Thread(new ServerOut(session.get(key), brocastPacket))
						.start();
			}
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
