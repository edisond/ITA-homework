package com.oocl.inputsuggest.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

import com.oocl.inputsuggest.dao.Dao;
import com.oocl.inputsuggest.dao.Db;
import com.oocl.inputsuggest.pojo.Area;
import com.oocl.inputsuggest.util.SearchCriteria;

public class AreaDao extends Db implements Dao<Area> {

	@Override
	public Integer insert(Area t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer insertAll(List<Area> t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer delete(Area t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer deleteAll(List<Area> t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer update(Area t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer updateAll(List<Area> t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Area> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Area> findAllByCriteria(SearchCriteria criteria) {
		List<Area> list = new LinkedList<Area>();
		try {
			connect();
			PreparedStatement statement = connection.prepareStatement(criteria.buildSQL("areas"));
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				Area area = new Area();
				area.setId(resultSet.getInt("id"));
				area.setAreaId(resultSet.getString("areaid"));
				area.setArea(resultSet.getString("area"));
				area.setCityId(resultSet.getString("cityid"));
				list.add(area);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return list;
	}

	public List<Area> findAllByStartWith(String str) {
		List<Area> list = new LinkedList<Area>();
		try {
			connect();
			String sql = "select * from areas where area like ?";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, str + "%");
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				Area area = new Area();
				area.setId(resultSet.getInt("id"));
				area.setAreaId(resultSet.getString("areaid"));
				area.setArea(resultSet.getString("area"));
				area.setCityId(resultSet.getString("cityid"));
				list.add(area);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return list;
	}

}
