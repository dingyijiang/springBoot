package com.spring.parent.controller.thread.many;

class EndThreadDemo2 extends Thread{
	private boolean flag=true;
	
	public EndThreadDemo2(String name) {
		super(name);
	}
	
	@Override
	public synchronized void run() {
		while(flag) {
			try {
				wait();//线程进入对象的等待池中  EndThreadDemo2 这个对象
				//intterupt线程之后在wait 线程会异常
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				//这里获取到异常 并关闭线程
				stopMethod();
			}
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
 * 
 *   3 标识停止线程
 * @author Administrator
 *
 */
public class StopThread2 {
	public static void main(String[] args) {
		EndThreadDemo2 thread1=new EndThreadDemo2("第一个线程");
		EndThreadDemo2 thread2=new EndThreadDemo2("第二个线程");
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
				thread1.interrupt();//中断线程
				thread2.interrupt();
			}
		}
	}
}
