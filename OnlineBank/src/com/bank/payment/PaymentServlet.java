package com.bank.payment;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bank.servlet.CheckPhone;

public class PaymentServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		String phoneNum = request.getParameter("phone");// 获取电话号码
		String count = request.getParameter("count");// 获取充值金额
		String tokenValue = request.getParameter("tokenValue");
		Object token = session.getAttribute("token7");
		response.setContentType("text/html;charset=utf-8");
		// 是否重复提交
		if (token != null && token.equals(tokenValue)) {
			// 是否为空
			if ("".equals(phoneNum) || "".equals(count)) {
				out.print(
						"<script language='javascript'>alert('请完善您的信息并重新输入');window.location.href='payment_1.jsp';</script>");

			} else {
				// 对电话号码和充值金额进行格式验证
				if (CheckPhone.checkPhone(phoneNum) == false || CheckIsDigital.isDital(count) == false) {
					out.print(
							"<script language='javascript'>alert('电话号码或充值金额不合法!请重新输入');window.location.href='payment_1.jsp';</script>");

				} else {
					// 格式正确，将充值信息写入session
					session.setAttribute("phone", phoneNum);
					session.setAttribute("count", count);
					out.print("<script language='javascript'>window.location.href='payment_2.jsp';</script>");
					session.removeAttribute("token7");
				}

			}

		} else {
			out.print(
					"<script language='javascript'>alert('您已提交订单！请勿重复提交！');window.location.href='payment_2.jsp';</script>");

		}

	}

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

}  
