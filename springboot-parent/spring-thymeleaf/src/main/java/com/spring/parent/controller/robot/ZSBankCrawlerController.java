package com.spring.parent.controller.robot;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.util.List;

import javax.imageio.ImageIO;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.Augmenter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
@RestController
@RequestMapping("/crawler/zsbank")
public class ZSBankCrawlerController {
  //  private WebDriver ieDriver = null;  全局变量只是当前类  

    @Autowired
    private ZsDataCreate dataCreate;
//    @Autowired
//	private GatewayService gatewayService;
    
    private String uid = "";
    private String data_source = "";
    private String phone = "";
    private String id_no = "";
    private String name = "";
    private String account="";

	private static final Logger logger = LoggerFactory.getLogger(ZSBankCrawlerController.class);
    
	@PostMapping("login")
	@ResponseBody
	public Object login(@RequestBody JSONObject jsonObject) throws InterruptedException, AWTException {
    	logger.info("test");
		if(WebDriverUtil.getDriver()!=null){
			logger.info("有未处理玩的任务");
			return JsonResult.ok("有未处理完的任务");
        }
//		File file_ie = new File("D:\\code\\pachong\\IEDriverServer.exe");
//	    System.setProperty("webdriver.ie.driver", file_ie.getAbsolutePath());
		String url="https://pbsz.ebank.cmbchina.com/CmbBank_GenShell/UI/GenShellPC/Login/Login.aspx?logintype=C";
		WebDriver ieDriver = WebDriverUtil.initIEDriver(url,null);
       try {
		 uid = jsonObject.get("uid").toString();
         data_source = jsonObject.get("data_source").toString();
         phone = jsonObject.get("phone").toString();
         id_no = jsonObject.get("id_no").toString();
         name= jsonObject.get("name").toString();
         logger.info("uid={},data_source={},phone={},id_no={},name={}",uid,data_source,phone,id_no,name);
        //账号
        account = jsonObject.get("account").toString();
        if(StringUtil.isEmpty(account)) {
        	logger.error("请输入账号");
//			gatewayService.quitGateway(uid);
        	return JsonResult.fail("401", "请输入账号");
        }
        logger.info("account=="+account);
		//密码
        String password = jsonObject.get("password").toString();
        if (StringUtil.isSpecialChar(password)) {
        	logger.error("passwerd 参数错误");
			//释放资源
//			gatewayService.quitGateway(uid);
			return JsonResult.fail("401", "密码中含有特殊字符");
		}
        logger.info("password=="+password);
		ieDriver.manage().window().maximize();
		// 登录
		logger.info("sleep three seconds please send message");
		Thread.sleep(3000);
		String js = "showPassWordLogin()";
		((JavascriptExecutor) ieDriver).executeScript(js);
		//输入账号密码
		WinUtil.clickEvent(account);
		Thread.sleep(1000);
		Robot robot=new Robot();
		robot.keyPress(KeyEvent.VK_TAB);
		robot.keyRelease(KeyEvent.VK_TAB);
		Thread.sleep(1000);
		WinUtil.clickEvent(password);
		
		WebElement search_button = ieDriver.findElement(By.id("LoginBtn"));
		search_button.click();
		
		//这里抓取下错误原因
		Thread.sleep(2000);
		logger.info("get error msg");
		List<WebElement> errMsgSpan = ieDriver.findElements(By.id("errMsgSpan"));
		if(errMsgSpan.size()>0) {
			String errorMsg=errMsgSpan.get(0).getText();
			logger.info("errMsgSpan={}",errorMsg);
			if("请输入附加码".equals(errorMsg)) {
				 WebElement newImgValidCodeALink = ieDriver.findElement(By.id("ImgExtraPwd"));
		         BufferedImage inputbig = createElementImage(ieDriver,newImgValidCodeALink);
		         String fileName = "" + System.currentTimeMillis();
		         File file = new File("C:\\codeimg\\" + fileName + ".jpg");
//		         Rectangle rect = new Rectangle(647, 410, 73, 25);
//		         BufferedImage image = robot.createScreenCapture(rect);
		         ImageIO.write(inputbig, "jpg", file);
			}			
//			gatewayService.quitGateway(uid);
		    return JsonResult.fail(errorMsg);
		}
		logger.info("no error msg  sleep three seconds");
		Thread.sleep(3000);
		//获取验证码事件e
		try {
			WebElement btnSendCode = ieDriver.findElement(By.id("btnSendCode"));
			btnSendCode.click();
		}catch(Exception e) {
			logger.info("点击发送验证码失败");
			//有可能直接登录进去了 这里给前端返回code=3  特殊情况  但是确实有
			Thread.sleep(3000);
		    dataCreate.doTask(ieDriver,uid, data_source, name, phone, id_no,account);
//		    return JsonResult.success("3", "获取验证码失败");
		}
		
		//这里要调用三方接口 执行robot 输入账号密码
       }catch(Exception e) {
    	   	logger.info("登录异常,uid={},error={}",uid,e);
    	   	logger.info("free 资源 uid={}",uid);
//			gatewayService.quitGateway(uid);
    	   	logger.info("free 资源 end uid={}",uid);
            ieDriver = null;
			return JsonResult.fail("500", "登录系统出错 请稍后再试");
       }
		return JsonResult.ok();
	}
	
