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

.content{
  width:100%;
  height:30%;
  background-color:#2F4F4F;
  opacity:0.9;
  cursor:default;
}
.detail{
  padding-left:7px;
  color:#FAEBD7;
}
.title h4{
  margin:0px;
  background-color:#003E3E;
  padding:5px;
  color:#FFE4C4;
}
.title h4 small{
  margin-right:5px;
  float:right;
}

//---------------------------------
* {
  -webkit-box-sizing: border-box;
     -moz-box-sizing: border-box;
          box-sizing: border-box;
}


.packery {
}

/* clearfix */
.packery:after {
  content: ' ';
  display: block;
  clear: both;
}

.item {
  width: 20%;
  height: 300px;
  border: 2px solid #333;
  border-color: #5F9EA0;
  word-break:keep-all;
  overflow:hidden;
}

.item-content {
  cursor:zoom-in;
  width:  100%;
  height: 70%;
  background-color:#2F4F4F;    
  padding-bottom:0px;
  -webkit-transition: width 0.4s, height 0.4s;
     -moz-transition: width 0.4s, height 0.4s;
       -o-transition: width 0.4s, height 0.4s;
          transition: width 0.4s, height 0.4s;
}
.zoomout{
	cursor:zoom-out;
}

/* item has expanded size */
.item.is-expanded {
  width: 40%;
  height:600px;
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
		 <c:choose>
		 	<c:when test="${!empty error}">
<%--  		 		<h1>${error}</h1> --%>
					<div style="margin-left:450px;margin-top:70px">
						<img style="opacity:.70"  src="${pageContext.request.contextPath}/imgs/search1.jpg" width="60%">
					</div>
 		 	</c:when>
 		 	<c:otherwise>
				<div class="page-header text-center">
	 		 		<h1>搜尋結果 </h1>
				</div>
 		 	</c:otherwise>
 		 </c:choose>
	<div class="packery">
		<c:forEach var="item" items="${items}">
			  <div class="item" >
		    	<c:if test="${imgNumMap.get(item.getItemId())!=null}">
		    		<img alt="" class="item-content" src="${pageContext.request.contextPath}/search/showImage?imageNo=${imgNumMap.get(item.getItemId())}">
		    	</c:if>
		    	<c:if test="${imgNumMap.get(item.getItemId())==null}">
		    		<img alt="" class="item-content" src="${pageContext.request.contextPath}/search/showImage">
		    	</c:if>
		    	<div class="content">
				    <div class="title"><h4>${item.title}<small><a style="color:	#FFECEC" href="${pageContext.request.contextPath}/search/item?itemid=${item.itemId}"><span class="glyphicon glyphicon-share-alt" aria-hidden="true"></span>詳細資訊</a></small></h4></div>
				    <div class="detail">
				    	<span>起始標價：<strong>${item.startPrice}</strong></span>
				    	<span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;直購價：<strong>${item.directPrice}</strong></span>
				    	<fmt:formatDate value="${item.endTime}" var="endTime" type="date" pattern="yyyy年  MM月 dd日 hh時  mm分"/>
				    	<div>結標時間：<strong>${endTime}</strong></div>
				    	<p>商品描述<br/>${item.itemDescribe}</p>
				    </div>
		    	</div>
			  </div>
		 </c:forEach>
	</div>
	
</article>
<footer>
</footer>

<script>
//http://packery.metafizzy.co/packery.pkgd.js added as external resource
var transitionProp = getStyleProperty('transition');
// get transition end event name
var transitionEndEvent = {
  WebkitTransition: 'webkitTransitionEnd',
  MozTransition: 'transitionend',
  OTransition: 'otransitionend',
  transition: 'transitionend'
}[ transitionProp ];

$( function() {
  var $container = $('.packery').packery({
    percentPosition: true
  });
  $container.on( 'click', '.item-content', function( event ) {
	 $(".item-content").toggleClass("zoomout");
	  
    var target = event.target;
    var $target = $( target );

    // disable transition
    $target.css( transitionProp, 'none' );
    // set current size
    $target.css({
      width: $target.width(),
      height: $target.height()
    });

    var $itemElem = $target.parent();
    var isExpanded = $itemElem.hasClass('is-expanded');
    $itemElem.toggleClass('is-expanded');
    // force redraw
    var redraw = target.offsetWidth;
    // renable default transition
    target.style[ transitionProp ] = '';

    // reset 100%/100% sizing after transition end
    if ( transitionProp ) {
      $target.one( transitionEndEvent, function() {
        target.style.width = '';
        target.style.height = '';
      });
    }

    // set new size
    $target.css({
      width: $itemElem.width(),
      height: $itemElem.height()
    });

    if ( isExpanded ) {
      // if shrinking, just layout
      $container.packery();
    } else {
      // if expanding, fit it
      $container.packery( 'fit', $itemElem[0] );
    }

  });
});


</script>
</body>
</html>