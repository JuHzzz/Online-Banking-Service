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
	 * 注册信息第一页的Servlet
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
			// 第一次提交
			if ("".equals(user_name.trim())||user_name==null || "".equals(user_id.trim())||user_id==null || "".equals(user_phone.trim())
					||user_mail ==null	|| "".equals(user_mail.trim())||user_mail ==null || "".equals(user_sex)||user_sex ==null) {
				out.print(
						"<script language='javascript'>alert('请重新完善你的信息');window.location.href='register_1.jsp';</script>");
				System.out.println(token);
			} else {

				// 检测此用户Id是否已经存在，限制每一个用户id只能在我行办理一张储蓄卡
				if (CheckIdIsExists.checkIDIsExists(user_id) == false) {
					out.print(
							"<script language='javascript'>alert('对不起！您已经拥有我行的储蓄卡！每位用户只能凭借身份证办理一张卡.');window.location.href='register_1.jsp';</script>");
				} else {

					// 检测用户姓名格式是否有错
					// 检测用户身份证号码是否有误
					// 检测用户手机号是否有误
					// 验证邮箱是否有误
					
					if (CheckName.checkName(user_name) == false || CheckId.checkIdcard(user_id) == false
							|| CheckPhone.checkPhone(user_phone) == false || CheckMail.checkMail(user_mail) == false) {

						out.print(
								"<script language='javascript'>alert('信息有误！请重新输入！');window.location.href='register_1.jsp';</script>");
					} else {
						//生成银行卡号
						String user_card = null;
						System.out.println("初始化："+user_card);
						user_card = new CreateNewCard().createNewCard();
						System.out.println("创建新卡:"+user_card);
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
						System.out.println("map中卡号:"+map.get("user_card"));
						out.print("<script language='javascript'>window.location.href='register_2.jsp';</script>");
						session.removeAttribute("token2");
						
					}
				}
			}
		} else {
			// 重复提交
			out.println(
					"<script language = 'javascript'>alert('对不起！您已经提交过信息，请勿重复提交!!!!');window.location.href='register_2.jsp';</script>");
			session.removeAttribute("user_card");
		}

	}

	/**
	 * 验证办卡人信息是否合法
	 */

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		doPost(req, resp);
	}

}  
