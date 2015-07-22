package com.oocl.kary.kk.server;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

import com.oocl.kary.kk.model.User;

public class Server {

	public static void main(String[] args) throws Exception {

		Properties config = new Properties();
		config.load(new FileInputStream("config.ini"));

		ObjectInputStream in = new ObjectInputStream(new FileInputStream(
				"users.data"));
		@SuppressWarnings({ "unchecked", "unused" })
		List<User> users = (LinkedList<User>) in.readObject();
		in.close();

		@SuppressWarnings("resource")
		ServerSocket server = new ServerSocket(Integer.parseInt(config
				.getProperty("port")));

		while (true) {
			try {
				Socket socket = server.accept();
				new Thread(new ServerThread(socket)).start();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
