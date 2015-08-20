package com.oocl.o2o.pojo;

import java.util.Date;
import java.util.Map;

public class Order {
	private Integer orderId;
	private Integer userId;
	private Date orderTime;
	private Map<Integer, Integer> foodMap;
	public Integer getOrderId() {
		return orderId;
	}
	public Map<Integer, Integer> getFoodMap() {
		return foodMap;
	}
	public void setFoodMap(Map<Integer, Integer> foodMap) {
		this.foodMap = foodMap;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Date getOrderTime() {
		return orderTime;
	}
	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}
}
