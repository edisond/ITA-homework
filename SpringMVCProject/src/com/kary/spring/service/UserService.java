package com.kary.spring.service;

import com.kary.spring.dao.IUserDao;
import com.kary.spring.pojo.User;

public class UserService {
	private IUserDao dao;

	public UserService(IUserDao dao) {
		super();
		this.dao = dao;
	}

	public User doLogin(String name, String password) {
		return dao.findByNameAndPassword(name, password);
	}
}
