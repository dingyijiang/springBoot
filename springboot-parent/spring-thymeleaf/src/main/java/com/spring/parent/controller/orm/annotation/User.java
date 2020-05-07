package com.spring.parent.controller.orm.annotation;

@SetTable("user_table")
public class User {
	@SetProperty(value="user_id")
	private String userId;
	@SetProperty(value="user_name")
	private String userName;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
}