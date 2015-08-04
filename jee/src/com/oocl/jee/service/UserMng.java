package com.oocl.jee.service;

import java.util.List;

import com.oocl.jee.dao.impl.UserDaoImpl;
import com.oocl.jee.pojo.User;

public class UserMng {
	private final static UserDaoImpl dao = new UserDaoImpl();

	public static User doLogin(String name, String psw) {
		return dao.findByNameAndPassword(name, psw);
	}

	public static boolean doRegister(User user) {
		return dao.add(user) > 0 ? true : false;
	}

	public static List<User> getAllUsers() {
		return dao.findAll();
	}

	public static User getUserById(int id) {
		return dao.find(id);
	}

	public static void deleteUserById(int id) {
		dao.delete(id);
	}
}
