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

				// 验证密码合法性：是否为空，长度是否是6位，是否是纯数字，两次输入是否一致

			 	if (CheckPassword.checkPassword(user_password, user_repassword) == false) {

					out.print(
							"<script language='javascript'>alert('密码设置错误，请重新输入!');window.location.href='register_2.jsp'</script>");

				} else {
					map.put("user_password", user_password);
					System.out.println("传到二级页面后：:"+map.get("user_card"));
					if(UpdateDBA.insert(map)==false){
						System.out.println(map);
						out.print(
								"<script language='javascript'>alert('发生系统错误！');window.location.href='index.html';</script>");
				
					}else{
						System.out.println("传到二级页面，入库成功后：:"+map.get("user_card"));
						map.put("user_card",null);
						out.print(
								"<script language='javascript'>alert('成功创建新卡');window.location.href='index.html';</script>");
						System.out.println("创建成功，销毁map中的卡号后:"+map.get("user_card"));
						map.clear();
						session.invalidate();
					}
					
				}
			} else {
				session.invalidate();
				// 重复提交
				out.println(
						"<script language = 'javascript'>window.location.href='index.html';</script>");
				
			}

			// 执行次数超限
		} else {
			// 密码输入错误次数超过10次，退出办卡
			out.println(
					"<script language = 'javascript'>alert('密码输入错误超过10次！退出系统  ');window.location.href = 'index.html'</script>");
			session.invalidate();

		}
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
}
