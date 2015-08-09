package com.oocl.o2o.dao.impl;

import com.oocl.o2o.dao.*;
import com.oocl.o2o.pojo.*;
import com.oocl.o2o.util.SearchCriteria;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Aquariuslt
 * @version 15-08-06
 */

public class FoodTypeDao extends Db implements Dao<FoodType> {

	public FoodType findById(Integer foodTypeId) {
		FoodType foodType = null;
		try {
			connect();
			String sql = "SELECT * FROM FOODTYPE WHERE FOODTYPEID =?";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, foodTypeId);
			ResultSet resultSet = statement.executeQuery();

			if (resultSet.next()) {
				foodType = this.buildFoodTypeFromResultSet(resultSet);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}

		return foodType;
	}

	public List<FoodType> findAll() {
		List<FoodType> foodTypeList = new ArrayList<>();
		try {
			connect();
			String sql = "SELECT * FROM FOOD_TYPE";
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				foodTypeList.add(this.buildFoodTypeFromResultSet(resultSet));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}

		return foodTypeList;
	}

	private FoodType buildFoodTypeFromResultSet(ResultSet resultSet) throws SQLException {
		FoodType foodType = new FoodType();
		foodType.setFoodTypeId(resultSet.getInt("FOODTYPEID"));
		foodType.setFoodTypeName(resultSet.getString("FOODTYPENAME"));
		return foodType;
	}

	@Override
	public Integer insert(FoodType t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer insertAll(List<FoodType> t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer delete(FoodType t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer deleteAll(List<FoodType> t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer update(FoodType t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer updateAll(List<FoodType> t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<FoodType> findAllByCriteria(SearchCriteria criteria) {
		// TODO Auto-generated method stub
		return null;
	}

}
