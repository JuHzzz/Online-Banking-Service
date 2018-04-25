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


	// ��ȡ����Ϊ18λ�����Id
	public static boolean checkIdcard(String id) {

		int length = id.length();

		if (length != 18) {
			return false;
		} else {
			// У��ǰ17λ
			if (!checkIsDigital(id.substring(0, 17))) {
				return false;
			} else {
				// У����λ��
				if (!checkLocation_code(id)) {
					return false;
				} else {
					// У���������
					if (!checkYear(id)) {
						return false;
					} else {
						// У��˳����
						if (!checkPower(id)) {
							return false;
						}
					}

				}
			}

		}

		return true;
	}

	// У��ǰ17λ�Ƿ�������
	private static boolean checkIsDigital(String id) {

		return id.matches("^[0-9]*$");
	}

	// У����λ��
	private static boolean checkLocation_code(String id) {
		// ���ǰ��λ����λ��
		String location = id.substring(0, 2);
		Connection con = Onload.getCon();
		PreparedStatement ps = null;
		ResultSet res = null;
		String num = null;
		// ���ҿ����Ƿ����ָ����λ��
		String sql = "select count(*) from tb_addr_code where location_code = ?";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, location);
			res = ps.executeQuery();
			while (res.next()) {
				num = res.getString(1);
			}
			if (num != null && !"0".equals(num)) {
				return true;// ����������ڶ�Ӧ��λ��ͷ���true;
			}
			res.close();
			ps.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;// ���򷵻�false;
	}

	// У�����������
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

	// У��˳����
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
