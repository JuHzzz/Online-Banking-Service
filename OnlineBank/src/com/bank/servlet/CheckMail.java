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
	 * ^ ：匹配输入的开始位置。 
	 * \：将下一个字符标记为特殊字符或字面值。
	 *  ：匹配前一个字符零次或几次。 
	 *  + ：匹配前一个字符一次或多次。
	 * x|y：匹配 x 或 y。 [a-z] ：表示某个范围内的字符。与指定区间内的任何字符匹配。 
	 * \w：与任何单词字符匹配，包括下划线。
	 * {n,m} 最少匹配 n 次且最多匹配 m 次
	 *  $ ：匹配输入的结尾。
	 */
	private static boolean check(String mail) {
		String regex = "\\w+(\\.\\w)*@\\w+(\\.\\w{2,3}){1,3}";
		return mail.matches(regex);
	}

}
