package com.spring.parent.controller.robot;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import junit.framework.Assert;

public class JsBank {
	public static void main(String[] args) throws AWTException {
		testLogin();
	}

	private static void testBaidu() throws AWTException {
		//		SpringApplication.run(CrawlerApplication.class, args);
		File file_ie = new File("D:\\code\\pachong\\IEDriverServer.exe");

		System.setProperty( "webdriver.ie.driver", file_ie.getAbsolutePath());

		DesiredCapabilities caps = DesiredCapabilities.internetExplorer();

		caps.setCapability("ignoreZoomSetting", true);
		caps.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
		caps.setCapability("initialBrowserUrl","https://www.baidu.com/");
		WebDriver ieDriver = new InternetExplorerDriver(caps);
		File PageSource = new File("");
		System.out.println(ieDriver.getPageSource());
	}

	private void testCmb() throws AWTException {
		//设置浏览器驱动
		File file_ie = new File("D:\\jxli\\driver\\IEDriverServer.exe");
		System.setProperty( "webdriver.ie.driver", file_ie.getAbsolutePath());
		//设置浏览器参数
		DesiredCapabilities caps = DesiredCapabilities.internetExplorer();
		caps.setCapability("ignoreZoomSetting", true);
		caps.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
//		caps.setCapability("initialBrowserUrl","https://www.baidu.com/");
		caps.setCapability("initialBrowserUrl","https://user.cmbchina.com/User/Login");
		WebDriver ieDriver = new InternetExplorerDriver(caps);
		//登录


		//获取页面信息
		ieDriver.findElement(By.id("imgCreditCard")).click();
		ieDriver.findElement(By.linkText("已出账单查询")).click();
		System.out.println(ieDriver.getPageSource());
		ieDriver.findElement(By.linkText("未出账单查询")).click();
		System.out.println(ieDriver.getPageSource());




//		WebDriver ieDriver = new InternetExplorerDriver();
//		ieDriver.get("https://user.cmbchina.com/User/Login");
//		ieDriver.get("https://www.baidu.com/");

//		WebElement search_text = ieDriver.findElement(By.id("kw"));
//		WebElement search_button = ieDriver.findElement(By.id("su"));

//		WebElement search_text = ieDriver.findElement(By.id("spnLoginName"));
//		WebElement search_button = ieDriver.findElement(By.id("su"));

//		search_text.sendKeys("Java");
//		search_text.clear();
//		search_text.sendKeys("Selenium");
//		search_button.click();
//		WebDriver driver = new InternetExplorerDriver();
//		driver.get("http://www.itest.info");
//
//		String title = driver.getTitle();
//		System.out.printf(title);

//		ieDriver.close();

		Robot robot = new Robot();
		robot.setAutoDelay(1000);
//		robot.mouseMove(100, 100);

		robot.keyPress(KeyEvent.VK_A);
		robot.keyRelease(KeyEvent.VK_A);
//		robot.mouseMove(850, 545);
//		System.out.println("单击");
//		robot.mousePress(InputEvent.BUTTON1_MASK);
//		robot.mouseRelease(InputEvent.BUTTON1_MASK);
		robot.keyPress(KeyEvent.VK_TAB);
		robot.keyRelease(KeyEvent.VK_TAB);
		robot.keyPress(KeyEvent.VK_TAB);
		robot.keyRelease(KeyEvent.VK_TAB);

		System.out.println("输入b");
		robot.keyPress(KeyEvent.VK_B);
		robot.keyRelease(KeyEvent.VK_B);
//
		//选择信用卡
		WebElement search_button = ieDriver.findElement(By.id("imgCreditCard"));
		search_button.click();

//		WebElement search_button = ieDriver.findElement(By.id("btnLogin"));
//		search_button.click();
//		System.out.println("右击");
//		robot.mousePress(InputEvent.BUTTON3_MASK);
//		robot.mouseRelease(InputEvent.BUTTON3_MASK);
		//未出账单请求
		//请求 URL: https://pbsz.ebank.cmbchina.com/CmbBank_GenShell/UI/GenShellPC/Login/ApplyToken.aspx
		//Accept: */*
//		Accept-Encoding: gzip, deflate
//		Accept-Language: zh-CN
//		Cache-Control: no-cache
//		Connection: Keep-Alive
//		Content-Length: 127
//		Content-Type: application/x-www-form-urlencoded
//		Cookie: CMB_GenServer=BranchNo:; browsehistory=%7B%22titlearray%22%3A%5B%22%u7F51%u4E0A%u94F6%u884C%7C%u5168%u5929%u5019%u94F6%u884C%u670D%u52A1%22%2C%22%u7F51%u4E0A%u94F6%u884C%u5927%u4F17%u7248%22%2C%22%u4E00%u5361%u901A%7C%u501F%u8BB0%u5361%u5206%u7C7B%u4E00%u89C8%u8868%22%2C%22%u4E00%u7F51%u901A%u8D26%u6237%22%2C%22%u7F51%u4E0A%u94F6%u884C%7C%u5168%u5929%u5019%u94F6%u884C%u670D%u52A1%22%5D%7D; www_bottom_adv_visit=visited; WEBTRENDS_ID=36.112.69.51-3146810224.30762881::6C46440C23F259D2425D2B2BCF207; AuthType=A; DeviceType=A; ProVersion=; ClientStamp=5115316484294577334; WTFPC=id=2941e016d9d8f1937271568095588535:lv=1568097338758:ss=1568095588535
//		Host: pbsz.ebank.cmbchina.com
//		Referer: https://pbsz.ebank.cmbchina.com/CmbBank_GenShell/UI/GenShellPC/Login/GenIndex.aspx
//		User-Agent: Mozilla/5.0 (Windows NT 10.0; WOW64; Trident/7.0; rv:11.0) like Gecko

		//已出账单查询未出账单查询
		ieDriver.findElement(By.linkText("已出账单查询")).click();
		ieDriver.findElement(By.tagName("body")).getText();
		ieDriver.findElement(By.linkText("未出账单查询")).click();
	}

