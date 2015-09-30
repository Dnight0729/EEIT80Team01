<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<%@include file="/include/include" %>
<title>Don't worry! it will be OK!</title>
<style>
	body{
		background-image: url(${pageContext.request.contextPath}/imgs/500.jpg);
    	background-size: 700px 600px;
   		background-repeat: no-repeat;
	}
</style>
</head>
<body>
<header></header>
<article>
	<table class="table table-bordered">
		<thead>
			<tr>
				<th>Servlet Name</th>
				<th>Exception Name</th>
				<th>Request URI</th>
				<th>Exception Message</th>
			</tr>	
		</thead>
		<tbody>
			<tr>
				<td>${servletName}</td>
				<td>${exceptionName}</td>
				<td>${requestUri}</td>
				<td>${exceptionMsg}</td>
			</tr>
		</tbody>
	</table>
</article>
</body>
</html>