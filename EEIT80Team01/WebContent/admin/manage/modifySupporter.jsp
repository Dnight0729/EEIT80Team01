<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<%@include file="/include/include"%>
<title>修改客服資料</title>
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
							<h3 class="panel-title">修改客服資料</h3>
						</div>
						<div class="panel-body">
							<form method="post" action="modifySupporter.do">
								<div class="form-group">
									<label class="tag" for="supportername">帳號：</label> <input
										type="text" name="supportername" title="請輸入客服帳號"
										value="${supporterInfo.supportername}" class="form-control"><font
										color="red" size="-1"><span class="error">${ErrorMsgs.supporternameError}</span></font>
								</div>
								<div class="form-group">
									<label class="tag" for="password">密碼：</label> <input
										type="password" name="password" title="請輸入密碼"
										class="form-control"><font color="red" size="-1"><span
										class="error">${ErrorMsgs.passwordError}</span></font>
								</div>
								<div class="form-group">
									<label class="tag" for="password">確認密碼：</label> <input
										type="password" name="passwordCheck" title="再次輸入密碼"
										class="form-control">
								</div>
								<div class="form-group">
									<label class="tag" for="employeeid">客服編號：</label> <input
										type="text" name="employeeid" title="輸入客服編號"
										value="${supporterInfo.employeeid}" class="form-control"><font
										color="red" size="-1"><span class="error">${ErrorMsgs.employeeidError}</span></font>
								</div>
								<div class="form-group">
									<label class="tag" for="lastname">姓氏：</label> <input
										type="text" name="lastname" title="請輸入客服人員的姓氏"
										value="${supporterInfo.lastname}" class="form-control"><font
										color="red" size="-1"><span class="error">${ErrorMsgs.lastnameError}</span></font>
								</div>
								<div class="form-group">
									<label class="tag" for="firstname">名字：</label> <input
										type="text" name="firstname" title="請輸入客服人員的名字"
										value="${supporterInfo.firstname}" class="form-control"><font
										color="red" size="-1"><span class="error">${ErrorMsgs.firstnameError}</span></font>
								</div>
								<input class="btn btn-default" type="submit" id="submit"
									value="送出"> <input class="btn btn-danger" type="reset"
									id="reset" value="清除"> <input type="hidden"
									id="oldSupporterName" name="oldSupporterName"
									value="${supporterInfo.supportername}">
							</form>
							<span style="color: red">${modifyResult}</span>
						</div>
					</div>
				</div>
			</div>
		</div>
	</article>
</body>
</html>