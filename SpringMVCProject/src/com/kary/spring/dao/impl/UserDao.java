package com.kary.spring.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import com.kary.spring.dao.IDb;
import com.kary.spring.dao.IUserDao;
import com.kary.spring.dao.util.SearchCriteria;
import com.kary.spring.pojo.User;

public class UserDao implements IUserDao {

	private IDb db;

	public UserDao(IDb db) {
		super();
		this.db = db;
	}

	@Override
	public Integer insert(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer insertAll(List<User> users) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer delete(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer deleteAll(List<User> users) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer update(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer updateAll(List<User> users) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> findAllByCriteria(SearchCriteria criteria) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User findByNameAndPassword(String name, String password) {
		User user = null;
		try {
			Connection connection = db.connect();
			String sql = "SELECT * FROM USERS where username=? and password = ?";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, name);
			statement.setString(2, password);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				user = new User(resultSet.getString("username"), resultSet.getString("password"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.disconnect();
		}
		return user;
	}
}
