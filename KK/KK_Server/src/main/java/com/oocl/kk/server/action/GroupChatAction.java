package com.oocl.kk.server.action;

import java.net.Socket;
import java.util.List;
import java.util.Map;

import com.oocl.kary.pojo.Packet;
import com.oocl.kary.pojo.User;
import com.oocl.kk.server.service.Request;

public class GroupChatAction implements Action {

	@Override
	public void execute(Packet packet,Socket socket, List<User> users,
			Map<Integer, Socket> session) {
		String json = GSON.toJson(packet, packet.getClass());
		for (User u : packet.getTo()) {
			if (session.containsKey(u.getId())
					&& !session.get(u.getId()).equals(socket)) {
				new Thread(new Request(
						session.get(u.getId()), json)).start();
			}
		}
	}

}
