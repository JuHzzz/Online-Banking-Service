package com.bank.servlet;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CheckName {
	
	public static boolean checkName(String name){
		//字符串长度不超过15,并且不能为空格
		if(checkLength(name) == false){
			return false;
		}else{
			//字符串并且不能有数字
			if(checkFormat(name) == true){
				return false;
			}else{
				//字符串中不能含有空格
				if(checkIsNull(name) == true){
					return false;
				}else{
					return true;
					
				}
			}
		}
	}
	
	//字符串长度问题 
	private static boolean checkLength(String name){
		int len = name.replace(" ", "").length();
		//判断姓名的长度是否为零和纯空格，并且规定名字最长不超过15个字符
		if(len == 0 || len > 15){
			return false;
		}else{
			return true;
		}
		
	}
	//字符串不能有数字
	private static boolean checkFormat(String name){
		
		Pattern p = Pattern.compile(".*\\d+.*");
		Matcher ma = p.matcher(name);
		//有数字返回true，否则返回false
		return ma.matches();
	}
	
	//字符串中不能含有空格
	private static boolean checkIsNull(String name){
		
		String str = " ";
		for (int i = 0; i < name.length(); i++) {
			if(str.equals(name.substring(i,i+1))){
				return true;
			
			}
		}
		return false;
		
	}
	

	
	
}
