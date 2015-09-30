<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<%@include file="/include/include" %>
<style>
.navbar{ 
 	margin-bottom: 0px;
}
body { padding-top: 50px; }
#contentPart { padding-top: 50px;
font-size:180%; }
.itemimg{
	width:300px;
}

#directPrice{
color:#FF0000;
font-size:150%;
 
}
#opener{
cursor: pointer; 
color:#0088A8;
}


#directPrice1{padding-left:60px; 
}


#itemDescribe{
border-width:1px;border-style:solid;border-color:#aaaaaa;padding:20px;
position:relative;
/*     left:25px; */
    top:0;
    width:600px;
    border-top:3px solid #aaaaaa;
word-break: break-all;
}
.font {font-weight:bolder}
</style>
<c:choose>
	<c:when test="${!empty item}">
		<title>${item.title}</title>
	
	</c:when>
	<c:otherwise>
		<title>查無此商品</title>	
	</c:otherwise>
</c:choose>
</head>
<body>
<header>
		<%@include file="/include/header" %>
</header>	
<article>
<fmt:formatNumber value="${ item.directPrice }"  var="directPrice" pattern="#" type="number"/>
		<c:if test="${!empty errorMsg }">
			<div class="alert alert-danger alert-dismissible text-center" role="alert">
			  <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
			  <strong>${errorMsg}</strong>
			</div>
			<c:remove var="errorMsg" scope="session" />
		</c:if>
		<c:if test="${!empty message }">
			<div class="alert alert-success alert-dismissible text-center" role="alert">
			  <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
			  <strong>${message}</strong>
			</div>
			<c:remove var="message" scope="session" />
		</c:if>
		<div class="container-fluid">
	      <div class="row">
			<%@include file="/include/itempage" %>
	        <div class="col-md-7 main" id="contentPart">
	        	<c:choose>
				<c:when test="${!empty item}">
				<div class="row">
				<div class="col-md-7">
				<div id="opener">賣家：${item.seller}</div>   
			<span class="font">	商品分類：</span>${itemCategory.categoryName}<br>
	        <span class="font">	商品主題：</span>${item.title}	<br>
	        		<fmt:formatNumber value="${ price }"  var="pri" pattern="#" type="number"/>
	        	<span class="font">	商品價格：</span>${pri}元<br>
	        		<fmt:formatDate value="${item.endTime}" var="formatDate" type="date" pattern="yyyy年MM月dd日HH時mm分" />	
	        	<span class="font">	結標時間：</span>${formatDate}<br>
	        	<span class="font">	最小加價：</span>${item.bid}<br><br>
				
				     
				     
 <!--=================登入後出現 ===========================-->
	        		<c:if test="${!empty LoginOK}">
	        		<c:choose>
	        		<c:when test="${item.itemStatus==0}">
	        		
	        		<c:if test="${!LoginOK.userName.equals(item.seller)}">
					<div class="col-md-12 ">
					<form action="${pageContext.request.contextPath}/product/bid.do" method="post" class="form-inline">
	        		<span id="directPrice1" class="alert alert-warning" role="alert">直購價：<span id="directPrice">${directPrice}元</span>
	        		<button type="submit" class="btn btn-primary">直購</button></span><br><BR>
	        		</div>	        		
	        			<input type="hidden" name="itemId" value="${item.itemId}">
	        			<input type="hidden" name="action" value="direct">	        				        			
	        		</form>
	        		
	        	
	        		<c:if test="${!LoginOK.userName.equals(topPrice.buyer)}">
	        		<div class="col-md-12 ">
	        		<div class="form-group col-md-5">
	        		<form action="${pageContext.request.contextPath}/product/bid.do" method="post">
	        			<input type="hidden" name="itemId" value="${item.itemId}">
	        			<input type="hidden" name="action" value="bid">
	        			 <div class="input-group">
	        			 <fmt:formatNumber value="${price + item.bid }"  var="bid" pattern="#" type="number"/>
	        			<input type="number" min="${bid}" value="${bid}" name="bidPrice" class="form-control">
	        			 <div class="input-group-btn">
	        			<button type="submit" class="btn btn-primary">出價</button>
	        			</div>
	        			</div>	        		
	        		</form>
	        		</div>
	        		</div>
	        		</c:if>
	        		<c:if test="${LoginOK.userName.equals(topPrice.buyer)}">

	        		<div class="col-md-12 ">	        		
	        			您是目前最高出價者
	        		</div>
	        		</c:if>
	        		</c:if>
	        		<c:if test="${LoginOK.userName.equals(item.seller)}">
	        		<div class="col-md-12 ">
	        			<span id="directPrice1" class="alert alert-warning" role="alert">直購價：<span id="directPrice">${directPrice}元</span></span><br><BR>
	        		</div>
	        		<div class="col-md-12 ">	 
	        			您是這個商品的賣家
	        		</div>
	        		</c:if>
	        		</c:when>
	        		<c:otherwise>
	        		<div class="col-md-12 ">
	        			<span id="directPrice1" class="alert alert-warning" role="alert">直購價：<span id="directPrice">${directPrice}元</span></span><br><BR>
	        		</div>
	        		<c:if test="${item.itemStatus==1}">

	           			<h1>此商品下架中</h1>
	        		</c:if>
	        		<c:if test="${item.itemStatus==2}">

	           			<h1>此商品已售出</h1>
	        		</c:if>
	        		</c:otherwise>
					</c:choose>
	        		</c:if>	
<!-- ========================================================--> 

<!-- ===================沒登入出現==================================-->
				<c:if test="${empty LoginOK}">
	        		<div class="col-md-12 ">
	        		<span id="directPrice1" class="alert alert-warning" role="alert">直購價：<span id="directPrice">${directPrice}元</span></span><br><BR>
	        		</div>
	        		<div  class="col-md-12">
	        			<a href="${pageContext.request.contextPath}/member/login.do?itemid=${item.itemId}">若要購買此商品請先登入</a><br>
	        		</div>
	        		</c:if>	 
<!-- 	======================================================== -->



<!-- =============商品內容描述====== -->
	        		<h4>商品內容描述：</h4>
					<div id="itemDescribe" class="col-md-7">
<!-- 	        		<h4>商品內容描述：</h4> -->
	        		${item.itemDescribe}
	        		</div>	
<!-- ===========================================================		     	 -->
				</div>
				<div class="col-md-2 col-md-offset-3">
					        		<h4>商品圖片：</h4>
	        		<c:if test="${!empty images}">
	        		<c:forEach items="${images}" var="image">
	        			<img src="${pageContext.request.contextPath}/search/showImage?imageNo=${image}" class="itemimg">
	        		</c:forEach>
	        		</c:if>
	        		<c:if test="${empty images}">
	        			<img src="${pageContext.request.contextPath}/search/showImage" class="itemimg">
	        		</c:if>
	        		</div>
				
					</div>        			        		
				</div>

				</c:when>
				<c:otherwise>
					<h3>查無此商品</h3>
				</c:otherwise>
				</c:choose>	        
 			 </div> 
 			 <%@include file="itempagedialog.jsp" %>

         </div>
        </div>
	</article>
</body>
</html>
