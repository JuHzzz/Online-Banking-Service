package com.bank.transfer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.bank.servlet.Onload;
import com.bank.servlet_query.GetId;

public class CheckGeterInformation {

	//验证收款卡号对应的收款人信息是否正确
	public static boolean checkInformation(String card , String name, String phone) {

		if(check(card, name, phone)==false){
			return false;
		}else{
			return true;
		}
	}

	
	
	private static boolean check( String card, String name , String phone) {
		String id = GetId.getUser_id(card);
		Connection con = Onload.getCon();
		PreparedStatement ps = null;
		ResultSet res = null;
		boolean isRight = false;
		String sum  = null; 
		String sql = "select count(*) from tb_user where user_name = ? and user_phone = ? and user_id = ?";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, name);
			ps.setString(2, phone);
			ps.setString(3, id);
			res = ps.executeQuery();
			while(res.next()){
				sum = res.getString(1);
			}
			if(sum!=null && !"0".equals(sum)){
				isRight = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return isRight;
		
	}
}
