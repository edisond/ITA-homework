package com.oocl.kk.client.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.oocl.kary.pojo.Packet;
import com.oocl.kk.client.action.Action;
import com.oocl.kk.client.action.ChatAction;
import com.oocl.kk.client.action.GetUserAction;
import com.oocl.kk.client.action.GroupChatAction;
import com.oocl.kk.client.action.ShakeAction;
import com.oocl.kk.client.ui.MainFrame;

public class Response implements Runnable {
	private MainFrame frame;
	@SuppressWarnings("rawtypes")
	private Map<String, Class> actionMap;

	@SuppressWarnings("rawtypes")
	public Response(MainFrame frame) {
		this.frame = frame;
		this.actionMap = new HashMap<String, Class>();
		actionMap.put(Action.CHAT, ChatAction.class);
		actionMap.put(Action.GROUP_CHAT, GroupChatAction.class);
		actionMap.put(Action.SHAKE, ShakeAction.class);
		actionMap.put(Action.GET_USER, GetUserAction.class);
	}

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

				System.out.println("GET:\t" + json);

				Packet packet = gson.fromJson(json, Packet.class);

				/**
				 * 根据请求包的类型做出不同响应
				 */
				Class<?> actionClass = actionMap.get(packet.getType());
				Action action = (Action) actionClass.newInstance();
				action.execute(packet, frame);

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
