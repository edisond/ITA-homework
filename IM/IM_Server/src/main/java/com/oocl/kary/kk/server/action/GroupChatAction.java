package com.oocl.kary.kk.server.action;

import java.net.Socket;
import java.util.List;
import java.util.Map;

import com.oocl.kary.kk.server.model.Packet;
import com.oocl.kary.kk.server.model.User;
import com.oocl.kary.kk.server.service.Request;

public class GroupChatAction implements Action {

	@Override
	public void execute(Packet packet,Socket socket, List<User> users,
			Map<String, Socket> session) {
		String json = GSON.toJson(packet, packet.getClass());
		for (User u : packet.to) {
			if (session.containsKey(u.getId())
					&& !session.get(u.getId()).equals(socket)) {
				new Thread(new Request(
						session.get(u.getId()), json)).start();
			}
		}
	}

}
