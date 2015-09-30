<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
#map{ height:300px ; width:700px; }
</style>
<title>常見問題</title>
</head>
<body>
	<header>
		<%@include file="/include/header" %>
	</header>
	<article>
		<div class="container-fluid">
	      <div class="row">
			<%@include file="/include/navPart" %>
	        <div class="col-md-7 main">
	        	<!-- 				以下本頁布置 -->
<div id="tabs">
  <ul>
    <li><a href="#tabs-1">常見問題 1</a></li>
    <li><a href="#tabs-2">常見問題 2</a></li>
    <li><a href="#tabs-3">常見問題 3</a></li>
  </ul>
  <div id="tabs-1">
	<h3>修改訂單</h3>
    <p>若買家完成結帳後，結帳資料有錯誤，交易雙方可以經由修改訂單動作後，訂單將回覆至未結帳狀態，買家即可重新結帳與選擇運送方式。</p>
  </div>
  <div id="tabs-2">
   	<h3>發現賣家的商品似乎有違規，如何檢舉呢 ?</h3>
     <p>如果會員發現站上的商品頁面有違反公告政策情形，請透過客服專區內檢舉商品違規提出，收到檢舉時，系統會依序對於被檢舉的商品頁面進行檢視。</p>
  </div>
  <div id="tabs-3">
   	<h3>服務方式</h3>
   	<p>常見問題無法滿足您的需求，請用以下這兩種方式聯絡我們為您服務</p>
   	<P>地址： 106台北市大安區復興南路一段390號</P>
   	<P>電話：02 6631 6599</P>
   	</div>
</div>
<div id="map"></div>
<!-- 				以上本頁布置 -->
	        	
	        </div>
			<%@include file="/include/blockPart" %>
         </div>
	</article>
	<footer>
	
	</footer>
	<%@include file="/include/modal" %>
	
	<!-- 	以下本頁script -->
	<script src="//code.jquery.com/jquery-1.10.2.js"></script>
  	<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
  	    <script async defer src="https://maps.googleapis.com/maps/api/js?callback=initMap"></script>
	<script>
	$(function() {
		$( "#tabs" ).tabs();
	  }(jQuery));
	function initMap() {
    	var myLocation = {lat: 25.033779, lng: 121.543271};

    	  // Create a map object and specify the DOM element for display.
    	  var map = new google.maps.Map(document.getElementById('map'), {
    	    center: myLocation,
    	    scrollwheel: false,
    	    zoom: 12
    	  });

    	  // Create a marker and set its position.
    	  var marker = new google.maps.Marker({
    	    map: map,
    	    position: myLocation,
    	    title: 'E7mm服務端!'
    	  });
    	}
	</script>
<!-- 	以上本頁script -->
</body>

<script>
$("#sectionItem9").addClass("active");
</script>
</html>