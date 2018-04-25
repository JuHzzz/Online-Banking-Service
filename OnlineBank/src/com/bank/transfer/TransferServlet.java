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
		String user_pay_card = request.getParameter("user_pay_card"); // �����
		String amount = request.getParameter("amount"); // ת�˽��
		String user_get_card = request.getParameter("user_get_card"); // �տ��
		String user_get_name = request.getParameter("user_get_name"); // �տ���
		String user_get_phone = request.getParameter("user_get_phone"); // �տ����ֻ���
		String tokenValue = request.getParameter("tokenValue");
		Object token = session.getAttribute("token5");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		// �Ƿ��ظ��ύ
		if (token != null && token.equals(tokenValue)) {

			// ��Ϣ�Ƿ�Ϊ��
			if ("".equals(user_get_card) || "".equals(user_pay_card) || "".equals(amount) || "".equals(user_get_name)
					|| "".equals(user_get_name)) {
				out.println(
						"<script language = 'javascript'>alert('�����������Ϣ.');window.location.href = 'transfer.jsp'</script>");
			} else {
				// �����Ƿ����
				if (CheckCard.checkCard(user_pay_card) == false || CheckCard.checkCard(user_get_card) == false) {
					out.println(
							"<script language = 'javascript'>alert('����Ż��տ�Ų�����,����������.');window.location.href = 'transfer.jsp'</script>");
				} else {
					//���ܸ��Լ�ת��
					if (user_get_card.equals(user_pay_card)) {
						out.println(
								"<script language = 'javascript'>alert('�տ������,����������.');window.location.href = 'transfer.jsp'</script>");

					} else {

						// �տ�����Ϣ�Ƿ���ȷ
						if (CheckGeterInformation.checkInformation(user_get_card, user_get_name,
								user_get_phone) == false) {
							out.println(
									"<script language = 'javascript'>alert('����ȷ�����տ�����Ϣ.');window.location.href = 'transfer.jsp'</script>");

						} else {
							// ��ѯת�˽���Ƿ�Ϸ�
							if (CheckBalance.checkBalance(amount, user_pay_card) == false) {
								out.println(
										"<script language = 'javascript'>alert('�˻�����');window.location.href = 'transfer.jsp'</script>");

							} else {
								// ת������֧��ҳ��,������ת����ת��֧���ڶ���ҳ��
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
			// �ظ��ύ
			out.println(
					"<script language = 'javascript'>alert('Sorry! You have submitted information! Please do not submit again!!!!');window.location.href='index.html';</script>");

		}
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
}
