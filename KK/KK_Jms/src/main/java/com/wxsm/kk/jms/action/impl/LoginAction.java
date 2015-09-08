package com.wxsm.kk.jms.action.impl;

import com.google.gson.Gson;
import com.oocl.kary.pojo.Packet;
import com.oocl.kary.pojo.User;
import com.oocl.kk.dao.impl.UserDaoImpl;
import com.wxsm.kk.jms.action.Action;
import com.wxsm.kk.jms.util.JmsUtil;

public class LoginAction implements Action {

	public void execute(Packet packet) {
		UserDaoImpl dao = new UserDaoImpl();
		Gson gson = new Gson();

		User userToVerify = gson.fromJson(packet.getBody().toString(),
				User.class);
		User user = dao.find(userToVerify.getId());
		Packet res = new Packet("login");
		res.setToken(packet.getToken());
		if (user != null
				&& user.getPassword().equals(userToVerify.getPassword())) {
			res.setBody(res.new LoginBody("success"));
		} else {
			res.setBody(res.new LoginBody("fail"));
		}
		String json = gson.toJson(res, res.getClass());
		JmsUtil.produce(json);
	}

}
