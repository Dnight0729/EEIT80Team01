<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="/include/include" %>
<title>主旨：${Message.messageTitle}</title>
<style type="text/css">
#contentPart,dt{border-width:1px; border-color:#939699; 
border-radius:10px;}
	.tag{ 
		display: inline-block;
		width: 115px;
		text-align: right;
		}
	.msgBody{
		padding-left:115px;
	}
	
	.navbar{ 
 		margin-bottom: 0px;
	}
body { padding-top: 50px; }
#contentPart { padding-top: 50px; }
#div {border-radius:20%;}

#div {border-radius:20% ;
}

dd{border-radius:10px;}

#dd{text-align:center}

</style>
</head>
<body>

	<header>
		<%@include file="/include/header" %>
	</header>
	<article>
	
	<div id="div" class="container-fluid">
	      <div  class="row">
			<%@include file="/include/navPart" %>
			
				<div class="col-md-7 main" id="contentPart">
				<dl class="dl-horizontal" >
 					
 					<dt class="list-group-item list-group-item-success">寄件者：</dt>
  					<dd >${Message.sender}</dd>
  					<dt class="list-group-item list-group-item-warning">收件者：</dt>
  					<dd >${Message.receiver}</dd>
  					<dt class="list-group-item list-group-item-info">信件主旨：</dt>
  					<dd >${Message.messageTitle}</dd>
  					<dt class="list-group-item list-group-item-danger">信件內容：</dt> 					
				</dl>
	<br>
	<dl>
		<dd class="list-group-item">${Message.messageBody}<BR><BR><BR><BR></dd>
	</dl>
		<input type ="button" onclick="history.back()" class="btn btn-default" value="上一頁"></input>	
		<c:if test="${!Message.sender.equals(LoginOK.userName)}">
			<a href="sendmessage.jsp?id=${Message.sender}" class="btn btn-default" role="button">回覆信件</a>
		</c:if>	
				<c:if test="${Message.sender.equals(LoginOK.userName)}">
			<a href="sendmessage.jsp" class="btn btn-default" role="button">寫新信件</a>
		</c:if>	
		</div>		
		<%@include file="/include/blockPart" %>
	</div>
	</div>
	
	</article>
</body>
</html>