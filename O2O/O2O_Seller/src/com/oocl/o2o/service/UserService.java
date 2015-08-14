package com.oocl.o2o.service;

import java.util.List;

import com.oocl.o2o.dao.impl.UserDao;
import com.oocl.o2o.pojo.User;

/**
 * @author Aquariuslt
 * @version 15-08-05
 */
public class UserService {
	private final static UserDao dao = new UserDao();

	public static User doLogin(String name, String psw) {
		User user = dao.findByName(name);
		if (user != null && user.getPassWord().equals(psw)) {
			return user;
		}
		return null;
	}

	public static boolean doRegister(User user) {
		return dao.insert(user) > 0 ? true : false;
	}

	public static List<User> getAllUsers() {
		return dao.findAll();
	}

	public static boolean deleteUserById(int id) {
		User user = new User();
		user.setUserId(id);
		return dao.delete(user) > 0 ? true : false;
	}
}
