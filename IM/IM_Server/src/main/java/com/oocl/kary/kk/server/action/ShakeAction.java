package com.oocl.kary.kk.server.action;

import java.net.Socket;
import java.util.List;
import java.util.Map;

import com.oocl.kary.kk.server.model.KPacket;
import com.oocl.kary.kk.server.model.User;
import com.oocl.kary.kk.server.service.Request;

public class ShakeAction implements Action{

	@Override
	public void execute(KPacket packet,Socket socket, List<User> users,
			Map<String, Socket> session) {
		String json = GSON.toJson(packet, packet.getClass());
		for (String key : session.keySet()) {
			if (key.equals(packet.to[0].getId())) {
				new Thread(new Request(session.get(key),
						json)).start();
				break;
			}
		}
	}

}
