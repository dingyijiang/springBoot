package com.spring.parent.controller.thread;
/**
 * ThreadLocal 不是 Thread，是一个线程内部的数据存储类
 * @author Administrator
 *
 */
public class ThreadLocalTest {
	public static void main(String[] args) {
		//threadLocal1 是一个公共类 但是每个线程在threadlocal取到的值不一样。
        final ThreadLocal<String> threadLocal1 = new ThreadLocal<>();
        final ThreadLocal<Integer> threadLocal2 = new ThreadLocal<>();

        new Thread(new Runnable() {
            @Override
            public void run() {
                threadLocal1.set("A");
                threadLocal2.set(1);
               
                System.out.println(Thread.currentThread().getName());
                System.out.println(threadLocal1.get());
                System.out.println(threadLocal2.get());
            }
        }).start();


        new Thread(new Runnable() {
            @Override
            public void run() {
                threadLocal1.set("B");
                threadLocal2.set(2);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName());
                System.out.println(threadLocal1.get());
                System.out.println(threadLocal2.get());
            }
        }).start();


        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

                System.out.println(Thread.currentThread().getName());
                System.out.println(threadLocal1.get());
                System.out.println(threadLocal2.get());
            }
        }).start();
    }
}
