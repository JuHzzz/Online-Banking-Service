<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>转账汇款</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>转账</title>
<style type="text/css">
* {
	margin: 0;
	padding: 0
}

.box {
	width: 100%;
	min-height: 700px;
	margin: 0 auto;
	background-size: cover;
	display: flex;
	justify-content: center;
	align-items: center;
}

.form {
	width: 380px;
	height: 400px;
	position: absolute;
	left: 70px;
}

.span {
	margin-top: 16px;
	display: inline-block;
	width: 120px;
	font-size: 18px;
	text-align: Justify;
	margin-right: 15px;
	/* color:#fff; */
}

input {
	width: 220px;
	height: 30px;
	margin-top: 15px;
}

.sex-box {
	width: 170px;
	height: 15px;
}

.submit {
	width: 100px;
	height: 50px;
	float: right;
	margin-top: 20px;
	background-color: #5cb85c;
	border-radius: 10px;
	color: #fff;
}

.sex {
	width: 15px;
	height: 15px;
}

body {
	background: url('img/pencil.jpg') repeat;
	-moz-background-size: 100% 100%;
	background-size: 100% 100%;
}

.check {
	width: 50px;
	height: 20px;
}
</style>
<!-- jQuery -->
<script type="text/javascript" src="js/jquery-3.3.1.js"></script>

<!--Dom 对象  -->
<script type="text/javascript">
	function disable() {
		document.getElementById("submit").disabled = true
	}
	function enable() {
		document.getElementById("submit").disabled = false
	}
</script>

</head>
<body>
	<%
	String tokenValue = new Date().getTime()+"";
	session.setAttribute("token5", tokenValue);
%>
	<div>
		<h1 align="left" style="color: red">转账汇款</h1>
		<HR style="FILTER: alpha(opacity = 100, finishopacity = 0, style = 3)"
			width="100%" color=#987cb9 SIZE=3>
	</div>

	<div class="box">
		<form action="transfer" class="form" method="post">

			<p style="color: gray;">请输入您的付款账号</p>
			<!-- 判断是否存在此账号以及格式是否正确 -->
			<span class="span">付款卡号:</span><input type="text"
				name="user_pay_card"> <span class="span">转账金额:</span><input
				type="text" name="amount">
			<p style="color: gray;">请输入收款账号</p>
			<!-- 判断是否存在此账号以及格式是否正确 -->
			<span class="span">收款卡号:</span><input type="text"
				name="user_get_card"> <span class="span">收款人:</span><input
				type="text" name="user_get_name"> <span class="span">收款人手机号:</span><input
				type="text" name="user_get_phone"> <br> <br> <br>
			<p style="color: red;">同意此协议：</p>
			<input type="checkbox" name="user_check" class="check"
				onclick="if (this.checked) {enable()} else {disable()}" id="cb">
			请确认您已仔细阅读了本服务条款，接受本站服务条款全部内容，成为本站的正式用户。用户在享受本站服务时必须完全、严格遵守本服务条款。

			本服务条款的所有解释权归本站所有。 <input type="submit" value="下一步" class="submit"
				id="submit" disabled="disabled"> <input type="hidden"
				name="tokenValue" value="<%=tokenValue%>">

		</form>
	</div>

</body>
</html>