package com.oocl.o2o.dao;



import java.util.List;
import com.oocl.o2o.pojo.FoodType;

/**
 * @author Aquariuslt
 * @version 15-08-06
 */
public interface FoodTypeDao {
    FoodType getById(Integer foodTypeId);
    List<FoodType> findAll();
}
