<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="/include/include" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>檢舉會員</title>
	<style>
		fieldset{
			width: 650px;
		}
		.tag{ 
			display: inline-block;
		 	width: 125px;
		 	text-align: right;
		 	}
	</style>
	<script src="http://cdn.ckeditor.com/4.5.3/basic/ckeditor.js"></script>
</head>
<body>
	<header>
		<%@include file="/include/header" %>
	</header>
	<div>
		<form method="post" class="cmxform" id="form" action="report">
		<fieldset>
			<legend>檢舉會員</legend>
			<p>
				<label class="tag">被檢舉人帳號：</label>
				<input type="text" id="username" name="username" value='${param.id}'>
				${ErrorMsgKey.username}
			</p>
			<p>
				<label class="tag">網址連結：</label>
				<input type="text" id="url" name="url">
				${ErrorMsgKey.url}
			</p>
			<p>
				<label class="tag">檢舉理由：</label>
				<input type="text" id="reason" name="reason"><br>
				${ErrorMsgKey.reason}
			<p>
             <label class="tag"></label>
			<input type="submit" id="submit" value="送出">
			<input type="reset" id="reset" value="清除">
		</fieldset>	
	</form>
	${success}
</div>


</body>
</html>