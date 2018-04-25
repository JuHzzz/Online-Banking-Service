package com.bank.servlet_query;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.bank.servlet.Onload;

public class GetId {

	//找卡号对应的户主名字：id-->tb_user-->name
		public static String getUser_id(String card){
			Connection con = Onload.getCon();
			PreparedStatement ps = null;
			ResultSet res = null;
			String id = null;
			String sql = "select user_id from tb_card where user_card = ?";
			try {
				ps = con.prepareStatement(sql);
				ps.setString(1, card);
				res = ps.executeQuery();
				while(res.next()){
					id = res.getString(1);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return id;
		}
		 
}
