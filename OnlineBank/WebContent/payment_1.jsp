<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>充值缴费</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>缴费</title>
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
.radio{
	width: 100px;
	height: 40px;
	
}
</style>
<script type="text/javascript" src = "js/jquery-3.3.1.js"></script>
<script type="text/javascript">
	
	$(function(){
		$("#1").click(function(){
			$("#count").val($("#1").val());
		})
		$("#2").click(function(){
			$("#count").val($("#2").val());
		})
		$("#3").click(function(){
			$("#count").val($("#3").val());
		})
		$("#4").click(function(){
			$("#count").val($("#4").val());
		})
		$("#5").click(function(){
			$("#count").val($("#5").val());
		})
		$("#6").click(function(){
			$("#count").val($("#6").val());
		})
		
		
	})

</script>


</head>
<body>

<%
	String tokenValue = new Date().getTime()+"";
	session.setAttribute("token7", tokenValue);
%>

	<div>
		<h1 align="left" style="color: red">充值中心</h1>
		<HR style="FILTER: alpha(opacity = 100, finishopacity = 0, style = 3)"
			width="100%" color=#987cb9 SIZE=3>
	</div>

	<div class="box">
		<form action="payment1" class="form" id="formER" method = "post">
			<p style="color: gray;">请输入您要充值的手机号码</p>
			<span class="span">手机号:</span><input  type="text" name="phone">
			<p style="color: gray;">请选择或输入您的充值金额</p>
			<span class="span">金额:</span><input type="text"
				name="count" id="count" >  
			
			<br><br>
			<input class = "radio" type = "button" name="count" value='500' id = "1">
			<input class = "radio" type = "button" name="count" value='200' id = "2">
			<input class = "radio" type = "button" name="count" value='100' id = "3">
			<input class = "radio" type = "button" name="count" value='50' id = "4">
			<input class = "radio" type = "button" name="count" value='30' id = "5">
			<input class = "radio" type = "button" name="count" value='10' id = "6">
				<br> <br>
				
			<br>
		 <input type="submit" value="提交" class="submit"
				id="accept" >
				<input type = "hidden" name="tokenValue" value="<%=tokenValue %>" >
		</form>
	</div>

</body>
</html>