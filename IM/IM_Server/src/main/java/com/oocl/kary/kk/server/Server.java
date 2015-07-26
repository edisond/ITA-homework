package com.oocl.kary.kk.server;

import java.io.*;
import java.net.*;
import java.util.*;

import com.oocl.kary.kk.server.model.User;
import com.oocl.kary.kk.server.service.Response;

/**
 * 服务器主线程
 * 
 * @author edisond@qq.com
 * 
 */
public class Server {

	public static void main(String[] args) throws Exception {

		/**
		 * 读取配置信息
		 */
		Properties config = new Properties();
		config.load(Server.class.getResourceAsStream("config.ini"));

		/**
		 * 创建测试用户
		 */

		// ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(
		// "users.data"));
		// List<User> users1 = new LinkedList<User>();
		// User user1 = new User();
		// user1.setId("kary");
		// user1.setUsername("Kary");
		// user1.setPassword("kary");
		// User user2 = new User();
		// user2.setId("jason");
		// user2.setUsername("Jason");
		// user2.setPassword("jason");
		// User user3 = new User();
		// user3.setId("james");
		// user3.setUsername("James");
		// user3.setPassword("james");
		// User user4 = new User();
		// user4.setId("kevin");
		// user4.setUsername("Kevin");
		// user4.setPassword("kevin");
		// User user5 = new User();
		// user5.setId("willson");
		// user5.setUsername("Willson");
		// user5.setPassword("willson");
		// User user6 = new User();
		// user6.setId("vera");
		// user6.setUsername("Vera");
		// user6.setPassword("vera");
		// users1.add(user1);
		// users1.add(user2);
		// users1.add(user3);
		// users1.add(user4);
		// users1.add(user5);
		// users1.add(user6);
		// out.writeObject(users1);
		// out.close();

		/**
		 * 读取测试用户至内存，并将在线状态设置为“离线”
		 */
		ObjectInputStream in = new ObjectInputStream(
				Server.class.getResourceAsStream("users.data"));
		
		@SuppressWarnings({ "unchecked" })
		List<User> users = (LinkedList<User>) in.readObject();
		in.close();
		for (User user : users) {
			user.setState("offline");
		}

		/**
		 * 程序Session集，用来保存已登陆的用户以及其对应的Socket
		 */
		Map<String, Socket> session = new LinkedHashMap<String, Socket>();

		/**
		 * 使用配置端口初始化服务器
		 */
		@SuppressWarnings("resource")
		ServerSocket server = new ServerSocket(Integer.parseInt(config
				.getProperty("port")));

		/**
		 * 开始接受客户端请求
		 */
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
