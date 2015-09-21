<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<%@include file="/include/include"%>
<title>新增客服帳號</title>
<style>
.navbar {
	margin-bottom: 0px;
}

#carousel1 {
	margin-bottom: 20px;
}

body {
	padding-top: 50px;
}
</style>
</head>
<body>
	<header><%@include file="/include/header-admin"%></header>
	<article>
		<div class="container-fluid">
			<div class="row">
				<div class="col-md-3">
					<%@include file="/admin/toolbar"%>
				</div>
				<div class="col-md-9">
					<div class="panel panel-default"
						style="margin: auto; margin-top: 20px; width: 40%">
						<div class="panel-heading">
							<h3 class="panel-title">新增客服帳號</h3>
							<span style="color: red">${addResult}</span>
						</div>
						<div class="panel-body">
							<form method="post" action="AddSupport.do">
								<div class="form-group">
									<label class="tag" for="user">帳號：</label> <input type="text"
										id="supportername" name="supportername" title="請輸入客服帳號"
										class="form-control">
								</div>
								<div class="form-group">
									<label class="tag" for="password">密碼：</label> <input
										type="password" id="password" name="password" title="請輸入密碼"
										class="form-control">
								</div>
								<div class="form-group">
									<label class="tag" for="password">確認密碼：</label> <input
										type="password" id="passwordCheck" name="passwordCheck"
										title="再次輸入密碼" class="form-control">
								</div>
								<div class="form-group">
									<label class="tag" for="employeeid">客服編號：</label> <input
										type="text" id="employeeid" name="employeeid" title="輸入客服編號"
										class="form-control">
								</div>
								<div class="form-group">
									<label class="tag" for="lastname">姓氏：</label> <input
										type="text" id="lastname" name="lastname" title="請輸入客服人員的姓氏"
										class="form-control">
								</div>
								<div class="form-group">
									<label class="tag" for="firstname">名字：</label> <input
										type="text" id="firstname" name="firstname" title="請輸入客服人員的名字"
										class="form-control">
								</div>
								<input class="btn btn-default" type="submit" id="submit"
									value="送出"> <input class="btn btn-danger" type="reset"
									id="reset" value="清除">
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</article>
</body>
</html>