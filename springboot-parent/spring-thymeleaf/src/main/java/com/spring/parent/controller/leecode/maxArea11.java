package com.spring.parent.controller.leecode;
/*
 * 求最大的矩形面积
 双指针法
 */
public class maxArea11 {
	
	public static void main(String[] args) {
		int[] arr= {1,8,6,2,5,4,8,3,7};
		int [] arr2=new int[] {1,8,6,2,5,4,8,3,7};
		System.out.println(getMaxArea(arr2));
	}
	
	public static int getMaxArea(int[] arr) {
		int maxArea=0 , left=0, right=arr.length-1;
		while(left<right) {
			maxArea=Math.max(maxArea,Math.min(arr[left], arr[right])*(right-left));
			if(arr[left]>=arr[right]) {
				right--;
			}else {
				left++;
			}
		}		
		return maxArea;
	}
}
