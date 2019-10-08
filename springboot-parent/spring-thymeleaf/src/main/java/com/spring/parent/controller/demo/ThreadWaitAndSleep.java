package com.spring.parent.controller.demo;
/**
 sleep 不会释放同步锁
 */
public class ThreadWaitAndSleep{
	private static Object obj=new Object();
	public static void main(String[] args) {
		Thread t1=new ThreadTest4("t1");
		Thread t2=new ThreadTest4("t2");
		Thread t3=new ThreadTest4("t3");
		t1.start();
		t2.start();
		t3.start();
	}
	
	
	
	
	static class ThreadTest4 extends Thread{
		public ThreadTest4(String name) {
			super(name);
		}
		
		public void run() {
			synchronized(obj) {
				try {
					for(int i=0;i<10;i++) {
						System.out.println(Thread.currentThread().getName() +" sleep() ---i="+i);
						 if (i%4 == 0)
						    Thread.sleep(500);//t1 sleep 并不会释放obj多持有的同步锁 
					}
				}catch (Exception e) {
					  e.printStackTrace();
				}
				
			}
			
		}
}


	
}
