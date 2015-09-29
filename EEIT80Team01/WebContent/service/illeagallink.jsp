<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style>
body { padding-top: 50px; }
.navbar{ 
 	margin-bottom: 0px;
}
#alertBar{margin-bottom:0px;}
#contentPart { padding-top: 50px; }
</style>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<%@include file="/include/include" %>
<title>Invalid Connection</title>
</head>
	<header>
		<%@include file="/include/header" %>
	</header>
<body>
	<div class="alert alert-warning alert-dismissible text-center" role="alert">
	  <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	  <strong>此連結已經失效，請回<a href="${pageContext.request.contextPath}/index.jsp">首頁</a>。</strong>	  
	</div>
	<div class="container-fluid">
		<div class="row">
			<div class="col-md-12 text-center">
				<%@include file="/include/logo" %>
			</div>
			
			<div class="col-md-12 text-center">
			<br>
				<h1><a href="${pageContext.request.contextPath}/login/login.jsp">登入頁面</a></h1>
			</div>
		</div>
	</div>
</body>
</html>