package com.spring.parent.controller.thread;

class ThreadRunnable implements Runnable{
	int sumNum=100;
	private Object obj=new Object();
	public static boolean flag=true;
	@Override
	public void run() {
		// TODO Auto-generated method stub
		if(flag) {
			while(sumNum>0) {
				synchronized(obj) {//obj
					if(sumNum>0) {
						try {
							Thread.sleep(100);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						System.out.println(Thread.currentThread().getName()+"出售第"+(100-sumNum+1)+"票");
						sumNum--;
					}
				}
			}
		}else {
			while(sumNum>0) {
				show();
			}
		}
	}
	public  synchronized void show(){
		if(sumNum>0) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName()+"出售第"+(100-sumNum+1)+"票");
			sumNum--;
		}
	}
}


/**
    *    证明同步方法是  this 锁
   
    两个线程抢同一个共享资源(全局变量或静态变量) 并进行写操作  会造成数据冲突
   
   1个obj 1个synchronized方法  两个会有数据冲突
  obj改成this 那么不会有冲突
   
 * @author Administrator
 *
 */
public class ThreadDemo2 {
	public static void main(String[] args) throws InterruptedException {
		ThreadRunnable runnable=new ThreadRunnable();
		Thread thread1=new Thread(runnable,"第一个线程");
		Thread thread2=new Thread(runnable,"第二个线程");
		thread1.start();
		System.out.println(Thread.currentThread().getName());
		Thread.sleep(100);//这里先睡眠  比较好出结果
		ThreadRunnable.flag=false;
		thread2.start();
		
	}
}
