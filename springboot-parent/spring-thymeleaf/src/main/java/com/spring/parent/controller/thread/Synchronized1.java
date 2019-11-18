package com.spring.parent.controller.thread;
/**
 * 1当我们调用某对象的synchronized方法时，就获取了该对象的同步锁。
 * 2不同线程对同步锁的访问是互斥的
 * 
 * 	规则：
 * 1: 当一个线程访问“某对象”的“synchronized方法”或者“synchronized代码块”时，
 * 	其他线程对“该对象”的该“synchronized方法”或者“synchronized代码块”的访问将被阻塞
 * 2  当一个线程访问“某对象”的“synchronized方法”或者“synchronized代码块”时，其他线程仍然可以访问“该对象”的非同步代码块。
 * 3  当一个线程访问“某对象”的“synchronized方法”或者“synchronized代码块”时，
 *          其他线程对“该对象”的其他的“synchronized方法”或者“synchronized代码块”的访问将被阻塞
 * @author Administrator
 *
 */
public class Synchronized1 {

	 public static void main(String[] args) {
//		 Runnable run=new MyRunnable();
//		 Thread r1=new Thread(run,"test1");
//		 Thread r2=new Thread(run,"test2");
//		 r1.start();
//		 r2.start();
		 
		 Thread t1=new MyThreadTest2("test1");
		 Thread t2=new MyThreadTest2("test2");
		 t1.start();
		 t2.start();
		 /*
		 test1 loop 0
		 test2 loop 1
		 test1 loop 1
		 test2 loop 2
		 test1 loop 2
		 test2 loop 3
		 test1 loop 3
		 */
		 
	 }
}

class MyThreadTest2 extends Thread{
	public MyThreadTest2(String name) {
		super(name);
	}
	public void run() {
			try {
				for(int i=0;i<10;i++) {
					Thread.sleep(500);
					System.out.println(Thread.currentThread().getName() + " loop " + i); 
				}
			}catch (InterruptedException e) {
			}
	}
}
	


class MyRunnable implements Runnable{

	@Override
	public void run() {
		//这里的this是“当前对象的同步锁” 当我们用Thread继承的时候 如果两个线程那么是两个对象  不会锁住  
		synchronized(this) {
			try {
				for(int i=0;i<10;i++) {
					Thread.sleep(500);
					System.out.println(Thread.currentThread().getName() + " loop " + i); 
				}
			}catch (InterruptedException e) {
			}
		}
	}
	
}
