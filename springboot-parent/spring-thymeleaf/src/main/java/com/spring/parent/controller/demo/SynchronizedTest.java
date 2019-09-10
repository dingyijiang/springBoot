package com.spring.parent.controller.demo;

public class SynchronizedTest {
	public static void main(String[] args)
			throws Exception {
		AccountingSyncClass accountingSyncClass = new AccountingSyncClass();
		Thread t1 = new Thread(new AccountingSyncClass());
		Thread t2 = new Thread(new AccountingSyncClass());
		t1.start();
		t2.start();
		t2.join();
		t1.join();
		System.out.println(accountingSyncClass.i);
	}
 
	public void getName2() {
		System.out.println(SynchronizedTest.class.getName());
	}
}

class AccountingSyncClass implements Runnable {
	static int i = 0;
	static boolean flag = true;
	
	// 作用于静态方法,锁是当前class对象,也就是AccountingSyncClass类对应的class对象
	public static synchronized void increase() {
		i++;
	}
 
	// 非静态方法，锁定当前实例对象 //在new一个对象  不会锁住
	public synchronized void increase2() {
		i++;
	}
 
	@Override
	public void run() {
		for (int j = 0; j < 1000000; j++) {
//			increase();
			increase2();
		}
	}
}