	private static void testLogin() throws AWTException {
		//设置浏览器驱动
		File file_ie = new File("D:\\\\code\\\\pachong\\\\IEDriverServer.exe");
		System.setProperty( "webdriver.ie.driver", file_ie.getAbsolutePath());
		//设置浏览器参数
		DesiredCapabilities caps = DesiredCapabilities.internetExplorer();
		caps.setCapability("ignoreZoomSetting", true);
		caps.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
//		caps.setCapability("initialBrowserUrl","https://www.baidu.com/");
		caps.setCapability("initialBrowserUrl","https://ibsbjstar.ccb.com.cn/CCBIS/V6/common/login.jsp");
		WebDriver ieDriver = new InternetExplorerDriver(caps);
		//登录


//		//获取页面信息
//		ieDriver.findElement(By.id("imgCreditCard")).click();
//		ieDriver.findElement(By.linkText("已出账单查询")).click();
//		System.out.println(ieDriver.getPageSource());
//		ieDriver.findElement(By.linkText("未出账单查询")).click();
//		System.out.println(ieDriver.getPageSource());
						
		Robot robot = new Robot();
		//robot.setAutoDelay(1000);
//			robot.mouseMove(100, 100);
		ieDriver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		WebElement search_box = ieDriver.findElement(By.id("LOGPASS_SHOW"));
		search_box.click();
		
		robot.keyPress(KeyEvent.VK_A);
		robot.keyRelease(KeyEvent.VK_A);
//		robot.mouseMove(850, 545);
//		System.out.println("单击");
//		robot.mousePress(InputEvent.BUTTON1_MASK);
//		robot.mouseRelease(InputEvent.BUTTON1_MASK);
		robot.keyPress(KeyEvent.VK_TAB);
		robot.keyRelease(KeyEvent.VK_TAB);
		robot.keyPress(KeyEvent.VK_TAB);
		robot.keyRelease(KeyEvent.VK_TAB);

		System.out.println("输入b");
		robot.keyPress(KeyEvent.VK_B);
		robot.keyRelease(KeyEvent.VK_B);
//
		//选择信用卡
		WebElement search_button = ieDriver.findElement(By.id("btnLogin"));
		search_button.click();
		
		
	}
	
	/**
	       * 在给定的时间内去查找元素，如果没找到则超时，抛出异常
	       * */
	      public static void waitForElementToLoad(WebDriver driver, int timeOut, final By By) {
	          try {
	              (new WebDriverWait(driver, timeOut)).until(new ExpectedCondition<Boolean>() {
	 
	                  public Boolean apply(WebDriver driver) {
	                      WebElement element = driver.findElement(By);
	                     return element.isDisplayed();
	                 }
	             });
	         } catch (TimeoutException e) {
	             Assert.fail("超时!! " + timeOut + " 秒之后还没找到元素 [" + By + "]");
	         }
	     }

}
