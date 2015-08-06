package com.oocl.o2o.dao.impl;

import com.oocl.o2o.dao.BaseDao;
import com.oocl.o2o.dao.StatusDao;
import com.oocl.o2o.pojo.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Aquariuslt
 * @version 15-08-05
 */
public class StatusDaoImpl extends BaseDao implements StatusDao {
	public Status getById(Integer statusId) {
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
}
