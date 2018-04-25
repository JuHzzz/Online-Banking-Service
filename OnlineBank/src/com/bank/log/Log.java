package com.bank.log;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import com.bank.servlet.Onload;

public class Log {

	private static String pay_card ;
	private static String get_card ;
	private static String get_name ;
	private static double balance_amount ;
	private static String dateTime ;
	
	
	public static void isLogIn(Map<String, String> map){
			
		log(map);
	}
	
	private static boolean log(Map<String, String> map){
		
		pay_card = map.get("pay_card");
		get_card = map.get("get_card");
		get_name = map.get("get_name");
		String amount = map.get("balance_amount");
		balance_amount = Double.parseDouble(amount);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		dateTime = df.format(new Date());// new Date()为获取当前系统时间
		
		Connection con = Onload.getCon();
		PreparedStatement ps = null;
		String sql = "insert into tb_log values (?,?,?,?,?)";
		try {
			ps = con.prepareStatement(sql); 
			ps.setString(1 , pay_card);
			ps.setString(2, get_card);
			ps.setDouble(3, balance_amount);
			ps.setString(4, dateTime);
			ps.setString(5, get_name);
			int num = ps.executeUpdate();
			if(num != 0){
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return false;
	}
	

}
