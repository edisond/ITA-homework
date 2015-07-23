package com.oocl.kary.kk.client;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

import com.google.gson.Gson;
import com.oocl.kary.kk.model.KPacket;

public class ClientOut implements Runnable {

	private Socket socket;
	private KPacket packet;

	public ClientOut(Socket socket, KPacket packet) {
		this.socket = socket;
		this.packet = packet;
	}

	@Override
	public void run() {
		PrintWriter out = null;
		try {
			out = new PrintWriter(socket.getOutputStream());
			Gson gson = new Gson();
			String json = gson.toJson(packet, packet.getClass());
			out.println(json);
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
