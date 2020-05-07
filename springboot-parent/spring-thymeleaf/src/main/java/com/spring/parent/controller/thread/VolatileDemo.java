package com.spring.parent.controller.thread;
/**
 * static
 * 
 * @author Administrator
 *
 */
class VolatileThread extends Thread{
	public volatile boolean flag=true;
	@Override
	public void run() {
		System.out.println(this.getName()+"----start");
		while(flag) {
			
		}
		System.out.println(this.getName()+"----end");
	}
	public void stopFlag() {
		flag=false;
	}
}
/*
 * volatile 关键字  
 *    main线程和  volatileThread 是两个互不干扰的线程.   
 *    volatileThread 实例化创建flag=true; 
 *    main线程的本地内存中将 volatileThread的flag改成了false;   
 *               但是mian和volatileThread两个线程之间是不可见的。
 *  
 *   volatile让线程之间的变量变为可见。线程改了变量的值 同时修改主内存的变量值   。  
 */
public class VolatileDemo {
	public static void main(String[] args) throws InterruptedException {
		VolatileThread volatileThread=new VolatileThread();
		volatileThread.start();
		Thread.sleep(3000);
		volatileThread.stopFlag();
		System.out.println("设置成false");
		Thread.sleep(1000);
		System.out.println(volatileThread.flag);


	}
}
