<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.List,items.model.*,java.util.Map" %>
<%
	ItemsService is = new ItemsService();
	List<Map<String,Object>> listmap = is.frontpage();
	pageContext.setAttribute("latestproducts", listmap);
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<%@include file="/include/include" %>
<link href="${pageContext.request.contextPath}/css/index.css" rel="stylesheet" type="text/css">
<style >
#carousel1{
	margin-bottom: 20px;
}
</style>
<title>Home</title>
</head>
<body>
<header>
<%@include file="/include/header" %>
</header>
<article>
	<div id="carousel1" class="carousel slide " data-ride="carousel">
	  <!-- Indicators -->
	  <ol class="carousel-indicators">
	    <li data-target="#carousel1" data-slide-to="0" class="active"></li>
	    <li data-target="#carousel1" data-slide-to="1"></li>
	    <li data-target="#carousel1" data-slide-to="2"></li>
	  </ol>
	
	  <!-- Wrapper for slides -->
	  <div class="carousel-inner" role="listbox">
	    <div class="item active">
	      <img src="imgs/aaa.jpg" alt="..." width="100%">
	      <div class="carousel-caption">
	      </div>
	    </div>
	    <div class="item">
	      <img src="imgs/bbb.jpg" alt="..." width="100%">
	      <div class="carousel-caption">
	      </div>
	    </div>
	    <div class="item">
	      <img src="imgs/ccc.jpg" alt="..." width="100%">
	      <div class="carousel-caption">
	      </div>
	    </div>
	  </div>
	
	  <!-- Controls -->
	  <a class="left carousel-control" href="#carousel1" role="button" data-slide="prev">
	    <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
	    <span class="sr-only">Previous</span>
	  </a>
	  <a class="right carousel-control" href="#carousel1" role="button" data-slide="next">
	    <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
	    <span class="sr-only">Next</span>
	  </a>
	</div>
	<div class="container-fluid">
		<div class="row">
			<div class="col-md-9">
				<div class="page-header">
				  <h4>影音娛樂<small><a href="${pageContext.request.contextPath}/search/searchItems.do?option=7&keyword=">查看更多</a></small></h4>
				</div>
				<div class="gallery js-flickity" id="gal1"
				  data-flickity-options='{ "freeScroll": true, "contain": true, "prevNextButtons": false, "pageDots": false }'>
				  <img class="gallery-cell" src=""/>
				  <img class="gallery-cell" src=""/>
				  <img class="gallery-cell" src=""/>
				  <img class="gallery-cell" src=""/>
				  <img class="gallery-cell" src=""/>
				  
				</div>
				<div class="page-header">
				  <h4>生活家居<small><a href="${pageContext.request.contextPath}/search/searchItems.do?option=8&keyword=">查看更多</a></small></h4>
				</div>
				<div class="gallery js-flickity" id="gal2"
				  data-flickity-options='{ "freeScroll": true, "contain": true, "prevNextButtons": false, "pageDots": false }'>
				  <img class="gallery-cell" src=""/>
				  <img class="gallery-cell" src=""/>
				  <img class="gallery-cell" src=""/>
				  <img class="gallery-cell" src=""/>
				  <img class="gallery-cell" src=""/>
				</div>
				<div class="page-header">
				  <h4>彩妝保養<small><a href="${pageContext.request.contextPath}/search/searchItems.do?option=10&keyword=">查看更多</a></small></h4>
				</div>
				<div class="gallery js-flickity" id="gal3"
				  data-flickity-options='{ "freeScroll": true, "contain": true, "prevNextButtons": false, "pageDots": false }'>
				  <img class="gallery-cell" src=""/>
				  <img class="gallery-cell" src=""/>
				  <img class="gallery-cell" src=""/>
				  <img class="gallery-cell" src=""/>
				  <img class="gallery-cell" src=""/>
				</div>
				<div class="page-header">
				  <h4>保健食品<small><a href="${pageContext.request.contextPath}/search/searchItems.do?option=11&keyword=">查看更多</a></small></h4>
				</div>
				<div class="gallery js-flickity" id="gal4"
				  data-flickity-options='{ "freeScroll": true, "contain": true, "prevNextButtons": false, "pageDots": false }'>
				  <img class="gallery-cell" src=""/>
				  <img class="gallery-cell" src=""/>
				  <img class="gallery-cell" src=""/>
				  <img class="gallery-cell" src=""/>
				  <img class="gallery-cell" src=""/>
				</div>
				<div class="page-header">
				  <h4>流行服飾<small><a href="${pageContext.request.contextPath}/search/searchItems.do?option=5&keyword=">查看更多</a></small></h4>
				</div>
				<div class="gallery js-flickity" id="gal5"
				  data-flickity-options='{ "freeScroll": true, "contain": true, "prevNextButtons": false, "pageDots": false }'>
				  <img class="gallery-cell" src=""/>
				  <img class="gallery-cell" src=""/>
				  <img class="gallery-cell" src=""/>
				  <img class="gallery-cell" src=""/>
				  <img class="gallery-cell" src=""/>
				</div>

			</div>
			
			<div class="col-md-3">
				<div class="panel panel-default">
	  				<div class="panel-heading">
	    				<h3 class="panel-title">最新上架商品</h3>
	  				</div>
	  				<div class="panel-body">
	  				<c:if test="${empty latestproducts}">  					  				
	    				<img src="http://cdn.kingstone.com.tw/cvlife/images/product/30100/3010000029712/3010000029712Abs1.JPG" alt="..." class="img-thumbnail img-responsive">
	    				<img src="http://www.everrich-group.com/Upload/fcd42f03-236f-4211-93b4-d1b31fee2fb8/EN/01.jpg" alt="..." class="img-thumbnail img-responsive">
	    				<img src="http://pic.eslite.com/Upload/Attachment/201306/635077767310229711.jpg" alt="..." class="img-thumbnail img-responsive">
	    				<img src="http://www.eayko.com/wp-content/uploads/2013/07/20101221nufirstimage.jpg" alt="..." class="img-thumbnail img-responsive">
	    			</c:if>
	    			<c:if test="${!empty latestproducts}"> 
					 <c:forEach items="${latestproducts}" var="product">  		
	    				<c:if test="${!empty product}">	
	    				<a href="${pageContext.request.contextPath}/search/item?itemid=${product.itemId}">
	    					<c:choose>
	    					<c:when test="${!empty product.imageNo}">
	    					<img src="${pageContext.request.contextPath}/search/showImage?imageNo=${product.imageNo}" title="${product.title}" alt="..." class="img-thumbnail img-responsive">
	    					</c:when>
	    					<c:otherwise>
	    					<img src="${pageContext.request.contextPath}/search/showImage" title="${product.title}" alt="..." class="img-thumbnail img-responsive">
	    					</c:otherwise>
	    					</c:choose>
	    				</a>
	    				</c:if>
	    			</c:forEach>
	    			</c:if>
	  				</div>
				</div>
			</div>
			
		</div>
	</div>
