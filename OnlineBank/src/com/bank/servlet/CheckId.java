package com.bank.servlet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CheckId {

	final static 	String[] arr = { "1", "0", "X", "9", "8", "7", "6", "5", "4", "3", "2" };


	// 获取长度为18位的身份Id
	public static boolean checkIdcard(String id) {

		int length = id.length();

		if (length != 18) {
			return false;
		} else {
			// 校验前17位
			if (!checkIsDigital(id.substring(0, 17))) {
				return false;
			} else {
				// 校验区位码
				if (!checkLocation_code(id)) {
					return false;
				} else {
					// 校验出生年月
					if (!checkYear(id)) {
						return false;
					} else {
						// 校验顺序码
						if (!checkPower(id)) {
							return false;
						}
					}

				}
			}

		}

		return true;
	}

	// 校验前17位是否是数字
	private static boolean checkIsDigital(String id) {

		return id.matches("^[0-9]*$");
	}

	// 校验区位码
	private static boolean checkLocation_code(String id) {
		// 获得前两位的区位码
		String location = id.substring(0, 2);
		Connection con = Onload.getCon();
		PreparedStatement ps = null;
		ResultSet res = null;
		String num = null;
		// 查找库中是否存在指定区位码
		String sql = "select count(*) from tb_addr_code where location_code = ?";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, location);
			res = ps.executeQuery();
			while (res.next()) {
				num = res.getString(1);
			}
			if (num != null && !"0".equals(num)) {
				return true;// 库中如果存在对应区位码就返回true;
			}
			res.close();
			ps.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;// 否则返回false;
	}

	// 校验出生年月日
	public static boolean checkYear(String id) {

		String birthday = id.substring(6, 14);
		int year = Integer.valueOf(id.substring(6, 10));
		int month = Integer.valueOf(id.substring(10, 12));
		int day = Integer.valueOf(id.substring(12, 14));

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

		try {
			Date birth = sdf.parse(birthday);
			String birthDate = sdf.format(birth);
			if (!birthday.equals(birthDate)) {
				return false;
			} else {

			}
		} catch (ParseException e) {
			e.printStackTrace();
		}

		if (month == 2 && day == 29) {
			if ((year % 100 != 0 && year % 4 == 0) == false && (year % 400 == 0) == false) {
				return false;
			}
		}
		return true;

	}

	private static int power[] = { 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2 };

	// 校验顺序码
	private static boolean checkPower(String id) {
		int id_sum = 0;
		int len = id.length() - 1;
		Integer[] inter = new Integer[len];
		for (int j = 0; j < inter.length; j++) {
			inter[j] = Integer.parseInt(id.substring(j, j + 1));
			id_sum += (inter[j] * power[j]);
		}
		int lastNum = id_sum % 11;
		String str = arr[lastNum];
		if (!str.equals(id.substring(len))) {
			return false;
		}
		return true;

	}

}
