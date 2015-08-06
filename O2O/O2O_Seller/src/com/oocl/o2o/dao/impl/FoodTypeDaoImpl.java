package com.oocl.o2o.dao.impl;

import com.oocl.o2o.dao.*;
import com.oocl.o2o.pojo.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Aquariuslt
 * @version 15-08-06
 */

public class FoodTypeDaoImpl extends BaseDao implements FoodTypeDao {

	public FoodType getById(Integer foodTypeId) {
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

}