</article>
<footer>
	<div class="container">
		<hr>
		<div class="row">
			<div class="col-md-offset-1 col-md-10">
				<p>&copy; EEIT80TeamOne-All Rights Reserved</p>
			</div>
		</div>
	</div>
	
</footer>
</body>
<script>
	$(function(){
		j = 1;
		k=1;
		l=1;
		m=1;
		n=1;
		$.ajax({
			"type":"get",
			"url":"${pageContext.request.contextPath}/rest/items/1",
			"dataType":"json",
			"success":function(data){
				$.each(data,function(i,item){
// 					console.log(item.itemId);
					$.ajax({
						"type":"get",
						"url":"${pageContext.request.contextPath}/rest/itemImg/"+item.itemId,
						"dataType":"json",
						"success":function(img){
							$("#gal1 img:nth-child(+"+j+")").prop("src","${pageContext.request.contextPath}/search/showImage?imageNo="+img[0]);
							j++;
						}
					});
				})
			},
		});
		$.ajax({
			"type":"get",
			"url":"${pageContext.request.contextPath}/rest/items/2",
			"dataType":"json",
			"success":function(data){
				$.each(data,function(i,item){
// 					console.log(item.itemId);
					$.ajax({
						"type":"get",
						"url":"${pageContext.request.contextPath}/rest/itemImg/"+item.itemId,
						"dataType":"json",
						"success":function(img){
							$("#gal2 img:nth-child(+"+k+")").prop("src","${pageContext.request.contextPath}/search/showImage?imageNo="+img[0]);
							k++;
						}
					});
				})
			},
		});
		$.ajax({
			"type":"get",
			"url":"${pageContext.request.contextPath}/rest/items/2",
			"dataType":"json",
			"success":function(data){
				$.each(data,function(i,item){
// 					console.log(item.itemId);
					$.ajax({
						"type":"get",
						"url":"${pageContext.request.contextPath}/rest/itemImg/"+item.itemId,
						"dataType":"json",
						"success":function(img){
							$("#gal3 img:nth-child(+"+l+")").prop("src","${pageContext.request.contextPath}/search/showImage?imageNo="+img[0]);
							l++;
						}
					});
				})
			},
		});
		$.ajax({
			"type":"get",
			"url":"${pageContext.request.contextPath}/rest/items/2",
			"dataType":"json",
			"success":function(data){
				$.each(data,function(i,item){
// 					console.log(item.itemId);
					$.ajax({
						"type":"get",
						"url":"${pageContext.request.contextPath}/rest/itemImg/"+item.itemId,
						"dataType":"json",
						"success":function(img){
							$("#gal4 img:nth-child(+"+m+")").prop("src","${pageContext.request.contextPath}/search/showImage?imageNo="+img[0]);
							m++;
						}
					});
				})
			},
		});
		$.ajax({
			"type":"get",
			"url":"${pageContext.request.contextPath}/rest/items/2",
			"dataType":"json",
			"success":function(data){
				$.each(data,function(i,item){
// 					console.log(item.itemId);
					$.ajax({
						"type":"get",
						"url":"${pageContext.request.contextPath}/rest/itemImg/"+item.itemId,
						"dataType":"json",
						"success":function(img){
							$("#gal5 img:nth-child(+"+n+")").prop("src","${pageContext.request.contextPath}/search/showImage?imageNo="+img[0]);
							n++;
						}
					});
				})
			},
		});
		
		
		
	})
</script>




</html>