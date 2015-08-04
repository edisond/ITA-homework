package com.oocl.webserver.util;

public class HttpHeaderGenerator {
	public static String genarateOK(String type) {
		String header = "HTTP/1.1 200 OK\r\n";
		String MIMEType = "";
		switch (type) {
		case "html":
			MIMEType = "text/html";
			break;
		case "image":
			MIMEType = "image/jpeg";
			break;
		default:
			break;
		}
		header += "Content-Type: " + MIMEType + "\r\n";
		header += "Keep-Alive: timeout=5, max=100\r\n";
		header += "Connection: Keep-Alive\r\n";
		return header;
	}

	public static String genarateNotFound() {
		String header = "HTTP/1.1 404 Not Found\r\n";
		header += "Connection: Close\r\n";
		return header;
	}
}
