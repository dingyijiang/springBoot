package com.spring.parent.controller.thread.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 创建一个可缓存的线程池，如果线程池的规模超过了处理需求，
 * 将自动回收空闲线程，而当需求增加时，则可以自动添加新线程，线程池的规模不存在任何限制。
 * 
 * 线程池无限大，  不会重复创建线程,代码走完了,成为了公共线程,第二个线程会直接用第一个线程
 * @author Administrator
 *
 */
public class NewCachedThreadDemo {
	public static void main(String[] args) {
		//推荐使用
		ExecutorService executorService=Executors.newCachedThreadPool();
		
		for (int i = 0; i < 10; i++) {
			final int index=i;
			//匿名内部类创建线程  匿名内部类中的变量必须是final的   jkd1.8之后 变量不一定是final 但是数值不能变化  ++后会报错.
			executorService.execute(new Runnable(){
				@Override
				public void run() {
					
					System.out.println(Thread.currentThread().getName()+"-----i="+index);
					//关闭线程池
					if(index==9) {
						executorService.shutdown();
					}
				}
				
			});
		}
		
	}
}
