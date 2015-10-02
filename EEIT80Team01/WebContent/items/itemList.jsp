<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<%@include file="/include/include" %>
<title>我的拍賣小店</title>
<style>
.page-header{
	text-align:center;
}
</style>

</head>
<body>
<header>
<%@include file="/include/header" %>
</header>
<article>
<div class="container-fluid">
	<div class="row page-header">
		<h2>我的拍賣小店</h2>
	</div>
	<div class="row">
		<div class="col-md-offset-1 col-md-10" style="background-color:#FCFCFC">
			<!-- Nav tabs -->
		   <ul class="nav nav-tabs nav-justified" role="tablist" id="myTabs">
		    <li role="presentation" class="active"><a href="#itemSell" role="tab" data-toggle="tab">出售中商品</a></li>
		    <li role="presentation"><a href="#itemUnsell" role="tab" data-toggle="tab">已下架商品</a></li>
		    <li role="presentation"><a href="#itemSold" role="tab" data-toggle="tab">已售出商品記錄</a></li>
		  	<li role="presentation">
				<a  href="${pageContext.request.contextPath}/items/itemAdd.jsp" role="button">新增我的商品</a>
			</li>
		  </ul>
		  
			<!-- Tab panes -->
			<div class="tab-content">
		    <div role="tabpanel" class="tab-pane fade in active" id="itemSell">
				<table class="table table-hover">
					<thead>
						<tr>
							<th>商品圖片</th>
							<th>商品名稱</th>
							<th>最高出價金額</th>
							<th>最高出價者</th>
							<th>出價人數</th>
							<th>結標時間</th>
						</tr>
					</thead>
					<tbody>
					<c:forEach var="item" items="${listPac }">
						<tr>
							<td>
								<c:if test="${!empty item.image}">
									<img height="80" width="80" src="${pageContext.request.contextPath}/search/showImage?imageNo=${item.image}">
								</c:if>
								<c:if test="${empty item.image}">
									<img height="80" width="80" src="${pageContext.request.contextPath}/search/showImage">
								</c:if>
							</td>
							<td>${item.itemsBean.title }</td>
							<td>${item.price}</td>
							<td>${item.buyer}</td>
							<td>${item.count }</td>
							<td>${item.itemsBean.endTime }</td>
							<td>
								<FORM METHOD="post" ACTION="${pageContext.request.contextPath }/items/itemSelectOne.controller">
								    <input type="submit" value="修改商品">
								    <input type="hidden" name="itemId" value="${item.itemsBean.itemId}">
									<input type="hidden" name="itemCategory" value="${item.itemsBean.itemCategory}">
									<input type="hidden" name="title" value="${item.itemsBean.title}">
									<input type="hidden" name="startPrice" value="${item.itemsBean.startPrice}">
									<input type="hidden" name="directPrice" value="${item.itemsBean.directPrice}">
									<input type="hidden" name="bid" value="${item.itemsBean.bid}">
									<input type="hidden" name="endTime" value="${item.itemsBean.endTime}">
									<input type="hidden" name="itemDescribe" value="${item.itemsBean.itemDescribe}">
								    <input type="hidden" name="action"	value="getOne_For_Update">
							     </FORM>
							</td>
							<td>
							     <form method="post" action="${pageContext.request.contextPath }/items/itemDown.controller">
										<input type="submit" value="商品下架">
									    <input type="hidden" name="DownBtn" value="${item.itemsBean.itemId }">
									    <input type="hidden" name="action"value="itemDown">
								</form>
							</td>
					</c:forEach>
					</tbody>
				</table>
			</div>
			
			<div role="tabpanel" class="tab-pane fade" id="itemUnsell">
				<table class="table table-hover">
					<thead>
						<tr>
							<th>商品圖片</th>
							<th>商品名稱</th>
							<th>最高出價金額</th>
							<th>最高出價者</th>
							<th>出價人數</th>
							<th>結標時間</th>
						</tr>
					</thead>
					<tbody>
					<c:forEach var="item" items="${listDownPac }">
						<tr>
							<td>
								<c:if test="${!empty item.image}">
									<img height="80" width="80" src="${pageContext.request.contextPath}/search/showImage?imageNo=${item.image}">
								</c:if>
								<c:if test="${empty item.image}">
									<img height="80" width="80" src="${pageContext.request.contextPath}/search/showImage">
								</c:if>
							</td>
							<td>${item.itemsBean.title }</td>
							<td>${item.price}</td>
							<td>${item.buyer}</td>
							<td>${item.count }</td>
							<td>${item.itemsBean.endTime }</td>
							<td>
								<FORM METHOD="post" ACTION="${pageContext.request.contextPath }/items/itemDel.controller" id="delItem">
								    <input type="button" class="DelBtn" value="移除商品">
								    <input type="hidden" name="DelBtn" value="${item.itemsBean.itemId }">
								    <input type="hidden" name="action" value="itemDel">
							     </FORM>
							</td>
							<td>
							    <form method="post" action="${pageContext.request.contextPath }/items/itemUp.controller">
										<input type="submit" value="商品上架">
									    <input type="hidden" name="UpBtn" value="${item.itemsBean.itemId }">
									    <input type="hidden" name="action"value="itemUp">
								</form>
							</td>
					</c:forEach>
					</tbody>
				</table>
			</div>
			
			<div role="tabpanel" class="tab-pane fade" id="itemSold">
				<table class="table table-hover">
					<thead>
						<tr>
							<th>商品圖片</th>
							<th>商品名稱</th>
							<th>最高出價金額</th>
							<th>最高出價者</th>
							<th>出價人數</th>
							<th>結標時間</th>
						</tr>
					</thead>
					<tbody>
					<c:forEach var="item" items="${listSoldPac }">
						<tr>
							<td>
								<c:if test="${!empty item.image}">
									<img height="80" width="80" src="${pageContext.request.contextPath}/search/showImage?imageNo=${item.image}">
								</c:if>
								<c:if test="${empty item.image}">
									<img height="80" width="80" src="${pageContext.request.contextPath}/search/showImage">
								</c:if>
							</td>
							<td>${item.itemsBean.title }</td>
							<td>${item.price}</td>
							<td>${item.buyer}</td>
							<td>${item.count }</td>
							<td>${item.itemsBean.endTime }</td>
					</c:forEach>
					</tbody>
				</table>
			</div>
			
			</div>
		</div><!-- col-md-offset-1 col-md-10 -->
	</div><!-- row -->
</div><!-- container-fluid -->
<div id="dialog" title="移除商品">
<h3>是否要移除商品?</h3>
</div>
<script>
var findValue;

$("#dialog").dialog({
	autoOpen: false,
	modal: true,
	closeOnEscape: false,//按ESC不能關閉
	open: function(event,ui){
		$(this).parent().children().children('.ui-dialog-titlebar-close').hide();	//隱藏「x」關閉按鈕
	},
	buttons:{
		"確定":function(){
			findValue.submit();
			$(this).dialog("close");
		},
		"取消":function(){
			$(this).dialog("close");
		}
	}
});

$(".DelBtn").click(function(){
	findValue = $(this).parent('form');
	$("#dialog").dialog("open");
});
</script>
</article>
<footer></footer>
</body>
</html>