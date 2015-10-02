<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<%@include file="/include/include" %>
<title>登入</title>
<style>
body{background-color:#F0F0F0;}
.container-fluid{margin-top:150px;}
.row{margin-bottom:50px;}
#logo{width:90px;height:90px;}

</style>
</head>
<body>

	<c:if test="${!empty registerTrue }">
	<div class="alert alert-success alert-dismissible text-center" role="alert">
	  <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	  <strong>註冊成功!</strong>
	</div>
	<c:remove var="registerTrue" scope="session" />
	</c:if>
	<c:if test="${!empty Logout }">
		<c:set var="memberName" value="${ Logout.userName }" />

		<c:remove var="Logout" scope="session" />
		
		<c:set var="funcName" value="OUT" scope="session"/>
		<c:set var="logoutMessage" scope="request" />
		<div class="alert alert-success alert-dismissible text-center" role="alert">
	  		<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	  		<strong>已登出!</strong>&nbsp;點選回<a href="${pageContext.request.contextPath}/index.jsp">首頁</a>。
	  	</div>	
	</c:if>
	<c:if test="${!empty message }">	
	<div class="alert alert-success alert-dismissible text-center" role="alert">
	  <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	  <strong>${message}</strong>
	  <c:remove var="message" scope="session" />
	</div>
	</c:if>
	<c:set var="funcName" value="LOG" scope="session"/>	
	<div class="container-fluid">
		<div class="row">
			<div class="col-md-12 text-center">
				<%@include file="/include/logo" %>
			</div>
		</div>
		<div class="row">
			<div class="col-md-12 text-center">
				<form class="form-inline" method="post" action="login.do" class="cmxform" id="form" >
				  <div class="form-group">
				    <label for="username control-label">帳號</label>
				    <input type="text" class="form-control" id="username" name="username" placeholder="請輸入帳號">
				  </div>
				  <div class="form-group">
				    <label for="password control-label">密碼</label>
				    <input type="password" class="form-control" id="password" name="password"  placeholder="請輸入密碼">
				  </div>
				  <div class="form-group">
					  <button type="submit" class="btn btn-primary ">登入</button>
					  <button type="reset" class="btn btn-default ">清除</button>
				  </div>
				</form>
			</div>
		</div>
		<div class="row">
			<div class="col-md-12 text-center">
				<span>${ErrorMsgKey.LoginError}&nbsp;</span>	
			</div>
		</div>
		<div class="row">
			<div class="col-md-12 text-center">
				<a href="${pageContext.request.contextPath}/service/forgetpassword.jsp">忘記密碼</a><br><br>
				<a href="${pageContext.request.contextPath}/register/register.jsp">註冊帳號</a><br><br>
				<a href="${pageContext.request.contextPath}">回到首頁</a>
			</div>
		</div>
	</div>
	
<!-- 	<script type="text/javascript" src="login.js"></script>	 -->
</body>
</html>