package com.spring.parent.controller.robot;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;

@RestController
@RequestMapping("/binfo")
public class LoginController {

    private WebDriver chromeDriver = null;


    @Autowired
    private DataCreate dataCreate;
    @GetMapping(value = "/initbrow")
    @ResponseBody
    public Object initBrow() throws InterruptedException, AWTException, IOException {
        if(chromeDriver!=null){
            return "有为处理完的任务";
        }
        File file_ie = new File("D:\\browerServer\\chromedriver.exe");
        System.setProperty( "webdriver.chrome.driver", file_ie.getAbsolutePath());
        WebDriver ieDriver = new ChromeDriver();
        ieDriver.get("https://creditcard.ecitic.com/citiccard/ucweb/entry.do");//加载中心银行信用卡登录页面
        ieDriver.manage().window().maximize();
        Thread.sleep(1000*3);
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_T);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyRelease(KeyEvent.VK_T);
        Thread.sleep(1000*1);
        ArrayList<String> tabs = new ArrayList<String> (ieDriver.getWindowHandles());
        for(int i=0;i<5;i++) {
            if (tabs.size() == 1) {
                Robot robot1 = new Robot();
                robot1.keyPress(KeyEvent.VK_CONTROL);
                robot1.keyPress(KeyEvent.VK_T);
                robot1.keyRelease(KeyEvent.VK_CONTROL);
                robot1.keyRelease(KeyEvent.VK_T);
                Thread.sleep(1000 * 1);
            }
        }
        ieDriver.switchTo().window(tabs.get(1)); //switches to new tab
        ieDriver.get("https://creditcard.ecitic.com/citiccard/ucweb/newvalicode.do");
        Thread.sleep(1000 * 1);
        String fileName = ""+System.currentTimeMillis();
        File file = new File("D:\\dididi\\"+fileName+".jpg");
        Rectangle rect = new Rectangle(647, 410, 73, 25);
        BufferedImage image = robot.createScreenCapture(rect);
        try {
            ImageIO.write(image, "jpg", file);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        FileInputStream inputStream = new FileInputStream(file);
        chromeDriver=ieDriver;
        ieDriver.switchTo().window(tabs.get(0)); //switches to new tab
        return JsonResult.ok(fileName);


    }

    @GetMapping(value = "/get_img_code",produces = MediaType.IMAGE_JPEG_VALUE)
    @ResponseBody
    public byte[] getImgCode(@RequestParam("pici")String pici) throws Exception {
        File file = new File("D:\\dididi\\"+pici+".jpg");
        FileInputStream inputStream = new FileInputStream(file);
        byte[] bytes = new byte[inputStream.available()];
        inputStream.read(bytes, 0, inputStream.available());
        inputStream.close();
        return bytes;
    }

    @GetMapping(value = "/refresh")
    @ResponseBody
    public String refresh(@RequestParam("pici")String pici) throws Exception {
        chromeDriver =null;
        return "success";
    }

    @PostMapping("next_pass")
    @ResponseBody
    public Object nextPass(@RequestBody JSONObject param) throws InterruptedException {

        WebDriver webDriver = chromeDriver;
        WebElement userNum = webDriver.findElement(By.id("phoneNbr"));
        for(int cs=0;cs<5;cs++) {
            if (userNum == null) {
                Thread.sleep(1000 * 3);
            }
        }
        userNum.sendKeys(param.getString("mobile"));//输入手机号
        WebElement imgvalicode = webDriver.findElement(By.id("imgvalicode"));
        if (imgvalicode.isDisplayed()){
            imgvalicode.sendKeys(param.getString("code"));
        }
        Thread.sleep(1000*1);
        WebElement getsms = webDriver.findElement(By.id("getsms"));
        getsms.click();
        WebElement reg_phone_error = webDriver.findElement(By.id("reg_phone_error"));
        if (reg_phone_error.isDisplayed()){
            return JsonResult.fail(reg_phone_error.getText());
        }else{
            return JsonResult.ok();
        }

    }
    @PostMapping("push_code")
    @ResponseBody
    public Object pushCode(@RequestBody JSONObject param) throws InterruptedException {
        WebDriver ieDriver = chromeDriver;
        WebElement valicode = ieDriver.findElement(By.id("valicode"));
        valicode.sendKeys(param.getString("smscode"));
        Thread.sleep(1000);
        WebElement search_button = ieDriver.findElement(By.id("checkcode"));
        search_button.click();
        if(ieDriver.findElements(By.id("reg_phone_error")).size()!=0 && ieDriver.findElement(By.id("reg_phone_error")).isDisplayed()){
            valicode.clear();
            return JsonResult.fail(ieDriver.findElement(By.id("reg_phone_error")).getText());
        }else{
            return JsonResult.ok();
        }
    }



    @PostMapping("get_data")
    @ResponseBody
    public Object getData(@RequestBody JSONObject param) throws InterruptedException {
        WebDriver ieDriver = chromeDriver;
        WebElement mm = ieDriver.findElement(By.id("mm"));
        mm.sendKeys(param.getString("password"));
        if(ieDriver.findElements(By.id("remm")).size()!=0 && ieDriver.findElement(By.id("remm")).isDisplayed()){
            ieDriver.findElement(By.id("remm")).sendKeys(param.getString("password"));

        }
        if (ieDriver.findElements(By.id("register")).size()!=0 && ieDriver.findElement(By.id("register")).isDisplayed()){
            ieDriver.findElement(By.id("register")).click();
        }else {
            WebElement login = ieDriver.findElement(By.id("login"));
            login.click();
        }
        if (ieDriver.findElements(By.id("mm_emsg")).size()!=0 && ieDriver.findElement(By.id("mm_emsg")).isDisplayed()){
            return JsonResult.fail(ieDriver.findElement(By.id("mm_emsg")).getText());
        }
        Thread.sleep(1000*5);
        if (ieDriver.findElements(By.className("ca_name")).size()==0){
            return JsonResult.fail("您还未绑定任何信用卡无法完成认证");
        }

        dataCreate.doTask(ieDriver);
        chromeDriver = null;
        return JsonResult.ok();

    }
}
