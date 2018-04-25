package com.bank.servlet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CheckPhone {

	public static boolean checkPhone(String phone) {

		// ����ֻ����Ƿ��пո�
		if (checkPhoneIsNull(phone)) {
			return false;
		} else {
			//�ֻ��ų����Ƿ�Ϸ�
			if (!checkPhoneLength(phone)) {
				return false;
			} else {
				//�ֻ����Ƿ��Ǵ�����
				if (!checkPhoneIsNum(phone)) {
					return false;
				} else {
					//�ֻ��ſ�ͷ�Ƿ�Ϸ�
					if(checkIslegal(phone) == false){
						return false;
					}else{
						return true;

					}

				}
			}
		}

	}

	// ����ֻ����Ƿ��пո� true:���пո� false��û�пո�
	private static boolean checkPhoneIsNull(String phone) {
		String str = " ";
		for (int i = 0; i < phone.length(); i++) {
			if (str.equals(phone.substring(i,i+1))) {
				return true;

			}
		}
		return false;
	}

	// ����ֻ��ų����Ƿ���11λ true:�� false:����
	private static boolean checkPhoneLength(String phone) {
		int len = phone.replace(" ", "").length();
		if (len != 11) {
			return false;
		} else {
			return true;
		}
	}

	// ����Pattern��֤�ֻ����Ƿ��Ǵ����� true:�� false������
	private static boolean checkPhoneIsNum(String phone) {

		Pattern p = Pattern.compile("[0-9]{1,}");
		Matcher m = p.matcher(phone);
		return m.matches();
	}

	//�ֻ����Ƿ�Ϸ�����ǰ3��4λ�ڿ����Ƿ��д��� true������
	private static boolean checkIslegal(String phone){

		String sum = null;
		String start_first = phone.substring(0,3);	//����ֻ��ŵ�ǰ��λ
		String start_second = phone.substring(0, 4);//����ֻ��ŵ�ǰ��λ
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
				return true; //���ڣ��Ϸ�
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
