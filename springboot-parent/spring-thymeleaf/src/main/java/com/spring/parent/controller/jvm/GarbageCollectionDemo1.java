package com.spring.pa,rent.controller.jvm;

public class GarbageCollectionDemo1 {

	
	public static void main(String[] args) {
		
		GarbageCollectionDemo1 demo1=new GarbageCollectionDemo1();
		demo1=null;
		System.gc();//手动调用gc不代表  百分之百回收
		
			
	}
	
	public int getSingNum(int [] nums) {
		for(int i=0;i<nums.l)
		return 0;
	}
	/**
	 * gc的时候会调用   finalize 是Object方法   
	 */
	@Override
	protected void finalize() throws Throwable {
		System.out.println("丁一江   --  开始垃圾回收啦");
		super.finalize();
	}
}
