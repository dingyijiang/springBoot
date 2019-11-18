package com.spring.parent.controller.thread;
/**
 * start()与run()的区别
 *start()的作用是启动一个新线程，新线程会执行相应的run()方法
 *run()就和普通的成员方法一样，可以被重复调用。单独调用run()的话，会在当前线程中执行run()，而并不会启动新线程！
 * @author Administrator
 *
 */
public class ThreadStartAndRun{
	public static void main(String[] args) {
		Thread thread=new ThreadTest("dyj");
		System.out.println(Thread.currentThread().getName()+" call thread.run");
		thread.run();
		System.out.println(Thread.currentThread().getName()+" call thread.start");
		thread.start();
	}

}
//MyThread 继承Thread 自定义线程,每个线程都会卖出10张票
class ThreadTest extends Thread{
	//当前线程命名
	public ThreadTest(String name) {
		super(name);
	}
	
	public void run() {
		System.out.println(Thread.currentThread().getName()+" is running");
	}
	
	
}
