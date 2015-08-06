package com.oocl.o2o.dao.impl;

import com.oocl.o2o.dao.*;
import com.oocl.o2o.pojo.*;
import com.oocl.o2o.util.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Aquariuslt
 * @version 15-08-05
 */
public class FoodDaoImpl extends BaseDao implements FoodDao {
	public static final String FOOD_INSERT_SQL = "INSERT INTO FOOD (FOODNAME,PRICE,PICTURE_URL,STATUSID,FOODTYPEID,USERID) VALUES(?,?,?,?,?,?)";
	public static final String FOOD_DELETE_SQL = "UPDATE FOOD SET STATUSID=? WHERE FOODID=?";
	public static final String FOOD_UPDATE_SQL = "UPDATE FOOD SET FOODNAME=?,PRICE=?,PICTURE_URL=?,STATUSID=?,FOODTYPEID=?,USERID=? WHERE FOODID =?";

	public int addFood(Food food) {
		int result = 0;
		try {
			connect();
			PreparedStatement statement = connection.prepareStatement(FOOD_INSERT_SQL);
			statement.setString(1, food.getFoodName());
			statement.setDouble(2, food.getPrice());
			statement.setString(3, food.getPictureUrl());
			statement.setInt(4, food.getStatusId());
			statement.setInt(5, food.getFoodTypeId());
			statement.setInt(6, food.getUserId());

			result = statement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}

		return result;
	}

	public int addFoodList(List<Food> foodList) {
		int result = 0;
		try {
			connect();
			for (Food food : foodList) {
				PreparedStatement statement = connection.prepareStatement(FOOD_INSERT_SQL);
				statement.setString(1, food.getFoodName());
				statement.setDouble(2, food.getPrice());
				statement.setString(3, food.getPictureUrl());
				statement.setInt(4, food.getStatusId());
				statement.setInt(5, food.getFoodTypeId());
				statement.setInt(6, food.getUserId());
				result += statement.executeUpdate();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return result;
	}

	public int deleteFood(Food food) {
		int result = 0;
		try {
			connect();
			PreparedStatement statement = connection.prepareStatement(FOOD_DELETE_SQL);
			statement.setInt(1, Constants.STATUS_DELETED);
			statement.setInt(2, food.getFoodId());
			result = statement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}

		return result;
	}

	public int deleteFoodList(List<Food> foodList) {
		int result = 0;
		try {
			connect();
			for (Food food : foodList) {
				PreparedStatement statement = connection.prepareStatement(FOOD_DELETE_SQL);
				statement.setInt(1, Constants.STATUS_DELETED);
				statement.setInt(2, food.getFoodId());
				result += statement.executeUpdate();
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}

		return result;
	}

	public int updateFood(Food food) {
		int result = 0;
		try {
			connect();
			PreparedStatement statement = connection.prepareStatement(FOOD_UPDATE_SQL);
			statement.setString(1, food.getFoodName());
			statement.setDouble(2, food.getPrice());
			statement.setString(3, food.getPictureUrl());
			statement.setInt(4, food.getStatusId());
			statement.setInt(5, food.getFoodTypeId());
			statement.setInt(6, food.getUserId());
			statement.setInt(7, food.getFoodId());
			result = statement.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}

		return result;
	}

	public int updateFoodList(List<Food> foodList) {
		int result = 0;
		try {
			connect();
			for (Food food : foodList) {
				PreparedStatement statement = connection.prepareStatement(FOOD_UPDATE_SQL);
				statement.setString(1, food.getFoodName());
				statement.setDouble(2, food.getPrice());
				statement.setString(3, food.getPictureUrl());
				statement.setInt(4, food.getStatusId());
				statement.setInt(5, food.getFoodTypeId());
				statement.setInt(6, food.getUserId());
				statement.setInt(7, food.getFoodId());
				result += statement.executeUpdate();

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}

		return result;
	}

	public Food getById(Integer foodId) {
		try {
			connect();
			String sql = "SELECT * FROM FOOD WHERE FOODID=?";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, foodId);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				return buildFoodFromResultSet(resultSet);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return null;
	}

	public Food findById(Integer id) {
		try {
			connect();
			String sql = "SELECT * FROM FOOD WHERE FOODID=?";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				return buildFoodFromResultSet(resultSet);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return null;
	}

	public List<Food> findAllByUserId(Integer userId) {
		List<Food> list = new LinkedList<Food>();
		try {
			connect();
			String sql = "SELECT * FROM FOOD WHERE USERID=?";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, userId);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				list.add(buildFoodFromResultSet(resultSet));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return list;
	}

	public List<Food> findAll() {
		List<Food> list = new LinkedList<Food>();
		try {
			connect();
			String sql = "SELECT * FROM FOOD";
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				list.add(buildFoodFromResultSet(resultSet));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return list;
	}

	private Food buildFoodFromResultSet(ResultSet resultSet) throws SQLException {
		Food food = new Food();
		food.setFoodId(resultSet.getInt("FOODID"));
		food.setFoodName(resultSet.getString("FOODNAME"));
		food.setPrice(resultSet.getDouble("PRICE"));
		food.setPictureUrl(resultSet.getString("PICTURE_URL"));
		food.setStatusId(resultSet.getInt("STATUSID"));
		food.setUserId(resultSet.getInt("USERID"));
		food.setFoodTypeId(resultSet.getInt("FOODTYPEID"));
		return food;
	}

	public List<Food> findAllByCriteria(SearchCriteria searchCriteria) {
		List<Food> list = new LinkedList<Food>();
		try {
			connect();
			PreparedStatement statement = connection.prepareStatement(searchCriteria.buildSQL("FOOD"));
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				list.add(buildFoodFromResultSet(resultSet));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return list;
	}
}
