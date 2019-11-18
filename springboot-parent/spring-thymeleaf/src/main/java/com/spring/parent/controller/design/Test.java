package com.spring.parent.controller.design;

public class Test {
	
	public static void main(String[] args) {
		Signleton test=Signleton.getInstance();
		Signleton test2=Signleton.getInstance();
		System.out.println(test);
		System.out.println(test2);
	}
}
