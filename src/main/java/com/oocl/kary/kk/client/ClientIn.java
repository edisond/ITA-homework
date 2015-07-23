package com.oocl.kary.kk.client;

import java.awt.EventQueue;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.oocl.kary.kk.model.KPacket;
import com.oocl.kary.kk.model.Message;
import com.oocl.kary.kk.model.User;
import com.oocl.kary.kk.ui.prototypes.MainFrame;
import com.oocl.kary.kk.ui.prototypes.ShakableJframe;

public class ClientIn implements Runnable {
	private MainFrame frame;

	public ClientIn(MainFrame frame) {
		this.frame = frame;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void run() {
		BufferedReader in = null;
		PrintWriter out = null;
		Gson gson = new Gson();
		Socket socket = frame.getSocket();
		try {
			/**
			 * 初始化读写流
			 */
			in = new BufferedReader(new InputStreamReader(
					socket.getInputStream()));
			out = new PrintWriter(socket.getOutputStream());

			/**
			 * 长连接，在注销时断开
			 */
			while (true) {

				/**
				 * 读取服务端发送的数据包，并转化为对象
				 */
				String json = in.readLine();

				// System.out.println(json);

				KPacket packet = gson.fromJson(json, KPacket.class);

				/**
				 * 根据请求包的类型做出不同响应
				 */
				switch (packet.type) {
				case "getuser": {
					frame.setUsers((LinkedList<User>) gson.fromJson(
							packet.body.toString(),
							new TypeToken<LinkedList<User>>() {
							}.getType()));
					EventQueue.invokeLater(new Runnable() {
						public void run() {
							frame.updateContactContainer();
						}
					});
					break;
				}
				case "chat": {
					Message message = new Message();
					message.body = packet.body.toString();
					message.from = packet.from.getId();
					message.to = packet.to[0].getId();
					message.groupChat = false;
					message.date = Calendar.getInstance();
					frame.getMessages().add(message);
					
					EventQueue.invokeLater(new Runnable() {
						public void run() {
							frame.updateChatContainer();
						}
					});

					break;
				}
				case "shake": {
					ShakableJframe sJframe=new ShakableJframe(frame);
					sJframe.startShake();
					break;
				}
				default:
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				in.close();
				out.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
