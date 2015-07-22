package com.oocl.kary.kk.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
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
public class ServerThread implements Runnable {
	private Socket socket;
	private List<User> users;
	Map<String, String> session;
	private static final Gson gson = new Gson();

	/**
	 * 构造方法
	 * 
	 * @param socket
	 * @param users
	 */
	public ServerThread(Socket socket, List<User> users,
			Map<String, String> session) {
		this.socket = socket;
		this.users = users;
		this.session = session;
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
			 * 读取客户端发送的JSON请求包，并转化为对象
			 */
			String json = in.readLine();
			KPacket packet = gson.fromJson(json, KPacket.class);

			/**
			 * 根据请求包的类型做出不同响应
			 */
			switch (packet.type) {
			case "login":
				/**
				 * 登录请求
				 */

				User user = gson.fromJson(packet.body.toString(), User.class);
				String msg = "fail";
				/**
				 * 寻找匹配用户ID与密码
				 */
				for (User u : users) {
					if (u.getId().equals(user.getId())
							&& u.getPassword().equals(user.getPassword())) {
						msg = "success";
						break;
					}
				}

				String token = "";

				if (msg.equals("success")) {
					token = user.toString();
					synchronized (session) {
						session.put(user.getId(), token);
					}
				}

				/**
				 * 封装返回包，并转化为JSON发送回客户端
				 */
				KPacket resultPacket = new KPacket("login", new LoginBody(msg));
				out.println(gson.toJson(resultPacket, resultPacket.getClass()));
				if (!token.equals("")) {
					out.println(token);
				}
				out.flush();
				break;
			default:
				break;
			}

			out.println(gson.toJson(packet, KPacket.class));
			out.flush();

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			out.close();
		}

	}
}
