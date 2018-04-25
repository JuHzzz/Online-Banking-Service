package com.bank.servlet;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CheckPassword {

	public static boolean checkPassword(String password, String rePassword) {

		if (checkIsNull(password, rePassword) == false) {
			return false;
		} else {

			if (checkPasswordLength(password, rePassword) == false) {
				return false;
			} else {
				if (checkIsDigital(password, rePassword) == false) {
					return false;
				} else {
					if (checkIsSame(password, rePassword) == false) {
						return false;
					} else {
						return true;
					}
				}
			}
		}
	}

	private static boolean checkIsNull(String a, String b) {
		if ("".equals(a) || "".equals(b) || a == null || b == null) {
			return false;
		} else {
			return true;
		}
	}

	// 检查密码长度是否是6位
	private static boolean checkPasswordLength(String a, String b) {
		int lenA = a.length();
		int lenB = b.length();
		if (lenA == 6 && lenB == 6) {
			return true;
		} else {
			return false;
		}
	}

	// 检查密码是否是纯数字
	private static boolean checkIsDigital(String a, String b) {
		String regex = "[0-9]{1,}";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(a);
		Matcher m2 = p.matcher(b);

		if (m.matches() == true && m2.matches() == true) {
			return true;
		} else {
			return false;
		}

	}

	// 检查两次输入是否一致
	private static boolean checkIsSame(String a, String b) {
		int len = a.length();
		int sum = 0;
		for (int i = 0; i < len; i++) {
			if (a.substring(i, i + 1).equals(b.substring(i, i + 1))) {
				sum++;
			}
		}
		if (sum == len) {

			return true;
		} else {

			return false;
		}
	}
}
