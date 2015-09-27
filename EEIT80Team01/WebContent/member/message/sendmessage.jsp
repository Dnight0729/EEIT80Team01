<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>發送訊息</title>
<%@include file="/include/include" %>
<%@include file="/include/datatables.file" %>
<style type="text/css">
	form{
		width:100%;
	}
	.tag{ 
		display: inline-block;
		width: 115px;
		text-align: right;
		}

	.navbar{ 
 		margin-bottom: 0px;
	}
body { padding-top: 50px; }
#contentPart { padding-top: 50px; }

</style>
<script src="http://cdn.ckeditor.com/4.5.3/basic/ckeditor.js"></script>
</head>
<body>
	<header>
		<%@include file="/include/header" %>
	</header>
<article>
	<div class="container-fluid">
	      <div class="row">
			<%@include file="/include/navPart" %>
			<div class="col-md-7 main" id="contentPart">
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
			 <div class="form-group">
			 <div class="col-sm-offset-2 col-sm-10">
			<input type="submit" id="submit" value="送出" class="btn btn-default">
			<input type="reset" id="reset" value="清除" class="btn btn-default">						
			</div>
			</div>
		</fieldset>
	</form> 

	&nbsp;<small><Font color='red' size="-3">${successMessage}</Font></small>
	&nbsp;<small><Font color='red' size="-3">${errorMessage}</Font></small>
	</div>
		<%@include file="/include/blockPart" %>
	</div>
	</div>
	</article>
</body>
<script>
$("#sectionItem6").addClass("active");
</script>
</html>