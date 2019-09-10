package com.spring.parent.controller.leecode;
/**
 * 最长回文子串
 * @author Administrator
 *
 */
public class Team3 {
	 public static void main(String[] args) {
		 System.out.println(longestPalindrome("a"));
		
	 }
	 
	 
	 /**
	  *1  暴力循环
	  * @param s
	  * @return
	  */
	 public static String longestPalindrome(String s) {
	     //最大长度  如果有那么更新数值
		 int max=0;
		 String ans="";
		 for(int i=0;i<s.length();i++) {
			 for(int j=i+1;j<=s.length();j++) {//如果只有1个字符那么i=0    j=0+1   j<s.length=1 一个都没有  所有要 <=
				 String field=s.substring(i, j);//含头不含尾
				 if(isPalindromic(field)&&field.length()>max) {
					 ans=field;//重新赋值
					 max=field.length();//重新赋值
				 }
			 }
		 }
			 
		 return ans;       
	 }
	 /**
	  * 判断是不是回文的
	  * 回文串的定义，正着和反着读一样
	  * @param s
	  * @return
	  */
	 public static boolean isPalindromic(String s) {//aba 3 1 0
			int len = s.length();
			for (int i = 0; i < len / 2; i++) {
				if (s.charAt(i) != s.charAt(len - i - 1)) {//0于length-1 对比    
					return false;
				}
			}
			return true;
		}
	 
	 
	 /**
	  * 最长公共子串
	  *1  申请一个二维数组初始化为0
	  *2 判断对应字符串是否相等 ***. 相等则  arr [ i ][ j ] = arr [ i - 1 ][ j - 1] + 1 。
	  *  当 i = 0 或者 j = 0 的时候单独分析，字符相等的话 arr [ i ][ j ] 就赋为 1 。
	  */
	 
	 
	 public String commonSubstring(String s) {
		 if("".equals(s)) {
			 return"";
		 }
		 //字符串反转
		 String reverse=new StringBuffer(s).reverse().toString();
		 int length=s.length();
		 //初始化一个数组
		 int [][] arr=new int[length][length];
		 int maxLen=0;
		 int maxEnd=0;
		 
		 for (int i = 0; i < length; i++) {
		        for (int j = 0; j < length; j++) {
		        	if(s.charAt(i)==reverse.charAt(j)) {
		        		if(i==0||j==0) {//s的第一个字符  与反转字符串对比  只要有相等的那么赋值为1
		        			arr[i][j]=1;
		        		}else {//
		        			arr[i][j]=arr[i-1][j-1]+1;
		        		}
		        	}
		        	if(arr[i][j]>maxLen) {//i=0    i=1  循环对比 知道找到最大的arr[i][j]然后替换maxLen
 		        		int beforeRev=length-1-j;//倒置前的下标 也就是s的下标
 		        		if(beforeRev+arr[i][j]-1 == i) {//判断下标是否对应  如果此  也就是回文串的首字符和尾字符相等
 		        			maxLen=arr[i][j];
 	 		        		maxEnd=i;//以i位置结束的字符
 		        		}
		        	}
		        	
		        }
		 }
		 
		 return s.substring(maxEnd-maxLen+1, maxEnd+1);
	 }

	 
	 
	 
	 
	 
	 
	 


}
