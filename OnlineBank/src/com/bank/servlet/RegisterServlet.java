package com.bank.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class RegisterServlet extends HttpServlet {

	/**
	 * ע����Ϣ��һҳ��Servlet
	 */
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String user_name = request.getParameter("user_name");
		String user_id = request.getParameter("user_id");
		String user_phone = request.getParameter("user_phone");
		String user_mail = request.getParameter("user_mail");
		String user_sex = request.getParameter("user_sex");
		String tokenValue = request.getParameter("tokenValue");
		response.setContentType("text/html;charset=utf-8");
		HttpSession session = request.getSession();
		Map<String, String> map = new HashMap<String, String>();
		Object token = session.getAttribute("token2");
		if (token != null && token.equals(tokenValue)) {
			// ��һ���ύ
			if ("".equals(user_name.trim())||user_name==null || "".equals(user_id.trim())||user_id==null || "".equals(user_phone.trim())
					||user_mail ==null	|| "".equals(user_mail.trim())||user_mail ==null || "".equals(user_sex)||user_sex ==null) {
				out.print(
						"<script language='javascript'>alert('���������������Ϣ');window.location.href='register_1.jsp';</script>");
				System.out.println(token);
			} else {

				// �����û�Id�Ƿ��Ѿ����ڣ�����ÿһ���û�idֻ�������а���һ�Ŵ��
				if (CheckIdIsExists.checkIDIsExists(user_id) == false) {
					out.print(
							"<script language='javascript'>alert('�Բ������Ѿ�ӵ�����еĴ����ÿλ�û�ֻ��ƾ�����֤����һ�ſ�.');window.location.href='register_1.jsp';</script>");
				} else {

					// ����û�������ʽ�Ƿ��д�
					// ����û����֤�����Ƿ�����
					// ����û��ֻ����Ƿ�����
					// ��֤�����Ƿ�����
					
					if (CheckName.checkName(user_name) == false || CheckId.checkIdcard(user_id) == false
							|| CheckPhone.checkPhone(user_phone) == false || CheckMail.checkMail(user_mail) == false) {

						out.print(
								"<script language='javascript'>alert('��Ϣ�������������룡');window.location.href='register_1.jsp';</script>");
					} else {
						//�������п���
						String user_card = null;
						System.out.println("��ʼ����"+user_card);
						user_card = new CreateNewCard().createNewCard();
						System.out.println("�����¿�:"+user_card);
						map.put("user_name", user_name);
						map.put("user_id", user_id);
						map.put("user_phone", user_phone);
						map.put("user_mail", user_mail);
						map.put("user_sex", user_sex);
						map.put("user_card", null);
						map.put("user_card", user_card);
						session.setAttribute("map",map);
						session.setAttribute("user_Card", user_card);
						System.out.println(map);
						System.out.println("map�п���:"+map.get("user_card"));
						out.print("<script language='javascript'>window.location.href='register_2.jsp';</script>");
						session.removeAttribute("token2");
						
					}
				}
			}
		} else {
			// �ظ��ύ
			out.println(
					"<script language = 'javascript'>alert('�Բ������Ѿ��ύ����Ϣ�������ظ��ύ!!!!');window.location.href='register_2.jsp';</script>");
			session.removeAttribute("user_card");
		}

	}

	/**
	 * ��֤�쿨����Ϣ�Ƿ�Ϸ�
	 */

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		doPost(req, resp);
	}

}
