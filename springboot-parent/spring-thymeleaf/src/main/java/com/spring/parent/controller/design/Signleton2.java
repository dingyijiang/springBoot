package com.spring.parent.controller.design;
/**
 * 保证这个类在jvm中只有一个实例
 * @author Administrator
 *
 */
class SingletonDemo{
	//static 在堆内存只会修饰一个      静态变量被多有对象共享   保存在jvm方法区  静态代码块>构造代码块>构造函数
	private static SingletonDemo singletonDemo;
	/**
	 * 构造函数私有化
	 * 反射不能初始化
	 */
	private SingletonDemo(){
		
	}
	/**
	 * 面试就写这个  要记得加上同步
	 * 懒汉式 当你需要的时候，我才会被实例化   之后都是用一个实例   懒汉式 线程不安全 所以需要加上同步
	 * 两个线程同时操作这个方法 可能导致两次new  所以添加synchronized   synchronized 只用添加会导致同步的代码上
	 * 
	 * 静态static加到方法上 并使用synchronized关键字修饰        等同于 synchronized(类.class)
	 * @return
	 */
	public static SingletonDemo get() {
		if(singletonDemo==null) {
			synchronized(SingletonDemo.class) {//静态字节码文件 用class  等同于 类名.class  SingletonDemo.class
				singletonDemo = new SingletonDemo();
			}
		}
		return singletonDemo;
	}
}

/**
 * 饿汉式写单例
 * @author Administrator
 *
 */
class SingletonDemo3 {
	
	//饿汉式 class加载的时候初始化    所以天生同步
	private final static SingletonDemo3 demo3=new SingletonDemo3();
	
	//私有构造函数
	private SingletonDemo3() {
		 
	}
	public static SingletonDemo3 getSingleton() {
		return demo3;
	}
}

/**
 * 单例模式   --懒汉式   和多线程关联起来  装逼   因为多线程可能有同步问题 所以要加synchronized关键字   就会消耗资源
 * 	   --饿汉式  class加载的时候实例化  static  所以天生同步   效率比懒汉式要高 （不用synchronized）  但是因为初始化内存 所有消耗内存
 * 
 * 
 * @author Administrator
 *
 */
public class Signleton2 {
	public static void main(String[] args) {
		SingletonDemo demo1=SingletonDemo.get();
		SingletonDemo demo2=SingletonDemo.get();
		System.out.println(demo1+"-----"+demo2);
		System.out.println(demo1==demo2);
		
		SingletonDemo3 demo3=SingletonDemo3.getSingleton();
		SingletonDemo3 demo4=SingletonDemo3.getSingleton();
		System.out.println(demo3+"-----"+demo4);
		System.out.println(demo3==demo4);
	}
	
	

}
