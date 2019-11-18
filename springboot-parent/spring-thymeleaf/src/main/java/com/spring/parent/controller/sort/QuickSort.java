package com.spring.parent.controller.sort;
/**
 * 快速排序
 * 1 从数列中挑出一个元素，称为“基准”
 * 2 重新排序数列，所有元素比基准值小的摆放在基准前面，所有元素比基准值大的摆在基准的后面（相同的数可以到任一边）。在这个分割之后，  
 * 该基准是它的最后位置。这个称为分割（partition）操作
 * 3 递归地把小于基准值元素的子数列和大于基准值元素的子数列排序。
 * 
快速排序的基本思想：通过一趟排序将待排记录分隔成独立的两部分，其中一部分记录的关键字均比另一部分的关键字小，则可分别对这两部分记录继续进行排序，以达到整个序列有序。
 * 
 * @author Administrator
 *
 */
public class QuickSort {

	public static void main(String[] args) {
//		String str1 = "通话";
//		String str2 = "重地";
//		System.out.println(String.format("str1：%d | str2：%d", 
//		                     str1.hashCode(),str2.hashCode()));
//		System.out.println(str1.equals(str2));
//	 
//		 System.out.println(Math.round(-1.5));//-1
//		
//		 System.out.println(Math.round(1.5));// 2

		int [] test=new int[] {1,5,14,23,566,13,2,4,134,234,23,453,23,42,3,2,34};
		quickSorts(test,0,16);
	}
		
	public static int[] quickSorts(int [] array, int start, int end) {
        if (array.length < 1 || start < 0 || end >= array.length || start > end) return null;
        System.out.println("start="+start+"--end="+end);
        int smallIndex = partition(array, start, end);
        
        System.out.println("smallIndex="+smallIndex);
        if(smallIndex>start)//分治法  这是前面的 smallIndex 比基准小的数    如果数多那么就多次循环
        	quickSorts(array, start, smallIndex - 1);
        if (smallIndex < end)//分治法  这是后面的 smallIndex 比基准大的数
        	quickSorts(array, smallIndex + 1, end);
        
        for(int i=0;i<array.length;i++) {
        	System.out.print(array[i]+",");
        }
    	System.out.println();

        return array;        
	}
	
	  /**
     * 快速排序算法——partition
     * @param array
     * @param start
     * @param end
     * @return
     */
    public static int partition(int[] array, int start, int end) {
        int pivot = (int) (start + Math.random() * (end - start + 1));//从开始和结束之间随机抽出一个数
        System.out.println("pivot="+pivot);
        int smallIndex = start - 1;// 最开始之前的数 
        System.out.println("smallIndex="+smallIndex);//-1 开始 
        swap(array, pivot, end);//交换随机数和最后一位的位置
        
        for (int i = start; i <= end; i++) //从数组start开始循环
        	//如果有个数比基数大  那么要把这个数放到基数的后面  也就是i 比smallIndex    大数的位置不变化
            if (array[i] <= array[end]) {//循环所有数和基数相比   最终实现 比基数小的摆到基数前面  大的摆到基数后面  最后基数会和比基数大的第一位互换
            	//如果array[i]比基数小 那么smallIndex+1 如果没有出过比基数大的值那么没变化    如果出过 那么i>smallIndex 替换i和smallIndex
            	//如果array[i]比基数大 那么i+1 smallIndex不变  那么下次进入的时候就会让 比基数小的值和比基数大的值进行交换
                smallIndex++;//最新的比基数小的下标  如果循环中有数比基数小则加1   smallIndex就是代表了有几个数比基数小 以及最后自己比自己 然后把自己和smallIndex下替换
                if (i > smallIndex)//这是关键   只有     
                    swap(array, i, smallIndex);
            }
        return smallIndex;//最后返回的smallIndex 基准所在的index 
    }
    
   

    /**
     * 交换数组内两个元素
     * @param array
     * @param i
     * @param j
     */
    public static void swap(int[] array, int i, int j) {
    	System.out.println("i="+i+"---j="+j);
    	System.out.println("swap之前的数据="+array.length);
    	for(int x=0;x<array.length;x++) {
        	System.out.print(array[x]+",");
        }
    	System.out.println();

        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    	System.out.println("swap之后的数据=");
        for(int z=0;z<array.length;z++) {
        	System.out.print(array[z]+",");
        }
    	System.out.println();
    }
}
