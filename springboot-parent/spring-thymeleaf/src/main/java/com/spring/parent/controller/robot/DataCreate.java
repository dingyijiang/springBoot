package com.spring.parent.controller.robot;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Component
public class DataCreate {

    @Async("taskExecutor")  //myTaskAsynPool即配置线程池的方法名，此处如果不写自定义线程池的方法名，会使用默认的线程池
    public void doTask(WebDriver ieDriver) throws InterruptedException{
        JSONObject jsonObject = new JSONObject();
        WebElement caName = ieDriver.findElement(By.className("ca_name"));
        jsonObject.put("信用卡名称",caName.getText());
        System.out.println(caName.getText());
        WebElement caNum = ieDriver.findElement(By.className("ca_num"));
        jsonObject.put("信用卡卡号",caNum.getText());
        System.out.println(caNum.getText());
        WebElement rmb = ieDriver.findElement(By.id("menu1"));
        jsonObject.put("币种",rmb.getText());
        java.util.List<WebElement> txt14s = ieDriver.findElements(By.className("txt14"));
        if (txt14s.size()>0){
            int tx = 1;
            for(WebElement webElement:txt14s){
                if (tx==1){
                    jsonObject.put("本期应还金额",webElement.getText());
                }
                if (tx==2){
                    jsonObject.put("最低还款金额",webElement.getText());
                }
                if (tx==3){
                    jsonObject.put("本期仍需还款",webElement.getText());
                }
                if (tx==4){
                    jsonObject.put("到期还款日",webElement.getText());
                }
                tx++;
            }
        }
        java.util.List<WebElement> zdcx = ieDriver.findElements(By.className("zdcx"));
        if (zdcx.size()>0){
            for(WebElement webElement:zdcx){
                if ("账单查询".equals(webElement.getText())){
                    webElement.click();
                }
            }
        }
        Thread.sleep(1000*3);
        /**
         * 账单基本信息
         */
        JSONArray jsonArray = new JSONArray();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM");
        for(int i=0;i<7;i++) {

            Calendar c = Calendar.getInstance();
            c.setTime(new Date());
            if(i!=0) {
                c.add(Calendar.MONTH, -i);
            }
            Date m = c.getTime();
            WebElement ul = ieDriver.findElement(By.className("bar-scroll"));
            WebElement li1s = ul.findElement(By.name(simpleDateFormat.format(m) + "-16"));
            if(li1s!=null) {
                JSONObject yuefendata = new JSONObject();
                li1s.click();
                Thread.sleep(1000 * 2);
                try {
                    if(i!=0) {
                        WebElement id_cur_amt_select = ieDriver.findElement(By.id("id_cur_amt_select"));
                        yuefendata.put(simpleDateFormat.format(m)+"期应还款额",id_cur_amt_select.getText());
                        WebElement id_stmt_date_select = ieDriver.findElement(By.id("id_stmt_date_select"));
                        yuefendata.put(simpleDateFormat.format(m)+"月账单日",id_stmt_date_select.getText());
                        WebElement getCash = ieDriver.findElement(By.id("getCash"));
                        yuefendata.put(simpleDateFormat.format(m)+"月提取现金",getCash.getText());
                        WebElement id_min_pay_select = ieDriver.findElement(By.id("id_min_pay_select"));
                        yuefendata.put(simpleDateFormat.format(m)+"最低还款金额",id_min_pay_select.getText());
                        WebElement id_dte_pymt_due_select = ieDriver.findElement(By.id("id_dte_pymt_due_select"));
                        yuefendata.put(simpleDateFormat.format(m)+"到期还款日",id_dte_pymt_due_select.getText());
                    }else {
                        WebElement idCurAmt = ieDriver.findElement(By.id("id_cur_amt"));
                        yuefendata.put(simpleDateFormat.format(m)+"期应还款额",idCurAmt.getText());
//                        WebElement id_bq_paied = ieDriver.findElement(By.id("id_bq_paied"));
                        WebElement id_stmt_date_text = ieDriver.findElement(By.id("id_stmt_date_text"));
                        yuefendata.put(simpleDateFormat.format(m)+"月账单日",id_stmt_date_text.getText());
                        WebElement id_min_pay = ieDriver.findElement(By.id("id_min_pay"));
                        yuefendata.put(simpleDateFormat.format(m)+"最低还款金额",id_min_pay.getText());
                        WebElement id_bq_need_pay = ieDriver.findElement(By.id("id_bq_need_pay"));
                        yuefendata.put("本期仍需还款",id_bq_need_pay.getText());
                        WebElement id_dte_pymt_due = ieDriver.findElement(By.id("id_dte_pymt_due"));
                        yuefendata.put(simpleDateFormat.format(m)+"到期还款日",id_dte_pymt_due.getText());
                    }
                } catch (Exception e) {
                    System.out.println("账单数据获取报错");
                    System.out.println(e.getMessage());
                }
                /**
                 * 账单详细信息
                 */
                for (int j = 0; j < 10; j++) {
                    WebElement settleLoadMore = ieDriver.findElement(By.id("settleLoadMore"));
                    if (settleLoadMore != null) {
                        try{
                            if (settleLoadMore.isDisplayed()) {
                                Thread.sleep(1000 * 2);
                                settleLoadMore.click();
                            }else {
                                j=10;
                            }
                        }catch (Exception e) {
                            System.out.println("不能再点了");
                        }
                    }
                }

                try {
                    WebElement table = ieDriver.findElement(By.id("billDetail"));
                    java.util.List<WebElement> trs = table.findElements(By.tagName("tr"));
                    JSONArray rdata = new JSONArray();
                    for (WebElement tr : trs) {
                        JSONObject sts = new JSONObject();
                        java.util.List<WebElement> tds = tr.findElements(By.tagName("td"));
                        int trnum = 1;
                        for (WebElement td : tds) {
                            if (trnum==1){
                                sts.put("交易描述",td.getText());
                            }
                            if (trnum==2){
                                sts.put("交易币种/金额",td.getText());
                            }
                            if (trnum==3){
                                sts.put("交易日期",td.getText());
                            }
                            if (trnum==4){
                                sts.put("结算币种/金额",td.getText());
                            }
                            if (trnum==5){
                                sts.put("入账日期",td.getText());
                            }
                            trnum++;
                        }
                        rdata.add(sts);
                    }
                    yuefendata.put("resultList",rdata);
                } catch (Exception e) {
                    System.out.println("详情数据获取报错");
                    System.out.println(e.getMessage());
                }
                jsonArray.add(yuefendata);
            }
            jsonObject.put("data",jsonArray);
        }
        /**
         * 额度管理
         */
        try {
            java.util.List<WebElement> szgl = ieDriver.findElements(By.className("szgl"));
            if (szgl.size() > 0) {
                for (WebElement webElement : szgl) {
                    if ("设置管理".equals(webElement.getText())) {
                        webElement.click();
                    }
                }
            }
            Thread.sleep(1000*3);
            java.util.List<WebElement> edgl = ieDriver.findElements(By.className("sub_menu"));
            if (edgl.size() > 0) {
                for (WebElement webElement:edgl){
                    List<WebElement> as = webElement.findElements(By.tagName("a"));
                    for (WebElement webElement1:as) {
                        if ("额度管理".equals(webElement1.getText())) {
                            webElement1.click();
                            break;
                        }
                    }
                }
            }
            Thread.sleep(1000*3);
            WebElement fixeded = ieDriver.findElement(By.id("fixeded"));
            jsonObject.put("fixeded",fixeded.getText());
            WebElement cashmoney = ieDriver.findElement(By.id("cashmoney"));
            jsonObject.put("cashmoney",cashmoney.getText());
        }catch (Exception e){
            System.out.println("设备管理报错");
            System.out.println(e.getMessage());
        }
        System.out.println(jsonObject);
        ieDriver.quit();
    }
}
