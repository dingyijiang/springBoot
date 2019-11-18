package com.spring.parent.controller.design;
/**
 * 单例模式 
 * 类的实例对象只能有一个
 * @author Administrator
 *
 */
public class Signleton {
	//private static Signleton signleton;
	private static Signleton signleton = new Signleton();
	private Signleton() {
		
	}
	public static Signleton getInstance() {
//		if(signleton==null) {
//			signleton=new Signleton();
//		}
		return signleton;
	}

}
