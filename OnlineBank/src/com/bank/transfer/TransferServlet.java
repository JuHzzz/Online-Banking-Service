package com.bank.transfer;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class TransferServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String user_pay_card = request.getParameter("user_pay_card"); // 付款卡号
		String amount = request.getParameter("amount"); // 转账金额
		String user_get_card = request.getParameter("user_get_card"); // 收款卡号
		String user_get_name = request.getParameter("user_get_name"); // 收款人
		String user_get_phone = request.getParameter("user_get_phone"); // 收款人手机号
		String tokenValue = request.getParameter("tokenValue");
		Object token = session.getAttribute("token5");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		// 是否重复提交
		if (token != null && token.equals(tokenValue)) {

			// 信息是否为空
			if ("".equals(user_get_card) || "".equals(user_pay_card) || "".equals(amount) || "".equals(user_get_name)
					|| "".equals(user_get_name)) {
				out.println(
						"<script language = 'javascript'>alert('请完善你的信息.');window.location.href = 'transfer.jsp'</script>");
			} else {
				// 卡号是否存在
				if (CheckCard.checkCard(user_pay_card) == false || CheckCard.checkCard(user_get_card) == false) {
					out.println(
							"<script language = 'javascript'>alert('付款卡号或收款卡号不存在,请重新输入.');window.location.href = 'transfer.jsp'</script>");
				} else {
					//不能给自己转账
					if (user_get_card.equals(user_pay_card)) {
						out.println(
								"<script language = 'javascript'>alert('收款卡号有误,请重新输入.');window.location.href = 'transfer.jsp'</script>");

					} else {

						// 收款人信息是否正确
						if (CheckGeterInformation.checkInformation(user_get_card, user_get_name,
								user_get_phone) == false) {
							out.println(
									"<script language = 'javascript'>alert('请正确输入收款人信息.');window.location.href = 'transfer.jsp'</script>");

						} else {
							// 查询转账金额是否合法
							if (CheckBalance.checkBalance(amount, user_pay_card) == false) {
								out.println(
										"<script language = 'javascript'>alert('账户余额不足');window.location.href = 'transfer.jsp'</script>");

							} else {
								// 转到密码支付页面,将请求转发到转账支付第二级页面
								session.setAttribute("pay_card", user_pay_card);
								session.setAttribute("amount", amount);
								session.setAttribute("get_card", user_get_card);
								session.setAttribute("get_name", user_get_name);
								session.setAttribute("get_phone", user_get_phone);
								session.removeAttribute("token5");
								out.println(
										"<script language = 'javascript'>window.location.href = 'transfer2.jsp'</script>");

							}

						}
					}

				}
			}
		} else {
			// 重复提交
			out.println(
					"<script language = 'javascript'>alert('Sorry! You have submitted information! Please do not submit again!!!!');window.location.href='index.html';</script>");

		}
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp); 
	}
}
