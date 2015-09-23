<%@ page import="java.util.*"%>
<%@page import="item.category.model.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
// 	ItemCategoryBean bean = (ItemCategoryBean) request.getAttribute("bean"); //EmpServlet.java (Concroller), 存入req的empVO物件 (包括幫忙取出的empVO, 也包括輸入資料錯誤時的empVO物件)
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>商品分類修改</title>
<script type="text/javascript">
// function clearForm(){
// 	var inputs = document.getElementsByTagName("input");
// 	for(var i=0; i<=inputs.length; i++){
// 		if(inputs[i].type == "text"){
// 			inputs[i].value="";
// 		}
// 	}
// }
</script>
</head>
<body>
<h3>商品資料修改:</h3>
<%-- 錯誤表列 --%>
<%-- <c:if test="${not empty error}"> --%>
<!-- 	<font color='red'>請修正以下錯誤: -->
<!-- 	<ul> -->
<%-- 		<c:forEach var="message" items="${error}"> --%>
<%-- 			<li>${message}</li> --%>
<%-- 		</c:forEach> --%>
<!-- 	</ul> -->
<!-- 	</font> -->
<%-- </c:if> --%>

<FORM METHOD="post" ACTION="${pageContext.request.contextPath }/support/manage/itemCategory/itemCategoryUpdate.controller">
<table border="0">
	<tr>
		<td>商品分類編號:</td>
		<td><input type="text" name="itemCategory" value="${param.itemCategory }" readonly="readonly"></td>
		<td><font color="red" size="-1"><span class="error">${error.itemCategoryError }</span></font></td>
	</tr>
	<tr>
		<td>商品分類名稱:</td>
		<td><input type="text" name="categoryName" value="${param.categoryName }"></td>
		<td><font color="red" size="-1"><span class="error">${error.categoryNameError }</span></font></td>
	</tr>
</table>
<br>

<input type="submit" value="進行修改">
<input type="hidden" name="action" value="update">
<!-- <input type="button" value="Clear" onclick="clearForm()"> -->

<h3><font color="red" size="-1"><span class="error" >${error.action }</span></font></h3>
</FORM>

</body>
</html>