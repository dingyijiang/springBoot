package com.spring.parent.controller.design.agent;
/**
 * 静态代理类
 * 需要自己去生成代理类
 * @author Administrator
 *
 */
public class StaticAgent implements House{
	private Xm xm;
	//在代理类中完成买房操作  需要把小明传进来  需要构造函数
	public StaticAgent(Xm xm) {
		this.xm=xm;
	}
	
	public void mai() {
		System.out.println("我是中介 --start");
		xm.mai();
		System.out.println("我是中介 --end");

	}
}
