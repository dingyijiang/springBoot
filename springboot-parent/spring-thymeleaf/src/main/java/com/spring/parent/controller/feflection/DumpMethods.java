package com.spring.parent.controller.feflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Java Reflection API
 * Class 类的方法演示
 * @author Administrator
 *
 */
public class DumpMethods {

	public static void main(String[] args) throws Exception {
		useMethods();
	}
	
	 public int add(int param1, int param2){
	        return param1 + param2;
	 }
	    public String echo(String message){
	        return "Hello: " + message;
	 }

	public static void useMethods() throws Exception{		 
        // 以前的常规执行手段
		DumpMethods tester = new DumpMethods();
        System.out.println(tester.add(1, 2));
        System.out.println(tester.echo("Tom"));
        System.out.println("---------------------------");
        
        //通过反射的方法调用
        //1 获取Class对象
        // 前面用的方法是：Class.forName()方法获取
        // 这里用第二种方法，类名.class
        Class<?> classType=tester.getClass();
        // 生成新的对象：用newInstance()方法
        Object dumpMethods = classType.newInstance();
        //第二种方法 生成对象  
        Constructor dumpMethods2 = classType.getConstructor(new Class[] {String.class,int.class});
        Object obj2=dumpMethods2.newInstance(new Object[] {"dyj","66"});

        
        System.out.println(dumpMethods instanceof DumpMethods); // 输出true
        
        // 通过反射调用方法
        // 首先需要获得与该方法对应的Method对象
        Method addMethod = classType.getMethod("add", new Class[] { int.class,
                int.class });
        // 第一个参数是方法名，第二个参数是这个方法所需要的参数的Class对象的数组
        
        // 调用目标方法
        Object result = addMethod.invoke(dumpMethods, new Object[] { 2, 3 });
        System.out.println(result); // 此时result是Integer类型
        
        Method stringMethod = classType.getMethod("echo", new Class[] { String.class});
        Object strResult = stringMethod.invoke(dumpMethods, new Object[] {"dingyijiang"});
        System.out.println(strResult);
	}
		
	
	//遍历出类中所有的方法
	public static void getMethods() throws ClassNotFoundException {
        //获得字符串所标识的类的class对象com.spring.parent.controller.leecode
		Class<?> classType=Class.forName("com.spring.parent.controller.leecode.Demo7");////在此处传入字符串指定类名，所以参数获取可以是一个运行期的行为，可以用args[0]
		//返回class对象所对应的类或接口中，所声明的所有方法的数组（包括私有方法）
        Method[] methods = classType.getDeclaredMethods();
        //遍历输出所有方法声明
        for(Method method : methods)
        {
            System.out.println(method);
        }		
	}
}
