package com.wxsm.o2o.pojo;

/**
 * @author Aquariuslt
 * @version 15-08-06
 */
public class FoodType {
    private Integer foodTypeId;
    private String foodTypeName;


    public void setFoodTypeId(Integer foodTypeId) {
        this.foodTypeId = foodTypeId;
    }

    public Integer getFoodTypeId() {
        return foodTypeId;
    }

    public String getFoodTypeName() {
        return foodTypeName;
    }

    public void setFoodTypeName(String foodTypeName) {
        this.foodTypeName = foodTypeName;
    }
}
