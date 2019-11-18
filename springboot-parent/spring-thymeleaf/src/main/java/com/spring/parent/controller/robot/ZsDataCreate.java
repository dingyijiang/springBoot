package com.spring.parent.controller.robot;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.crawler.api.service.DataCenterService;
import com.crawler.api.service.GatewayService;
import com.crawler.api.utils.DateUtils;
import com.crawler.api.utils.UUIDUtils;
import com.galaxy.common.utils.StringUtil;
import com.galaxy.common.utils.WebDriverUtil;

@Component
public class ZsDataCreate {
	@Autowired
	private GatewayService gatewayService;
	@Autowired
	private DataCenterService dataCenterService;
	private static final Logger logger = LoggerFactory.getLogger(ZsDataCreate.class);

    @Async("taskExecutor")  //myTaskAsynPool即配置线程池的方法名，此处如果不写自定义线程池的方法名，会使用默认的线程池
    public void doTask(WebDriver ieDriver, String uid, String data_source, String name, String phone, String id_no,String account) throws InterruptedException{
    	long start = System.currentTimeMillis();
		 
       
        
    	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	JSONObject endResult = new JSONObject();
    	endResult.put("generate_time", sdf.format(new Date()));
    	WebDriverUtil.setStatus(1);
    	try { 
        //点击获取一卡通用户
        WebElement imgUser = ieDriver.findElement(By.id("imgUser"));
        imgUser.click();
        Thread.sleep(1500);
        
        
        //获取两名卡别   可用额度  累计交易
        ieDriver.switchTo().frame("mainWorkArea");
        Thread.sleep(1000);
        JSONArray card_lists=new JSONArray();
        JSONObject cardListJson=new JSONObject();//1个json包含了 card数组 bill_flow数组
        JSONArray cards=new JSONArray();

        //真实姓名
        WebElement lbRealName = ieDriver.findElement(By.id("lbRealName"));
        logger.info("realName="+lbRealName.getText());
        String bank_name=lbRealName.getText();
        //证件类型
        WebElement lbIDType = ieDriver.findElement(By.id("lbIDType"));
        logger.info("IDType="+lbIDType.getText());
        String bank_id_type=lbRealName.getText();

        //证件号码
        WebElement lbIdentNo = ieDriver.findElement(By.id("lbIdentNo"));
        logger.info("IDCard="+lbIdentNo.getText());
        String bank_id_card=lbRealName.getText();

        //手机号
        WebElement tbCellPhone = ieDriver.findElement(By.id("tbCellPhone"));
        logger.info("lbIdentNo="+tbCellPhone.getAttribute("value"));
        String bank_phone=tbCellPhone.getAttribute("value");

        //email
        WebElement tbEmail = ieDriver.findElement(By.id("tbEmail"));
        logger.info("tbEmail="+tbEmail.getText());
        String bank_email=tbEmail.getText();
        
        ieDriver.switchTo().defaultContent();//跳出iframe
        Thread.sleep(1000);

        //点击获取信用卡
        WebElement imgCreditCard = ieDriver.findElement(By.id("imgCreditCard"));
        imgCreditCard.click();
        
        //在跳到iframe获取元素
        ieDriver.switchTo().frame("mainWorkArea");
        
        //信用额度  信用卡额度
        WebElement RMBXYED = ieDriver.findElement(By.id("RMBXYED"));
        logger.info("credit_limit="+RMBXYED.getText());
        String bank_credit_limit=RMBXYED.getText();


        //可用额度  信用卡可用额度   balance
        WebElement RMBKYED = ieDriver.findElement(By.id("RMBKYED"));
        logger.info("balance="+RMBKYED.getText());
        String bank_balance=RMBKYED.getText();

        //未出账分期本金
//        WebElement RMBWCZFQ = ieDriver.findElement(By.id("RMBWCZFQ"));
//        xykMsg.put("RMBWCZFQ",RMBWCZFQ.getText());
//        xykMsg.put("creditQuota",RMBXYED.getText());s
        
        //预借现金可用额度    cash_balance
        WebElement RMBYJXJ = ieDriver.findElement(By.id("RMBYJXJ"));
        logger.info("cash_balance="+RMBYJXJ.getText());
        String bank_cash_balance=RMBYJXJ.getText();

        //每月账单日  这个给账单更新时间 不知道对不对   更新时间
        WebElement MYZD = ieDriver.findElement(By.id("MYZD"));
        logger.info("MYZD="+MYZD.getText());
        String bank_update_time=MYZD.getText();

        
        //本期账单金额
        WebElement LiterRMBZDJE = ieDriver.findElement(By.id("LiterRMBZDJE"));
        logger.info("LiterRMBZDJE="+LiterRMBZDJE.getText());
        String bank_current_bill_amt=LiterRMBZDJE.getText();

        
        
        //本期剩余应还金额
        WebElement LiterRMBBQJE = ieDriver.findElement(By.id("LiterRMBBQJE"));
        logger.info("LiterRMBBQJE="+LiterRMBBQJE.getText());
        String bank_current_bill_remain_amt=LiterRMBBQJE.getText();

        
       //本期剩余应还金额=本期账单金额-本期剩余应还金额
        //bank.put("current_bill_paid_amt",transforMobey(LiterRMBZDJE.getText())-transforMobey(LiterRMBBQJE.getText()));
        
        //本期剩余最低应还款金额
        WebElement RMBZDJE = ieDriver.findElement(By.id("RMBZDJE"));
        logger.info("RMBZDJE="+RMBZDJE.getText());
        String bank_current_bill_remain_min_payment=RMBZDJE.getText();

        
        ieDriver.switchTo().defaultContent();//跳出iframe
        Thread.sleep(1000);
        //获取卡类型 等3个字段
        //在跳到iframe获取元素
        WebElement vmenu_1 = ieDriver.findElement(By.id("vmenu_1"));
        vmenu_1.findElement(By.linkText("卡片管理首页")).click();
        Thread.sleep(1000);
        ieDriver.switchTo().frame("mainWorkArea");
        WebElement fixAction = ieDriver.findElement(By.className("page-table-common"));
        List<WebElement> mycardTrs = fixAction.findElements(By.tagName("tr"));
        logger.info("mycardOp.size={}",mycardTrs.size());
        for(int i=0;i<mycardTrs.size();i++) {
        	JSONObject bank_new=new JSONObject();
        	//没有的字段
        	bank_new.put("payment_due_date","");//最后还款日
        	bank_new.put("account_type","");//账户类型
        	bank_new.put("login_account",account);//登录账号
        	bank_new.put("card_date","");//开卡时间
        	bank_new.put("provisional_quota","");//临时额度
        	bank_new.put("provisional_quota_start_date","");//临时额度失效日期
        	bank_new.put("provisional_quota_end_date","");//临时额度失效日期

        	bank_new.put("bank_location","");

        	 
        	bank_new.put("bank","招商银行");
        	bank_new.put("type","credit");
        	bank_new.put("bank_name","招商银行");

        	bank_new.put("name",bank_name);
        	bank_new.put("id_type",bank_id_type);
        	bank_new.put("id_card",bank_id_card);
            bank_new.put("phone",bank_phone);//获取input的value
            bank_new.put("email",bank_email);
            bank_new.put("credit_limit",transforMobey(bank_credit_limit));
            bank_new.put("cash_limit",transforMobey(bank_credit_limit));
            bank_new.put("balance",transforMobey(bank_balance));
            bank_new.put("cash_balance",transforMobey(bank_cash_balance));
            bank_new.put("update_time",sdf.format(new Date()));
            bank_new.put("current_bill_amt",transforMobey(bank_current_bill_amt));
            bank_new.put("current_bill_remain_amt",transforMobey(bank_current_bill_remain_amt));
            bank_new.put("current_bill_paid_amt",(int)transforMobey(bank_current_bill_amt)-(int)transforMobey(bank_current_bill_remain_amt));
            bank_new.put("current_bill_remain_min_payment",transforMobey(bank_current_bill_remain_min_payment));
        	if(i>0){
        		List<WebElement> tds=mycardTrs.get(i).findElements(By.tagName("td"));
        		int cardNum=1;
        		for(WebElement td:tds) {
        			if(cardNum==1){//卡号
                     	logger.info("card_no="+td.getText().trim());
                     	bank_new.put("card_no", td.getText().trim());//card_no
                     }
        			if(cardNum==3){//卡类型
                     	logger.info("card_type="+td.getText().trim());
                     	bank_new.put("card_type", td.getText().trim());//card_type
                     }else {
                    	cardNum++;
                      	continue;
                     }
        			cardNum++;
        		}
        		logger.info("bank={}",bank_new.toString());
        		cards.add(bank_new);
        	}

        }
        cardListJson.put("card", cards);//将cards的数组放到card  json 中

        //重新点击信用卡 进入iframe
        ieDriver.switchTo().defaultContent();//跳出iframe
        Thread.sleep(1000);
        //点击获取信用卡
        imgCreditCard = ieDriver.findElement(By.id("imgCreditCard"));
        imgCreditCard.click();
        ieDriver.switchTo().frame("mainWorkArea");        
        //总数组
        JSONArray bill_flow=new JSONArray();
        //未出账数据
        JSONObject undoneBills=new JSONObject();
        //点击未出账单查询
        WebElement caName = ieDriver.findElement(By.className("page-header-link-container"));
        List<WebElement> mycardOp = caName.findElements(By.tagName("a"));
        logger.info("mycardOp.size="+mycardOp.size());
        for(WebElement webElement:mycardOp) {
        	logger.info("webElement.getText()="+webElement.getText());
        	if ("未出账单查询".equals(webElement.getText())){
                webElement.click();
                break;
        	}
        }
        Thread.sleep(1000*3);
        
        logger.info("start 未出账账单的数据爬取");
        WebElement unBillTable = ieDriver.findElement(By.id("dgReckoningNotDetail"));
        logger.info("get  table message");
        java.util.List<WebElement> trs = unBillTable.findElements(By.tagName("tr"));
        logger.info("get  trs message trs.size="+trs.size());
        
        //未出账的bill
        JSONObject unDonebill=new JSONObject();
        unDonebill.put("bill_type", "UNDONE");
        unDonebill.put("bill_id", UUIDUtils.generateId());
        unDonebill.put("month", DateUtils.parseMonthDate());
        undoneBills.put("bill", unDonebill);
        logger.info("bill=="+unDonebill.toString());
        JSONArray unDoneFlows=new JSONArray();
        for (WebElement tr : trs) {//每一个tr对应一个flow
        	 JSONObject flow=new JSONObject();
             //包含本月总信息以及流水
           	 flow.put("id",UUIDUtils.generateId());//id
          	 flow.put("currency", "CNY");
         	 int billResultNum=1;
             java.util.List<WebElement> tds = tr.findElements(By.tagName("td"));
             logger.info("tds.size===="+tds.size());
             for (WebElement td : tds) {
              	 flow.put("id",UUIDUtils.generateId());//id
            	 if (billResultNum==1){//交易易日 消费时间
                 	//logger.info("trans_date="+td.getText().trim());
                 	flow.put("trans_date", td.getText().trim());//账单月份
                 }
                 if (billResultNum==2){//记账日期
                 	//logger.info("post_date="+td.getText().trim());
                 	flow.put("post_date", td.getText().trim());
                 }
                 if (billResultNum==3){//交易摘要
                  	//logger.info("description="+td.getText().trim());
                  	String description=td.getText().trim();
                  	flow.put("description",description);
                  	if(description.matches("循环利息")) {
            			flow.put("category","INTEREST");//利息
            		}else if(description.matches("掌上预借现金")){//掌上预借现金（转账）
            			flow.put("category","WITHDRAW");//取现
            		}else if(description.matches("违约金")){//违约金
            			flow.put("category","OVERDUEPAYMENT");//逾期违约金
            		}else if(description.matches("账单分期")){//账单分期(账单)
            			flow.put("category","INSTALLMENT");//利息
            		}else if(description.matches("年费")||description.matches("预借现金(转账)手续费")){//其它
            			flow.put("category","OTHERFEE");//其他
            		}else {
            			flow.put("category","SHOPPING");//消费
            		}
                 }
                 if (billResultNum==4){//交易地点
                   	//logger.info("trans_area="+td.getText().trim());
                   	flow.put("trans_area",td.getText().trim());
                  }
                 if (billResultNum==5){//卡号末四位
                   	//logger.info("cardNo_last4_digits="+td.getText());
                   	flow.put("cardNo_last4_digits",td.getText().trim());
                  }
                 if (billResultNum==6){//人民币金额
                    //logger.info("rmb_amount="+td.getText());
                    flow.put("rmb_amount",transforMobey(td.getText()));
                 }
                 if (billResultNum==7){//交易地金额
                    //logger.info("rmb_org_amount="+td.getText());
                    flow.put("rmb_org_amount",transforMobey(td.getText()));
                    //这里如果金额是负数 那么修改category 为还款
            		if((int)transforMobey(td.getText())<0) {
                		flow.put("category","PAYMENTS");//还款
            		}
                 }
                 billResultNum++;
             }
             if(flow.size()>3) {//如果没有值 那么不加
                 logger.info("flow====="+flow.toString());
            	 unDoneFlows.add(flow);                   	
     		 }
        }
        undoneBills.put("flow", unDoneFlows);
        JSONArray installments=new JSONArray();
        undoneBills.put("installments",installments);
        bill_flow.add(undoneBills);
        
        logger.info("bill_flow==="+bill_flow.toString());
        //点击已出账单查询
        WebElement caNameNew = ieDriver.findElement(By.className("page-header-link-container"));
        List<WebElement> mycardOpNew = caNameNew.findElements(By.tagName("a"));
        logger.info("mycardOp.size="+mycardOpNew.size());
        for(WebElement webElement:mycardOpNew) {
        	logger.info("webElement.getText()="+webElement.getText());
        	if ("已出账单查询".equals(webElement.getText())){
                webElement.click();
                break;
        	}
        }
        Thread.sleep(1000*3);

        //获取已出账账单
        logger.info("get  已出账账单");
        WebElement table = ieDriver.findElement(By.id("dgReckoningInfo1"));
        logger.info("get  table message");
        //已出账账单
        java.util.List<WebElement> doneTrs = table.findElements(By.tagName("tr"));
        logger.info("get  doneTrs message doneTrs.size="+doneTrs.size());
        int bugBillNum=0;
        for (WebElement tr : doneTrs) {
        	//已出账账单的数据    这个是每个用都有一个doneBills
            JSONObject doneBills=new JSONObject();
            //包含本月总信息以及流水
            JSONObject bill=new JSONObject();
            bill.put("bill_type", "DONE");
            bill.put("bill_id", UUIDUtils.generateId());
            bill.put("currency", "");//币种
            bill.put("cash_advance_limit", "");//预借现金额度
            bill.put("available_credit", "");//本期余额


            if(bugBillNum==0) {
                logger.info("目录tr no td is th");
                bugBillNum++;
                continue;
            }
            if(bugBillNum>6) {
                logger.info("只爬取前6个月--到bugBillNum=7后直接break");
                break;
            }
        	//这里有一个问题每次切换页面回来那么tr里面的内容会丢失 所有要重新获取 而且要把之前已经获取数据的 
            //首先这里每次都要重新回去trs  然后找到对应的tds
            List<WebElement> tds= new ArrayList<WebElement>();
            //在跳到iframe获取元素  只有第3次循环才会重新进入 iframe
            if(bugBillNum>1) {
            	logger.info("tr第一行之后数据的获取");
            	ieDriver.switchTo().frame("mainWorkArea");
                Thread.sleep(1000);
                WebElement loogTable = ieDriver.findElement(By.id("dgReckoningInfo1"));
                java.util.List<WebElement> loopTrs = loogTable.findElements(By.tagName("tr"));
                for(int i=0;i<loopTrs.size();i++) {
                	if(i==bugBillNum) {
                		tds=loopTrs.get(i).findElements(By.tagName("td"));
                	}
                }
            }
            
            if(bugBillNum==1) {
            	logger.info("tr第一行数据的获取");
        		tds=tr.findElements(By.tagName("td"));
            }
           
            logger.info("重新赋值的tds.size=="+tds.size());
       
        	int billResultNum=1;
            for (WebElement td : tds) {
                if (billResultNum==1){
                	//logger.info("month="+td.getText());
                	bill.put("month", td.getText());//账单月份
                }
                if (billResultNum==2){
                	//logger.info("new_balance="+td.getText());
                	bill.put("new_balance", transforMobey(td.getText()));//人民币应还总额
                }
                if (billResultNum==3){
                	//logger.info("min_payment="+td.getText());
                	bill.put("min_payment",transforMobey(td.getText()));//人民币最低还款额
                }
                if (billResultNum==6){//获取账单明细
                	//这里获取a标签点击 进入新的页面
                    java.util.List<WebElement> tagAs = td.findElements(By.tagName("a"));
                    for(WebElement tagA:tagAs){
                        if ("账单明细".equals(tagA.getText())){
                        	logger.info("账单明细数据爬取 start");
                        	tagA.click();//点击出现新的页面
                        	Thread.sleep(2000);
                        	
                        	//切换到新页面找数据
                        	String currentWindow = ieDriver.getWindowHandle();//获取当前窗口句柄
                        	logger.info("currentWindow=={}",currentWindow);
                        	ArrayList<String> handles = new ArrayList<String> (ieDriver.getWindowHandles());//获取所有窗口句柄
                        	logger.info("*****tabs.size==="+handles.size());                        	
                        	for(int i=0;i<handles.size();i++) {//循环
                        		logger.info("hadle={},i={}",handles.get(i),i);
                        		if (currentWindow.equals(handles.get(i))) {
                        			continue;
                        		}
                        		WebDriver newIeDriver = ieDriver.switchTo().window(handles.get(i));//切换到新窗口
                            	String handlenew = newIeDriver.getWindowHandle();//获取当前窗口句柄
                            	logger.info("handlenew=={},sleep five seconds",handlenew);
                        		Thread.sleep(5000);
                        		
                        		//在这里爬取账单明细的数据
                        		WebElement rmbTds6=null;
                        		try {
                            		 rmbTds6 = newIeDriver.findElement(By.xpath("//span[@id='fixBand6']/table/tbody/tr/td/table/tbody/tr"));
                        		}catch(Exception e) {
                        			logger.info("获取不到fixBand6-xpath");
                        			Thread.sleep(5000);
                           		    rmbTds6 = newIeDriver.findElement(By.xpath("//span[@id='fixBand6']/table/tbody/tr/td/table/tbody/tr"));
                          		    if(rmbTds6==null) {
                            			logger.info("获取不到fixBand6");
                          		    }
                        		}
                        		java.util.List<WebElement> firstFonts6=rmbTds6.findElements(By.tagName("font"));
                        		logger.info("账单周期=="+firstFonts6.get(0).getText());
                        		//处理时间
                        		String[] strs=firstFonts6.get(0).getText().split("-");
                        		//转化成时间
                        		bill.put("statement_start_date", transTime(strs[0]));//账单开始时间
                        		bill.put("statement_end_date", transTime(strs[1]));//账单结束时间
                        		bill.put("payment_cur_date", transTime(strs[1]));//账单结束时间
                        		//logger.info("statement_start_date=="+transTime(strs[0]));
                        		//logger.info("statement_end_date=="+transTime(strs[1]));
                        		//logger.info("payment_cur_date=="+transTime(strs[1]));

                        		//信用额度
                        		logger.info("信用额度=="+firstFonts6.get(1).getText());
                        		bill.put("credit_limit", transforMobey(firstFonts6.get(1).getText()));
                        		
                        		WebElement rmbTds8 = newIeDriver.findElement(By.xpath("//span[@id='fixBand8']/table/tbody/tr/td/table/tbody/tr"));
                        		java.util.List<WebElement> firstFonts8=rmbTds8.findElements(By.tagName("font"));
                        		logger.info("账单周期=="+firstFonts8.get(0).getText());
                        		bill.put("payment_due_date", transTime(firstFonts8.get(0).getText()));//账单结束时间
                        		String thisTear=firstFonts8.get(0).getText().substring(0, 4).replaceAll(" ", "");//这个是下面详细账单的年份
                        		logger.info("bill=="+bill.toString());

                        		//人民币账户
                        		logger.info("开始爬取 人民币账户");
                            	java.util.List<WebElement> rmbTds = newIeDriver.findElements(By.xpath("//span[@id='fixBand13']/table/tbody/tr/td/table/tbody/tr/td"));
                        		logger.info("rmbTds.size=="+rmbTds.size());

                            	//循环 人民币账户循序  只有6个字段 
                            	int billDetailNum=1;//计数  
                            	for(WebElement rmbTd : rmbTds) {
                            			if(billDetailNum==1){//第一个什么都没有
                                    		billDetailNum++;
                            				continue;
                            			}
                            			WebElement font=rmbTd.findElement(By.tagName("font"));
//                                		if(billDetailNum==2) {//本期应还金额
//                                    		logger.info("本期应还金额=="+font.getText());
//                                    		bill.put("currentRepayMoney", font.getText());
//                                		}                            			
                                		if(billDetailNum==3) {//上期账单金额
                                    		//logger.info("上期账单金额=="+font.getText());
                                    		bill.put("last_balance", transforMobey(font.getText()));
                                		}
                                		if(billDetailNum==4) {//上期还款金额
                                			//logger.info("上期还款金额=="+font.getText());
                                			bill.put("last_payment", transforMobey(font.getText()));
                                		}
                                		if(billDetailNum==5) {//本期账单金额
                                			//logger.info("本期账单金额=="+font.getText());
                                			bill.put("new_charges", transforMobey(font.getText()));
                                		}
                                		if(billDetailNum==6) {//本期调整金额
                                			//logger.info("本期调整金额=="+font.getText());
                                			bill.put("cur_adjust_amount", transforMobey(font.getText()));
                                		}
                                		if(billDetailNum==7) {//循环利息
                                			//logger.info("循环利息=="+font.getText());
                                			bill.put("interest", transforMobey(font.getText()));
                                		}
                                		billDetailNum++;
                            	}  
                    			logger.info("bill=="+bill.toString());
                            	doneBills.put("bill", bill);
                            	
                            	//循环交易明细
                            	JSONArray flows=new JSONArray();
                                java.util.List<WebElement> realTrs = newIeDriver.findElements(By.xpath("//span[@id='fixBand15']/table/tbody/tr/td/table/tbody/tr"));
                            	logger.info("realTrs.size=="+realTrs.size());
                            	//logger.info("****realTds.size=="+realTrs.size());
                            	for(WebElement realTr:realTrs) {
                                    JSONObject flow=new JSONObject();
                            		java.util.List<WebElement> fonts=realTr.findElements(By.tagName("font"));
                                	//logger.info("fonts.size=="+fonts.size());
                                    int transactionDetailNum=1;
                            		for(WebElement font:fonts) {
                                     	flow.put("id",UUIDUtils.generateId());//id
                                     	flow.put("currency", "CNY");
                            			if(transactionDetailNum==1) {//交易日
                            				//第一次获取数据是“” 或者“ ”
                            				String day=font.getText();
                            				if(!StringUtil.isEmpty(day)) {
                            					//logger.info("trans_date=="+thisTear+font.getText());
                                        		flow.put("trans_date", transTime2(thisTear+font.getText()));
                            				}
                                    	}
                                    	if(transactionDetailNum==2) {//记账日
                                    		//logger.info("post_date=="+font.getText());
                                    		flow.put("post_date", transTime2(thisTear+font.getText()));
                                    	}
                                    	if(transactionDetailNum==3) {//交易摘要
                                    		//logger.info("description=="+font.getText());
                                    		String description=font.getText();
                                    		//这里判断该笔交易属于什么类型
                                    		if(description.matches("循环利息")) {
                                    			flow.put("category","INTEREST");//利息
                                    		}else if(description.matches("掌上预借现金")){//掌上预借现金（转账）
                                    			flow.put("category","WITHDRAW");//取现
                                    		}else if(description.matches("违约金")){//违约金
                                    			flow.put("category","OVERDUEPAYMENT");//逾期违约金
                                    		}else if(description.matches("账单分期")){//账单分期(账单)
                                    			flow.put("category","INSTALLMENT");//利息
                                    		}else if(description.matches("年费")||description.matches("预借现金(转账)手续费")){//其它
                                    			flow.put("category","OTHERFEE");//其他
                                    		}else {
                                    			flow.put("category","SHOPPING");//消费
                                    		}
                                    		flow.put("description", font.getText());
                                    	}
                                    	if(transactionDetailNum==4) {//人民币金额
                                    		//logger.info("rmb_amount=="+font.getText());
                                    		//这里如果金额是负数 那么修改category 为还款
                                    		if((int)transforMobey(font.getText())<0) {
                                        		flow.put("category","PAYMENTS");//还款
                                        		if(StringUtil.isEmpty(flow.getString("trans_date"))) {
                                            		flow.put("trans_date", flow.getString("post_date"));//还款是没有交易日的   但是退款是有的
                                        		}
                                    		}
                                    		flow.put("rmb_amount", transforMobey(font.getText()));
                                    	}
                                    	if(transactionDetailNum==5) {//卡号末四位
                                    		//logger.info("cardNo_last4_digits=="+font.getText());
                                    		flow.put("cardNo_last4_digits", font.getText());
                                    	}
                                    	if(transactionDetailNum==6) {//交易地点
                                    		//logger.info("trans_area=="+font.getText().trim());
                                    		flow.put("trans_area", font.getText().trim());
                                    	}
                                    	if(transactionDetailNum==7) {//交易地金额
                                    		//logger.info("rmb_org_amount=="+font.getText());
                                    		flow.put("rmb_org_amount", transforMobey(font.getText()));
                                    	}
                                    	transactionDetailNum++;
                            		}
                            		logger.info("flow==="+flow.toString());
                            		if(flow.size()>3) {//如果没有值 那么不加
                                    	flows.add(flow);                   	
                            		}
                            	}
                            	doneBills.put("flow", flows);
                            	JSONArray doneInstallments=new JSONArray();
                            	doneBills.put("installments",doneInstallments);
                            	newIeDriver.close();//关闭新窗口
                        	}//进入新页面  循环账单
                        	ieDriver.switchTo().window(currentWindow);//回到原来页面
                        }//账单明细
                        break;
                    }//账单明细
                }//账单明细
                billResultNum++;
            }  
            bugBillNum++;
        	bill_flow.add(doneBills);            
        }//已出账账单
		//已出账账单 已经循环完毕
    	ieDriver.quit();
    	ieDriver=null;
    	
    	cardListJson.put("bill_flow", bill_flow);
    	card_lists.add(cardListJson);
    	endResult.put("card_lists", card_lists);
        logger.info("********endResult********={}",endResult.toString());
        JSONObject returnMsg=dataCenterService.pushData(uid + "," + data_source + "," + name + "," + phone + "," + id_no, "success", "credit", endResult);
        logger.info("returnMsg={}",returnMsg.toString());
    	gatewayService.quitGateway(uid);//释放资源
    	long over = System.currentTimeMillis();
        System.out.println("business used " + (over - start)/1000 + "secondes");
    }catch(Exception e) {
          logger.error("ERROR", e);
		  ieDriver.quit(); 
		  logger.info("iedriver is null");
          ieDriver = null;
		  logger.info("iedriver is null  ok");
      	  gatewayService.quitGateway(uid);
          logger.info("数据爬取出错,发送datacenter error");
          JSONObject returnMsg=dataCenterService.pushData(uid + "," + data_source + "," + name + "," + phone + "," + id_no, "fail", "credit", null);
          logger.info("returnMsg=={}",returnMsg.toString());
      }
    }
    
    
    /**
     * String 元转成 int 分
     * yuan  ￥-3,300.00
     */
    
    public Object transforMobey(String yuan) {
    	yuan=yuan.replace("￥", "");
    	yuan=yuan.replace(",", "");
    	yuan=yuan.replace(" ", "");
    	if(!StringUtil.isEmpty(yuan)) {
        	return new BigDecimal(yuan).multiply(new BigDecimal(100)).intValue();
    	}else {//如果没有获取到数据那么返回""
    		return "";
    	}

    }
    
 
    /**
     * 2019/08/20 装成 2019-08-20
     * @param time
     * @return
     */
    public String transTime(String time) {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy/MM/dd");

        SimpleDateFormat nomalSdf=new SimpleDateFormat("yyyy-MM-dd");
        try {
        	
			return nomalSdf.format(sdf.parse(time));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
    }
    
    /**
     * 20190219 装成 2019-02-19
     * @param time
     * @return
     */
    public String transTime2(String time) {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");    	
        SimpleDateFormat nomalSdf=new SimpleDateFormat("yyyy-MM-dd");
        try {
        	
			return nomalSdf.format(sdf.parse(time));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
    }
    
  
}
