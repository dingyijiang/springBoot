package com.spring.parent.controller.zhujie.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义注解   使用@interface
 * 
 * --- @Target  表示允许注解的使用范围   比如 方法  类  
 * --- @Retention 表示允许反射获取信息    被描述的注解在什么范围内有效
 * @author Administrator
 *
 */
@Target(value=ElementType.METHOD)//只能在方法上使用
@Retention(RetentionPolicy.RUNTIME)//该注解用于反射
public @interface AnnotationDemo {
	
	String value() default "";//传入的值
	int classId() default 0;
	String[] arr() default {"111","222"};
}

class AnnDemo{
	private String name;
	@AnnotationDemo(value="dingyijiang",classId=19,arr= {"111","222"})    //ElementType.METHOD
	private  void add() {
		
	}
}
