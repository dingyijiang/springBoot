package com.spring.parent.controller.sort;
/**
 * 插入排序
 * @author Administrator
 *  从第一个元素开始，该元素可以认为已经被排序；
	取出下一个元素，在已经排序的元素序列中从后向前扫描；
	如果该元素（已排序）大于新元素，将该元素移到下一位置；
	重复步骤3，直到找到已排序的元素小于或者等于新元素的位置；
	将新元素插入到该位置后；
	重复步骤2~5
 */
public class InsertSort {

	public static void main(String[] args) {
		int [] test=new int[] {1,5,14,23,566,244,33,13,21,4,134,234,23,453,23,42,3,2,34};
		int [] end= insertSorts(test);
		for(int i=0;i<end.length;i++) {
			System.out.print(end[i]+",");
		}
	}
	
	public static int[] insertSorts(int [] array) {
		if(array.length==0) {
			return array;
		}
		int current;//当前元素
		for(int i=0;i<array.length-1;i++) {// 因为下面用到了 i+1 所以这里要循环到 length-1
		  current=array[i+1];//取出下一个元素 在已经排序的元素序列中从后向前扫描；
		  int preIndex=i;//已经排序完成的最后一个元素
		  while(preIndex>=0&&current<array[preIndex]) {//preIndex 从后道前 直到最前的元素   && 如果新元素< 从后往前的当前比较元素  则继续往前比较
			  array[preIndex+1]=array[preIndex];//如果 以排序的元素>新元素  则排序元素的index+1; 为了给新元素腾出地方
			  preIndex--;
		  }
		  //新元素>以排序元素了  那么位置找到了  current 就在  preIndex +1 的位置
		  array[preIndex+1]=current;
		}
		
		return array;
	}
	
}
