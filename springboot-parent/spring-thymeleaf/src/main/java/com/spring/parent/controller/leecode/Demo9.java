package com.spring.parent.controller.leecode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 判断一个整数是否是回文数。回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。


输入: -121
输出: false
解释: 从左向右读, 为 -121 。 从右向左读, 为 121- 。因此它不是一个回文数。
示例 3:

输入: 10
输出: false
解释: 从右向左读, 为 01 。因此它不是一个回文数。


 * @author Administrator
 * 
 *
 */
public class Demo9 {
	public static void main(String[] args) {
		String test="222test111";
		test=test.replaceAll("222", "ding");
		System.out.println(test);
		List list=new ArrayList();
	    HashMap map=new HashMap();				
		//System.out.println(isPalindrome2(123321));
		//System.out.println(isPalindrome2(123311));

	}
	
	public static String reverse(String str) {
		String reverse="";
		for(int i=0;i<str.length();i++) {
			char a=str.charAt(str.length()-1-i);
			reverse+=a;
		}
		return reverse;
	}
	
	public static boolean isPalindrome(int x) {
		if(x<0) {
			return false;
		}else {
			if(x==rev(x)) {
				return true;
			}
			return false;
		}	
	}
	//原始 倒转  这里如果数字越界则会有问题 
	public static int rev(int x) {
		int rev=0;
		while(x!=0) {
			int pop=x%10;
			x=x/10;
			rev=rev*10+pop;
		}
		return rev;
	}
	
	//只对比一般的数字   如果判断到一半  rev>原始数字 的时候 
	
	public static boolean isPalindrome2(int x) {
		 // 特殊情况：
        // 如上所述，当 x < 0 时，x 不是回文数。
        // 同样地，如果数字的最后一位是 0，为了使该数字为回文，
        // 则其第一位数字也应该是 0
        // 只有 0 满足这一属性
        if(x < 0 || (x % 10 == 0 && x != 0)) {
            return false;
        }
        int revertedNumber = 0;
        while(x > revertedNumber) {
            revertedNumber = revertedNumber * 10 + x % 10;
            x /= 10;
        }

        // 当数字长度为奇数时，我们可以通过 revertedNumber/10 去除处于中位的数字。
        // 例如，当输入为 12321 时，在 while 循环的末尾我们可以得到 x = 12，revertedNumber = 123，
        // 由于处于中位的数字不影响回文（它总是与自己相等），所以我们可以简单地将其去除。
        return x == revertedNumber || x == revertedNumber/10;

	}
	public static int rev2(int x) {
		int rev=0;
		while(x>rev) {
			int pop=x%10;
			x=x/10;
			rev=rev*10+pop;
		}
		return rev;
	}
	
	
}
