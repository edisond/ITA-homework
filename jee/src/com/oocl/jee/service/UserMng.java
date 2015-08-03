package com.oocl.jee.service;

import com.oocl.jee.dao.impl.UserDaoImpl;
import com.oocl.jee.pojo.User;

public class UserMng {
	public static User doLogin(String name, String psw) {
		UserDaoImpl dao = new UserDaoImpl();
		return dao.findByNameAndPassword(name, psw);
	}

	public static boolean doRegister(User user) {
		UserDaoImpl dao = new UserDaoImpl();
		return dao.add(user) > 0 ? true : false;
	}
}
