<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@include file="/include/include" %>
<style >
body { padding-top: 50px; }
//---------------------------------
* {
  -webkit-box-sizing: border-box;
     -moz-box-sizing: border-box;
          box-sizing: border-box;
}

body { font-family: sans-serif; }

.packery {
  background: #FDD;
  background: hsla(45, 100%, 40%, 0.2);
}

/* clearfix */
.packery:after {
  content: ' ';
  display: block;
  clear: both;
}

.item {
  width: 20%;
  height: 250px;
}

.item-content {
  width:  100%;
  height: 70%;
  background: #09D;
  border: 2px solid #333;
  border-color: hsla(0, 0%, 100%, 0.4);
  -webkit-transition: width 0.4s, height 0.4s;
     -moz-transition: width 0.4s, height 0.4s;
       -o-transition: width 0.4s, height 0.4s;
          transition: width 0.4s, height 0.4s;
}
.detail{
	width:100%;
	height:30%;
	word-break:keep-all;
	overflow:hidden;
	background:red;
}
/* item has expanded size */
.item.is-expanded {
  width: 40%;
  height: 500px;
}

.item:hover { cursor: pointer; }

.item:hover .item-content {
  border-color: white;
}

.item.is-expanded {
  z-index: 2;
}

.item.is-expanded .item-content {
  background: #C90;
}
</style>
<title>Search</title>
</head>
<body>
<header>
	<%@include file="/include/header" %>
</header>
<article>
	<div class="page-header text-center">
		 <c:choose>
		 	<c:when test="${!empty error}">
 		 		<h1>${error}</h1>
 		 	</c:when>
 		 	<c:otherwise>
	 		 	<h1>搜尋結果 </h1>
 		 	</c:otherwise>
 		 </c:choose>
	</div>
	<div class="packery">
		<c:forEach var="item" items="${items}">
			  <div class="item">
			    <div class="item-content">
			    	<c:if test="${imgNumMap.get(item.getItemId())!=null}">
			    		<img alt="" src="${pageContext.request.contextPath}/search/showImage?imageNo=${imgNumMap.get(item.getItemId())}" width="100%" height="100%">
			    	</c:if>
			    	<c:if test="${imgNumMap.get(item.getItemId())==null}">
			    		<img alt="" src="${pageContext.request.contextPath}/search/showImage" width="100%" height="100%">
			    	</c:if>
			    </div>
			    <div class="detail">
			    	<h4>${item.title}</h4>
			    	<div>起標價：${item.startPrice}</div>
			    	<div>直購價：${item.directPrice}</div>
			    	<fmt:formatDate value="${item.endTime}" var="endTime" type="date" pattern="yyyy年MM月dd日hh時mm分"/>
			    	<div>結標時間：${endTime}</div>
			    	<p>${item.itemDescribe}</p>
			    </div>
			  </div>
		</c:forEach>
	</div>
	
</article>
<footer>
</footer>

<script>
//http://packery.metafizzy.co/packery.pkgd.js added as external resource

$( function() {
  var $container = $('.packery').packery();

  $container.on( 'click', '.item', function( event ) {
    var $item = $( event.currentTarget );
    var isExpanded = $item.hasClass('is-expanded');
    $item.toggleClass('is-expanded');
    if ( isExpanded ) {
      // if shrinking, just layout
      $container.packery();
    } else {
      // if expanding, fit it
      $container.packery( 'fit', event.currentTarget );
    }
  });
});
</script>
</body>
</html>