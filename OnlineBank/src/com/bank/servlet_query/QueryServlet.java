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
		// 是否重复提交
		if (token != null && token.equals(tokenValue)) {

			// 卡号密码是否为空
			if ("".equals(user_card) || "".equals(user_password)) {
				out.println(
						"<script language = 'javascript'>alert('卡号密码不能为空.');window.location.href = 'query.jsp'</script>");
			} else {
				// 卡号是否存在
				if (CheckCard.checkCard(user_card) == false) {
					out.println(
							"<script language = 'javascript'>alert('卡号不存在或非本行银行卡，请重新输入.');window.location.href = 'query.jsp'</script>");
				} else {
					// 卡号存在，验证密码是否正确
					if (CheckPasswordIsRight.checkPassword(user_card, user_password) == false) {
						out.println(
								"<script language = 'javascript'>alert('密码输入错误！请重新输入.');window.location.href = 'query.jsp'</script>");
					} else {
						// 卡号密码都没有问题,返回结果集,并且将卡号添加到session中
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
			// 重复提交
			out.println(
					"<script language = 'javascript'>alert('Sorry! You have submitted information! Please do not submit again!!!!');window.location.href='queryResult.jsp';</script>");
		}
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	} 
}
