package com.oocl.kk.jms.action.impl;

import java.util.LinkedList;

import com.google.gson.Gson;
import com.oocl.kary.pojo.Packet;
import com.oocl.kary.pojo.User;
import com.oocl.kk.dao.impl.UserDaoImpl;
import com.oocl.kk.jms.action.Action;
import com.oocl.kk.jms.util.JmsUtil;

public class GetUserAction implements Action {

	public void execute(Packet packet) {
		UserDaoImpl dao = new UserDaoImpl();
		Gson gson = new Gson();

		Packet res = new Packet("getuser");
		res.setToken(packet.getToken());
		LinkedList<User> users = (LinkedList<User>) dao.findAll();
		for (User u : users) {
			u.setBirth(null);
			u.setAddr(null);
			u.setSex(null);
			u.setTel(null);
		}
		res.setBody(users);
		String json = gson.toJson(res, res.getClass());
		JmsUtil.produce(json);
	}

}
