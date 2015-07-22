package com.oocl.kary.kk.server;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
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
		/*
		 * ObjectOutputStream out=new ObjectOutputStream(new
		 * FileOutputStream("users.data")); List<User> users=new
		 * LinkedList<User>(); User user1=new User(); user1.setId("edisond");
		 * user1.setUsername("Edisond"); user1.setPassword("33635468"); User
		 * user2=new User(); user2.setId("admin"); user2.setUsername("Admin");
		 * user2.setPassword("admin"); users.add(user1); users.add(user2);
		 * out.writeObject(users);
		 */

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
		 * 程序Session集，用来保存已登陆的用户以及其对应的Token，该Token同时将发送给客户端，
		 * 客户端发出请求时将附带此Token以提供校验
		 */
		Map<String, String> session = new LinkedHashMap<String, String>();

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
				new Thread(new ServerThread(socket, users, session)).start();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
