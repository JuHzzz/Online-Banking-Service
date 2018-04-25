package com.bank.servlet;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CheckName {
	
	public static boolean checkName(String name){
		//�ַ������Ȳ�����15,���Ҳ���Ϊ�ո�
		if(checkLength(name) == false){
			return false;
		}else{
			//�ַ������Ҳ���������
			if(checkFormat(name) == true){
				return false;
			}else{
				//�ַ����в��ܺ��пո�
				if(checkIsNull(name) == true){
					return false;
				}else{
					return true;
					
				}
			}
		}
	}
	
	//�ַ����������� 
	private static boolean checkLength(String name){
		int len = name.replace(" ", "").length();
		//�ж������ĳ����Ƿ�Ϊ��ʹ��ո񣬲��ҹ涨�����������15���ַ�
		if(len == 0 || len > 15){
			return false;
		}else{
			return true;
		}
		
	}
	//�ַ�������������
	private static boolean checkFormat(String name){
		
		Pattern p = Pattern.compile(".*\\d+.*");
		Matcher ma = p.matcher(name);
		//�����ַ���true�����򷵻�false
		return ma.matches();
	}
	
	//�ַ����в��ܺ��пո�
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
