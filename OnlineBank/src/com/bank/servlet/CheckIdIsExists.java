package com.bank.servlet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CheckIdIsExists {

	public static boolean checkIDIsExists(String id){
		
		Connection con = Onload.getCon();
		PreparedStatement ps = null;
		String sql = "select count(*) from tb_card where user_id = ?";
		String sum = null;
		ResultSet res = null;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, id);
			res = ps.executeQuery();
			while(res.next()){
				sum = res.getString(1);
			}
			if(sum!=null&&!"0".equals(sum)){
				return false; //´æÔÚ´Ëid
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return true;
	}
	
}
