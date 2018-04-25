package com.bank.payment;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import com.bank.servlet.Onload;

public class Top {

	public static boolean isSuccess(String pay_card , String phone , double amount){
		
		if(top(pay_card, phone, amount) == false){
			return true;
		}else{
			return false;
		}
	}
	
	private static boolean top(String pay_card , String phone , double amount){
		Connection con = Onload.getCon();
		CallableStatement call = null;
		String sql = "{call top(?,?,?)}";
		boolean res = false;
		try {
			call = con.prepareCall(sql);
			call.setString(1, pay_card);
			call.setString(2, phone);
			call.setDouble(3, amount);
			res = call.execute();
			call.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}
} 
