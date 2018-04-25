package com.bank.servlet_query;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.bank.servlet.Onload;

public class GetData {

	private static String user_name = null;//用户姓名
	private static Double user_balance ;	//账户余额
	private static Map<Object, Object> map = new HashMap<Object,Object>();
	public static Map<Object, Object> getData(String card){
		user_name = getUser_name(GetId.getUser_id(card));
		user_balance = getUser_balance(card);
		map.put("user_name", user_name);
		map.put("user_balance", user_balance);
		
		return map;
	}
	
	
	
	private static String getUser_name(String id){
		Connection con = Onload.getCon();
		PreparedStatement ps = null;
		ResultSet res = null;
		String name = null;
		String sql = "select user_name from tb_user where user_id = ?";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, id);
			res = ps.executeQuery();
			while(res.next()){
				name = res.getString(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return name;
	}
	
	
	private static Double getUser_balance(String card){
		Connection con = Onload.getCon();
		PreparedStatement ps = null;
		ResultSet res = null;
		Double balance = 0.0;
		String sql = "select user_balance from tb_card where user_card = ?";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, card);
			res = ps.executeQuery();
			while(res.next()){
				balance = res.getDouble(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return balance;
	}

}
