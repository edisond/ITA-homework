package com.kary.spring.dao;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbcp.BasicDataSource;

/**
 * @author Aquariuslt
 * @version 15-08-05
 */
public class Db implements IDb {
	private BasicDataSource dataSource;
	protected Connection connection;

	public Db() {
		super();
	}

	public Db(String driverClass, String url, String user, String password) {
		super();
		dataSource = new BasicDataSource();
		dataSource.setDriverClassName(driverClass);
		dataSource.setUrl(url);
		dataSource.setUsername(user);
		dataSource.setPassword(password);
	}

	public Connection connect() {
		try {
			connection = dataSource.getConnection();
			connection.setAutoCommit(false);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}

	public void disconnect() {
		if (connection != null) {
			try {
				connection.commit();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
