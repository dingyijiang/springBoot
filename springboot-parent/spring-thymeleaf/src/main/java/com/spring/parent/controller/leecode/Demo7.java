package com.spring.parent.controller.leecode;
/**
 * Integer inversion  整数反转
         给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。

假设我们的环境只能存储得下 32 位的有符号整数，则其数值范围为 [−231,  231 − 1]。请根据这个假设，
如果反转后整数溢出那么就返回 0。
 * @author Administrator
 * 
 *
 */
public class Demo7 {
	public static void main(String[] args) {
		System.out.println(3/10);
		System.out.println(Integer.MAX_VALUE);//2147483647 
		System.out.println(Integer.MIN_VALUE);//-2147483648
		System.out.println(reverse(2147483647));//2147483647 

	}
	
	public static int reverse(int x) {
		//通过%找到 最后一位 然后
				int rev=0;
				while(x!=0) {
					int pop = x%10;//最后一位
					x = x/10;//前面的所有
					if(rev > Integer.MAX_VALUE/10 ||(rev ==Integer.MAX_VALUE/10 && pop > Integer.MAX_VALUE % 10))  return 0;
					if(rev < Integer.MIN_VALUE/10 ||(rev ==Integer.MIN_VALUE/10 && pop < Integer.MIN_VALUE % 10)) return 0;
					rev=rev*10+pop;
				}
			return rev;
	}
}
