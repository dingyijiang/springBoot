package com.spring.parent.controller.design.factory;

public class Main {
	public static void main(String[] args) {
		FactoryDemo demo=new FactoryDemo();
		Car car1=demo.getCar("奥迪");
		Car car2=demo.getCar("奔驰");
		car1.run();
		car2.run();
	}
}
