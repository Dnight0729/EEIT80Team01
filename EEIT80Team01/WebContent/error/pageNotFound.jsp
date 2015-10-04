<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<%@include file="/include/include" %>
<style >
#carousel1{
	margin-bottom: 20px;
}
.div1{
	text-align: center;
	margin:0px auto;
}
.img{
	width:600px;
	height:360px;
}
</style>
<title>HTTP404:Page Not Found</title>
</head>
<body>
<header>
<%@include file="/include/header" %>
</header>
<article>

<div class="div1">
<img class="img" src="${pageContext.request.contextPath}/imgs/pageNotFound.jpg">
<h1>找不到網頁，請確認網址連結是否合法</h1>
</div>

</article>

</body>
</html>