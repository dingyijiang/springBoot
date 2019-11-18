package com.spring.parent.controller.sort;
/**
 * 冒泡排序
 *  比较相邻的元素。如果第一个比第二个大，就交换它们两个；
   	对每一对相邻元素作同样的工作，从开始第一对到结尾的最后一对，这样在最后的元素应该会是最大的数；
	针对所有的元素重复以上的步骤，除了最后一个；
	
	T(n) = O(n)   最差情况：T(n) = O(n2)   平均情况：T(n) = O(n2)
 * @author Administrator
 *
 */
public class MaoPaoSort {
	public static void main(String[] args) {
		int [] test=new int[] {1,5,14,23,566,244,33,13,21,4,134,234,23,453,23,42,3,2,34};
		int [] end= maoPaoSorts(test);
		for(int i=0;i<end.length;i++) {
			System.out.print(end[i]+",");
		}
	}

	public static int[] maoPaoSorts(int [] array) {
		if(array!=null&&array.length>0) {
			for(int i=0;i<array.length;i++) {
				boolean flag=true;
				for(int j=0;j<array.length-i-1;j++) {//每次从i下一个数开始比较   找到最大值  下次循环 最后i个数 就不用循环了      -1 是因为下面用了j+1  所以最后一个元素的比较是length-1
					if(array[j]>array[j+1]) {
						int temp=array[j];
						array[j]=array[j+1];
						array[j+1]=temp;
						flag=false;//如果某次循环没有任何变化 那么说明所有元素都按大小排好了  就不需要在循环了  所以下面break;
					}
				}
				if(flag) {
					break;
				}
			}
		}
		return array;
	}
	
	
}
