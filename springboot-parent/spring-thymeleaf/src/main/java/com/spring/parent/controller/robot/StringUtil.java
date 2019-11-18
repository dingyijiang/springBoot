package com.spring.parent.controller.robot;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {

	/**
	 * 判断字符串是否为null 或者空串或者空格串
	 *
	 * @param str 传入若干字符串
	 * @return，若有空则返回true
	 */
	public static boolean isEmpty(String str) {
		if (str == null || str.trim().length() == 0) {
			return true;
		}
		return false;
	}
	/**
	 * 判断字符串是否包含字符串
	 *
	 * @param str 查询的字符串
	 * @param args 查询条件
	 * @return，若包含则返回true
	 */
	public static boolean contains(String str,String args) {
		    int result = str.indexOf(args);
		    if(result != -1){
		    	return true;
		    }else{
		    	return false;
		    }
	}
	/**
	 * 判断是否含有特殊字符
	 *
	 * @param str
	 * @return true为包含，false为不包含
	 */
	public static boolean isSpecialChar(String str) {
		if(isEmpty(str)) {
			return true;
		}
		String regEx = "[ _`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]|\n|\r|\t";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(str);
		return m.find();
	}

	/**
	 * 字符串去除符号转换为数字
	 *
	 * @param str
	 * @return 有数字格式字符串就返回数字 没有返回null
	 */
	public static String getNumbers(String str) {
		String s = str.replaceAll("[^0-9]", "");
		if (isEmpty(s)) {
			return null;
		}
//		int num = Integer.parseInt(s);
		Long num = Long.valueOf(s);
		s = String.valueOf(num);
		return s;
	}

	/**
	 * @param phone 字符串类型的手机号 传入手机号,判断后返回 true为手机号,false相反
	 */
	public static boolean isPhone(String phone) {
		
		if (isEmpty(phone)&&phone.length() != 11) {
			return false;
		} else {
			String regex = "^((13[0-9])|(14[5,7,9])|(15([0-3]|[5-9]))|(166)|(17[0,1,3,5,6,7,8])|(18[0-9])|(19[8|9]))\\d{8}$";
			Pattern p = Pattern.compile(regex);
			Matcher m = p.matcher(phone);
			return m.matches();
		}
	}

	/**
	 * 校验银行卡卡号 校验过程： 1、从卡号最后一位数字开始，逆向将奇数位(1、3、5等等)相加。
	 * 2、从卡号最后一位数字开始，逆向将偶数位数字，先乘以2（如果乘积为两位数，将个位十位数字相加，即将其减去9），再求和。
	 * 3、将奇数位总和加上偶数位总和，结果应该可以被10整除。
	 * 
	 * @param bankCard 字符串类型的银行卡号 传入银行卡号,判断后返回 true为银行卡号,false相反
	 */
	public static boolean checkBankCard(String bankCard) {
		if (bankCard.length() < 15 || bankCard.length() > 19) {
			return false;
		}
		char bit = getBankCardCheckCode(bankCard.substring(0, bankCard.length() - 1));
		if (bit == 'N') {
			return false;
		}
		return bankCard.charAt(bankCard.length() - 1) == bit;
	}

	/**
	 * 从不含校验位的银行卡卡号采用 Luhm 校验算法获得校验位
	 *
	 * @param nonCheckCodeBankCard
	 * @return 26
	 */
	private static char getBankCardCheckCode(String nonCheckCodeBankCard) {
		if (nonCheckCodeBankCard == null || nonCheckCodeBankCard.trim().length() == 0 || !nonCheckCodeBankCard.matches("\\d+")) {
			// 如果传的不是数据返回N
			return 'N';
		}
		char[] chs = nonCheckCodeBankCard.trim().toCharArray();
		int luhmSum = 0;
		for (int i = chs.length - 1, j = 0; i >= 0; i--, j++) {
			int k = chs[i] - '0';
			if (j % 2 == 0) {
				k *= 2;
				k = k / 10 + k % 10;
			}
			luhmSum += k;
		}
		return (luhmSum % 10 == 0) ? '0' : (char) ((10 - luhmSum % 10) + '0');
	}

}
