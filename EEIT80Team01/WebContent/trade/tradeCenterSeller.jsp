<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<%@include file="/include/include" %>
<title>Trade Center</title>
<style>
.page-header{
	text-align:center;
}
.menu .btn{
	width:287px;
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
		<h2>交易中心</h2>
	</div>
	<div class="row">
		<div class="col-md-offset-1 col-md-10" style="background-color:#FCFCFC">
		
		  <!-- Nav tabs -->
		  <ul class="nav nav-tabs nav-justified" role="tablist" id="myTabs">
		    <li role="presentation" class="active"><a href="#uncheck" aria-controls="sellerCheck" role="tab" data-toggle="tab">待確認的交易</a></li>
		    <li role="presentation"><a href="#checked" aria-controls="sellerUncheck" role="tab" data-toggle="tab">已確認的交易</a></li>
		    <li role="presentation"><a href="#finished" aria-controls="buyerCheck" role="tab" data-toggle="tab">已完成的交易</a></li>
		    <li role="presentation dropdown">
			  <a id="toggle" data-toggle="dropdown" role="button" aria-haspopup="true">
			   	買賣家切換<span class="caret"></span>
			  </a>
			  <ul class="dropdown-menu menu" aria-labelledby="toggle">
			    	<li class="active"  href="${pageContext.request.contextPath}/trade/myTradeCenter.do"><a class="btn">我是買家</a></li>
			    	<li><a class="btn" href="#">我是賣家</a></li>
			  </ul>
			</li>
		  </ul>
			
			
		  <!-- Tab panes -->
		  <div class="tab-content">
		    <div role="tabpanel" class="tab-pane fade in active" id="uncheck">
				<table class="table table-hover">
					<thead>
						<tr>
							<th>商品編號</th>
							<th>商品圖片</th>
							<th>商品名稱</th>
							<th>買家</th>
							<th>賣家</th>
							<th>aaa</th>
							<th>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="item" items="${sessionScope.mySellItemsUncheck}">
							<tr>
								<td>${item.itemId}</td>
								<td><img src="${pageContext.request.contextPath}/search/showImage?itemid=${item.imageNo}" width="50px" height="50px"/></td>
								<td>${item.title}</td>
								<td>${item.buyer}</td>
								<td>${item.seller}</td>
								<td>
									<form action="trade.do" method="post">
										<input type="hidden" name="itemId" value="${item.itemId}">
										<input type="hidden" name="action" value="check">
										<input type="submit" value="確認">
									</form>
								</td>
								<td>
									<form action="trade.do" method="post">
										<input type="hidden" name="itemId" value="${item.itemId}">
										<input type="hidden" name="action" value="cancel">
										<input type="submit" value="取消">
									</form>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		    <div role="tabpanel" class="tab-pane fade" id="checked">
				<table class="table table-hover">
					<thead>
						<tr>
							<th>商品編號</th>
							<th>商品圖片</th>
							<th>商品名稱</th>
							<th>買家</th>
							<th>賣家</th>
							<th>交易狀態</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="item" items="${sessionScope.mySellItemsCheck}">
							<tr>
								<td>${item.itemId}</td>
								<td><img src="${pageContext.request.contextPath}/search/showImage?itemid=${item.imageNo}" width="50px" height="50px"/></td>
								<td>${item.title}</td>
								<td>${item.buyer}</td>
								<td>${item.seller}</td>
								<td>
									<span>等待對方確認交易中</span>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		    <div role="tabpanel" class="tab-pane fade" id="finished">
		    	<table class="table table-hover">
					<thead>
						<tr>
							<th>商品編號</th>
							<th>商品圖片</th>
							<th>商品名稱</th>
							<th>買家</th>
							<th>賣家</th>
							<th>交易狀態</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="item" items="${sessionScope.mySellItemsFinished}">
							<tr>
								<td>${item.tradeBean.itemId}</td>
								<td><img src="${pageContext.request.contextPath}/search/showImage?itemid=${item.imageNo}" width="50px" height="50px"/></td>
								<td>${item.title}</td>
								<td>${item.tradeBean.buyer}</td>
								<td>${item.tradeBean.seller}</td>
								<td>
									<span>交易已完成</span>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
		    </div>
		    
		  </div><!--Tab panes-->
		  
		</div>
	</div><!-- row -->
</div><!-- container-fluid -->
</article>
<footer>

</footer>
<script>

</script>
</body>
</html>