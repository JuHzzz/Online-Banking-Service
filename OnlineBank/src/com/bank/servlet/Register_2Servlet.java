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

public class Register_2Servlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static int num = 0;
	
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		response.setContentType("text/html;charset=utf-8");
		String user_password = request.getParameter("user_password");
		String user_repassword = request.getParameter("user_repassword");
		String tokenValue = request.getParameter("tokenValue");
		HttpSession session = request.getSession();
		Map<String , String > map = new HashMap<String , String>();
		map = (Map<String, String>) session.getAttribute("map");
		Object token = session.getAttribute("token3");
		if (num < 9) {
			if (token != null && token.equals(tokenValue)) {

				// ��֤����Ϸ��ԣ��Ƿ�Ϊ�գ������Ƿ���6λ���Ƿ��Ǵ����֣����������Ƿ�һ��

				if (CheckPassword.checkPassword(user_password, user_repassword) == false) {

					out.print(
							"<script language='javascript'>alert('�������ô�������������!');window.location.href='register_2.jsp'</script>");

				} else {
					map.put("user_password", user_password);
					System.out.println("��������ҳ���:"+map.get("user_card"));
					if(UpdateDBA.insert(map)==false){
						System.out.println(map);
						out.print(
								"<script language='javascript'>alert('����ϵͳ����');window.location.href='index.html';</script>");
				
					}else{
						System.out.println("��������ҳ�棬���ɹ���:"+map.get("user_card"));
						map.put("user_card",null);
						out.print(
								"<script language='javascript'>alert('�ɹ������¿�');window.location.href='index.html';</script>");
						System.out.println("�����ɹ�������map�еĿ��ź�:"+map.get("user_card"));
						map.clear();
						session.invalidate();
					}
					
				}
			} else {
				session.invalidate();
				// �ظ��ύ
				out.println(
						"<script language = 'javascript'>window.location.href='index.html';</script>");
				
			}

			// ִ�д�������
		} else {
			// ������������������10�Σ��˳��쿨
			out.println(
					"<script language = 'javascript'>alert('����������󳬹�10�Σ��˳�ϵͳ  ');window.location.href = 'index.html'</script>");
			session.invalidate();

		}
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
}
