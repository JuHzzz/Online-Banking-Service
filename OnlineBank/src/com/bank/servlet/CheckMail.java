package com.bank.servlet;


public class CheckMail {

	public static boolean checkMail(String mail) {

		if (check(mail) == false) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * ^ ��ƥ������Ŀ�ʼλ�á� 
	 * \������һ���ַ����Ϊ�����ַ�������ֵ��
	 *  ��ƥ��ǰһ���ַ���λ򼸴Ρ� 
	 *  + ��ƥ��ǰһ���ַ�һ�λ��Ρ�
	 * x|y��ƥ�� x �� y�� [a-z] ����ʾĳ����Χ�ڵ��ַ�����ָ�������ڵ��κ��ַ�ƥ�䡣 
	 * \w�����κε����ַ�ƥ�䣬�����»��ߡ�
	 * {n,m} ����ƥ�� n �������ƥ�� m ��
	 *  $ ��ƥ������Ľ�β��
	 */
	private static boolean check(String mail) {
		String regex = "\\w+(\\.\\w)*@\\w+(\\.\\w{2,3}){1,3}";
		return mail.matches(regex);
	}

}
