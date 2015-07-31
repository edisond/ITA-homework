package com.oocl.kk.server.action.impl;

import java.net.Socket;
import java.util.List;
import java.util.Map;

import com.oocl.kary.pojo.Packet;
import com.oocl.kary.pojo.User;
import com.oocl.kk.server.action.Action;
import com.oocl.kk.server.service.Request;

public class GetUserAction implements Action {

	@Override
	public void execute(Packet packet,Socket socket, List<User> users,
			Map<Integer, Socket> session) {
		Packet resultPacket = new Packet("getuser");
		resultPacket.setBody(users);
		new Thread(new Request(socket, resultPacket))
				.start();

	}

}
