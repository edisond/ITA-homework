package com.oocl.webserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Response implements Runnable {
	Socket socket;
	String root;

	public Response(Socket socket, String root) {
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
			if (url.startsWith("GET")) {
				splitResult = url.split(" ");
				filePath = splitResult[1].substring(1);
				Path path = Paths.get(root, filePath);
				String result = null;
				if (!Files.exists(path)) {
					result += "HTTP/1.1 404 Not Found Connection: close";
					try {
						out.write(result.toString().getBytes());
						out.flush();
					} catch (IOException e) {
						e.printStackTrace();
					}
				} else {
					byte[] fileContents = null;
					try {
						fileContents = Files.readAllBytes(path);
					} catch (IOException e) {
						e.printStackTrace();
					}
					if (fileContents != null) {
						result += "HTTP/1.1 200 OK";
						if (filePath.endsWith("html")) {
							result += " Content-Type: text/html; charset=utf-8";
						} else if (filePath.endsWith("jpg") || filePath.endsWith("jpeg")) {
							result += " Content-Type: image/jpeg";
						}
						result += " Keep-Alive: -1";
						result += " ";

						try {
							out.write(result.toString().getBytes());
							out.write(fileContents);
							out.flush();
						} catch (IOException e) {
							e.printStackTrace();
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
