package com.spring.parent.controller.thread;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * static
 * 
 * @author Administrator
 *
 */
class VolatileNoAtomic extends Thread{
	static int count = 0;
	private static AtomicInteger atomicInteger = new AtomicInteger(0);

	@Override
	public void run() {
		for (int i = 0; i < 1000; i++) {
			//等同于i++
			count=atomicInteger.incrementAndGet();
		}
		System.out.println(count);
	}
}
/* volatile类不具有原子性
 * 
 * VolatileNoAtomic 类 
 *   volatile让线程之间的变量变为可见。线程改了变量的值 同时修改主内存的变量值   。  
 */
public class VolatileDemo2 {
	public  static void main(String[] args){
		// 初始化10个线程
				VolatileNoAtomic[] volatileNoAtomic = new VolatileNoAtomic[10];
				for (int i = 0; i < 10; i++) {
					// 创建
					volatileNoAtomic[i] = new VolatileNoAtomic();
				}
				for (int i = 0; i < volatileNoAtomic.length; i++) {
					volatileNoAtomic[i].start();
				}
	}
}
