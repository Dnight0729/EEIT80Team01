<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<%@include file="/include/include" %>
<title>Forget Password</title>
<style>
.container-fluid{margin-top:150px;}
.row{margin-bottom:50px;}
#logo{width:90px;height:90px;}
</style>
</head>
<body>

	<c:set var="funcName" value="LOG" scope="session"/>
	
	<c:if test="${!empty message }">	
	<div class="alert alert-success alert-dismissible text-center" role="alert">
	  <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	  <strong>${message}</strong>
	  <c:remove var="message" scope="session" />
	</div>
	</c:if>
	<div class="container-fluid">
		<div class="row">
			<div class="col-md-12 text-center">
				<%@include file="/include/logo" %>
			</div>
		</div>
		<div class="row">
			<div class="col-md-12 text-center">
				<form method="post" class="cmxform form-inline" id="form" action="forgetpassword">
				  <div class="form-group">
				    <label for="username">帳號</label>
				    <input type="text" class="form-control" id="username" name="username" placeholder="請輸入帳號">
				  </div>
				  <div class="form-group">
				    <label for="email">E-mail</label>
				    <input type="text" class="form-control" id="email" name="email" placeholder="請輸入E-mail信箱">
				  </div>
				  <button type="submit" class="btn btn-default">送出</button>
				  <button type="reset" class="btn btn-default">清除</button>
				</form>
			</div>
		</div>
		</div>
		<div class="row">
			<div class="col-md-12 text-center">
				<a href="${pageContext.request.contextPath}/login/login.jsp">登入</a><br><br>
				<a href="${pageContext.request.contextPath}/register/register.jsp">註冊帳號</a><br><br>
				<a href="${pageContext.request.contextPath}">回到首頁</a>
			</div>
		</div>
	</div>
	
	<script type="text/javascript" src=""></script>	
</body>
</html>



