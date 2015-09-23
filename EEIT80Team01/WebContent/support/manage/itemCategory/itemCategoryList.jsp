<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="item.category.model.*"%>
<%
    ItemCategoryService service= new ItemCategoryService();
    List<ItemCategoryBean> list = service.selectCategory(null);
    pageContext.setAttribute("list",list);
%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>商品分類清單</title>
</head>
<body>
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
<table>
	<tr>
		<th>商品分類編號</th>
		<th>商品分類名稱</th>
	</tr>
	<c:forEach var="item" items="${list}" >
		<tr align='center' valign='middle'>
			<td>${item.itemCategory}</td>
			<td>${item.categoryName}</td>
			
			<td>
			 	 <FORM METHOD="post" ACTION="${pageContext.request.contextPath }/support/manage/itemCategory/itemCategorySelectOne.controller">
				     <input type="submit" value="修改">
				     <input type="hidden" name="itemCategory" value="${item.itemCategory}">
				     <input type="hidden" name="categoryName" value="${item.categoryName}">
				     <input type="hidden" name="action"	value="getOne_For_Update">
				     <font color="red" size="-1"><span class="error">${error.action }</span></font>
			     </FORM>
			     
			</td>
			<td>
			  	<FORM METHOD="post" ACTION="${pageContext.request.contextPath }/support/manage/itemCategory/itemCategoryList.controller">
				    <input type="submit" value="刪除">
				    <input type="hidden" name="action"value="delete">
				    <input type="hidden" name="itemCategory" value="${item.itemCategory}">
				    <font color="red" size="-1"><span class="error">${error.action }</span></font>
			    </FORM>
			</td>
		</tr>
	</c:forEach>
</table>
</body>
</html>