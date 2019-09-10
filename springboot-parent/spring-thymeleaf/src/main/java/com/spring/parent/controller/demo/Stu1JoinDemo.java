package com.spring.parent.controller.demo;
/**
 * join() Thread.java中   
 * 作用: 让“主线程”等待“子线程”结束之后才能继续运行
 * @author Administrator
 *
 */
public class Stu1JoinDemo {
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
        for(int i=0; i <1000000; i++)
           ;

        System.out.printf("%s finish\n", this.getName()); 
    } 
} 
