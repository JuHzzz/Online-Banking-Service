package com.bank.servlet_query;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.bank.servlet.Onload;

public class CheckPasswordIsRight {
	
	//验证密码是否正确，如果正确返回true
	public static boolean checkPassword(String card , String password){
		
		if(checkPasswordIsRight(card, password) == false){
			return false;
		}else{
			return true;

		}
		
	}
	

	// 卡号存在,验证密码的合法性
	private static boolean checkPasswordIsRight(String card, String password) {
		Connection con = Onload.getCon();
		PreparedStatement ps = null;
		boolean exists = false;
		ResultSet res = null;
		String sql = "select count(*) from tb_card where user_card = ? and user_password = ?";
		String sum = null;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, card);
			ps.setString(2, password);
			res = ps.executeQuery();
			while (res.next()) {
				sum = res.getString(1);
			}
			if (sum != null && !"0".equals(sum)) {
				exists = true;
			}
			res.close();
			ps.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return exists;

	}
}
