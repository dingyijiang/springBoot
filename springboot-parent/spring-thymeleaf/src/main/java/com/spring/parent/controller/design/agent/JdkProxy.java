package com.spring.parent.controller.design.agent;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import org.springframework.cglib.proxy.Proxy;

import com.spring.parent.controller.design.factory.Main;

public class JdkProxy implements InvocationHandler{
	private Object target;
	public JdkProxy (Object target) {
		this.target=target;
	}
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println("我是中介 --start");
		Object ivoke=method.invoke(target, args);
		System.out.println("我是中介 --end");
		return ivoke;
	}
	
	
}

class Test {
		public static void main(String[] args) {
			Xm xm=new Xm();
			JdkProxy jdkProxy = new JdkProxy(xm);
			House hose=(House) Proxy.newProxyInstance(xm.getClass().getClassLoader(), xm.getClass().getInterfaces(), jdkProxy);
			host.mai();
		}
}
