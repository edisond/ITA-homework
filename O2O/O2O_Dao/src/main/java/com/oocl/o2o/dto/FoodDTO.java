package com.oocl.o2o.dto;

public class FoodDTO {
	private Integer foodId;
	private String foodName;
	private Double price;
	private String pictureUrl;
	private Integer statusId;
	private Integer foodTypeId;
	private Integer userId;
	public Integer getFoodId() {
		return foodId;
	}
	public void setFoodId(Integer foodId) {
		this.foodId = foodId;
	}
	public String getFoodName() {
		return foodName;
	}
	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public String getPictureUrl() {
		return pictureUrl;
	}
	public void setPictureUrl(String pictureUrl) {
		this.pictureUrl = pictureUrl;
	}
	public Integer getStatusId() {
		return statusId;
	}
	public void setStatusId(Integer statusId) {
		this.statusId = statusId;
	}
	public Integer getFoodTypeId() {
		return foodTypeId;
	}
	public void setFoodTypeId(Integer foodTypeId) {
		this.foodTypeId = foodTypeId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getFoodTypeName() {
		return foodTypeName;
	}
	public void setFoodTypeName(String foodTypeName) {
		this.foodTypeName = foodTypeName;
	}
	public byte[] getPictureBody() {
		return pictureBody;
	}
	public void setPictureBody(byte[] pictureBody) {
		this.pictureBody = pictureBody;
	}
	private String foodTypeName;
	private byte[] pictureBody;
	private String pictureBodyBase64;
	public String getPictureBodyBase64() {
		return pictureBodyBase64;
	}
	public void setPictureBodyBase64(String pictureBodyBase64) {
		this.pictureBodyBase64 = pictureBodyBase64;
	}
}