	//多添加一个接口 如果登录页面需要图形验证码 那么这个接口就要使用
	@PostMapping("pushImgCode")
	@ResponseBody
	public Object pushImgCode(@RequestBody JSONObject jsonObject) throws InterruptedException {
		WebDriver ieDriver=WebDriverUtil.getDriver();
		try {
			WebElement ExtraPwd = ieDriver.findElement(By.id("ExtraPwd"));
			ExtraPwd.click();
			//输入图形验证码
			String imgMsg=jsonObject.getString("imgMsg");
			for(int i=0;i<imgMsg.length();i++) {
				RobotUtil.keyClick(imgMsg.charAt(i));
				Thread.sleep(500);
			}
			try {
				WebElement btnSendCode = ieDriver.findElement(By.id("btnSendCode"));
				btnSendCode.click();
			}catch(Exception e) {
				logger.info("点击发送验证码失败");
				//有可能直接登录进去了 这里给前端返回code=3  特殊情况  但是确实有
				Thread.sleep(3000);
			    dataCreate.doTask(ieDriver,uid, data_source, name, phone, id_no,account);
			    return JsonResult.success("3", "获取验证码失败");
			}
		}catch(Exception e) {
			logger.info("登录异常,uid={},error={}",uid,e);
    	   	logger.info("free 资源 uid={}",uid);
			gatewayService.quitGateway(uid);
    	   	logger.info("free 资源 end uid={}",uid);
    	   	ieDriver = null;
			return JsonResult.fail("500", "登录系统出错 请稍后再试");
		}		
		return jsonObject;
		
	}
	
	 
	
	
	 //输入验证码
	 @PostMapping("push_code")
	 @ResponseBody
	 public Object pushCode(@RequestBody JSONObject jsonObject) throws InterruptedException {
		 logger.info("push_code start");
		 WebDriver ieDriver=WebDriverUtil.getDriver();
		 String code = jsonObject.get("code").toString();
		if(StringUtil.isEmpty(code)) {
            return JsonResult.fail("验证码不能为空");
		}
		 logger.info("code={}",code);
		 try{
		    Thread.sleep(1000);
		    //获取输入验证码事件
		    WebElement txtSendCode = ieDriver.findElement(By.id("txtSendCode"));
			txtSendCode.click(); 
			for(int i=0;i<code.length();i++) {
				RobotUtil.keyClick(code.charAt(i));
				Thread.sleep(500);
			}
		    Thread.sleep(1000);
		    //获取登录按钮的事件
		    WebElement submit_button = ieDriver.findElement(By.id("btnVerifyCode"));
		    submit_button.click();
		    
	        List<WebElement> controlExplain = ieDriver.findElements(By.className("control-explain"));
	        if(controlExplain.size()>0) {
	        	String errorMsg=controlExplain.get(0).getText();
	        	logger.info("error message={}",errorMsg);
	            return JsonResult.fail(errorMsg);
	        }		    
		    Thread.sleep(3000);
	        dataCreate.doTask(ieDriver,uid, data_source, name, phone, id_no,account);
		}catch(Exception e) {
            logger.error("输入验证码并且登录异常：",e);
            ieDriver.quit();
            ieDriver=null;//释放全局变量
            gatewayService.quitGateway(uid);
            return JsonResult.fail("释放资源");
		}
		return JsonResult.ok();  
	}
	 
	 
	 
	
	 
	
	
	 public BufferedImage createElementImage(WebDriver driver, WebElement webElement)
             throws Exception {
	     // 获得webElement的位置和大小。
	     Point location = webElement.getLocation();
	     Dimension size = webElement.getSize();
	     // 创建全屏截图。
	     BufferedImage originalImage =
	                 ImageIO.read(new ByteArrayInputStream(takeScreenshot(driver)));
	     // 截取webElement所在位置的子图。
	     BufferedImage croppedImage = originalImage.getSubimage(
	                 location.getX(),
	                 location.getY(),
	                 size.getWidth(),
	                 size.getHeight());
	     return croppedImage;
	 }
	 public static byte[] takeScreenshot(WebDriver driver) throws Exception {
	        WebDriver augmentedDriver = new Augmenter().augment(driver);
	        return ((TakesScreenshot) augmentedDriver).getScreenshotAs(OutputType.BYTES);
	        //TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
	        //return takesScreenshot.getScreenshotAs(OutputType.BYTES);
	 }


}
