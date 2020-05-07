package com.spring.parent.controller.reflect.entity;

public class UserEntity {
	private String id;
	private String name;
	
	public UserEntity() {
		System.out.println("反射调用无参构造函数-- ");
	}
	public UserEntity(String id) {
		System.out.println("id="+id);
	}
	public UserEntity(String id,String name) {
		System.out.println(id+"---------"+name);
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}
