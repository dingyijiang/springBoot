package com.spring.parent.controller.robot;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

public class ZsyhBank {
    private WebDriver driver = null;

    @Autowired
    private ZsDataCreate dataCreate;
	
	
	@PostMapping("login")
	@ResponseBody
	public Object login(@RequestBody JSONObject param) throws InterruptedException, AWTException {
		if(driver!=null){
            return "有为处理完的任务";
        }		
		// 设置浏览器驱动
		File file_ie = new File("D:\\code\\pachong\\IEDriverServer.exe");
	    System.setProperty("webdriver.ie.driver", file_ie.getAbsolutePath());
		// 设置浏览器参数
		DesiredCapabilities caps = DesiredCapabilities.internetExplorer();
		caps.setCapability("ignoreZoomSetting", true);
		caps.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
		//  caps.setCapability("initialBrowserUrl","https://www.baidu.com/");
		caps.setCapability("initialBrowserUrl",
		"https://pbsz.ebank.cmbchina.com/CmbBank_GenShell/UI/GenShellPC/Login/Login.aspx?logintype=C");
		WebDriver ieDriver = new InternetExplorerDriver(new InternetExplorerOptions(caps));
		ieDriver.manage().window().maximize();
		// 登录
		System.out.println("鼠标移动");
		Robot robot = new Robot();
				
		WebElement goPassWordLogin = ieDriver.findElement(By.id("goPassWordLogin"));
		goPassWordLogin.click();

		robot.setAutoDelay(300);
		//只能一个一个字符的转成对应的KeyEvent  因为获取不到元素  所有WebElement不能使用
		String idPhone=param.getString("idPhone");
		for(int i=0;i<idPhone.length();i++) {
			RobotUtil.keyClick(idPhone.charAt(i));
			Thread.sleep(1000);
		}
		
		robot.keyPress(KeyEvent.VK_TAB);
		robot.keyRelease(KeyEvent.VK_TAB);

		System.out.println("输入密码");
		//只能一个一个字符的转成对应的KeyEvent  因为获取不到元素  所有WebElement不能使用
		String password=param.getString("password");
		for(int i=0;i<password.length();i++) {
			RobotUtil.keyClick(password.charAt(i));
			Thread.sleep(1000);
		}				
		
		//到下个页面点击发送验证码
		// 选择信用卡
	    WebElement search_button = ieDriver.findElement(By.id("LoginBtn"));
		search_button.click();

		Thread.sleep(2000);
		//获取验证码事件
		WebElement btnSendCode = ieDriver.findElement(By.id("btnSendCode"));
		btnSendCode.click();
		driver=ieDriver;
		return JsonResult.ok();
	}
	
	 //输入验证码
	 @PostMapping("push_code")
	 @ResponseBody
	 public Object pushCode(@RequestParam("code") String code) throws InterruptedException {
	    WebDriver ieDriver = driver;
	    //获取输入验证码事件
	    WebElement txtSendCode = ieDriver.findElement(By.id("txtSendCode"));
		txtSendCode.click();
		txtSendCode.sendKeys(code);
	    Thread.sleep(1000);
	    //获取登录按钮的事件
	    WebElement submit_button = ieDriver.findElement(By.id("btnVerifyCode"));
	    submit_button.click();
		return JsonResult.ok();  
	}
	 
	 @PostMapping("get_data")
	    @ResponseBody
	    public Object getData(@RequestBody JSONObject param) throws InterruptedException {
	        WebDriver ieDriver = driver;
	        JSONObject message=dataCreate.doTask(ieDriver);
	        System.out.println(message.toString());
	        return JsonResult.ok();
	    }
	 
	
	
	public static void main(String[] args) throws InterruptedException, AWTException {
		File file_ie = new File("D:\\code\\pachong\\IEDriverServer.exe");
	    System.setProperty("webdriver.ie.driver", file_ie.getAbsolutePath());
		// 设置浏览器参数
		DesiredCapabilities caps = DesiredCapabilities.internetExplorer();
		caps.setCapability("ignoreZoomSetting", true);
		caps.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
		//  caps.setCapability("initialBrowserUrl","https://www.baidu.com/");
		caps.setCapability("initialBrowserUrl",
		"https://pbsz.ebank.cmbchina.com/CmbBank_GenShell/UI/GenShellPC/Login/Login.aspx?logintype=C");
		WebDriver ieDriver = new InternetExplorerDriver(new InternetExplorerOptions(caps));
		ieDriver.manage().window().maximize();
		// 登录
		//System.out.println("鼠标移动");
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_T);
		Thread.sleep(1000);
		System.out.println("open new window");
		robot.keyRelease(KeyEvent.VK_CONTROL);	
		robot.keyRelease(KeyEvent.VK_T);	
		System.out.println("sleep five seconds");
		Thread.sleep(5000);
		System.out.println("search tabs");

		//切换到新页面找数据
    	ArrayList<String> tabs = new ArrayList<String> (ieDriver.getWindowHandles()); 
    	System.out.println("标签num="+tabs.size());
    	for(String str:tabs) {
    		System.out.println("tab="+str);
    	}
		
		//WebElement goPassWordLogin = ieDriver.findElement(By.id("kw"));
		//goPassWordLogin.click();
		
		//robot.setAutoDelay(300);
		//只能一个一个字符的转成对应的KeyEvent  因为获取不到元素  所有WebElement不能使用
		//Thread.sleep(1000);
		//robot.keyPress(KeyEvent.VK_A);
		//robot.keyRelease(KeyEvent.VK_A);
		//Thread.sleep(1000);
//		String name="Dingyijiang12345";
//		for(int i=0;i<name.length();i++) {
//			RobotUtil.keyClick(name.charAt(i));
//			Thread.sleep(500);
//		}
		//robot.keyPress(KeyEvent.VK_CONTROL);
		//robot.keyRelease(KeyEvent.VK_CONTROL);
		//Thread.sleep(1000);

		//WebElement baidu = ieDriver.findElement(By.id("su"));
		//baidu.click();
//		Thread.sleep(3000);
		
//		WebElement test = ieDriver.findElement(By.xpath("//div[@id='1']/h3/a"));
//		System.out.println(test.toString());
//		//WebElement id1 = ieDriver.findElement(By.id("1"));
//		test.click();
//		
//		//切换到新页面找数据
//    	ArrayList<String> tabs = new ArrayList<String> (ieDriver.getWindowHandles()); 
//    	System.out.println("标签num="+tabs.size());
//    	for(String str:tabs) {
//    		System.out.println("tab="+str);
//    	}
//    	ieDriver.switchTo().window(tabs.get(tabs.size()-1)); //switches to new tab

//		robot.keyPress(KeyEvent.VK_TAB);dsfsdf
//		robot.keyRelease(KeyEvent.VK_TAB);
//
//		System.out.println("输入密码");
//		robot.keyPress(KeyEvent.VK_9);
//		robot.keyRelease(KeyEvent.VK_9);
		//  robot.keyRelease(KeyEvent.VK_1);

		//
		  // 选择信用卡
	//	  WebElement search_button = ieDriver.findElement(By.id("LoginBtn"));
//		  search_button.click();

	//	  Thread.sleep(2000);
				  //
	//	  WebElement btnSendCode = ieDriver.findElement(By.id("btnSendCode"));
	//	  btnSendCode.click();
		  }


}
