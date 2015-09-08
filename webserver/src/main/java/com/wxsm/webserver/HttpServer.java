package com.wxsm.webserver;

import java.io.FileInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Properties;

public class HttpServer {
	private ServerSocket serverSocket;
	private String root;
	private int port;

	public HttpServer() throws Exception {
		Properties properties = new Properties();
		properties.load(new FileInputStream("config.ini"));
		this.root = properties.getProperty("root");
		this.port = Integer.parseInt(properties.getProperty("port"));
	}

	public void start() throws Exception {
		serverSocket = new ServerSocket(port);
		while (true) {
			Socket socket = serverSocket.accept();
			
			new Thread(new ServerThread(socket, root)).start();
		}
	}
}
