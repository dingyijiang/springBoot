package com.spring.parent.controller.robot;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Component
public class ZsDataCreate {

    @Async("taskExecutor")  //myTaskAsynPool即配置线程池的方法名，此处如果不写自定义线程池的方法名，会使用默认的线程池
    public JSONObject doTask(WebDriver ieDriver) throws InterruptedException{
        JSONObject endResult = new JSONObject();
        //点击获取一卡通用户
        WebElement imgUser = ieDriver.findElement(By.id("imgUser"));
        imgUser.click();
        
        JSONObject userMsg = new JSONObject();
        //真实姓名
        WebElement lbRealName = ieDriver.findElement(By.id("lbRealName"));
        userMsg.put("realName",lbRealName.getText());
        //证件类型
        WebElement lbIDType = ieDriver.findElement(By.id("lbIDType"));
        userMsg.put("IDType",lbIDType.getText());
        //证件号码
        WebElement lbIdentNo = ieDriver.findElement(By.id("lbIdentNo"));
        userMsg.put("IDCard",lbIdentNo.getText());
        //手机号
        WebElement tbCellPhone = ieDriver.findElement(By.id("tbCellPhone"));
        userMsg.put("lbIdentNo",tbCellPhone.getAttribute("value"));//获取input的value
        //email
        WebElement tbEmail = ieDriver.findElement(By.id("tbEmail"));
        userMsg.put("email",tbEmail.getText());
        endResult.put("userMsg", userMsg);
        
        
        //点击获取信用卡
        JSONObject xykMsg = new JSONObject();

        WebElement imgCreditCard = ieDriver.findElement(By.id("imgCreditCard"));
        imgUser.click();
        //信用额度
        WebElement RMBXYED = ieDriver.findElement(By.id("RMBXYED"));
        WebElement USXYED = ieDriver.findElement(By.id("USXYED"));
        xykMsg.put("RMBXYED",RMBXYED.getText());
        xykMsg.put("USXYED",USXYED.getText());

        //可用额度
        WebElement RMBKYED = ieDriver.findElement(By.id("RMBKYED"));
        WebElement USKYED = ieDriver.findElement(By.id("USKYED"));

        xykMsg.put("RMBKYED",RMBKYED.getText());
        xykMsg.put("USKYED",USKYED.getText());

        //未出账分期本金
        WebElement RMBWCZFQ = ieDriver.findElement(By.id("RMBWCZFQ"));

        xykMsg.put("RMBWCZFQ",RMBWCZFQ.getText());
        xykMsg.put("creditQuota",RMBXYED.getText());
        
        //预借现金可用额度
        WebElement RMBYJXJ = ieDriver.findElement(By.id("RMBYJXJ"));
        WebElement USYJXJ = ieDriver.findElement(By.id("USYJXJ"));

        xykMsg.put("RMBYJXJ",RMBYJXJ.getText());
        xykMsg.put("USYJXJ",USYJXJ.getText());
        //每日账单日
        WebElement MYZD = ieDriver.findElement(By.id("MYZD"));
        xykMsg.put("MYZD",MYZD);
        endResult.put("xykMsg", xykMsg);

        //点击已出账单查询
        WebElement caName = ieDriver.findElement(By.className("page-header-link-container"));
        List<WebElement> mycardOp = caName.findElements(By.tagName("a"));
        for(WebElement webElement:mycardOp) {
        	if ("已出账单查询".equals(webElement.getText())){
                webElement.click();
        	}
        }
        Thread.sleep(1000*3);

                
        WebElement table = ieDriver.findElement(By.id("dgReckoningInfo1"));
        WebElement tbody = table.findElement(By.tagName("table"));
        java.util.List<WebElement> trs = table.findElements(By.tagName("tr"));
        JSONArray billResults = new JSONArray();//账单结果
        for (WebElement tr : trs) {
        	int billResultNum=1;
            JSONObject billResult = new JSONObject();
            java.util.List<WebElement> tds = tr.findElements(By.tagName("td"));
            for (WebElement td : tds) {
                if (billResultNum==1){
                	billResult.put("billMonth", td.getText());//账单月份
                }
                if (billResultNum==2){
                	billResult.put("RMBReturnMoney", td.getText());//人民币应还总额
                }
                if (billResultNum==3){
                	billResult.put("minRMBReturnMoney",td.getText());//人民币最低还款额
                }
                if (billResultNum==4){
                	billResult.put("USReturnMoney",td.getText());//美元应还总额
                }
                if (billResultNum==5){
                	billResult.put("minUSReturnMoney",td.getText());//美元最低还款额
                }
                if (billResultNum==6){//获取账单明细
                	JSONObject billDetail=new JSONObject();
                	//这里获取a标签点击 进入新的页面
                    java.util.List<WebElement> tagAs = td.findElements(By.tagName("a"));
                    for(WebElement tagA:tagAs){
                        if ("账单明细".equals(tagA.getText())){
                        	tagA.click();//点击出现新的页面
                        	
                        	Thread.sleep(2000);
                        	
                        	//切换到新页面找数据
                        	ArrayList<String> tabs = new ArrayList<String> (ieDriver.getWindowHandles());  
                        	ieDriver.switchTo().window(tabs.get(tabs.size()-1)); //switches to new tab
                        	
                        	//新页面获取信息
                        	java.util.List<WebElement> rmbTds = ieDriver.findElements(By.xpath("//span[@id='fixBand13']/table/tbody/tr/td/table/tbody/tr/td"));
                        	//循环 人民币账户循序  只有6个字段 
                        	int billDetailNum=1;//计数  
                        	for(WebElement rmbTd : rmbTds) {
                        		WebElement div=rmbTd.findElement(By.tagName("div"));
                        		if(div!=null) {
                            		WebElement font=div.findElement(By.tagName("font"));
                            		if(billDetailNum==1) {//本期应还总额
                            			billDetail.put("currentRepayMoney", font.getText());
                            		}
                            		if(billDetailNum==2) {//上期账单金额
                            			billDetail.put("currentRepayMoney", font.getText());//本期应还总额
                            		}
                            		if(billDetailNum==3) {//上期还款金额、
                            			billDetail.put("currentRepayMoney", font.getText());//本期应还总额
                            		}
                            		if(billDetailNum==4) {//本期账单金额
                            			billDetail.put("currentRepayMoney", font.getText());//本期应还总额
                            		}
                            		if(billDetailNum==5) {//本期调整金额
                            			billDetail.put("currentRepayMoney", font.getText());//本期应还总额
                            		}
                            		if(billDetailNum==6) {//循环利息
                            			billDetail.put("currentRepayMoney", font.getText());//本期应还总额
                            		}
                            		billDetailNum++;
                        		}
                        	}
                        	
                        	//获取详细交易信息  JSONArray
                            JSONArray transactionDetails = new JSONArray();//交易明细

                        	//获取表单数据 ？
                        	//java.util.List<WebElement> spans = ieDriver.findElements(By.id("loopBand15"));
                        	java.util.List<WebElement> realTds = ieDriver.findElements(By.xpath("//span[@id='loopBand15']/table/tbody/tr/td/table/tbody/tr/td"));
                        	int transactionDetailNum=1;
                        	for(WebElement realTd:realTds) {
                                JSONObject transactionDetail = new JSONObject();//交易明细
                        		WebElement div=realTd.findElement(By.tagName("div"));
                        		if(div!=null) {
                            		WebElement font=div.findElement(By.tagName("font"));
                            		if(transactionDetailNum==1) {//交易日
                            			transactionDetail.put("tradingDay", font.getText());
                            		}
                            		if(transactionDetailNum==2) {//记账日
                            			transactionDetail.put("accountingDay", font.getText());
                            		}
                            		if(transactionDetailNum==3) {//交易摘要
                            			transactionDetail.put("transactionContent", font.getText());
                            		}
                            		if(transactionDetailNum==4) {//人民币金额
                            			transactionDetail.put("rmbAcount", font.getText());
                            		}
                            		if(transactionDetailNum==5) {//卡号末四位
                            			transactionDetail.put("cardLast", font.getText());
                            		}
                            		if(transactionDetailNum==6) {//交易地点
                            			transactionDetail.put("transactionPlace", font.getText());
                            		}
                            		if(transactionDetailNum==7) {//交易地金额
                            			transactionDetail.put("transactionAmount", font.getText());
                            		}
                            		transactionDetailNum++;
                        		}
                        		transactionDetails.add(transactionDetail);
                        	}
                        	billDetail.put("transactionDetails", transactionDetails);//将交易明细 放到账单信息中  那么这个页面就解析完了  下面关闭标签回到上一标签 继续循环
                        	//爬完该页面需要关闭该标签 返回上一标签继续循环
                    		ieDriver.close();
                    		Thread.sleep(1000);
                    		ieDriver.switchTo().window(tabs.get(0));//回到第一个标签
                    		Thread.sleep(2000);
                        }
                    }
                    //将账单明细放入 账单结果
                    billResult.put("billDetail", billDetail);
                }
                billResultNum++;
            }           
            billResults.add(billResult);
        }
        
        endResult.put("billResults", billResults);
        return endResult;
    }
}
