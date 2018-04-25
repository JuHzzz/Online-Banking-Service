package com.bank.transfer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.bank.servlet.Onload;

public class CheckBalance {

	public static boolean checkBalance(String balance, String card) {
		Double account = Double.valueOf(balance); // 转账金额
		Double count_balance = getBalance(card); // 账户余额
		if (count_balance != 0.0 && account <= count_balance) {
			return true;	//转账金额合法
		}else{
			return false;	//转账金额不合法
		}
	}

	private static Double getBalance(String card) {
		Double balance = 0.0;
		Connection con = Onload.getCon();
		PreparedStatement ps = null;
		ResultSet res = null;
		String sql = "select user_balance from tb_card where user_card = ?";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, card);
			res = ps.executeQuery();
			while (res.next()) {
				balance = res.getDouble(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return balance;
	}
}
