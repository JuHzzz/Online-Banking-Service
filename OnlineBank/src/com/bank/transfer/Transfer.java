package com.bank.transfer;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import com.bank.servlet.Onload;

public class Transfer {

	public static boolean transferIsSuccess(String card_1, String card_2 , Double amount ){
		
		if(transfer(card_1, card_2, amount) == false){
			return true;
		}else{
			return false;
		}
		
	}
	/**
	 * 
	 * @param card_1 : �տ�
	 * @param card_2    ������
	 * @param amount  ��ת�˽��
	 * @return false:�����һ������� ResultSet �� ���򷵻� true�������һ������Ǹ��¼�������û �н�����򷵻� false
	 */
	private static boolean transfer(String card_1, String card_2 , Double amount ){
		
		Connection con = Onload.getCon();
		CallableStatement call = null;
		boolean isSuccess = false;
		String sql = "{call transfer(?,?,?)}";
		try {
			call = con.prepareCall(sql);
			call.setString(1, card_1);
			call.setString(2, card_2);
			call.setDouble(3, amount);
			isSuccess = call.execute();
			call.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return isSuccess;
		
	}
	
	public static void main(String[] args) {
		System.out.println(transferIsSuccess("6216615367438500061","6216615367996823947",1000.0));
	}
}
