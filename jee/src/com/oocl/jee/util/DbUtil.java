package com.oocl.jee.util;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.dbcp.BasicDataSource;

public class DbUtil {

	private static final String DRIVER_CLASS = "com.mysql.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost:3306/kary";
	private static final String USER = "root";
	private static final String PSW = "3363";
	private static BasicDataSource dataSource;
	static {
		dataSource = new BasicDataSource();
		dataSource.setDriverClassName(DRIVER_CLASS);
		dataSource.setUrl(URL);
		dataSource.setPassword(PSW);
		dataSource.setUsername(USER);
	}

	public static Connection getConnection() {
		Connection con = null;
		try {
			Class.forName(DRIVER_CLASS).newInstance();
			con = DriverManager.getConnection(URL, USER, PSW);
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
