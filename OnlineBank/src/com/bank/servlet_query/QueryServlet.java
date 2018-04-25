package com.bank.servlet_query;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bank.transfer.CheckCard;

public class QueryServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected static Map<Object, Object> map = new HashMap<Object, Object>();

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		String user_card = request.getParameter("user_card");
		String user_password = request.getParameter("user_password");
		String tokenValue = request.getParameter("tokenValue");
		Object token = session.getAttribute("token4");
		response.setContentType("text/html;charset=utf-8");
		// �Ƿ��ظ��ύ
		if (token != null && token.equals(tokenValue)) {

			// ���������Ƿ�Ϊ��
			if ("".equals(user_card) || "".equals(user_password)) {
				out.println(
						"<script language = 'javascript'>alert('�������벻��Ϊ��.');window.location.href = 'query.jsp'</script>");
			} else {
				// �����Ƿ����
				if (CheckCard.checkCard(user_card) == false) {
					out.println(
							"<script language = 'javascript'>alert('���Ų����ڻ�Ǳ������п�������������.');window.location.href = 'query.jsp'</script>");
				} else {
					// ���Ŵ��ڣ���֤�����Ƿ���ȷ
					if (CheckPasswordIsRight.checkPassword(user_card, user_password) == false) {
						out.println(
								"<script language = 'javascript'>alert('���������������������.');window.location.href = 'query.jsp'</script>");
					} else {
						// �������붼û������,���ؽ����,���ҽ�������ӵ�session��
						map = GetData.getData(user_card);
						session.setAttribute("user_card", user_card);
						session.setAttribute("user_name", map.get("user_name"));
						session.setAttribute("user_balance", map.get("user_balance"));
						map.clear();
						out.println("<script language = 'javascript'>window.location.href = 'queryResult.jsp'</script>");
						session.removeAttribute("token4");
					}

				}
			}
		} else {
			// �ظ��ύ
			out.println(
					"<script language = 'javascript'>alert('Sorry! You have submitted information! Please do not submit again!!!!');window.location.href='queryResult.jsp';</script>");
		}
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
}
