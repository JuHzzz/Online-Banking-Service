package com.bank.servlet;

import java.util.Random;

/**
 * 1.����һ��19λ�����顾Integer�͡�
 * 2.ǰ��λ��ʽ�̶� ��6216 6153
 * 3.����10���������
 * 4.����Luhn�㷨�����У����		
 * 	��1.��������λ��ż��λ�ĺ�
 * 		��.����λ���
 * 		��.ż��λ*2,����д��ڵ���10��,����Ǹ�λ��ʮλ�ĺ�,�����ս�����=sum
 * 	��2.����sum%10�Ƿ����0:
 * 		��.�������0,���һλУ����Ϊ0;
 * 		��.�������һλУ�������10-����.
 */
public class CreateNewCard {

	
	// 1.����һ��19λ�����顾Integer�͡�
	private  Integer[] str = new Integer[19];
	//�洢���п���
	private  String card = "";
	

	// 2.ǰ��λ��ʽ�̶� ��6216 6153
	private  void setEight(Integer[] arr) {
		String eightStr = "62166153";
		for (int i = 0; i < 8; i++) {
			arr[i] =Integer.valueOf(eightStr.substring(i, i + 1)) ;
		}
	}
	
	//3.����10�������
	private  void createRandom(Integer[] arr){
		Random random = new Random();
		for (int i = 8; i < arr.length-1; i++) {
			arr[i] = random.nextInt(10);
		}
	}
	
	//4.�����У����
	/**
 * 	��1.��������λ��ż��λ�ĺ�
 * 		��.����λ���
 * 		��.ż��λ*2,����д��ڵ���10��,����Ǹ�λ��ʮλ�ĺ�,�����ս�����=sum
 * 	��2.����sum%10�Ƿ����0:
 * 		��.�������0,���һλУ����Ϊ0;
 * 		��.�������һλУ�������10-����.
	 */
	private  Integer getCheckCode(Integer[] arr){
		
		int sum = 0;//���ս����
		int oddSum = 0;	//����λ��
		int evenSum = 0; //ż��λ��
		int code = 0;	//У����
		for (int i = 0; i < arr.length-1; i++) {
			//����λ���
			if(i % 2 != 0){
				oddSum += arr[i];
			}else {
				//ż��λ���
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
	
	//�������п���
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
