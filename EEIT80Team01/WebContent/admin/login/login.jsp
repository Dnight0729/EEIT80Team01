<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<%@include file="/include/include"%>
<title>系統管理員登入</title>
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
					<%@include file="/admin/toolbar" %>
				</div>
				<div class="col-md-9">
					<c:if test="${empty LoginAdmin }">
						<c:set var="funcName" value="LOG" scope="session" />
						<div class="panel panel-default"
							style="margin: auto; margin-top: 20px; width: 40%">
							<div class="panel-heading">
								<h3 class="panel-title">系統管理員登入</h3>
							</div>
							<div class="panel-body">
								<form method="post" action="login.do">
									<div class="form-group">
										<label for="username">帳號：</label> <input type="text"
											id="username" name="username" title="請輸入帳號"
											class="form-control">
									</div>
									<div class="form-group">
										<label for="password">密碼：</label> <input type="password"
											id="password" name="password" title="請輸入密碼"
											class="form-control">
									</div>
									<input class="btn btn-default" type="submit" id="submit"
										value="登入"> <input class="btn btn-danger" type="reset"
										id="reset" value="清除">
								</form>
								<font color="red" size="-1">${ErrorMsgKey.LoginError}&nbsp;</font>
							</div>
						</div>
						<script type="text/javascript" src="login.js"></script>
					</c:if>


					<c:if test="${!empty LoginAdmin }">
						<script>
							location.href = ('${pageContext.request.contextPath}/admin/manage/listSupporters.jsp');
						</script>
					</c:if>


				</div>
			</div>
		</div>
	</article>
	<footer> </footer>
	<%@include file="/include/modal"%>
</body>
</html>