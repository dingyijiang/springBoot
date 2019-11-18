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
 *          
 *    证明第二条规则
 *          
 * @author Administrator
 *
 */
public class Synchronized2 {

	 public static void main(String[] args) {
	     //主线程中新建了两个子线程t1和t2。t1会调用count对象的synMethod()方法，该方法内含有同步块；\
		 //而t2则会调用count对象的nonSynMethod()方法，该方法不是同步方法。t1运行时，
		 //虽然调用synchronized(this)获取“count的同步锁”；但是并没有造成t2的阻塞，因为t2没有用到“count”同步锁。
		 final Test test=new Test();
		 Thread t1=new Thread(new Runnable() {
			@Override
			public void run() {
				test.synMethod();
			}
			 
		 },"t1");
		 
		Thread t2=new Thread(new Runnable() {

			@Override
			public void run() {
				//test.synMethod();
				test.noSynMethod();
			}
			
		},"t2");
		
		t1.start();
		t2.start();
	 }
	 
}

class Test{
	public void synMethod() {
		synchronized (this) {
			try {
				for(int i=0;i<10;i++) {
					Thread.sleep(500);
					System.out.println(Thread.currentThread().getName() + " loop " + i); 
				}
			}catch (InterruptedException e) {
			}
			
		}
	}
	
	public void noSynMethod() {
		synchronized (this) {
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
