package com.spring.parent.controller.mapDemo;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ConCurrentHashMapDemo {

	
	public static void main(String[] args) {
		ConcurrentHashMap<String, Object> conMap=new ConcurrentHashMap<String, Object>();
		conMap.put("test", "dingyijiang");
		
		//p = tab[i = (n - 1) & hash]) == null
		
		Map <String,Object> map=new HashMap<String,Object>();
		map.put("test", "123");
		int sc; 
		if((sc=10)<3) {
			
		}else{
			System.out.println("sc="+sc);
		}
		System.out.println(3&5); //二进制 取相同
	}
}
