package com.spring.parent.controller.thread.many;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class User{
	public String name;
	public String sex;
	public boolean flag=false;
	public Lock lock=new ReentrantLock();//保证单例 
	Condition connection=lock.newCondition();//保证单例
	@Override
	public String toString() {
		return super.toString();
	}
}

class IpuThread extends Thread {
	public User user;
	public IpuThread(User user) {
		this.user=user;
	}
	@Override
	public void run() {
		int count=0;
		while(true) {
			synchronized (user) {
				if(user.flag) {//已经写过一次了 现在该读了
					try {
						user.wait();//当前线程等待 wait使用在多线程之间同步,  与synchronized一起使用  wait可以释放锁,sleep不会释放锁.
						//wait 是让线程释放对象锁. 进入对象的等待池 等待池的对象不会竞争对象的锁
					}catch(Exception e){
						
					}
				}
				
				if(count==0) {
						user.name="丁一江";
						user.sex="男";
				}else {
						user.name="小红";
						user.sex="女";
				}
				count=(count+1)%2;
				user.flag=true;
				//随机唤醒对象等待池中的一个线程。
				user.notify();
			}
		}
	}
}

class OutThread extends Thread {
	public User user;
	public OutThread(User user) {
		this.user=user;
	}
	@Override
	public void run() {
		while(true) {
			synchronized (user) {
				if(!user.flag) {
					try {
						user.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}//当前线程等待 wait使用在多线程之间同步,  与synchronized一起使用  wait可以释放锁,sleep不会释放锁.
				}
				System.out.println(user.name+"---"+user.sex);
				user.flag=false;
				user.notify();
			}
		}
	}
}

/**
    *   多线程 1个input  1个out
   
 * @author Administrator
 *
 */
public class ThreadDemo3 {
	public static void main(String[] args) throws InterruptedException {
		User user=new User();
		Thread thread1=new IpuThread(user);
		Thread thread2=new OutThread(user);
		thread1.start();
		//Thread.sleep(100);//这里先睡眠  比较好出结果
		thread2.start();
		
	}
}
