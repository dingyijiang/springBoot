package com.spring.parent.controller.design.agent;
/**
 * 代理 即为控制一个对象的访问    访问不访问真实的对象而是访问代理类
 * @author Administrator
 *
 */
public class Mian {
	public static void main(String[] args) {
		StaticAgent agent=new StaticAgent(new Xm());
		agent.mai();
	}
}
