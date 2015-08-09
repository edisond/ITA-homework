package com.oocl.o2o.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

import com.oocl.o2o.dao.*;
import com.oocl.o2o.pojo.FoodPackage;
import com.oocl.o2o.util.SearchCriteria;

/**
 * @author Aquariuslt
 * @version 15-08-05
 */
public class FoodPackageDao extends Db implements Dao<FoodPackage> {
	public static final String FOOD_PACKAGE_INSERT_SQL = "INSERT INTO FOOD_PACKAGE (FOODID,PACKAGEID) VALUES(?,?)";

	public Integer insert(FoodPackage foodPackage) {
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

	public Integer deleteByPackageId(Integer id) {
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

	@Override
	public Integer insertAll(List<FoodPackage> t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer delete(FoodPackage t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer deleteAll(List<FoodPackage> t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer update(FoodPackage t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer updateAll(List<FoodPackage> t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<FoodPackage> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<FoodPackage> findAllByCriteria(SearchCriteria criteria) {
		// TODO Auto-generated method stub
		return null;
	}
}
