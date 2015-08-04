package com.oocl.webserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.oocl.webserver.util.HttpHeaderGenerator;

public class ServerThread implements Runnable {
	Socket socket;
	String root;

	public ServerThread(Socket socket, String root) {
		this.socket = socket;
		this.root = root;
	}

	@Override
	public void run() {
		BufferedReader in = null;
		OutputStream out = null;
		try {
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = socket.getOutputStream();
			String url = in.readLine();
			String filePath = null;
			String splitResult[] = null;
			
			System.out.println(url);
			
			if (url.startsWith("GET")) {
				splitResult = url.split(" ");
				filePath = splitResult[1].substring(1);
				Path path = Paths.get(root, filePath);
				if (!Files.exists(path)) {
					try {
						out.write(HttpHeaderGenerator.genarateNotFound().getBytes());
						out.flush();
					} catch (IOException e) {
						e.printStackTrace();
					}
				} else {
					byte[] fileContents = StaticResources.get(path);
					if (fileContents != null) {
						if (filePath.endsWith("html")) {
							try {
								out.write(HttpHeaderGenerator.genarateOK("html").getBytes());
								out.write(fileContents);
								out.flush();
							} catch (IOException e) {
								e.printStackTrace();
							}
						} else if (filePath.endsWith("jpg") || filePath.endsWith("jpeg")) {
							try {
								out.write(HttpHeaderGenerator.genarateOK("image").getBytes());
								out.write(fileContents);
								out.flush();
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
						

					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (in != null) {
					in.close();
				}
				if (out != null) {
					out.close();
				}
				socket.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
