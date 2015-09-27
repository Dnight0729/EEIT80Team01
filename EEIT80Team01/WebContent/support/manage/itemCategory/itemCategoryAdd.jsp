<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
<title>新增商品分類</title>
<script type="text/javascript">
function clearForm(){
	var inputs = document.getElementsByTagName("input");
	for(var i=0; i<=inputs.length; i++){
		if(inputs[i].type == "text"){
			inputs[i].value="";
		}
	}
}
</script>
</head>
<body>
<header><%@include file="/include/header-support"%></header>
	<article>
		<div class="container-fluid">
			<div class="row">
				<div class="col-md-3">
					<%@include file="/support/toolbar"%>
				</div>
				<div class="col-md-9">
<br>				
<a class="btn btn-default" href="${pageContext.request.contextPath}/support/manage/itemCategory/itemCategoryList.jsp" role="button">回商品分類</a>
<br>
<h3>新增商品分類</h3>
<form method="post" action="${pageContext.request.contextPath }/support/manage/itemCategory/itemCategoryAdd.controller">
	<table class="table table-hover">
		<tr>
			<td>商品分類編號</td>
			<td><input type="text" name="itemCategory" class="form-control" value="${param.itemCategory }"></td>
			<td><font color="red" size="-1"><span class="error">${error.itemCategoryError }</span></font></td>
		</tr>
		<tr>
			<td>商品分類名稱</td>
			<td><input type="text" name="categoryName" class="form-control" value="${param.categoryName }"></td>
			<td><font color="red" size="-1"><span class="error">${error.categoryNameError }</span></font></td>
		</tr>
	</table>
	<input type="submit" class="btn btn-default" value="新增">
	<input type="hidden" name="categoryButton" class="btn btn-default" value="Insert">
	<input type="button" value="Clear" class="btn btn-default" onclick="clearForm()">
<h3><font color="red" size="-1"><span class="error" >${error.action }</span></font></h3>
</form>
</div>
</div>
</div>
</article>
</body>
</html>