package com.spring.parent.controller.thread;
/**
wait() 						   -- 让当前线程处于“等待(阻塞)状态”，“直到其他线程调用此对象的 notify() 方法或 notifyAll() 方法”，当前线程被唤醒(进入“就绪状态”)。
wait(long timeout)             -- 让当前线程处于“等待(阻塞)状态”，“直到其他线程调用此对象的 notify() 方法或 notifyAll() 方法，或者超过指定的时间量”，当前线程被唤醒(进入“就绪状态”)。
wait(long timeout, int nanos)  -- 让当前线程处于“等待(阻塞)状态”，“直到其他线程调用此对象的 notify() 方法或 notifyAll() 方法，或者其他某个线程中断当前线程，或者已超过某个实际时间量”，当前线程被唤醒(进入“就绪状态”)。
notify()     				   -- 唤醒在此对象监视器上等待的单个线程。
notifyAll()   				   -- 唤醒在此对象监视器上等待的所有线程。
*****   wait()的作用是让“当前线程”等待，而“当前线程”是指正在cpu上运行的线程！
 */
public class ThreadWaitAndNotify{
	public static void main(String[] args) {
		Thread t1=new ThreadTest3("dyj");
		synchronized(t1) {//获取“t1对象的同步锁”。
			try {
				  // 启动“线程t1”
                System.out.println(Thread.currentThread().getName()+" start t1");//main start t1
                t1.start();//因为目前在t1的锁中 所有不会运行run方法

                // 主线程等待t1通过notify()唤醒。
                System.out.println(Thread.currentThread().getName()+" wait()");//main wait()
                t1.wait();//wait 当前线程阻塞 这里是main方法阻塞  并且释放t1对象的同步锁  开始执行t1的run方法

                System.out.println(Thread.currentThread().getName()+" continue");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
		}
	}
	
 	
}

 class ThreadTest3 extends Thread{
	public ThreadTest3(String name) {
		super(name);
	}
	
	public void run() {
		synchronized(this) {
			System.out.println(Thread.currentThread().getName() +" call notify()");
			notify();//唤醒此对象监视器上等待的单个线程  也就是唤醒“主线程”。  
			//线程t1”运行完毕之后，释放“当前对象的锁”   主线程获取“t1对象的锁”，然后接着运行。
		}
		
	}
	
}
