package com.oocl.kary.kk.server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

import com.google.gson.Gson;
import com.oocl.kary.kk.model.KPacket;

public class ServerOut implements Runnable {

	private Socket socket;
	private KPacket packet;
	private String json;

	public ServerOut(Socket socket, KPacket packet) {
		this.socket = socket;
		this.packet = packet;
	}

	public ServerOut(Socket socket, String json) {
		this.socket = socket;
		this.json = json;
	}

	@Override
	public void run() {
		PrintWriter out = null;
		if (packet != null) {
			try {
				out = new PrintWriter(socket.getOutputStream());
				Gson gson = new Gson();
				String json = gson.toJson(packet, packet.getClass());
				out.println(json);
				out.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else if (json != null) {
			try {
				out = new PrintWriter(socket.getOutputStream());
				out.println(json);
				out.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

}
