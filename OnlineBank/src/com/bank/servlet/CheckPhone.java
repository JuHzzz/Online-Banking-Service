package com.bank.servlet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CheckPhone {

	public static boolean checkPhone(String phone) {

		// 检测手机号是否含有空格
		if (checkPhoneIsNull(phone)) {
			return false;
		} else {
			//手机号长度是否合法
			if (!checkPhoneLength(phone)) {
				return false;
			} else {
				//手机号是否是纯数字
				if (!checkPhoneIsNum(phone)) {
					return false;
				} else {
					//手机号开头是否合法
					if(checkIslegal(phone) == false){
						return false;
					}else{
						return true;

					}

				}
			}
		}

	}

	// 检测手机号是否含有空格 true:含有空格 false：没有空格
	private static boolean checkPhoneIsNull(String phone) {
		String str = " ";
		for (int i = 0; i < phone.length(); i++) {
			if (str.equals(phone.substring(i,i+1))) {
				return true;

			}
		}
		return false;
	}

	// 检测手机号长度是否是11位 true:是 false:不是
	private static boolean checkPhoneLength(String phone) {
		int len = phone.replace(" ", "").length();
		if (len != 11) {
			return false;
		} else {
			return true;
		}
	}

	// 利用Pattern验证手机号是否是纯数字 true:是 false：不是
	private static boolean checkPhoneIsNum(String phone) {

		Pattern p = Pattern.compile("[0-9]{1,}");
		Matcher m = p.matcher(phone);
		return m.matches();
	}

	//手机号是否合法，即前3，4位在库中是否有存在 true：存在
	private static boolean checkIslegal(String phone){

		String sum = null;
		String start_first = phone.substring(0,3);	//获得手机号的前三位
		String start_second = phone.substring(0, 4);//获得手机号的前四位
		String sql = "select count(*) from tb_phone where phone_num in (?,?)";
		Connection con = Onload.getCon();
		PreparedStatement ps = null;
		ResultSet res = null;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, start_first);
			ps.setString(2, start_second);
			res = ps.executeQuery();
			while(res.next()){
				sum = res.getString(1);
			}
			if(sum != null && !"0".equals(sum)){
				return true; //存在，合法
			}
			res.close();
			ps.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	


}
