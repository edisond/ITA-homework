package com.oocl.kk.server.action;

import java.net.Socket;
import java.util.List;
import java.util.Map;

import com.oocl.kary.pojo.Packet;
import com.oocl.kary.pojo.User;
import com.oocl.kk.server.service.Request;

public class ShakeAction implements Action {

	@Override
	public void execute(Packet packet, Socket socket, List<User> users,
			Map<Integer, Socket> session) {
		String json = GSON.toJson(packet, packet.getClass());
		for (Integer key : session.keySet()) {
			if (key.equals(packet.getTo()[0].getId())) {
				new Thread(new Request(session.get(key), json)).start();
				break;
			}
		}
	}

}
