package com.spring.parent.controller.leecode.day01;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 找到不重复的数字
 * @author Administrator
 *
 */
public class getSingleNum {
	private static int[] arr= {1,2,34,5,6,7,1,2,34,5,6};
		
	public static void main(String[] args) {
		
		System.out.println("getNumDemo1="+getNumDemo1(arr));
		
		System.out.println("getNumDemo2="+getNumDemo2(arr));
		
		System.out.println("getNumDemo3="+getNumDemo3(arr));

		
		int a=0;
		int b=0;

		System.out.print("++a=");System.out.print(++a);System.out.println();
		System.out.println("b++="+b++);
		System.out.println("b="+b);

	}
	

	
	//1 通过hashmap 
	public static int getNumDemo1(int[] arr) {
		Map<Integer,Integer> map=new HashMap<Integer,Integer>();
		for(int i:arr) {
			Integer count=map.get(i);
			count = count==null?1:++count;//如果count为null 那么说明第一次 给个1 
			map.put(i, count);
		}
		//map轮询
		for(Integer i:map.keySet()) {
			Integer count=map.get(i);
			if(count==1) {
				return i;
			}
		}		
		return -1;
	}
	
	//2 通过List  如果有两条那么就删除
	public static int getNumDemo2(int[] arr) {
		List<Integer> numList=new LinkedList<Integer>();
		for(int i:arr) {
			if(numList.contains(i)) {
				numList.remove((Object)i);//remove(i) 删除第几个元素  remove(Object o)  删除value=o的元素
			}else {
				numList.add(i);
			}
		}
		if(numList.size()==0) {
			return -1;
		}else {
			return numList.get(0);
		}
	}
	
	//3 最牛逼的位运算
	/*
	 * 如果我们对 0 和二进制位做 XOR 运算，得到的仍然是这个二进制位
		a + 0 = a
		a+0=a
		
		如果我们对相同的二进制位做 XOR 运算，返回的结果是 0
		a + a = 0
		a+a=0
		
		XOR 满足交换律和结合律
		a + b + a = (a + a) + b = 0 + b = b
		a⊕b⊕a=(a⊕a)⊕b=0⊕b=b
	*/
	
	public static int getNumDemo3(int[] arr) {
		int a=0;
		for(int i:arr) {
			a ^= i;
		}
		return a;
	}
	
}
