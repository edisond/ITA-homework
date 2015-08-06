package com.oocl.o2o.dao;

import java.util.List;

import com.oocl.o2o.pojo.Food;
import com.oocl.o2o.util.SearchCriteria;

/**
 * @author Aquariuslt
 * @version 15-08-05
 */
public interface FoodDao {
	int addFood(Food food);

	int addFoodList(List<Food> foodList);

	int deleteFood(Food food);

	int deleteFoodList(List<Food> foodList);

	int updateFood(Food food);

	int updateFoodList(List<Food> foodList);

	Food getById(Integer foodId);

	List<Food> findAllByUserId(Integer userId);

	List<Food> findAll();

	List<Food> findAllByCriteria(SearchCriteria searchCriteria);
}
