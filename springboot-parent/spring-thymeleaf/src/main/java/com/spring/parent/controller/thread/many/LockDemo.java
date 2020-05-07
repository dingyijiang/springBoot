package com.spring.parent.controller.thread.many;


class IptThread extends Thread {
	public User user;
	public IptThread(User user) {
		this.user=user;
	}
	@Override
	public void run() {
		int count=0;
		while(true) {
			//获取锁资源
			try {
				user.lock.lock();
				if(user.flag) {//如果为true 那么等待
					try {
						user.connection.await();//当前线程等待.
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
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
				user.connection.signal();//唤醒一个等待线程.
			} catch (Exception e) {
			
			}finally {
				user.lock.unlock();//释放资源
			}
			
		}
	}
}

class OutsThread extends Thread {
	public User user;
	public OutsThread(User user) {
		this.user=user;
	}
	@Override
	public void run() {
		while(true) {
			try {
				//获取锁资源
				user.lock.lock();
				if(!user.flag) {
					user.connection.await();//当前线程等待
				}
				System.out.println(user.name+"---"+user.sex);
				user.flag=false;
				user.connection.signal();//唤醒一个等待线程 即为inputthread
			} catch (Exception e) {
				// TODO: handle exception
			}finally {
				user.lock.unlock();//释放资源
			}
				
		}
	}
}

/**
    *   使用lock实现同步    保证线程安全  
   
 * @author Administrator
 *
 */
public class LockDemo {
	public static void main(String[] args) throws InterruptedException {
		User user=new User();
		Thread thread1=new IptThread(user);
		Thread thread2=new OutsThread(user);
		thread1.start();
		//Thread.sleep(100);//这里先睡眠  比较好出结果
		thread2.start();
		
	}
}
