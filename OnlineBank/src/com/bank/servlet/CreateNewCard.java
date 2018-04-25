package com.bank.servlet;

import java.util.Random;

/**
 * 1.设置一个19位的数组【Integer型】
 * 2.前八位格式固定 ：6216 6153
 * 3.生成10个随机数：
 * 4.逆用Luhn算法计算出校验码		
 * 	（1.）求奇数位和偶数位的和
 * 		①.奇数位求和
 * 		②.偶数位*2,如果有大于等于10的,结果是个位和十位的和,将最终结果求和=sum
 * 	（2.）用sum%10是否等于0:
 * 		①.如果等于0,最后一位校验码为0;
 * 		②.否则最后一位校验码等于10-余数.
 */
public class CreateNewCard {

	
	// 1.设置一个19位的数组【Integer型】
	private  Integer[] str = new Integer[19];
	//存储银行卡号
	private  String card = "";
	

	// 2.前八位格式固定 ：6216 6153
	private  void setEight(Integer[] arr) {
		String eightStr = "62166153";
		for (int i = 0; i < 8; i++) {
			arr[i] =Integer.valueOf(eightStr.substring(i, i + 1)) ;
		}
	}
	
	//3.生成10个随机数
	private  void createRandom(Integer[] arr){
		Random random = new Random();
		for (int i = 8; i < arr.length-1; i++) {
			arr[i] = random.nextInt(10);
		}
	}
	
	//4.计算出校验码
	/**
 * 	（1.）求奇数位和偶数位的和
 * 		①.奇数位求和
 * 		②.偶数位*2,如果有大于等于10的,结果是个位和十位的和,将最终结果求和=sum
 * 	（2.）用sum%10是否等于0:
 * 		①.如果等于0,最后一位校验码为0;
 * 		②.否则最后一位校验码等于10-余数.
	 */
	private  Integer getCheckCode(Integer[] arr){
		
		int sum = 0;//最终结果和
		int oddSum = 0;	//奇数位和
		int evenSum = 0; //偶数位和
		int code = 0;	//校验码
		for (int i = 0; i < arr.length-1; i++) {
			//奇数位求和
			if(i % 2 != 0){
				oddSum += arr[i];
			}else {
				//偶数位求和
				evenSum += arr[i];
			}
		}
		sum = oddSum + evenSum ;
		
		if(sum%10==0){
			code = 0;
		}else{
			code = 10-(sum%10);
		}
	
		return code;
	}
	
	//生成银行卡号
	public  String createNewCard() {
		setEight(str);
		createRandom(str);
		str[str.length-1] = getCheckCode(str);
		for (int i = 0; i < str.length; i++) {
			card += str[i].toString();
		}
		return card ;
	}


}
