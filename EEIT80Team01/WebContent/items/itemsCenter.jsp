<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>商品中心</title>
</head>
<body>
<h1>商品中心</h1>
<input type="button" value="商品上架" onclick="location.href='${pageContext.request.contextPath }/items/itemAdd.jsp'"><br><br>
<input type="button" value="商品修改" onclick="location.href='${pageContext.request.contextPath }/items/itemRevise.jsp'"><br><br>
<input type="button" value="商品移除" onclick="location.href="><br><br>
<input type="button" value="商品下架" onclick="location.href="><br><br>

</body>
</html>