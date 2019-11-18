package com.spring.parent.controller.thread;
/**
 * join() Thread.java中   
 * 作用: 让“主线程”等待“子线程”结束之后才能继续运行
 * @author Administrator
 *
 */
public class JoinDemo {
	public static void main(String[] args) {
		try {
			ThreadA t1=new ThreadA("t1");
			t1.start();
			t1.join();
			System.out.printf("%s finish\n", Thread.currentThread().getName()); 
		}catch (InterruptedException e) {
            e.printStackTrace();
        }
	}
	
}

 class ThreadA extends Thread{

    public ThreadA(String name){ 
        super(name); 
    } 
    public void run(){ 
        System.out.printf("%s start\n", this.getName()); 

        // 延时操作
        try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        System.out.printf("%s finish\n", this.getName()); 
    } 
} 
