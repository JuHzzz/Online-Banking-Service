package com.bank.payment;

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
import com.bank.transfer.CheckBalance;
import com.bank.transfer.CheckCard;

public class PaymentServlet_Second extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static int num = 0;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		String user_card = request.getParameter("user_card");// 支付卡号
		String user_password = request.getParameter("user_password");// 支付密码
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		Map<String, String> map = new HashMap<String, String>();
		String count = session.getAttribute("count").toString();//转账金额
		double amount = Double.parseDouble(count);
		String phone = session.getAttribute("phone").toString();//充值号码
		// 密码错误三次
		if (num < 3) {
			// 验证卡号密码是否为空
			if ("".equals(user_card) || "".equals(user_password)) {
				out.print(
						"<script language='javascript'>alert('卡号密码不能为空请重新输入！');window.location.href='payment_2.jsp';</script>");
			} else {
				// 验证卡号是否存在
				if (CheckCard.checkCard(user_card) == false) {
					out.print(
							"<script language='javascript'>alert('卡号不存在或非本行卡号！请重新输入！');window.location.href='payment_2.jsp';</script>");
				} else {
					// 卡号存在,验证密码
					if (CheckPasswordIsRight.checkPassword(user_card, user_password) == false) {
						num++;
						out.println(
								"<script language = 'javascript'>alert('密码输入错误！请重新输入！');window.location.href = 'payment_2.jsp'</script>");
						// 卡号密码正确，检查余额是否充足
					} else {
						// 余额不足
						if (CheckBalance.checkBalance(count, user_card) == false) {
							out.println(
									"<script language = 'javascript'>alert('账户余额不足!');window.location.href = 'payment_1.jsp'</script>");
							//余额充足
						} else {
							//开始转帐前，先写日志
							map.put("pay_card", user_card);	//付款卡号
							map.put("get_card", phone);    //收款卡号
							map.put("get_name", "话费充值");	//收款姓名
							map.put("balance_amount", count);
							Log.isLogIn(map);
							//执行存储过程top
							if(Top.isSuccess(user_card, phone, amount)==false){
								//出现事务内部故障
								out.println(
										"<script language = 'javascript'>alert('系统错误');window.location.href = 'index.html'</script>");
								session.invalidate();
							}else{
								//转账成功
								out.println(
										"<script language = 'javascript'>alert('充值成功');window.location.href = 'index.html'</script>");
								session.invalidate();
						
							} 
							
						}
					}
				}
			}
		} else {
			// 密码输入错误次数超过3次，退出充值
			out.println(
					"<script language = 'javascript'>alert('Password input error 3 times!  You can not transfer the money today.');window.location.href = 'index.html'</script>");
			session.invalidate();
		}

	}

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
}
 
