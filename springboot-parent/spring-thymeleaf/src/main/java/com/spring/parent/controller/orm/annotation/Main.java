package com.spring.parent.controller.orm.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

public class Main {
	public static void main(String[] args) throws ClassNotFoundException {
		//1  项目中使用注解 肯定会用到反射  反射 场景   jdbc  框架  springioc   注解实现
		
		Class<?> forName=Class.forName("com.spring.parent.controller.orm.annotation.User");
//		Annotation[] anns=forName.getAnnotations();//返回该元素上的注解
//		for(Annotation a:anns) {
//			System.out.println(a);
//		}
		StringBuffer buffer=new StringBuffer();
		buffer.append(" select ");
		//获取该实体类中所有元素
		Field[] fields=forName.getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			//循环每一个元素  获取该元素的注解
			SetProperty setPreperty=fields[i].getAnnotation(SetProperty.class);//获取该注解的值
			String name=setPreperty.value();
			buffer.append(name);
			if(i==fields.length-1) {
				buffer.append(" from ");
			}else {
				buffer.append(" , ");
			}
		}
		
		//获取表的名称
		SetTable setTable=forName.getAnnotation(SetTable.class);
		String tableName=setTable.value();
		buffer.append(tableName);
		System.out.println(buffer.toString());
	}
}
