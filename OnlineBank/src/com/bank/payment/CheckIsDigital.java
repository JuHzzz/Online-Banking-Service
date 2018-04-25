package com.bank.payment;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CheckIsDigital {

	
	public static boolean isDital(String count){
		if(checkPhoneIsNum(count)==false){
			return false;
		}else{
			return true;
		}
	}
	
	// 利用Pattern验证手机号是否是纯数字 true:是 false：不是
		private static boolean checkPhoneIsNum(String count) {

			Pattern p = Pattern.compile("[0-9]{1,}");
			Matcher m = p.matcher(count);
			return m.matches();
		}
}
 
