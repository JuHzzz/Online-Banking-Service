<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
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
	font-size: 20px;
	text-align: Justify;
	margin-right: 15px;
	/* color:#fff; */
}

input {
	width: 250px;
	height: 30px;
	margin-top: 15px;
}

.sex-box {
	width: 170px;
	height: 15px;
}

.submit {
	width: 90px;
	height: 40px;
	float: right;
	margin-top: 15px;
	background-color: #5cb85c;
	border-radius: 10px;
	color: #fff;
}
.submit_1 {
	width: 90px;
	height: 40px;
	float: right;
	margin-top: 15px;
	background-color: teal;
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
	function returnIndex(){
		
		window.location.href = "index.html";
	}
	
	
</script>


</head>
<body>

<%
	String tokenValue = new Date().getTime()+"";
	session.setAttribute("token2", tokenValue);
%>

	<div>
		<h1 align="left" style="color: red">账户查询</h1>
		<HR style="FILTER: alpha(opacity = 100, finishopacity = 0, style = 3)"
			width="100%" color=#987cb9 SIZE=3>
	</div>

	<div class="box">
		<form action="" class="form" id="formER" method = "post">

			<span class="span">姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名:</span><input type="text" name="user_name" readonly="readonly" value = "${sessionScope.user_name} ">
			<span class="span">卡&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;号:</span><input type="text" name="user_card" readonly="readonly" value = "${sessionScope.user_card} ">
			<span class="span">余&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;额:</span><input type="text" name="user_balance" readonly="readonly" value ="${sessionScope.user_balance}">
			<br><br><br>
			
			
			<input type="button" value="返回主页" class="submit"
				id="accept" onclick = "javascript:returnIndex()">
			
			 
				<input type = "hidden" name="tokenValue" value="${tokenValue}" >
		</form>
	</div>
			
</body>
</html>