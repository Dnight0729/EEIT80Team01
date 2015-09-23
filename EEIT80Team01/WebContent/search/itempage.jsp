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
	width:100px;
	hight:100px
}

#directPrice{
color:#FF0000;
font-size:150%;
 
}
#opener{
cursor: pointer; 
color:#0088A8;
}

#directPrice1{padding-left:50px; }
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
				
					<div id="opener">賣家：${item.seller}</div>        	
	        		商品分類：${itemCategory.categoryName}<br>
	        		商品主題：${item.title}	<br>
	        		商品價格：${price}<br>
	        		<fmt:formatDate value="${item.endTime}" var="formatDate" type="date" pattern="yyyy年MM月dd日HH時mm分" />	
	        		結標時間：${formatDate}<br>
	        		最小加價：${item.bid}<br><br>
	        		<span id="directPrice1" class="alert alert-warning" role="alert">直購價：<span id="directPrice">${item.directPrice}</span></span><br><BR>
	        		<c:if test="${!empty LoginOK}">
	        		<c:if test="${!LoginOK.userName.equals(item.seller)}">
	        		<c:if test="${!LoginOK.userName.equals(topPrice.buyer)}">
	        		<form action="${pageContext.request.contextPath}/product/bid.do" method="post">
	        			<input type="number" min="${price + item.bid}" value="${price + item.bid}" name="bidPrice">
	        			<input type="hidden" name="itemId" value="${item.itemId}">
	        			<input type="hidden" name="action" value="bid">
	        			<button type="submit" class="btn btn-primary">出價</button>
	        		</form>
	        		</c:if>
	        		<c:if test="${LoginOK.userName.equals(topPrice.buyer)}">
	        		<div>
	        			您是目前最高出價者
	        		</div>
	        		</c:if>
	        		<form action="${pageContext.request.contextPath}/product/bid.do" method="post">
	        			<input type="hidden" name="itemId" value="${item.itemId}">
	        			<input type="hidden" name="action" value="direct">	        			
	        			<button type="submit" class="btn btn-primary">直購</button>
	        		</form>
	        		</c:if>
	        		<c:if test="${LoginOK.userName.equals(item.seller)}">
	        			您是這個商品的賣家<br>
	        		</c:if>	        
	        		</c:if>
	        		<c:if test="${empty LoginOK}">
	        			<a href="${pageContext.request.contextPath}/member/login.do?itemid=${item.itemId}">若要購買此商品請先登入</a><br>
	        		</c:if>	        				
	        		<div>
	        		<h4>商品內容描述：</h4>
	        		${item.itemDescribe}
	        		</div>
	        		<c:if test="${!empty images}">
	        		<c:forEach items="${images}" var="image">
	        			<img src="${pageContext.request.contextPath}/search/showImage?imageNo=${image}" class="itemimg">
	        		</c:forEach>
	        		</c:if>
	        		<c:if test="${empty images}">
	        			<img src="${pageContext.request.contextPath}/search/showImage" class="itemimg">
	        		</c:if>
				</c:when>
				<c:otherwise>
					<h3>查無此商品</h3>
				</c:otherwise>
				</c:choose>	        
 			 </div> 
 			 <%@include file="itempagedialog.jsp" %>
<%-- 			<%@include file="/include/blockPart" %> --%>
         </div>
        </div>
	</article>
</body>
</html>
