package com.spring.parent.controller;

import java.util.HashMap;
import java.util.Map;

public class TestController {
	
	public static void main(String[] args) {
		boolean a=Boolean.FALSE;
		
		byte b=127;
		System.out.println((short)(b+(short)100));
		
		int c=Integer.MAX_VALUE;
		int d=Integer.MIN_VALUE;
		//max=2147483647,min=-2147483648
		System.out.println("max="+c+",min="+d);

		Integer e=3;
		Integer f=3;
		Integer g=new Integer(3);

		System.out.println(e==f);
		System.out.println(e==g);
		Map map=new HashMap();
		map.put("test", "dyj");
	}

}
