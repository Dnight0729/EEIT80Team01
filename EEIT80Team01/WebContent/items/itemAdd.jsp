<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>商品上架</title>
<%@include file="/include/include" %>

<link href="jquery-ui-timepicker-addon.css" rel="stylesheet"></link>
<script src="jquery-ui-timepicker-addon.js" type="text/javascript"></script>
<script src="jquery-ui-sliderAccess.js" type="text/javascript"></script>

<style type="text/css">
.goCenter{
text-align: center;
}
</style>
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
<header>
<%@include file="/include/header" %>
</header>
<article>
<h2 align="center">新增我的商品</h2>
<hr><br>
<div align="center">
<form action="${pageContext.request.contextPath }/items/itemAdd.controller" method="post" enctype="multipart/form-data" role="form" class="horizontal"> 
	<div class="form-group">
	<label for="inputCategory" class="col-sm-2 control-label">商品分類</label>
		<div class="row">
			<div class="col-sm-3">
				<select name="itemCategory" class="form-control" id="inputCategory">
					<option selected="selected" value="" >請選擇商品分類
					<c:forEach var="list" items="${list }">
						<option value="${list.itemCategory}">${list.categoryName}
					</c:forEach>
				</select>
			</div>
			<font color="red" size="-1"><span class="error">${error.itemCategoryError }</span></font>
		</div>	
	</div>
	<div class="form-group">
	<label for="inputTitle" class="col-sm-2 control-label">商品名稱</label>
		<div class="row">
			<div class="col-sm-3" >
				<input type="text" name="title" class="form-control" id="inputTitle" placeholder="請輸入商品名稱" value="${param.title }">
			</div>
			<font color="red" size="-1"><span class="error">${error.titleError }</span></font>
		</div>		
	</div>
	<div class="form-group">
	<label for="inputStartPrice" class="col-sm-2 control-label">起標價格</label>
		<div class="row">
			<div class="col-sm-3" >
				<input type="text" name="startPrice" class="form-control" id="inputStartPrice" placeholder="請輸入起標價格" value="${param.startPrice }">
			</div>
			<font color="red" size="-1"><span class="error">${error.startPriceError }</span></font>
		</div>		
	</div>
	<div class="form-group">
	<label for="inputDirectPrice" class="col-sm-2 control-label">直購價格</label>
		<div class="row">
			<div class="col-sm-3" >
				<input type="text" name="directPrice" class="form-control" id="inputDirectPrice" placeholder="請輸入直購價格" value="${param.directPrice }">
			</div>
			<font color="red" size="-1"><span class="error">${error.directPriceError }</span></font>
		</div>		
	</div>
	<div class="form-group">
	<label for="inputBid" class="col-sm-2 control-label">加價金額</label>
		<div class="row">
			<div class="col-sm-3" >
				<input type="text" name="bid" class="form-control" id="inputBid" placeholder="請輸入加價金額" value="${param.bid }">
			</div>
			<font color="red" size="-1"><span class="error">${error.bidError }</span></font>
		</div>		
	</div>
	<div class="form-group">
	<label class="col-sm-2 control-label">結標時間</label>
		<div class="row">
			<div class="col-sm-3" >
				<input type="text" name="endTime" class="form-control" id="dateTime"   placeholder="請點擊選擇時間" value="${param.endTime }" readonly="readonly">
			</div>
			<font color="red" size="-1"><span class="error">${error.endTimeError }</span></font>
		</div>		
	</div>
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
 	<br>
 	<div class="form-group">
	<label for="inputPic" class="col-sm-2 control-label">商品圖片</label>
		<div class="row">
			<div class="col-sm-3" >
				<input type="file" name="image1" id="inputPic">
				<input type="file" name="image2" id="inputPic">
				<input type="file" name="image3" id="inputPic">
			</div>
		</div>		
	</div>
	<div class="form-group">
		<label for="inputPic" class="col-sm-2 control-label">商品描述</label>
			<div class="row">
				<div class="col-sm-5" >
					<textarea name="itemDescribe" rows="3" class="form-control"></textarea>
				</div>
			</div>		
	</div>
	<input type="submit" value="新增商品">
	<input type="hidden" name="itemsButton" value="Insert">
	<input type="button" value="Clear" onclick="clearForm()">
	<h3><font color="red" size="-1"><span class="error" >${error.loginError }</span></font></h3>
	<h3><font color="red" size="-1"><span class="error" >${error.action }</span></font></h3>
</form>
</div>
</article>
<footer>
</footer>
</body>
</html>