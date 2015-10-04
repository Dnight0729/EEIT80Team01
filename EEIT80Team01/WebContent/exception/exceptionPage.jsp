<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<%@include file="/include/include" %>
<title>Don't worry! it will be OK!</title>
<style>
	body{
		padding:0px;
	}
	.container{
		margin:0px;
	}
	.jumbotron{
		width:1420px;height:650px;
		background-image: url(${pageContext.request.contextPath}/imgs/500.jpg);
    	background-size:1420px 650px;
   		background-repeat: no-repeat;
   		margin:0px;
   		padding-top:0px;
	}
	h2{
		text-align:center;
	}
</style>
</head>
<body>
<header>
</header>
<article>
	<div class="container jumbotron">
		<div class="row">
			<div class="col-md-12" id="accordion" role="tablist" aria-multiselectable="true" >
				  <div class="panel panel-default">
				    <div class="panel-heading" role="tab" id="headingOne">
				     <h2>找不到網頁，請確認網址連結是否合法</h2>
				      <h2 class="panel-title">
				        <a class="button" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
				          	查看錯誤訊息<span class="glyphicon glyphicon-triangle-bottom"></span>
				        </a>
				      </h2>
				    </div>
				    <div id="collapseOne" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingOne">
				      <div class="panel-body">
						<table class="table">
							<thead>
								<tr>
									<th>Status Code</th>
									<th>Servlet Name</th>
									<th>Exception Name</th>
									<th>Request URI</th>
									<th>Exception Message</th>
								</tr>	
							</thead>
							<tbody>
								<tr>
									<td>${statusCode}</td>
									<td>${servletName}</td>
									<td>${exceptionName}</td>
									<td>${requestUri}</td>
									<td>${exceptionMsg}</td>
								</tr>
							</tbody>
						</table>				      
					   </div>
				    </div>
				  </div>

			</div>
		</div>
		<div class="row">
			<div class="col-md-12">
			</div>
		</div>
	</div>
</article>
</body>
</html>