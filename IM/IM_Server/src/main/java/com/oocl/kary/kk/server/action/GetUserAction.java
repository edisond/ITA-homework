package com.oocl.kary.kk.server.action;

import java.net.Socket;
import java.util.List;
import java.util.Map;

import com.oocl.kary.kk.server.model.KPacket;
import com.oocl.kary.kk.server.model.User;
import com.oocl.kary.kk.server.service.Request;

public class GetUserAction implements Action {

	@Override
	public void execute(KPacket packet,Socket socket, List<User> users,
			Map<String, Socket> session) {
		KPacket resultPacket = new KPacket("getuser");
		resultPacket.body = users;
		new Thread(new Request(socket, resultPacket))
				.start();

	}

}
