package com.spring.parent.controller.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 线程池的创建
 * @author Administrator
 * Executors提供四种线程池
 *  newCachedThreadPool创建一个可缓存线程池，如果线程池长度超过处理需要，可灵活回收空闲线程，若无可回收，则新建线程。
	newFixedThreadPool 创建一个定长线程池，可控制线程最大并发数，超出的线程会在队列中等待。
	newScheduledThreadPool 创建一个定长线程池，支持定时及周期性任务执行。
	newSingleThreadExecutor 创建一个单线程化的线程池，它只会用唯一的工作线程来执行任务，保证所有任务按照指定顺序(FIFO, LIFO, 优先级)执行。
 */
public class ThreadPool {
	static ScheduledExecutorService  scheduledExecutor =Executors.newScheduledThreadPool(3);

	public static void main(String[] args) {
		newCachedThreadPool();
	}
	
	public static void newCachedThreadPool() {
		ExecutorService cachedThreadPool=Executors.newCachedThreadPool();
		 for (int i = 0; i < 10; i++) {  
			   final int index = i;  
			   cachedThreadPool.execute(new Runnable() {  
				    public void run() {  
				    	try {
							Thread.sleep(3000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						System.out.println(Thread.currentThread().getName() + " index:" + index);
				    }  
		 	   });  
		 } 
		 try {
				Thread.sleep(4000);
		 } catch (InterruptedException e) {
				e.printStackTrace();
		 }
		 System.out.println("4秒后...");
		 cachedThreadPool.shutdown();
	}
	
	public static void newScheduledThreadPool() {
		 for (int i = 0; i < 3; i++) {  
			   final int index = i;  
			   //scheduleWithFixedDelay 固定的延迟时间执行任务； scheduleAtFixedRate 固定的频率执行任务
			   scheduledExecutor.scheduleWithFixedDelay(new Runnable() {  
				    public void run() {  
						System.out.println(Thread.currentThread().getName() + " index:" + index);
				    }  
		 	   }, 0, 3 ,TimeUnit.SECONDS);  
		 } 
		 try {
				Thread.sleep(4000);
		 } catch (InterruptedException e) {
				e.printStackTrace();
		 }
		 System.out.println("4秒后...");
		 scheduledExecutor.shutdown();
	}
	
}
