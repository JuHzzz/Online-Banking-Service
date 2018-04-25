package com.bank.transfer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.bank.servlet.Onload;

public class CheckCard {

	//连接数据库
	
	public static boolean checkCard(String card){
		
		if(checkIsCardExists(card)==false){
			//不存在该卡号
			return false;
		}else{
			//卡号存在
			return true;
		}
		
	}
	
	//检验卡号是否存在,如果存在验证密码的合法性
	private static boolean checkIsCardExists(String card ){
		Connection con = Onload.getCon();
		PreparedStatement ps = null;
		boolean exists = false;
		ResultSet res = null;
		String sql = "select count(*) from tb_card where user_card = ?";
		String sum = null;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, card);
			res = ps.executeQuery();
			while(res.next()){
				sum = res.getString(1);
			}
			if(sum != null && !"0".equals(sum)){
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
