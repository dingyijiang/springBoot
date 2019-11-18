package com.spring.parent.controller.robot;

import java.io.File;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

public class WebDriverUtil {

	private static DesiredCapabilities caps = DesiredCapabilities.internetExplorer();
	private static File iefile = new File("C:\\api\\webdriver\\IEDriverServer.exe");
	private static File chromefile = new File("C:\\api\\webdriver\\chromedriver.exe");
	private static WebDriver webDriver;
	private static ChromeOptions options = new ChromeOptions();
	private static int loginStatus = 0; // 设置状态 默认 0  

	// 设置浏览器驱动
	static {
		caps.setCapability("ignoreZoomSetting", true);
		caps.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
		caps.setJavascriptEnabled(true);
		options.addArguments("--no-default-browser-check");
		options.addArguments("about:histograms");
		options.addArguments("about:cache");
		// 设置浏览器大小
		options.addArguments("--start-maximized");
		options.addArguments("--disable-infobars");
		options.addArguments("--enable-automation");
		options.addArguments("--no-first-run");
	}

	private WebDriverUtil() {

	}

	public static WebDriver initIEDriver(String url, String ip) {
		if (!StringUtil.isEmpty(ip)) {
			String PROXY = "http://" + ip;
			org.openqa.selenium.Proxy proxy = new org.openqa.selenium.Proxy();
			proxy.setHttpProxy(PROXY).setFtpProxy(PROXY).setSslProxy(PROXY);
			caps.setCapability(CapabilityType.PROXY, proxy);
		}
		// 设置浏览器参数
		caps.setCapability(InternetExplorerDriver.INITIAL_BROWSER_URL, url);
		System.setProperty("webdriver.ie.driver", iefile.getAbsolutePath());
		webDriver = new InternetExplorerDriver(new InternetExplorerOptions(caps));
		webDriver.manage().window().maximize();
//		webDriver.get(url);
		return webDriver;
	}

	public static WebDriver initChromeDriver(String url, String ip) {
		if (!StringUtil.isEmpty(ip)) {
			options.addArguments("--proxy-server=http://" + ip);
		}
		System.setProperty("webdriver.chrome.driver", chromefile.getAbsolutePath());
		webDriver = new ChromeDriver(options);
		webDriver.get(url);
		return webDriver;
	}

	public static WebDriver getDriver() {
		return webDriver;
	}

	public static void quitDriver() {
		if(webDriver!=null) {
			webDriver.quit();
			webDriver = null;
		}
	}
	
	public static void setStatus(int status) {
		loginStatus = status;
	}

	public static int getStatus() {
		return loginStatus;
	}
}
