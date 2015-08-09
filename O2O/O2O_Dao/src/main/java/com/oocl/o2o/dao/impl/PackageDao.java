package com.oocl.o2o.dao.impl;

import com.oocl.o2o.dao.*;
import com.oocl.o2o.pojo.Package;
import com.oocl.o2o.util.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Aquariuslt
 * @version 15-08-05
 */
public class PackageDao extends Db implements Dao<Package> {
	public static final String PACKAGE_INSERT_SQL = "INSERT INTO PACKAGE (PACKAGENAME,PRICE,STATUSID,USERID) VALUES (?,?,?,?)";
	public static final String PACKAGE_DELETE_SQL = "UPDATE PACKAGE SET STATUSID = ? WHERE PACKAGEID = ?";
	public static final String PACKAGE_UPDATE_SQL = "UPDATE PACKAGE SET PACKAGENAME=?,PRICE=?,STATUSID=?,USERID=? WHERE PACKAGEID = ?";

	public Integer insert(Package pkg) {
		int result = 0;
		try {
			connect();
			PreparedStatement statement = connection.prepareStatement(PACKAGE_INSERT_SQL, Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, pkg.getPackageName());
			statement.setDouble(2, pkg.getPrice());
			statement.setInt(3, pkg.getStatusId());
			statement.setInt(4, pkg.getUserId());
			statement.executeUpdate();
			ResultSet rs = statement.getGeneratedKeys();
			if (rs.next()) {
				result = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return result;
	}

	public Integer delete(Package pkg) {
		int result = 0;
		try {
			connect();
			PreparedStatement statement = connection.prepareStatement(PACKAGE_DELETE_SQL);
			statement.setInt(1, Constants.STATUS_DELETED);
			statement.setInt(2, pkg.getPackageId());
			result = statement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return result;
	}

	public Integer deleteAll(List<Package> packageList) {
		int result = 0;
		try {
			connect();
			for (Package pkg : packageList) {
				PreparedStatement statement = connection.prepareStatement(PACKAGE_DELETE_SQL);
				statement.setInt(1, Constants.STATUS_DELETED);
				statement.setInt(2, pkg.getPackageId());
				result += statement.executeUpdate();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return result;
	}

	public Integer update(Package pkg) {
		int result = 0;
		try {
			connect();
			PreparedStatement statement = connection.prepareStatement(PACKAGE_UPDATE_SQL);
			statement.setString(1, pkg.getPackageName());
			statement.setDouble(2, pkg.getPrice());
			statement.setInt(3, pkg.getStatusId());
			statement.setInt(4, pkg.getUserId());
			statement.setInt(5, pkg.getPackageId());
			result = statement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}

		return result;
	}

	public Integer updateAll(List<Package> packageList) {
		int result = 0;
		try {
			connect();
			for (Package pkg : packageList) {
				PreparedStatement statement = connection.prepareStatement(PACKAGE_UPDATE_SQL);
				statement.setString(1, pkg.getPackageName());
				statement.setDouble(2, pkg.getPrice());
				statement.setInt(3, pkg.getStatusId());
				statement.setInt(4, pkg.getUserId());
				result += statement.executeUpdate();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}

		return result;
	}

	public Package findById(Integer packageId) {
		Package pkg = null;
		try {
			connect();
			String sql = "SELECT * FROM PACKAGE WHERE PACKAGEID = ?";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, packageId);

			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				pkg = this.buildPackageFromResultSet(resultSet);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}

		return pkg;
	}

	public List<Package> findAllByUserId(Integer userId) {
		List<Package> packageList = new ArrayList<>();
		try {
			connect();
			String sql = "SELECT * FROM PACKAGE WHERE USERID = ?";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, userId);
			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {
				packageList.add(this.buildPackageFromResultSet(resultSet));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}

		return packageList;
	}

	public List<Package> findAllByCriteria(SearchCriteria searchCriteria) {
		List<Package> list = new LinkedList<Package>();
		try {
			connect();
			PreparedStatement statement = connection.prepareStatement(searchCriteria.buildSQL("PACKAGE"));
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				list.add(buildPackageFromResultSet(resultSet));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return list;
	}

	private Package buildPackageFromResultSet(ResultSet resultSet) throws SQLException {
		Package pkg = new Package();
		pkg.setPackageId(resultSet.getInt("PACKAGEID"));
		pkg.setPackageName(resultSet.getString("PACKAGENAME"));
		pkg.setPrice(resultSet.getDouble("PRICE"));
		pkg.setStatusId(resultSet.getInt("STATUSID"));
		pkg.setUserId(resultSet.getInt("USERID"));
		return pkg;
	}

	@Override
	public Integer insertAll(List<Package> t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Package> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
}
