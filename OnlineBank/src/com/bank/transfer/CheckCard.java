package com.bank.transfer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.bank.servlet.Onload;

public class CheckCard {

	//�������ݿ�
	
	public static boolean checkCard(String card){
		
		if(checkIsCardExists(card)==false){
			//�����ڸÿ���
			return false;
		}else{
			//���Ŵ���
			return true;
		}
		
	}
	
	//���鿨���Ƿ����,���������֤����ĺϷ���
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
