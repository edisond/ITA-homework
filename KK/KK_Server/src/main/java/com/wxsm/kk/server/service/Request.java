package com.wxsm.kk.server.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.google.gson.Gson;
import com.oocl.kary.pojo.Packet;

public class Request implements Runnable {

	private Socket socket;
	private Packet packet;
	private String json;

	public Request(Socket socket, Packet packet) {
		this.socket = socket;
		this.packet = packet;
		if (packet.getStamp() == null) {
			SimpleDateFormat packetDateFormat = new SimpleDateFormat(
					"yyyy-MM-dd-hh-mm-ss");
			packet.setStamp(packetDateFormat.format(Calendar.getInstance()
					.getTime()));
		}
	}

	public Request(Socket socket, String json) {
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

				System.out.println("SENT:\t" + json);

				out.println(json);
				out.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else if (json != null) {
			try {
				out = new PrintWriter(socket.getOutputStream());

				System.out.println("SENT:\t" + json);

				out.println(json);
				out.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

}
