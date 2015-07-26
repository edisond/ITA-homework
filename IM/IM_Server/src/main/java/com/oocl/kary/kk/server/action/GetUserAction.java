package com.oocl.kary.kk.server.action;

import java.net.Socket;
import java.util.List;
import java.util.Map;

import com.oocl.kary.kk.server.model.Packet;
import com.oocl.kary.kk.server.model.User;
import com.oocl.kary.kk.server.service.Request;

public class GetUserAction implements Action {

	@Override
	public void execute(Packet packet,Socket socket, List<User> users,
			Map<String, Socket> session) {
		Packet resultPacket = new Packet("getuser");
		resultPacket.body = users;
		new Thread(new Request(socket, resultPacket))
				.start();

	}

}
