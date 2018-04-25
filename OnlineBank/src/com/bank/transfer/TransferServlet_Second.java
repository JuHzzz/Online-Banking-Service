package com.bank.transfer;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bank.log.Log;
import com.bank.servlet_query.CheckPasswordIsRight;

public class TransferServlet_Second extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static int count = 0;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		Map<String, String> map = new HashMap<String,String>();
		String card_2 = session.getAttribute("get_card").toString();//收款卡号
		String card_1 = session.getAttribute("pay_card").toString();//付款卡号
		String get_name = session.getAttribute("get_name").toString();//收款人姓名
		String amount = session.getAttribute("amount").toString();
		response.setContentType("text/html;charset=utf-8");
		double amounter = Double.parseDouble(amount);// 转账金额
		String password = request.getParameter("user_password");	//付款密码
		PrintWriter out = response.getWriter();
		if(count < 3){
			if(CheckPasswordIsRight.checkPassword(card_1, password)==false){
				count++;
				out.println(
						"<script language = 'javascript'>alert('密码输入错误');window.location.href = 'transfer2.jsp'</script>");
			}else{
				//开始转帐前，先写日志
				map.put("pay_card", card_1);	//付款卡号
				map.put("get_card", card_2);    //收款卡号
				map.put("get_name", get_name);	//收款姓名
				map.put("balance_amount", amount);
				Log.isLogIn(map);
				//密码正确,执行存储过程
				if(Transfer.transferIsSuccess(card_2, card_1, amounter)==false){
					//出现事务内部故障
					out.println(
							"<script language = 'javascript'>alert('系统错误');window.location.href = 'index.html'</script>");
					session.invalidate();
				}else{
				
					//转账成功
					out.println(
							"<script language = 'javascript'>alert('转账成功！');window.location.href = 'index.html'</script>");
					session.invalidate();
				}
				
				
			}
		}else{
			//密码输入错误次数超过3次，退出转账
			out.println(
					"<script language = 'javascript'>alert('Password input error 3 times!  You can not transfer the money today.');window.location.href = 'index.html'</script>");
			session.invalidate();
		}
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
 
