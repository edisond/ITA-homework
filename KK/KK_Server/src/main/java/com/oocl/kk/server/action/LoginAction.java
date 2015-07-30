package com.oocl.kk.server.action;

import java.net.Socket;
import java.util.List;
import java.util.Map;

import com.oocl.kary.pojo.Packet;
import com.oocl.kary.pojo.User;
import com.oocl.kk.server.service.Request;

public class LoginAction implements Action {

	@Override
	public void execute(Packet packet, Socket socket, List<User> users,
			Map<Integer, Socket> session) {
		/**
		 * 登录请求
		 */
		User user = GSON.fromJson(packet.getBody().toString(), User.class);
		String msg = "fail";

		/**
		 * 寻找匹配用户ID与密码
		 */
		for (User u : users) {
			if (u.getId().equals(user.getId())
					&& u.getPassword().equals(user.getPassword())) {
				msg = "success";

				/**
				 * 将新用户登陆的信息发送给所有其他客户端，以更新用户在线列表
				 */
				Packet brocastPacket = new Packet("getuser");
				brocastPacket.setBody(users);
				for (Integer key : session.keySet()) {
					new Thread(new Request(session.get(key), brocastPacket))
							.start();
				}

				u.setState("online");
				synchronized (session) {
					session.put(user.getId(), socket);
				}
				break;
			}
		}

		Packet resultPacket = new Packet("login");
		resultPacket.setBody(resultPacket.new LoginBody(msg));

		if (msg.equals("success")) {
			resultPacket.setToken(socket.toString());
			System.out.println("Logined:\t" + socket.toString()
					+ "\tOnline num: " + session.size());
		}

		/**
		 * 为Socket用户发送返回包
		 */
		new Thread(new Request(socket, resultPacket)).start();
	}

}
