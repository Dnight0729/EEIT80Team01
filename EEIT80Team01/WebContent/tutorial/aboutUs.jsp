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
#map { height:500px; widht:300px; margin:16px;	}
</style>
<title>關於我們</title>
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
	        <h2>聯絡方式</h2>
	        <div id="map"></div>
   			<P>地址： 106台北市大安區復興南路一段390號</P>
   			<P>電話：02 6631 6599</P>
	        </div>
			<%@include file="/include/blockPart" %>
         </div>
	</article>
	<footer>
	
	</footer>
	<%@include file="/include/modal" %>
<!-- 	以下google map -->
			<script async defer
	      src="https://maps.googleapis.com/maps/api/js?callback=initMap">
    	</script>
		<script type="text/javascript">
	    function initMap() {
	    	var myLocation = {lat: 25.033767, lng: 121.543401};

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
	    	    title: '本網服務端!'
	    	  });
	    	}
    	</script>
<!-- 	以上google map -->
</body>
<script>
$("#sectionItem10").addClass("active");
</script>
</html>