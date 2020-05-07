package com.spring.parent.controller.zhujie;

import java.util.ArrayList;
import java.util.List;

/**
 * object 类的方法   wait toString notify notifyAll hashcode equals
 * JDK内置注解 
 * //重写父类的方法  @Override 该注解 会检查方法名 父类中是否存在 
 * 			   @Deprecated//表示过时的意思
 * 			   @SuppressWarnings("all")//去除警告
 * @author Administrator
 *
 */
public class UserEntity {

	//重写父类的方法  @Override 该注解 会检查方法名 父类中是否存在 
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}
	@Deprecated//表示过时的意思
	public void testDeprecated() {
		
	}
	@SuppressWarnings("all")//去除警告
	public static void main(String[] args) {
		List list1=new ArrayList();//黄线警告的意思
	}
}
