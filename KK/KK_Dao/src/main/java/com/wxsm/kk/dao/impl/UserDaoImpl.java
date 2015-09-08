package com.wxsm.kk.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import com.oocl.kary.pojo.User;
import com.wxsm.kk.dao.Dao;
import com.wxsm.kk.dao.util.DbUtil;

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
				c.setAddr(rs.getString("address"));
				c.setTel(rs.getString("tel"));
				c.setSex(rs.getString("sex"));
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
				c.setAddr(rs.getString("address"));
				c.setTel(rs.getString("tel"));
				c.setSex(rs.getString("sex"));
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
				result.setAddr(rs.getString("address"));
				result.setTel(rs.getString("tel"));
				result.setSex(rs.getString("sex"));
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
		String sql = "insert into users (name,password,birth,address,tel,sex) values (?,?,?,?,?,?)";
		PreparedStatement pstm = null;
		int rs = 0;
		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, user.getName());
			pstm.setString(2, user.getPassword());
			pstm.setDate(3, new java.sql.Date(user.getBirth().getTime()));
			pstm.setString(4, user.getAddr());
			pstm.setString(5, user.getTel());
			pstm.setString(6, user.getSex());
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
		String sql = "update users set name=?,password=?,birth=?,address=?,tel=?,sex=? where id=?";
		PreparedStatement pstm = null;
		int rs = 0;
		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, user.getName());
			pstm.setString(2, user.getPassword());
			pstm.setDate(3, new java.sql.Date(user.getBirth().getTime()));
			pstm.setString(4, user.getAddr());
			pstm.setString(5, user.getTel());
			pstm.setString(6, user.getSex());
			pstm.setInt(7, user.getId());
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
		String sql = "insert into users (name,password,birth,address,tel,sex) values (?,?,?,?,?,?)";
		PreparedStatement pstm = null;
		int[] rs = null;
		try {
			pstm = con.prepareStatement(sql);
			for (User user : list) {
				pstm.setString(1, user.getName());
				pstm.setString(2, user.getPassword());
				pstm.setDate(3, new java.sql.Date(user.getBirth().getTime()));
				pstm.setString(4, user.getAddr());
				pstm.setString(5, user.getTel());
				pstm.setString(6, user.getSex());
				pstm.addBatch();
			}
			rs = pstm.executeBatch();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DbUtil.free(con, pstm, null);
		return rs;
	}
	
	public User findByNameAndPassword(String name,String password){
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
				result.setAddr(rs.getString("address"));
				result.setTel(rs.getString("tel"));
				result.setSex(rs.getString("sex"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DbUtil.free(con, pstm, rs);
		return result;
	}
}
