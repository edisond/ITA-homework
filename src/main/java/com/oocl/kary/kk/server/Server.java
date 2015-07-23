package com.oocl.kary.kk.server;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.oocl.kary.kk.model.User;

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
		config.load(new FileInputStream("config.ini"));

		/**
		 * 创建测试用户
		 */

//		 ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(
//		 "users.data"));
//		 List<User> users = new LinkedList<User>();
//		 User user1 = new User();
//		 user1.setId("edisond");
//		 user1.setUsername("Edisond");
//		 user1.setPassword("33635468");
//		 User user2 = new User();
//		 user2.setId("admin");
//		 user2.setUsername("Admin");
//		 user2.setPassword("admin");
//		 User user3 = new User();
//		 user3.setId("u3");
//		 user3.setUsername("User3");
//		 user3.setPassword("u3");
//		 User user4 = new User();
//		 user4.setId("u4");
//		 user4.setUsername("User4");
//		 user4.setPassword("u4");
//		 User user5 = new User();
//		 user5.setId("u5");
//		 user5.setUsername("User5");
//		 user5.setPassword("u5");
//		 users.add(user1);
//		 users.add(user2);
//		 users.add(user3);
//		 users.add(user4);
//		 users.add(user5);
//		 out.writeObject(users);

		/**
		 * 读取测试用户至内存，并将在线状态设置为“离线”
		 */
		ObjectInputStream in = new ObjectInputStream(new FileInputStream(
				"users.data"));
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
				new Thread(new Chater(socket, users, session)).start();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
