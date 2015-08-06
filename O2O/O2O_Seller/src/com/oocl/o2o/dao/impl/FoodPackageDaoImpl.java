package com.oocl.o2o.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

import com.oocl.o2o.dao.*;
import com.oocl.o2o.pojo.FoodPackage;

/**
 * @author Aquariuslt
 * @version 15-08-05
 */
public class FoodPackageDaoImpl extends BaseDao implements FoodPackageDao {
	public static final String FOOD_PACKAGE_INSERT_SQL = "INSERT INTO FOOD_PACKAGE (FOODID,PACKAGEID) VALUES(?,?)";

	public int add(FoodPackage foodPackage) {
		int result = 0;
		try {
			connect();
			PreparedStatement statement = connection.prepareStatement(FOOD_PACKAGE_INSERT_SQL);
			statement.setInt(1, foodPackage.getFoodId());
			statement.setInt(2, foodPackage.getPackageId());
			result = statement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return result;
	}

	public int deleteByPackageId(int id) {
		int result = 0;
		try {
			connect();
			String sql = "delete from food_package where packageId=?";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, id);
			result = statement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return result;
	}

	public List<FoodPackage> findByPackageId(int id) {
		List<FoodPackage> result = new LinkedList<FoodPackage>();
		try {
			connect();
			String sql = "select * from food_package where PackageId=?";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, id);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				FoodPackage fp = new FoodPackage(rs.getInt("foodId"), rs.getInt("packageId"));
				result.add(fp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return result;
	}
}
