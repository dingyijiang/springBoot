package com.spring.parent.controller.thread;
/**
 * https://www.cnblogs.com/skywang12345/p/3479024.html
 * 线程的两种实现方法
 * @author Administrator
 *
 */
public class ThreadTwoMethod{
	public static void main(String[] args) {
		//主线程main创建并启动3个MyThread子线程。每个子线程都各自卖出了10张票。
//		MyThread t1=new MyThread("t1");
//		MyThread t2=new MyThread("t2");
//		MyThread t3=new MyThread("t3");
//		t1.start();
//		t2.start();
//		t3.start();
		
		//主线程main创建并启动3个子线程，而且这3个子线程都是基于“mt这个Runnable对象”而创建的。运行结果是这3个子线程一共卖出了10张票。这说明它们是共享了MyThread接口的。
		MyThread2 run=new MyThread2();
		Thread t1=new Thread(run,"t1");
		Thread t2=new Thread(run,"t2");
		Thread t3=new Thread(run,"t3");
		t1.start();
		t2.start();
		t3.start();
		
		
	}

}
//MyThread 继承Thread 自定义线程,每个线程都会卖出10张票
class MyThread extends Thread{
	
	public MyThread(String name) {
		super(name);
	}
	
	private int ticket=10;
	public void run() {
		
			for(int i=0;i<20;i++) {
				System.out.println(this.getName()+"买票:ticket="+this.ticket--);
			}
		
		
	}
}

//和上面“MyThread继承于Thread”不同；这里的MyThread实现了Runnable接口。
class MyThread2 implements Runnable{
	private int ticket=10; 
	@Override
	public void run() {
		synchronized(this) {//加上这个就会同步进行不会三个线程同步进行
			
			for(int i=0;i<20;i++) {
				if(this.ticket>0) {
					try {
						Thread.sleep(1000);//t1买票睡眠 然后t2来买票获取到的ticket还是等于10
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.println(Thread.currentThread().getName()+"买票:ticket="+this.ticket--);
				}
			}
		}
	}
	
}
