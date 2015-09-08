package com.wxsm.o2o.dao.impl;

import java.io.ByteArrayInputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import com.wxsm.o2o.dao.Dao;
import com.wxsm.o2o.dao.Db;
import com.wxsm.o2o.pojo.Image;
import com.wxsm.o2o.util.SearchCriteria;

public class ImageDao extends Db implements Dao<Image> {

	@Override
	public Integer insert(Image t) {
		int result = 0;
		try {
			connect();
			String sql = "insert into images (id,body) values (?,?)";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, t.getId());
			statement.setBinaryStream(2, new ByteArrayInputStream(t.getBody()));
			result = statement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return result;
	}

	@Override
	public Integer insertAll(List<Image> t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer delete(Image t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer deleteAll(List<Image> t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer update(Image t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer updateAll(List<Image> t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Image> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Image> findAllByCriteria(SearchCriteria criteria) {
		// TODO Auto-generated method stub
		return null;
	}

	public Image findById(String id) {
		Image image = null;
		try {
			connect();
			String sql = "select * from images where id=?";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, id);
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				image = new Image(rs.getString("id"), rs.getBytes("body"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return image;
	}

}
