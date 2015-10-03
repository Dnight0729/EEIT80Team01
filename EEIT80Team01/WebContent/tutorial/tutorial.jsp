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
    <li><a href="#tabs-1">修改訂單</a></li>
    <li><a href="#tabs-2">檢舉賣家</a></li>
    <li><a href="#tabs-3">交易取消</a></li>
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
   	<h3>提出棄標，交易取消</h3>
   	<p>棄標投訴並不等於取消交易，如果交易未完成且雙方同意取消交易，您可以請交易方於表明確認取消之編號，待對方確實留言後，再向客服中心提出申請，經客服人員確認無誤後將協助雙方取消該筆交易。</p>
   	</div>
</div>
<div id="map"></div>
<!-- 				以上本頁布置 -->
	        	
	        </div>
			<%@include file="/include/blockPart" %>
         </div>
	</article>
	<footer>
	<%@include file="/include/footer" %>
	</footer>
	<%@include file="/include/modal" %>
	
<!-- 	以下本頁script -->
	<script src="//code.jquery.com/jquery-1.10.2.js"></script>
  	<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
	<script>
		$(function() {
			$( "#tabs" ).tabs();
		  }(jQuery));
	</script>
<!-- 	以上本頁script -->
</body>

<script>
$("#sectionItem9").addClass("active");
</script>
</html>