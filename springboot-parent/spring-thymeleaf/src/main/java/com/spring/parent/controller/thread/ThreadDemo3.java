package com.spring.parent.controller.thread;

class ThreadRunnable2 implements Runnable{
	int sumNum=100;
	private Object obj=new Object();
	public boolean flag=true;
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		if(flag) {
			while(sumNum>0) {
				//true   先obj锁 在this锁 
				//false  先this锁在obj锁
				synchronized(obj) {//obj
					show();
				}
			}
		}else {
			while(sumNum>0) {
				show();
			}
		}
	}
	public  synchronized void show(){
		synchronized(obj) {//obj
			if(sumNum>0) {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
				}
				System.out.println(Thread.currentThread().getName()+"出售第"+(100-sumNum+1)+"票");
				sumNum--;
			}
		}
	}
}


/**
    *   死锁
    *   两个线程在执行过程中 互相持有对方所有需要的资源 而导致所有线程等待的状态
   
 * @author Administrator
 *
 */
public class ThreadDemo3 {
	public static void main(String[] args) throws InterruptedException {
		ThreadRunnable2 runnable2=new ThreadRunnable2();
		Thread thread1=new Thread(runnable2,"第一个线程");
		Thread thread2=new Thread(runnable2,"第二个线程");
		thread1.start();
		System.out.println(Thread.currentThread().getName());
		Thread.sleep(100);//这里先睡眠  比较好出结果
		runnable2.flag=false;
		thread2.start();
		
	}
}
