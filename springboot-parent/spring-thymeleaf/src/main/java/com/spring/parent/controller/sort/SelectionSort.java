package com.spring.parent.controller.sort;
/**
 * 选择排序
 * @author Administrator
 * 1  首先在未排序序列中找到最小（大）元素，存放到排序序列的起始位置，
 * 2   然后，再从剩余未排序元素中继续寻找最小（大）元素，然后放到已排序序列的末尾。以此类推，直到所有元素均排序完毕。 
 */
public class SelectionSort {

	public static void main(String[] args) {
		int [] test=new int[] {1,5,14,23,566,2,33,13,2,4,134,234,23,453,23,42,3,2,34};
		int [] end= selectionSorts(test);
		for(int i=0;i<end.length;i++) {
			System.out.print(end[i]+",");
		}
	}
	
	public static int[] selectionSorts(int [] array) {
		if(array.length==0) {
			return array;
		}
		System.out.println("length="+array.length);
		for(int i=0; i<array.length;i++) {
			int maxIndex=i;//
			for(int j=i;j<array.length;j++) {
				//System.out.println("j="+j+",,maxIndex="+maxIndex);
				if(array[j]<array[maxIndex]) {//找到最小的数
					maxIndex=j;
				}
			}
			//交换 把最小值放到最前面 将第i个数和 最小数交换
			int temp = array[maxIndex];//取出最小值
			array[maxIndex]=array[i];
			array[i]=temp;
			System.out.println("num="+temp);
			System.out.print("第"+i+"次排序后的数据为");
			for(int a=0;a<array.length;a++) {
				System.out.print(array[a]+",");
			}
			System.out.println("");
		}
		return array;
	}
}
