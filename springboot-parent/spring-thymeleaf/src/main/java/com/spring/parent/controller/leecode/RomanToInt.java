package com.spring.parent.controller.leecode;

public class RomanToInt {
	
	public static void main(String[] args) {
		String test="MIV";
		System.out.println("END==="+toInt(test));
	}
	
	//例如 XIV  10+4   10   就是让preNum>nowNum 则加preNum  如果 preNum<nowNum 则减preNum 最后将最后一个数上
	public static int toInt(String s) {
		int sum = 0;
        int preNum = getValue(s.charAt(0));
        for(int i = 1;i < s.length(); i ++) {
            int num = getValue(s.charAt(i));
            if(preNum < num) {
                sum -= preNum;//例如 XIV  10+4   10 
            } else {
                sum += preNum;
            }
            preNum = num;
        }
        sum += preNum;//最后一个数
        return sum;
	}
	
	public static int getValue(char str) {
		switch(str) {
			case 'I': return 1;
			case 'V': return 5;
			case 'X': return 10;
			case 'L': return 50;
			case 'C': return 100;
			case 'D': return 500;
			case 'M': return 1000;
			default:  return 0;
		}
	}
	
	
	
	
}
