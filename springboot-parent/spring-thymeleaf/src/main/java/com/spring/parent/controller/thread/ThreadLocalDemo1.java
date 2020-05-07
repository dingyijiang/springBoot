package com.spring.parent.controller.thread;


class TestThread1 implements Runnable{
	//private int count=0;
	private ThreadLocal<Integer> count=new ThreadLocal<Integer>() {
		//初始化值
		protected Integer initialValue() {
			return 0;
		}
	};
	
	@Override
	public void run() {
		for (int i = 0; i < 3; i++) {
			add();
			System.out.println(Thread.currentThread().getName()+"----i="+i+"---count="+count.get());
		}
		
	}
	
	public void add() {
		count.set(count.get()+1);
	}
	
}
/**
 * threadLocal  每个线程内部的变量
 * @author Administrator
 *
 */
public class ThreadLocalDemo1 {
	public static void main(String[] args) {
		TestThread1 test=new TestThread1();
		Thread thread1=new Thread(test,"t1");
		Thread thread2=new Thread(test,"t2");
		Thread thread3=new Thread(test,"t3");
		thread1.start();
		thread2.start();
		thread3.start();
	}
}
