package com.oocl.o2o.dao.impl;

import com.oocl.o2o.dao.*;
import com.oocl.o2o.pojo.*;
import com.oocl.o2o.util.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Aquariuslt
 * @version 15-08-05
 */
public class UserDao extends Db implements Dao<User> {
	public static final String USER_INSERT_SQL = "INSERT INTO USERS (USERNAME,PASSWORD,IDCARD,LICENSE,TEL,ROLE,STATUSID) VALUES (?,?,?,?,?,?,?)";
	public static final String USER_DELETE_SQL = "UPDATE USERS SET STATUSID =? WHERE USERID =?";
	public static final String USER_UPDATE_SQL = "UPDATE USERS SET USERNAME=?,PASSWORD=?,IDCARD=?,LICENSE=?,TEL=?,ROLE=?,STATUSID=? WHERE ID=?";

	public Integer insert(User user) {
		int result = 0;
		try {
			connect();
			PreparedStatement statement = connection.prepareStatement(USER_INSERT_SQL);
			statement.setString(1, user.getUserName());
			statement.setString(2, user.getPassWord());
			statement.setString(3, user.getIdCard());
			statement.setString(4, user.getLicense());
			statement.setString(5, user.getTel());
			statement.setString(6, user.getRole());
			statement.setInt(7, user.getStatusId());
			result = statement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return result;
	}

	public Integer delete(User user) {
		int result = 0;
		try {
			connect();
			PreparedStatement statement = connection.prepareStatement(USER_DELETE_SQL);
			statement.setInt(1, Constants.STATUS_DELETED);
			statement.setInt(2, user.getUserId());
			result = statement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}

		return result;

	}

	public Integer deleteAll(List<User> userList) {
		int result = 0;
		try {
			connect();
			for (User user : userList) {
				PreparedStatement statement = connection.prepareStatement(USER_INSERT_SQL);
				statement.setInt(1, Constants.STATUS_DELETED);
				statement.setInt(2, user.getUserId());
				result += statement.executeUpdate();
			}
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return result;
	}

	public Integer update(User user) {
		int result = 0;
		try {
			connect();
			PreparedStatement statement = connection.prepareStatement(USER_UPDATE_SQL);
			statement.setInt(1, user.getUserId());
			statement.setString(2, user.getUserName());
			statement.setString(3, user.getPassWord());
			statement.setString(4, user.getIdCard());
			statement.setString(5, user.getLicense());
			statement.setString(6, user.getRole());
			statement.setInt(7, user.getStatusId());
			result = statement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return result;
	}

	public Integer updateAll(List<User> userList) {
		int result = 0;
		try {
			connect();
			for (User user : userList) {
				PreparedStatement statement = connection.prepareStatement(USER_UPDATE_SQL);
				statement.setInt(1, user.getUserId());
				statement.setString(2, user.getUserName());
				statement.setString(3, user.getPassWord());
				statement.setString(4, user.getIdCard());
				statement.setString(5, user.getLicense());
				statement.setString(6, user.getRole());
				statement.setInt(7, user.getStatusId());
				result += statement.executeUpdate();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return result;
	}

	public User findById(Integer userId) {
		User user = null;
		try {
			connect();
			String sql = "SELECT * FROM USERS WHERE USERID=?";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, userId);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				user = this.buildUserFromResultSet(resultSet);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return user;
	}

	public User findByName(String userName) {
		User user = null;
		try {
			connect();
			String sql = "SELECT * FROM USERS WHERE USERNAME=?";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, userName);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				user = this.buildUserFromResultSet(resultSet);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return user;
	}

	public List<User> findAll() {
		List<User> userList = new ArrayList<>();
		try {
			connect();
			String sql = "SELECT * FROM USERS";
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				userList.add(this.buildUserFromResultSet(resultSet));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return userList;
	}

	public List<User> findAllByCriteria(SearchCriteria searchCriteria) {
		List<User> userList = new ArrayList<>();
		try {
			connect();
			PreparedStatement statement = connection.prepareStatement(searchCriteria.buildSQL("USERs"));
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				userList.add(this.buildUserFromResultSet(resultSet));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return userList;
	}

	private User buildUserFromResultSet(ResultSet resultSet) throws SQLException {
		User user = new User();
		user.setUserId(resultSet.getInt("USERID"));
		user.setUserName(resultSet.getString("USERNAME"));
		user.setPassWord(resultSet.getString("PASSWORD"));
		user.setIdCard(resultSet.getString("IDCARD"));
		user.setLicense(resultSet.getString("LICENSE"));
		user.setTel(resultSet.getString("TEL"));
		user.setRole(resultSet.getString("ROLE"));
		user.setStatusId(resultSet.getInt("STATUSID"));
		return user;
	}

	@Override
	public Integer insertAll(List<User> t) {
		// TODO Auto-generated method stub
		return null;
	}

}
