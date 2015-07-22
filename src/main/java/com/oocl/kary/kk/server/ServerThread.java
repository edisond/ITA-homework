package com.oocl.kary.kk.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import com.google.gson.Gson;
import com.oocl.kary.kk.model.KPacket;
import com.oocl.kary.kk.model.LoginBody;

public class ServerThread implements Runnable {
	private Socket socket;
	private static final Gson gson = new Gson();

	public ServerThread(Socket socket) {
		this.socket = socket;
	}

	public void run() {
		BufferedReader in = null;
		PrintWriter out = null;
		try {
			in = new BufferedReader(new InputStreamReader(
					socket.getInputStream()));
			out = new PrintWriter(socket.getOutputStream());
			String json = in.readLine();
			KPacket packet = gson.fromJson(json, KPacket.class);

			switch (packet.getType()) {
			case "login":
				
				KPacket resultPacket = new KPacket("login", new LoginBody(
						"success"));
				out.println(gson.toJson(resultPacket, resultPacket.getClass()));
				out.flush();
				break;
			default:
				break;
			}

			out.println(gson.toJson(packet, KPacket.class));
			out.flush();

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			out.close();
		}

	}
}
