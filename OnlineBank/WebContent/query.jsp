<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>账户查询</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>查询</title>
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
	height: 370px;
	position: absolute;
	left: 70px;
}

.span {
	margin-top: 16px;
	display: inline-block;
	width: 100px;
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
<script type="text/javascript">
	function disable() {
		document.getElementById("accept").disabled = true
	}
	function enable() {
		document.getElementById("accept").disabled = false
	}
</script>


</head>
<body>

<%
	String tokenValue = new Date().getTime()+"";
	session.setAttribute("token4", tokenValue);
%>

	<div>
		<h1 align="left" style="color: red">账户查询</h1>
		<HR style="FILTER: alpha(opacity = 100, finishopacity = 0, style = 3)"
			width="100%" color=#987cb9 SIZE=3>
	</div>

	<div class="box">
		<form action="query" class="form" id="formER" method = "post">

			<p style="color: gray;">请输入您在我行办理的19位银行卡号！</p>
			<span class="span">卡号:</span><input type="text" name="user_card">
			<p style="color: gray;">请输入您的密码 密码格式为长度6位的纯数字</p>
			<span class="span">密码:</span><input type="password"
				name="user_password">  <br> <br>
			<br>
			<p style="color: red;">同意此协议：</p>
			<input type="checkbox" class="check"
				onclick="if (this.checked) {enable()} else {disable()}">
			请确认您已仔细阅读了本服务条款，接受本站服务条款全部内容，成为本站的正式用户。用户在享受本站服务时必须完全、严格遵守本服务条款。

			本服务条款的所有解释权归本站所有。 <input type="submit" value="提交" class="submit"
				id="accept" disabled="disabled">
				<input type = "hidden" name="tokenValue" value="<%=tokenValue %>" >
		</form>
	</div>

</body>
</html>