package com.spring.parent.controller.thread;

import java.util.ArrayList;
import java.util.List;

import com.spring.parent.controller.thread.entity.ListUtil;
import com.spring.parent.controller.thread.entity.UserEntity;

/**
 * 给10万用户发短信,多线程处理
 * 
 * 数据切割  多线程分发
 * @author Administrator
 *
 */
public class ManyThreadTest {
	
	public static void main(String[] args) {
		//初始化用户
		List<UserEntity> list=new ArrayList<UserEntity>();
		for(int i=0;i<10;i++) {
			UserEntity user=new UserEntity();
			user.setId(String.valueOf(i));
			user.setName("name="+i);
			list.add(user);
		}
		//每个线程最大数量
		int maxNum=2;
		//计算出线程数量，并且对应跑的数据。
		int threadNum=list.size()/2;
		List<List<UserEntity>> end= ListUtil.splitList(list, threadNum);
		//让子线程进行分批异步处理数据
		for(int i=0;i<end.size();i++) {
			
		}
	}
}
