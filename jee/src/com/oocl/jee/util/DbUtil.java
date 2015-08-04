package com.oocl.jee.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.commons.dbcp.BasicDataSource;

public class DbUtil {
	private static Properties config;
	private static BasicDataSource dataSource;
	static {
		config = new Properties();
		try {
			config.load(DbUtil.class.getResourceAsStream("config.ini"));
			dataSource = new BasicDataSource();
			dataSource.setDriverClassName(config.getProperty("driverClass"));
			dataSource.setUrl(config.getProperty("url"));
			dataSource.setPassword(config.getProperty("password"));
			dataSource.setUsername(config.getProperty("user"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static Connection getConnection() {
		Connection con = null;
		try {
			Class.forName(config.getProperty("driverClass")).newInstance();
			con = dataSource.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	public static Connection getConnectionFromDataSource() {
		Connection con = null;
		try {
			con = dataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}

	public static void free(Connection con, Statement stmt, ResultSet rs) {
		try {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			if (con != null)
				con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
