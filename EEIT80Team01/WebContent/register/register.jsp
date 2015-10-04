<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
.form-group{height:45px;}
.error{color:red;}
</style>
<title>註冊</title>
</head>
<body>
	<header>
		<%@include file="/include/header" %>
	</header>
	<article>
	<c:if test="${!empty registerFalse }">
	<div class="alert alert-danger alert-dismissible text-center" role="alert">
	  <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	  <strong>註冊失敗!</strong>
	</div>
	</c:if>
	<div class="container-fluid">
		<div class="row text-center">
			<h5>&nbsp;</h5>
		</div>
		<div class="row text-center">
			<h1><strong>建立您的E7MM帳號</strong></h1>
		</div>
		<div class="row text-center">
			<h3>&nbsp;</h3>
		</div>
		<div class="row">
			<div class="col-md-6">
				<div class="col-md-offset-2 col-md-10 ">

<br><br>
				</div>
				<img  src="${pageContext.request.contextPath}/imgs/register.jpg" width="90%" >
				
			</div>
			<div class="col-md-5 ">
				<form method="post" class="form-horizontal jumbotron  cmxform" id="form" action="register.do">
				  <div class="form-group" >
				    <label for="username" class="col-sm-4 control-label">帳號：</label>
				    <div class="col-sm-8">
				      <input type="text" class="form-control" id="username" name="username" placeholder="請輸入帳號">
				    </div>
				  </div>
				  <div class="form-group">
				    <label for="password" class="col-sm-4 control-label">密碼：</label>
				    <div class="col-sm-8">
				      <input type="password" class="form-control" id="password" name="password" placeholder="請輸入密碼" title="請注意密碼大小寫">
				    </div>
				  </div>
				  <div class="form-group">
				    <label for="passwordCheck" class="col-sm-4 control-label">確認密碼：</label>
				    <div class="col-sm-8">
				      <input type="password" class="form-control" id="passwordCheck" name="passwordCheck" placeholder="請再次輸入密碼" title="請注意密碼大小寫">
				    </div>
				  </div>
				  <div class="form-group">
				    <label for="id" class="col-sm-4 control-label">身份證字號：</label>
				    <div class="col-sm-8">
				      <input type="text" class="form-control" id="id" name="id" placeholder="請輸入身份證字號">
				    </div>
				  </div>
				  <div class="form-group">
				    <label for="lastName" class="col-sm-4 control-label">姓：</label>
				    <div class="col-sm-8">
				      <input type="text" class="form-control" id="lastName" name="lastName" placeholder="請輸入姓">
				    </div>
				  </div>
				  <div class="form-group">
				    <label for="firstName" class="col-sm-4 control-label">名：</label>
				    <div class="col-sm-8">
				      <input type="text" class="form-control" id="firstName" name="firstName" placeholder="請輸入名">
				    </div>
				  </div>
				  <div class="form-group">
				    <label for="email" class="col-sm-4 control-label">E-mail：</label>
				    <div class="col-sm-8">
				      <input type="text" class="form-control" id="email" name="email" placeholder="請輸入E-mail信箱">
				    </div>
				  </div>
				  <div class="form-group">
				    <label for="birthDay" class="col-sm-4 control-label">生日：</label>
				    <div class="col-sm-8">
				      <input type="text" class="form-control" id="birthDay" name="birthDay" placeholder="請輸入生日日期">
				    </div>
				  </div>
	
				  <div class="form-group">
				  	<label for="gender" class="col-sm-4 control-label">性別：</label>
				    <div class="col-sm-8">
					  <div class="radio-inline">
						<label>
						  <input type="radio" name="gender" id="gender" value="1" checked>男
					    </label>
				 	  </div>
					  <div class="radio-inline">
						<label>
						  <input type="radio" name="gender" value="0" >女
					    </label>
				 	  </div>
				    </div>
				  </div>
				  <div class="form-group">
				    <div class="col-sm-offset-4 col-sm-8">
				      <button type="submit" id="submit" class="btn btn-default">送出</button>
				      <button type="reset" id="reset" class="btn btn-default">清除</button>
				    </div>
				  </div>
				</form>
			</div>
		</div>
	</div>
	</article>
	<footer>
	<%@include file="/include/footer" %>
	</footer>
<script src="register.js">
</script>
</body>
</html>