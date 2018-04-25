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
		String phoneNum = request.getParameter("phone");// ��ȡ�绰����
		String count = request.getParameter("count");// ��ȡ��ֵ���
		String tokenValue = request.getParameter("tokenValue");
		Object token = session.getAttribute("token7");
		response.setContentType("text/html;charset=utf-8");
		// �Ƿ��ظ��ύ
		if (token != null && token.equals(tokenValue)) {
			// �Ƿ�Ϊ��
			if ("".equals(phoneNum) || "".equals(count)) {
				out.print(
						"<script language='javascript'>alert('������������Ϣ����������');window.location.href='payment_1.jsp';</script>");

			} else {
				// �Ե绰����ͳ�ֵ�����и�ʽ��֤
				if (CheckPhone.checkPhone(phoneNum) == false || CheckIsDigital.isDital(count) == false) {
					out.print(
							"<script language='javascript'>alert('�绰������ֵ���Ϸ�!����������');window.location.href='payment_1.jsp';</script>");

				} else {
					// ��ʽ��ȷ������ֵ��Ϣд��session
					session.setAttribute("phone", phoneNum);
					session.setAttribute("count", count);
					out.print("<script language='javascript'>window.location.href='payment_2.jsp';</script>");
					session.removeAttribute("token7");
				}

			}

		} else {
			out.print(
					"<script language='javascript'>alert('�����ύ�����������ظ��ύ��');window.location.href='payment_2.jsp';</script>");

		}

	}

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

}
