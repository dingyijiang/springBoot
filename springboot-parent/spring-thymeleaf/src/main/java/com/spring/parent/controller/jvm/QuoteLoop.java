package com.spring.parent.controller.jvm;
/**
 * 循环引用  -- 可达计数法无法使用的原因
 * @author Administrator
 *　1、代码执行到line6，myObject1的引用计数为2。

　　2、myObject1 = null，myObject1的引用计数为1。

　　3、myObject1=0，垃圾回收器才能进行垃圾回收，myObject1 = myObject2;因为myObject1持有myObject2的引用，而要清除掉这个引用的前提条件是myObject2引用的对象被回收。而myObject2 = myObject1，最终死循环。

　　4、myObject1 和myObject2均为1，导致不能进行垃圾回收。
 */

class User{
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}

public class QuoteLoop {
	public static void main(String[] args) {
		User user1=new User();
		System.out.println("user1="+user1.toString());
		User user2=new User();
		System.out.println("user2="+user2.toString());
		user1=user2;
		System.out.println("user1=user2---user1="+user1.toString());
		System.out.println("user1=user2---user2="+user2.toString());

		user2=user1;
		System.out.println("user2=user1---user1="+user1.toString());
		System.out.println("user2=user1---user2="+user2.toString());
		
		user1=null;
		System.out.println("user1=null---user1="+user1.toString());
		System.out.println("user1=null---user2="+user2.toString());
		user2=null;
		System.out.println("user2=null---user1="+user1.toString());
		System.out.println("user2=null---user2="+user2.toString());

	}
	
}
