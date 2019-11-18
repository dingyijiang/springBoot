package com.spring.parent.controller.thread;
/**
 * 匿名内部类实现方法
 * @author Administrator
 *
 */
public class ThreadDemo {
	public static void main(String[] args) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println("this thread=="+Thread.currentThread().getName());
			}			
		}).start();
	}
}
