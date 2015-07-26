package com.oocl.kary.kk.server.action;

import java.net.Socket;
import java.util.List;
import java.util.Map;
import com.oocl.kary.kk.server.model.KPacket;
import com.oocl.kary.kk.server.model.User;
import com.oocl.kary.kk.server.service.Request;

public class LoginAction implements Action {

	@Override
	public void execute(KPacket packet, Socket socket, List<User> users,
			Map<String, Socket> session) {
		/**
		 * 登录请求
		 */
		User user = GSON.fromJson(packet.body.toString(), User.class);
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
				KPacket brocastPacket = new KPacket("getuser");
				brocastPacket.body = users;
				for (String key : session.keySet()) {
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

		KPacket resultPacket = new KPacket("login");
		resultPacket.body = resultPacket.new LoginBody(msg);

		if (msg.equals("success")) {
			resultPacket.token = socket.toString();
			System.out.println("Logined:\t" + socket.toString()
					+ "\tOnline num: " + session.size());
		}

		/**
		 * 为Socket用户发送返回包
		 */
		new Thread(new Request(socket, resultPacket)).start();
	}

}
