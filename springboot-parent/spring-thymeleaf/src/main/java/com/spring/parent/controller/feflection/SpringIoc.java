package com.spring.parent.controller.feflection;

import java.lang.reflect.Field;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.spring.parent.controller.reflect.entity.UserEntity;

/**
 * java 反射实现springioc  
 * spring实现原理   springioc 加载过程
 * @author Administrator
 *
 */
public class SpringIoc {
	private String xmlPath;
	public SpringIoc(String xmlPath) {
		this.xmlPath=xmlPath;
	}
	
	
	public Object getBean(String beanName) throws DocumentException, ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchFieldException, SecurityException {
		//1 找到并读取接续xml文件
		//xml解析器
		SAXReader reader=new SAXReader();
		//获取当前项目路径
		Document read=reader.read(this.getClass().getClassLoader().getResourceAsStream(xmlPath));
		//获取根今典
		Element rootElement=read.getRootElement();
		List<Element> elements=rootElement.elements();
		Object obj=null;
		for(Element sonEle:elements) {
			// 2.获取到每个bean配置 获取class地址
			String sonBeanId = sonEle.attributeValue("id");
			if(!beanName.equals(sonBeanId)) {
				continue;
			}
			//全局路径  class 用于反射
			String sonBeanClass = sonEle.attributeValue("class");
			Class<?> forName=Class.forName(sonBeanClass);
			obj=forName.newInstance();
			
			List<Element> sonelements=sonEle.elements();
			for(Element sonSonele:sonelements) {
				String name = sonSonele.attributeValue("name");
				String value = sonSonele.attributeValue("value");
				Field declareField=forName.getDeclaredField(name);
				declareField.setAccessible(true);
				declareField.set(obj, value);
			}
			
		}
		
		//2 读取每个bean配置,找到class地址
		//3 拿到class地址，进行反射实例化， 通过反射api 将xml中的值 set到实例化对象中

		return obj;
	}

	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchFieldException, SecurityException, DocumentException {
		SpringIoc springIoc=new SpringIoc("user.xml");
		Object obj=springIoc.getBean("user1");
		UserEntity entity=(UserEntity) obj;
		System.out.println(entity.getId()+"------"+entity.getName());
	}
}
