+<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="item.category.model.*"%>
<%@ page import="java.util.List" %>

<%
ItemCategoryService service = new ItemCategoryService();
List<ItemCategoryBean> list = service.selectCategory(null);
pageContext.setAttribute("list", list);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>商品上架</title>
<%@include file="/include/include" %>

<link href="jquery-ui-timepicker-addon.css" rel="stylesheet"></link>
<script src="jquery-ui-timepicker-addon.js" type="text/javascript"></script>
<script src="jquery-ui-sliderAccess.js" type="text/javascript"></script>

<style type="text/css"></style>
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
<h3>商品頁面</h3>
<form action="${pageContext.request.contextPath }/items/itemAdd.controller" method="post" enctype="multipart/form-data"> 
	<table>
		<tr>
			<td>商品分類</td>
			<td>
				<select name="itemCategory">
				<option selected="selected" value="">請選擇商品分類
				<c:forEach var="list" items="${list }">
					<option value="${list.itemCategory}">${list.categoryName}
				</c:forEach>
				</select>
			</td>
			<td><font color="red" size="-1"><span class="error">${error.itemCategoryError }</span></font></td>
		</tr>
		<tr>
			<td>商品名稱</td>
			<td><input type="text" name="title" value="${param.title }"></td>
			<td><font color="red" size="-1"><span class="error">${error.titleError }</span></font></td>
		</tr>
		<tr>
			<td>起標價格</td>
			<td><input type="text" name="startPrice" value="${param.startPrice }"></td>
			<td><font color="red" size="-1"><span class="error">${error.startPriceError }</span></font></td>
		</tr>
		<tr>
			<td>直購價</td>
			<td><input type="text" name="directPrice" value="${param.directPrice }"></td>
			<td><font color="red" size="-1"><span class="error">${error.directPriceError }</span></font></td>
		</tr>
		<tr>
			<td>加價金額</td>
			<td><input type="text" name="bid" value="${param.bid }"></td>
			<td><font color="red" size="-1"><span class="error">${error.bidError }</span></font></td>
		</tr>
		<tr>
			<td>結標時間</td>
			<td><input id="dateTime" type="text" name="endTime" value="${param.endTime }" readonly="readonly"></td>
			<td><font color="red" size="-1"><span class="error">${error.endTimeError }</span></font></td>
		</tr>
		<tr>
			<td>商品圖片</td>
			<td><input type="file" name="image1"></td>
			<td><input type="file" name="image2"></td>
			<td><input type="file" name="image3"></td>
		</tr>
	</table>
	<script>
    $(document).ready(function(){ 
    	var opt={dateFormat: 'yy-mm-dd',
    			timeFormat: 'HH:mm',
                showSecond: false,
                showMinute: false,
                showTime:	false,
                showButtonPanel: false,
                hourText:"請選擇幾點結標",
                controlType:"select" ,
                prevText:"上月",
                nextText:"下月",
                dayNamesMin:["日","一","二","三","四","五","六"],
                monthNames:["一月","二月","三月","四月","五月","六月","七月","八月","九月","十月","十一月","十二月"]
                };
      	$('#dateTime').datetimepicker(opt);
      	});
  </script>
	<div>
		<p>商品描述</p>
		<textarea name="itemDescribe" cols="50" rows="5"></textarea>
	</div>
	<input type="submit" value="新增商品">
	<input type="hidden" name="itemsButton" value="Insert">
	<input type="button" value="Clear" onclick="clearForm()">
	<h3><font color="red" size="-1"><span class="error" >${error.loginError }</span></font></h3>
	<h3><font color="red" size="-1"><span class="error" >${error.action }</span></font></h3>

</form>
</body>
</html>