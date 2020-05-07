package com.spring.parent.controller.design.factory;


/**
 * 简单工厂模式
 * 创建者和调用者分离
 * 
 * @author Administrator
 *
 */
public class FactoryDemo {
	public Car getCar(String name) {
		Car car=null;
		if(name.equals("奥迪")) {
			car=new Aodi();
		}else if(name.equals("奔驰")) {
			car=new BenChi();
		}
		return car;
	}
}
