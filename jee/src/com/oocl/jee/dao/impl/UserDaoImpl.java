package com.oocl.jee.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import com.oocl.jee.dao.Dao;
import com.oocl.jee.pojo.User;
import com.oocl.jee.util.DbUtil;

public class UserDaoImpl implements Dao<User> {

	public static void main(String[] args) {
		UserDaoImpl userDaoImpl = new UserDaoImpl();

		System.out.println(userDaoImpl.findAll(2, 5).size());
	}

	public List<User> findAll(int start, int length) {
		List<User> result = new LinkedList<User>();
		Connection con = DbUtil.getConnection();
		String sql = "select * from users limit ?,?";
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, start);
			pstm.setInt(2, length);
			rs = pstm.executeQuery();
			while (rs.next()) {
				User c = new User();
				c.setId(rs.getInt("id"));
				c.setName(rs.getString("name"));
				c.setPassword(rs.getString("password"));
				c.setBirth(new Date(rs.getDate("birth").getTime()));
				c.setIdCard(rs.getString("idcard"));
				c.setEmail(rs.getString("email"));
				c.setNickName(rs.getString("nickname"));
				c.setSex(rs.getBoolean("sex"));
				result.add(c);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DbUtil.free(con, pstm, rs);
		return result;
	}

	@Override
	public List<User> findAll() {
		List<User> result = new LinkedList<User>();
		Connection con = DbUtil.getConnection();
		String sql = "select * from users";
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			pstm = con.prepareStatement(sql);
			rs = pstm.executeQuery();
			while (rs.next()) {
				User c = new User();
				c.setId(rs.getInt("id"));
				c.setName(rs.getString("name"));
				c.setPassword(rs.getString("password"));
				c.setBirth(new Date(rs.getDate("birth").getTime()));
				c.setIdCard(rs.getString("idcard"));
				c.setEmail(rs.getString("email"));
				c.setNickName(rs.getString("nickname"));
				c.setSex(rs.getBoolean("sex"));
				result.add(c);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DbUtil.free(con, pstm, rs);
		return result;
	}

	@Override
	public User find(int id) {
		User result = null;
		Connection con = DbUtil.getConnection();
		String sql = "select * from users where id=?";
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, id);
			rs = pstm.executeQuery();
			if (rs.next()) {
				result = new User();
				result.setId(rs.getInt("id"));
				result.setName(rs.getString("name"));
				result.setPassword(rs.getString("password"));
				result.setBirth(new Date(rs.getDate("birth").getTime()));
				result.setIdCard(rs.getString("idcard"));
				result.setEmail(rs.getString("email"));
				result.setNickName(rs.getString("nickname"));
				result.setSex(rs.getBoolean("sex"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DbUtil.free(con, pstm, rs);
		return result;
	}

	@Override
	public int add(User user) {
		Connection con = DbUtil.getConnection();
		String sql = "insert into users (name,password,birth,sex,email,nickname,idcard) values (?,?,?,?,?,?,?)";
		PreparedStatement pstm = null;
		int rs = 0;
		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, user.getName());
			pstm.setString(2, user.getPassword());
			pstm.setDate(3, new java.sql.Date(user.getBirth().getTime()));
			pstm.setBoolean(4, user.getSex());
			pstm.setString(5, user.getEmail());
			pstm.setString(6, user.getNickName());
			pstm.setString(7, user.getIdCard());
			rs = pstm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DbUtil.free(con, pstm, null);
		return rs;
	}

	@Override
	public int delete(int id) {
		Connection con = DbUtil.getConnection();
		String sql = "delete from users where id=?";
		PreparedStatement pstm = null;
		int rs = 0;
		try {
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, id);
			rs = pstm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DbUtil.free(con, pstm, null);
		return rs;
	}

	@Override
	public int update(User user) {
		Connection con = DbUtil.getConnection();
		String sql = "update users set name=?,password=?,birth=?,email=?,nickname=?,sex=?,idcard=? where id=?";
		PreparedStatement pstm = null;
		int rs = 0;
		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, user.getName());
			pstm.setString(2, user.getPassword());
			pstm.setDate(3, new java.sql.Date(user.getBirth().getTime()));
			pstm.setString(4, user.getEmail());
			pstm.setString(5, user.getNickName());
			pstm.setBoolean(6, user.getSex());
			pstm.setString(7, user.getIdCard());
			pstm.setInt(8, user.getId());
			rs = pstm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DbUtil.free(con, pstm, null);
		return rs;
	}

	@Override
	public int[] addAll(List<User> list) {
		Connection con = DbUtil.getConnection();
		String sql = "insert into users (name,password,birth,email,nickname,sex,idcard) values (?,?,?,?,?,?)";
		PreparedStatement pstm = null;
		int[] rs = null;
		try {
			pstm = con.prepareStatement(sql);
			for (User user : list) {
				pstm.setString(1, user.getName());
				pstm.setString(2, user.getPassword());
				pstm.setDate(3, new java.sql.Date(user.getBirth().getTime()));
				pstm.setString(4, user.getEmail());
				pstm.setString(5, user.getNickName());
				pstm.setBoolean(6, user.getSex());
				pstm.setString(7, user.getIdCard());
				pstm.addBatch();
			}
			rs = pstm.executeBatch();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DbUtil.free(con, pstm, null);
		return rs;
	}

	public User findByNameAndPassword(String name, String password) {
		User result = null;
		Connection con = DbUtil.getConnection();
		String sql = "select * from users where name=? and password=?";
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, name);
			pstm.setString(2, password);
			rs = pstm.executeQuery();
			if (rs.next()) {
				result = new User();
				result.setId(rs.getInt("id"));
				result.setName(rs.getString("name"));
				result.setPassword(rs.getString("password"));
				result.setBirth(new Date(rs.getDate("birth").getTime()));
				result.setIdCard(rs.getString("idcard"));
				result.setEmail(rs.getString("email"));
				result.setNickName(rs.getString("nickname"));
				result.setSex(rs.getBoolean("sex"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DbUtil.free(con, pstm, rs);
		return result;
	}
}
