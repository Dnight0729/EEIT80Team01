<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="/include/include" %>
<link href="jquery-ui-timepicker-addon.css" rel="stylesheet"></link>
<script src="jquery-ui-timepicker-addon.js" type="text/javascript"></script>
<script src="jquery-ui-sliderAccess.js" type="text/javascript"></script>
<title>商品修改</title>
<style>
	.image{
		position:relative;
		height:100px; 
		width:100px
	}
	.image img{
		position:relative;
		height:100%;
 		width:100%;
 		z-index:-100;
	}
	.myButton {
		padding:1px 5px;
		position:absolute;
 		top:0;
 		right:0;
 		z-index:100;
	}
	.imginput{
		position:relative;
 		top:45px;
	}
</style>
</head>
<body>
<header>
<%@include file="/include/header" %>
</header>

<article>
<h2 align="center">修改我的商品</h2>
<hr><br>
<div align="center">
<form action="${pageContext.request.contextPath }/items/itemUpdate.controller" method="post" enctype="multipart/form-data" role="form" class="horizontal"> 
	<!-- 商品分類能正常修改運作，但是暫無法seleted之前新增商品的分類   -->
	<div class="form-group">
	<label for="inputCategory" class="col-sm-2 control-label">商品分類</label>
		<div class="row">
			<div class="col-sm-3">
				<select name="itemCategory" class="form-control" id="inputCategory">
					<c:forEach var="list" items="${list }">
						<option value="${list.itemCategory}">${list.categoryName}
					</c:forEach>
				</select>
			</div>
			<font color="red" size="-1"><span class="error">${error.itemCategoryError }</span></font>
		</div>	
	</div>
	<script type="text/javascript">
	(function($){
		$('option:eq(${param.itemCategory-1})').attr("selected", true);
	})(jQuery)
	</script>
	<div class="form-group">
	<label for="inputTitle" class="col-sm-2 control-label">商品名稱</label>
		<div class="row">
			<div class="col-sm-3" >
				<input type="text" name="title" class="form-control" id="inputTitle" value="${param.title }">
			</div>
			<font color="red" size="-1"><span class="error">${error.titleError }</span></font>
		</div>		
	</div>
	<div class="form-group">
	<label for="inputStartPrice" class="col-sm-2 control-label">起標價格</label>
		<div class="row">
			<div class="col-sm-3" >
				<input type="text" name="startPrice" class="form-control" id="inputStartPrice" value="<fmt:formatNumber value="${ param.startPrice }" pattern="#" type="number"/>">
			</div>
			<font color="red" size="-1"><span class="error">${error.startPriceError }</span></font>
		</div>		
	</div>
	<div class="form-group">
	<label for="inputDirectPrice" class="col-sm-2 control-label">直購價格</label>
		<div class="row">
			<div class="col-sm-3" >
				<input type="text" name="directPrice" class="form-control" id="inputDirectPrice" value="<fmt:formatNumber value="${ param.directPrice }" pattern="#" type="number"/>">
			</div>
			<font color="red" size="-1"><span class="error">${error.directPriceError }</span></font>
		</div>		
	</div>
	<div class="form-group">
	<label for="inputBid" class="col-sm-2 control-label">加價金額</label>
		<div class="row">
			<div class="col-sm-3" >
				<input type="text" name="bid" class="form-control" id="inputBid" value="<fmt:formatNumber value="${ param.bid }" pattern="#" type="number"/>">
			</div>
			<font color="red" size="-1"><span class="error">${error.bidError }</span></font>
		</div>		
	</div>
	<div class="form-group">
	<label class="col-sm-2 control-label">結標時間</label>
		<div class="row">
			<div class="col-sm-3" >
				<input type="text" name="endTime" class="form-control" id="dateTime" value="${param.endTime }" readonly="readonly">
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
	
	<div class="form-group">
		<label for="inputPic" class="col-sm-2 control-label">商品描述</label>
			<div class="row">
				<div class="col-sm-5" >
					<textarea name="itemDescribe" rows="3" class="form-control" id="describe"></textarea>
					<script>
						var ta = document.getElementById('describe').value ="${param.itemDescribe}";
					</script>
				</div>
			</div>		
	</div>
	<input type="hidden" name="itemId" value="${param.itemId}">
	<input type="submit" value="進行修改">
	<input type="hidden" name="action" value="update">
	<h3><font color="red" size="-1"><span class="error" >${error.loginError }</span></font></h3>
	<h3><font color="red" size="-1"><span class="error" >${error.action }</span></font></h3>
</form>
<form method="get" action="${pageContext.request.contextPath }/items/ImageRemove.controller" enctype="multipart/form-data" role="form" class="horizontal">
	<div id="img">
	<div class="form-group">
		<label for="inputPic" class="col-sm-2 control-label">商品圖片</label>
			<div class="row">
				<div class="col-sm-3" >
				<c:forEach var="i" begin="0" end="2">
					<div id="image${i+1}" class="image">
						<c:if test="${!empty images.get(i) }">
							<img height="200" width="200" src="${pageContext.request.contextPath}/search/showImage?imageNo=${images.get(i)}">
							<input type="button" class="myButton" value="x" name="DelBtn">
							<input type="hidden" name="action"value="delImg">
						</c:if>
						<c:if test="${empty images.get(i) }">
							<div class="imginput">
								<input type="file" name="image${i+1}">
							</div>
						</c:if>
					</div>
				</c:forEach>
			</div>		
		</div>
	</div>
	</div>
</form>

</div>
</article>
<footer>
</footer>
</body>
</html>