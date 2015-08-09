package com.oocl.o2o.dao;

import java.sql.Connection;
import java.sql.SQLException;

import com.oocl.o2o.util.DBUtil;

/**
 * @author Aquariuslt
 * @version 15-08-05
 */
public class Db {
	public Connection connection;

	public void connect() {
		connection = DBUtil.getConnection();
		try {
			connection.setAutoCommit(false);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void disconnect() {
		try {
			connection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBUtil.freeConnection(connection);
	}

}
