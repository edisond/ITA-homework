package com.oocl.o2o.dao.impl;

import com.oocl.o2o.dao.Dao;
import com.oocl.o2o.dao.Db;
import com.oocl.o2o.pojo.*;
import com.oocl.o2o.util.SearchCriteria;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Aquariuslt
 * @version 15-08-05
 */
public class StatusDao extends Db implements Dao<Status> {
	public Status findById(Integer statusId) {
		Status status = null;
		try {
			connect();
			String sql = "SELECT * FROM STATUS WHERE STATUSID = ?";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, statusId);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				status = this.buildStatusFromResultSet(resultSet);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return status;
	}

	public List<Status> findAll() {
		List<Status> statusList = new ArrayList<>();
		try {
			connect();
			String sql = "SELECT * FROM STATUS";
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {
				statusList.add(buildStatusFromResultSet(resultSet));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return statusList;
	}

	private Status buildStatusFromResultSet(ResultSet resultSet) throws SQLException {
		Status status = new Status();

		status.setStatusId(resultSet.getInt("STATUSID"));
		status.setStatusName(resultSet.getString("STATUSNAME"));

		return status;
	}

	@Override
	public Integer insert(Status t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer insertAll(List<Status> t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer delete(Status t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer deleteAll(List<Status> t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer update(Status t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer updateAll(List<Status> t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Status> findAllByCriteria(SearchCriteria criteria) {
		// TODO Auto-generated method stub
		return null;
	}
}
