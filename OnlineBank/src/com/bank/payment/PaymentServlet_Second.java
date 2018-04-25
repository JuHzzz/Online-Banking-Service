package com.bank.payment;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bank.log.Log;
import com.bank.servlet_query.CheckPasswordIsRight;
import com.bank.transfer.CheckBalance;
import com.bank.transfer.CheckCard;

public class PaymentServlet_Second extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static int num = 0;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		String user_card = request.getParameter("user_card");// ֧������
		String user_password = request.getParameter("user_password");// ֧������
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		Map<String, String> map = new HashMap<String, String>();
		String count = session.getAttribute("count").toString();//ת�˽��
		double amount = Double.parseDouble(count);
		String phone = session.getAttribute("phone").toString();//��ֵ����
		// �����������
		if (num < 3) {
			// ��֤���������Ƿ�Ϊ��
			if ("".equals(user_card) || "".equals(user_password)) {
				out.print(
						"<script language='javascript'>alert('�������벻��Ϊ�����������룡');window.location.href='payment_2.jsp';</script>");
			} else {
				// ��֤�����Ƿ����
				if (CheckCard.checkCard(user_card) == false) {
					out.print(
							"<script language='javascript'>alert('���Ų����ڻ�Ǳ��п��ţ����������룡');window.location.href='payment_2.jsp';</script>");
				} else {
					// ���Ŵ���,��֤����
					if (CheckPasswordIsRight.checkPassword(user_card, user_password) == false) {
						num++;
						out.println(
								"<script language = 'javascript'>alert('��������������������룡');window.location.href = 'payment_2.jsp'</script>");
						// ����������ȷ���������Ƿ����
					} else {
						// ����
						if (CheckBalance.checkBalance(count, user_card) == false) {
							out.println(
									"<script language = 'javascript'>alert('�˻�����!');window.location.href = 'payment_1.jsp'</script>");
							//������
						} else {
							//��ʼת��ǰ����д��־
							map.put("pay_card", user_card);	//�����
							map.put("get_card", phone);    //�տ��
							map.put("get_name", "���ѳ�ֵ");	//�տ�����
							map.put("balance_amount", count);
							Log.isLogIn(map);
							//ִ�д洢����top
							if(Top.isSuccess(user_card, phone, amount)==false){
								//���������ڲ�����
								out.println(
										"<script language = 'javascript'>alert('ϵͳ����');window.location.href = 'index.html'</script>");
								session.invalidate();
							}else{
								//ת�˳ɹ�
								out.println(
										"<script language = 'javascript'>alert('��ֵ�ɹ�');window.location.href = 'index.html'</script>");
								session.invalidate();
						
							}
							
						}
					}
				}
			}
		} else {
			// ������������������3�Σ��˳���ֵ
			out.println(
					"<script language = 'javascript'>alert('Password input error 3 times!  You can not transfer the money today.');window.location.href = 'index.html'</script>");
			session.invalidate();
		}

	}

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
}
