<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<%@include file="/include/include" %>
<title>重設密碼</title>
<style>
body{background-color:#F0F0F0;}
.container-fluid{margin-top:150px;}
.row{margin-bottom:50px;}
#logo{width:90px;height:90px;}
</style>
</head>
<body>

	<article>
	<div class="container-fluid">
		<div class="row">
			<div class="col-md-12 text-center">
				<%@include file="/include/logo" %>
			</div>
		</div>
		<div class="row">
			<div class="col-md-12 text-center">
				<form class="form-inline cmxform" method="post" action="resetPassword" id="form" >
				  <div class="form-group">
				    <label for="username">帳號</label>
				    <input type="text" class="form-control" id="username" name="username" value="${EmailChecked}" readonly>
				  </div>
				  <div class="form-group">
				    <label for="password">新密碼</label>
				    <input type="password" class="form-control" id="password" name="password" placeholder="請輸入密碼">
				  </div>
				  <div class="form-group">
				    <label for="password">確認密碼</label>
				    <input type="password" class="form-control" id="passwordCheck" name="passwordCheck" placeholder="請再次輸入密碼">
				  </div>
				  <button type="submit" class="btn btn-primary">送出</button>
				  <button type="reset" class="btn btn-default">清除</button>
				</form>
			</div>
		</div>
		<div class="row">
			<div class="col-md-12 text-center">
				<Font color='red' size="-1">${loginFalure}&nbsp;</Font><br/>
				<a href="${pageContext.request.contextPath}/register/register.jsp">註冊帳號</a>
			</div>
		</div>
	</div>
	
	<script type="text/javascript" src="login.js"></script>	
</article>
</body>
</html>




		
