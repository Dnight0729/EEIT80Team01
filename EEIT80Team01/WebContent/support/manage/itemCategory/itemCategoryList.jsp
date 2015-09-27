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
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="/include/include"%>
<style>
.navbar {
	margin-bottom: 0px;
}

#carousel1 {
	margin-bottom: 20px;
}

body {
	padding-top: 50px;
}
.table{
	border: 1px solid #E3E3E3;
}
</style>
<title>商品分類清單</title>
</head>
<body>
<header><%@include file="/include/header-support"%></header>
	<article>
	<c:if test="${!empty Success}">		
		<div id="alertBar"class="alert alert-success alert-dismissible text-center" role="alert">
		  <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		  <strong>${Success}</strong>
		  <c:remove var="Success" scope="session" />
		</div>
	</c:if>
	<c:if test="${!empty Failure}">		
		<div id="alertBar"class="alert alert-danger alert-dismissible text-center" role="alert">
		  <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		  <strong>${Failure}</strong>
		  <c:remove var="Failure" scope="session" />
		</div>
	</c:if>
		<div class="container-fluid">
			<div class="row">
				<div class="col-md-3">
					<%@include file="/support/toolbar"%>
				</div>
				<div class="col-md-9">
<br>				
<a class="btn btn-default" href="${pageContext.request.contextPath}/support/manage/itemCategory/itemCategoryAdd.jsp" role="button">新增商品分類</a>
<br>				
<h3>商品分類清單:</h3>	
<table class="table table-hover">
	<tr>
		<th>商品分類編號</th>
		<th>商品分類名稱</th>
		<th></th>
		<th></th>
	</tr>
	<c:forEach var="item" items="${list}" >
		<tr align='center' valign='middle'>
			<td>${item.itemCategory}</td>
			<td>${item.categoryName}</td>
			
			<td>
			 	 <FORM METHOD="post" ACTION="${pageContext.request.contextPath }/support/manage/itemCategory/itemCategorySelectOne.controller">
				     <input type="submit" class="btn btn-default" value="修改">
				     <input type="hidden" name="itemCategory" class="btn btn-default" value="${item.itemCategory}">
				     <input type="hidden" name="categoryName" value="${item.categoryName}">
				     <input type="hidden" name="action"	value="getOne_For_Update">
				     <font color="red" size="-1"><span class="error">${error.action }</span></font>
			     </FORM>
			     
			</td>
			<td>
			  	<FORM METHOD="post" ACTION="${pageContext.request.contextPath }/support/manage/itemCategory/itemCategoryList.controller">
				    <input type="submit" class="btn btn-default" value="刪除">
				    <input type="hidden" name="action"value="delete">
				    <input type="hidden" name="itemCategory" value="${item.itemCategory}">
				    <font color="red" size="-1"><span class="error">${error.action }</span></font>
			    </FORM>
			</td>
		</tr>
	</c:forEach>
</table>
</div>
</div>
</div>
</article>
</body>
</html>