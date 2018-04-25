package com.bank.transfer;

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

public class TransferServlet_Second extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static int count = 0;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		Map<String, String> map = new HashMap<String,String>();
		String card_2 = session.getAttribute("get_card").toString();//�տ��
		String card_1 = session.getAttribute("pay_card").toString();//�����
		String get_name = session.getAttribute("get_name").toString();//�տ�������
		String amount = session.getAttribute("amount").toString();
		response.setContentType("text/html;charset=utf-8");
		double amounter = Double.parseDouble(amount);// ת�˽��
		String password = request.getParameter("user_password");	//��������
		PrintWriter out = response.getWriter();
		if(count < 3){
			if(CheckPasswordIsRight.checkPassword(card_1, password)==false){
				count++;
				out.println(
						"<script language = 'javascript'>alert('�����������');window.location.href = 'transfer2.jsp'</script>");
			}else{
				//��ʼת��ǰ����д��־
				map.put("pay_card", card_1);	//�����
				map.put("get_card", card_2);    //�տ��
				map.put("get_name", get_name);	//�տ�����
				map.put("balance_amount", amount);
				Log.isLogIn(map);
				//������ȷ,ִ�д洢����
				if(Transfer.transferIsSuccess(card_2, card_1, amounter)==false){
					//���������ڲ�����
					out.println(
							"<script language = 'javascript'>alert('ϵͳ����');window.location.href = 'index.html'</script>");
					session.invalidate();
				}else{
				
					//ת�˳ɹ�
					out.println(
							"<script language = 'javascript'>alert('ת�˳ɹ���');window.location.href = 'index.html'</script>");
					session.invalidate();
				}
				
				
			}
		}else{
			//������������������3�Σ��˳�ת��
			out.println(
					"<script language = 'javascript'>alert('Password input error 3 times!  You can not transfer the money today.');window.location.href = 'index.html'</script>");
			session.invalidate();
		}
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
