package com.bank.servlet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;

public class UpdateDBA {

	/**
	 * 与数据库获取连接,将Map中的信息录入到数据库中
	 * 
	 * @return
	 */
	
	
	public static boolean insert(Map<String, String> map){
		
		if(insertData(map)==true&&insertData2(map)==true){
			return true;
		}else{
			return false;
		}
	}
	
	
	
	private static boolean insertData(Map<String, String> map) {

		Connection con = Onload.getCon();
		boolean isInsert = false;
		PreparedStatement ps = null;
		String sql = "insert into tb_user(user_id,user_name,user_phone,user_mail,user_sex) values(?,?,?,?,?)";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, map.get("user_id"));
			ps.setString(2, map.get("user_name"));
			ps.setString(3, map.get("user_phone"));
			ps.setString(4, map.get("user_mail"));
			ps.setString(5, map.get("user_sex"));
			int sum = ps.executeUpdate();
			if (sum != 0) {
				isInsert = true;
			}
			ps.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return isInsert;

	}
	
	
	private static boolean insertData2(Map<String, String> map) {

		Connection con = Onload.getCon();
		boolean isInsert = false;
		PreparedStatement ps = null;
		String sql = "insert into tb_card(user_id,user_card,user_password,user_balance) values(?,?,?,?)";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, map.get("user_id"));
			ps.setString(2, map.get("user_card"));
			ps.setString(3, map.get("user_password"));
			ps.setDouble(4, 5000);
			int sum = ps.executeUpdate();
			if (sum != 0) {
				isInsert = true;
			}
			ps.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return isInsert;

	}
}
