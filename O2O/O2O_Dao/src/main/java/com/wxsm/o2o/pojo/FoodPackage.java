package com.wxsm.o2o.pojo;

/**
 * @author Aquariuslt
 * @version 15-08-05
 */
public class FoodPackage {

    private Integer foodId;
    private Integer packageId;


    public Integer getFoodId() {
        return foodId;
    }

    public FoodPackage(Integer foodId, Integer packageId) {
		super();
		this.foodId = foodId;
		this.packageId = packageId;
	}

	public void setFoodId(Integer foodId) {
        this.foodId = foodId;
    }

    public Integer getPackageId() {
        return packageId;
    }

    public void setPackageId(Integer packageId) {
        this.packageId = packageId;
    }
}
