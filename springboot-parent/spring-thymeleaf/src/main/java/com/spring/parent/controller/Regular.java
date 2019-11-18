package com.spring.parent.controller;

import java.util.regex.Pattern;

/**
 * 	正则表达式学习
 * ^   以什么开始
 * \d+ 匹配一个或多个数组
 * 	？     设置括号内的选项是可选的
 *  \. 匹配"."
 *  
 * @author Administrator
 *
 */
public class Regular {
	public static void main(String[] args) {
		String content = "I am noob " + "from runoob.com.";
		String pattern = ".*runoob.*";
		boolean isMatch = Pattern.matches(pattern, content);
	    System.out.println("字符串中是否包含了 'runoob' 子字符串? " + isMatch);

	}
}
