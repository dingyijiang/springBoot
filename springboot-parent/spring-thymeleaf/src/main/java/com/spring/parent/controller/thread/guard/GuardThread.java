package com.spring.parent.controller.thread.guard;
class MinThread extends Thread{
	@Override
	public void run() {
		for(int i=0;i<100;i++) {
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("我是子线程 ---- i="+i);
		}
	}
}
/**
 * 守护线程
 * 线程分为  用户线程（前台线程）和守护线程（后台线程）
 *  主线程或jvm挂了 那么守护线程也会自动停止掉， gc也是守护线程
 * setDeamon(设置当前线程为守护线程)
 * 
 * @author Administrator
 *
 */
public class GuardThread {
	public static void main(String[] args) {
	
		MinThread minThread=new MinThread();
		minThread.setDaemon(true);//一定要在启动之前设置
		minThread.start();
		for(int i=0;i<30;i++) {
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("我是主线程 ---- i="+i);
		}
		System.out.println("主线程执行完毕");
	}
	
	
}
