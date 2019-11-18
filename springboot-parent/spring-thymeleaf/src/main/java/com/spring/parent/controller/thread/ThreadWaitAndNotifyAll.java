package com.spring.parent.controller.thread;
/**
wait() 						   -- 让当前线程处于“等待(阻塞)状态”，“直到其他线程调用此对象的 notify() 方法或 notifyAll() 方法”，当前线程被唤醒(进入“就绪状态”)。
wait(long timeout)             -- 让当前线程处于“等待(阻塞)状态”，“直到其他线程调用此对象的 notify() 方法或 notifyAll() 方法，或者超过指定的时间量”，当前线程被唤醒(进入“就绪状态”)。
wait(long timeout, int nanos)  -- 让当前线程处于“等待(阻塞)状态”，“直到其他线程调用此对象的 notify() 方法或 notifyAll() 方法，或者其他某个线程中断当前线程，或者已超过某个实际时间量”，当前线程被唤醒(进入“就绪状态”)。
notify()     				   -- 唤醒在此对象监视器上等待的单个线程。
notifyAll()   				   -- 唤醒在此对象监视器上等待的所有线程。
*****   wait()的作用是让“当前线程”等待，而“当前线程”是指正在cpu上运行的线程！
 */
public class ThreadWaitAndNotifyAll{
	private static Object obj=new Object();
	public static void main(String[] args) {
		Thread t1=new ThreadTest4("t1");
		Thread t2=new ThreadTest4("t2");
		Thread t3=new ThreadTest4("t3");
		t1.start();
		t2.start();
		t3.start();
		try {
			  System.out.println(Thread.currentThread().getName()+" sleep(3000)");
			  Thread.sleep(3000);
			} catch (InterruptedException e) {
			  e.printStackTrace();
			}
			
			synchronized(obj) {
			// 主线程等待唤醒。
			System.out.println(Thread.currentThread().getName()+" notifyAll()");
			obj.notifyAll();
			}
		
	}
	
	
	
	
	static class ThreadTest4 extends Thread{
		public ThreadTest4(String name) {
			super(name);
		}
		
		public void run() {
			synchronized(obj) {
				try {
				System.out.println(Thread.currentThread().getName() +" wait()");
				obj.wait();
				System.out.println(Thread.currentThread().getName() + "continue");
				}catch (Exception e) {
					  e.printStackTrace();
				}
				
			}
			
		}
}


	
}
