package com.spring.parent.controller.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.spring.parent.controller.reflect.entity.UserEntity;

public class ClassFrom {
	public static void main(String[] args) throws InstantiationException, IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException {
		//1 new 创建对象   还可以使用反射创建对象
		//+UserEntity user=new UserEntity();
		
		//2反射创建对象   必须完整路径
		try {
			Class<?> forName=Class.forName("com.spring.parent.controller.reflect.entity.UserEntity");
			//3 newInstance使用无参构造函数  创建对象。。    所以有些框架要求必须有无参构造方法  例如hibernate
			Object newInstance=forName.newInstance();
			UserEntity userEtity=(UserEntity)newInstance;
			System.out.println("userEtity:"+userEtity);
			userEtity.setId("111");
			userEtity.setName("222");
			System.out.println(userEtity.getId()+"----"+userEtity.getName());
			// 使用发射技术创建对象和new 哪个效率高  new的效率高  new直接区堆创建地址     反射式class地址 再去实例化
			
			//有参构造函数
			Constructor<?> constructor= forName.getConstructor(String.class);
			Object newInstance1=constructor.newInstance("111");
			UserEntity user=(UserEntity)newInstance1;
			
			Constructor<?> constructor2= forName.getConstructor(String.class,String.class);
			Object newInstance2=constructor2.newInstance("111","222");
			UserEntity user2=(UserEntity)newInstance2;
			
			//获取类的所有方法
			Method[] methds=forName.getDeclaredMethods();
			for (int i = 0; i < methds.length; i++) {
				System.out.println(methds[i].getName()+"----"+methds[i].getReturnType());
			}
			
			//获取类的成员属性
			Field[] fields=forName.getDeclaredFields();
			for (int i = 0; i < fields.length; i++) {
				System.out.println(fields[i].getName()+"----");
			}
			
			//获取类的私有成员属性
			Field field=forName.getDeclaredField("id");
			Object obj=forName.newInstance();
			field.setAccessible(true);
			field.set(obj, "test");
			UserEntity entityUser=(UserEntity) obj;
			System.out.println("id----=="+entityUser.getId());
			//System.out.println("id----=="+field.get(obj));
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
