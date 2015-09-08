package com.wxsm.kk.server.action.impl;

import java.net.Socket;
import java.util.List;
import java.util.Map;

import com.oocl.kary.pojo.Packet;
import com.oocl.kary.pojo.User;
import com.wxsm.kk.server.action.Action;
import com.wxsm.kk.server.service.Request;

public class LogoutAction implements Action {

	@Override
	public void execute(Packet packet, Socket socket, List<User> users,
			Map<Integer, Socket> session) {
		
		Integer userId = null;
		synchronized (session) {
			for (Integer key : session.keySet()) {
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
		brocastPacket.setBody(users);
		for (Integer key : session.keySet()) {
			new Thread(new Request(session.get(key), brocastPacket))
					.start();
		}

	}

}
