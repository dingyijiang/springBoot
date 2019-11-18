package com.spring.parent.controller.thread;
/**
 * 1当我们调用某对象的synchronized方法时，就获取了该对象的同步锁。
 * 2不同线程对同步锁的访问是互斥的
 * 
 * 	规则：
 * 1: 当一个线程访问“某对象”的“synchronized方法”或者“synchronized代码块”时，
 * 	其他线程对“该对象”的该“synchronized方法”或者“synchronized代码块”的访问将被阻塞
 * 2  当一个线程访问“某对象”的“synchronized方法”或者“synchronized代码块”时，其他线程仍然可以访问“该对象”的非同步代码块。
 * 3  当一个线程访问“某对象”的“synchronized方法”或者“synchronized代码块”时，
 *          其他线程对“该对象”的其他的“synchronized方法”或者“synchronized代码块”的访问将被阻塞
 *          
 *    证明第二条规则
 *          
 * @author Administrator
 *
 */
public class Synchronized3 {

	 public synchronized void synMethod() {
	        for(int i=0; i<400000000; i++)
	            ;
	    }

	    public void synBlock() {
	        synchronized( this ) {
	            for(int i=0; i<400000000; i++)
	                ;
	        }
	    }

	    public static void main(String[] args) {
	    	Synchronized3 demo = new Synchronized3();

	        long start, diff;
	        start = System.currentTimeMillis();                // 获取当前时间(millis)
	        demo.synMethod();                                // 调用“synchronized方法”
	        diff = System.currentTimeMillis() - start;        // 获取“时间差值”
	        System.out.println("synMethod() : "+ diff);
	        
	        start = System.currentTimeMillis();                // 获取当前时间(millis)
	        demo.synBlock();                                // 调用“synchronized方法块”
	        diff = System.currentTimeMillis() - start;        // 获取“时间差值”
	        System.out.println("synBlock()  : "+ diff);
	    }
	
}
