package com.oocl.kary.kk.server.action;

import java.net.Socket;
import java.util.List;
import java.util.Map;

import com.oocl.kary.kk.server.model.Packet;
import com.oocl.kary.kk.server.model.User;
import com.oocl.kary.kk.server.service.Request;

public class LogoutAction implements Action {

	@Override
	public void execute(Packet packet, Socket socket, List<User> users,
			Map<String, Socket> session) {
		
		String userId = null;
		synchronized (session) {
			for (String key : session.keySet()) {
				if (session.get(key).equals(socket)) {
					session.remove(key);
					userId = key;
					break;
				}
			}
		}

		for (User u : users) {
			if (u.getId().equals(userId)) {
				u.setState("offline");
				break;
			}
		}

		Packet brocastPacket = new Packet("getuser");
		brocastPacket.body = users;
		for (String key : session.keySet()) {
			new Thread(new Request(session.get(key), brocastPacket))
					.start();
		}

	}

}
