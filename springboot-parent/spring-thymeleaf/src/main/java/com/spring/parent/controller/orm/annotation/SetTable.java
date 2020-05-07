package com.spring.parent.controller.orm.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 定义注解  表的别名
 * 
 * @author Administrator
 *
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface SetTable {
	String value();
}

