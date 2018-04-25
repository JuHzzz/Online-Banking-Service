<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>填写个人信息</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>注册_1</title>
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
	position:absolute;
	left:70px;
}

.span {
	margin-top: 16px;
	display: inline-block;
	width: 100px;
	font-size:18px;
	text-align:Justify;
	margin-right:15px;
	/* color:#fff; */
}

input{
	width:220px;
	height:30px;
	margin-top:15px;
}

.sex-box {
	width: 170px;
	height: 15px;
}

.submit {
	width: 100px;
	height: 50px;
	float:right;
	margin-top: 20px;
	background-color: #5cb85c;
	border-radius: 10px;
	color: #fff;
}
.sex{
	width:15px;
	height:15px;
}
body{
	background:url('img/pencil.jpg') repeat;
	-moz-background-size:100% 100%; background-size:100% 100%;
}
</style>
</head>
<body >

<%
	String tokenValue = new Date().getTime()+"";
	session.setAttribute("token2", tokenValue);
%>

<div>
		<h1 align="left" style="color: red">行卡办理</h1>
		<HR style="FILTER: alpha(opacity = 100, finishopacity = 0, style = 3)"
			width="100%" color=#987cb9 SIZE=3>
	</div>

	<div class="box">
		<form action="register_1" class="form" method="post">
				<span class="span">持卡人姓名:</span><input type="text" name="user_name" > 
				<span class="span">身份证号码:</span><input	type="text" name="user_id" > 
				<span class="span">联&nbsp;系&nbsp;方&nbsp;式:</span><input	type="text" name="user_phone" > 
				<span class="span">邮&nbsp;箱&nbsp;地&nbsp;址:</span><input	type="text" name="user_mail" > 
				<span class="span">性&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;别:</span> 
				<input type="radio" name="user_sex" class='sex' value="male">男&nbsp;&nbsp;&nbsp;
				<input type="radio" name="user_sex" class='sex' value ="female">女 <br>
				<input type="submit" value="下一步" class="submit" > 
				<input type = "hidden" name="tokenValue" value="<%=tokenValue %>" >
		</form>
	</div>

</body>
</html>