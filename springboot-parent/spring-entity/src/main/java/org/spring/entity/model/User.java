package org.spring.entity.model;

public class User {
	private int id;
	private String name;
	private int age;
	private int teacher;//1 男 2女
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public int getTeacher() {
		return teacher;
	}
	public void setTeacher(int teacher) {
		this.teacher = teacher;
	}
	
	
	
}
