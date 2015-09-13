<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>發送訊息</title>
<%@include file="/css/cssforvalidate.file" %>
<style type="text/css">
	form{
		width:75%;
	}
	.tag{ 
		display: inline-block;
		width: 115px;
		text-align: right;
		}
</style>
<script src="http://cdn.ckeditor.com/4.5.3/basic/ckeditor.js"></script>
</head>
<body>
	<%@include file="head/link.file" %>
	<form action="newmessage" method="post">
		<fieldset>
			<legend>發送訊息</legend>
			<label class="tag">收件者帳號：</label><input type="text" name="receiver" value='${param.id}'>
			 &nbsp;<small><Font color='red' size="-3">${ErrorMsgKey.receiver}</Font></small><br>
			<label class="tag">信件主旨：</label><input type="text" name="messagetitle">
			 &nbsp;<small><Font color='red' size="-3">${ErrorMsgKey.title}</Font></small><br>
       		<textarea name="messagebody" id="editor1" rows="10" cols="60">
            </textarea>
             &nbsp;<small><Font color='red' size="-3">${ErrorMsgKey.body}</Font></small>
			<script>
				CKEDITOR.replace( 'editor1' );
			</script>
			<label class="tag"></label>
			<input type="submit" id="submit" value="送出">
			<input type="reset" id="reset" value="清除">
		</fieldset>
	</form>
	&nbsp;<small><Font color='red' size="-3">${successMessage}</Font></small>
	&nbsp;<small><Font color='red' size="-3">${errorMessage}</Font></small>
	
</body>
</html>