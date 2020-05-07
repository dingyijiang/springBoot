package com.spring.parent.controller.thread.many;

class EndThreadDemo extends Thread{
	private boolean flag=true;
	
	public EndThreadDemo(String name) {
		super(name);
	}
	
	@Override
	public void run() {
		while(flag) {
			
			System.out.println(Thread.currentThread().getName()+"-----i am 子线程");
		}
	}
	
	public void stopMethod() {
		System.out.println(getName()+"--被停止了");
		flag=false;
	}
}


/**
 * 
 * 怎么停止线程 * Thread stop 已经过时了 不好 stop没有处理异常机制
 * 
 * 1 退出标识 
 * 2 stop 强制终止线程， 不推荐 
 * 3 interrupt 中断 企业开发都是用线程池
 * 
 * @author Administrator
 *
 */
public class StopThread {
	public static void main(String[] args) {
		EndThreadDemo thread1=new EndThreadDemo("第一个线程");
		EndThreadDemo thread2=new EndThreadDemo("第二个线程");
		thread1.start();
		thread2.start();
		for(int i=0;i<10;i++) {
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName()+"----"+i);
			if(i==9) {
				thread1.stopMethod();
				thread2.stopMethod();
			}
		}
	}
}
