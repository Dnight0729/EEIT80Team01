<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<%@include file="/include/include"%>
<%@include file="/include/datatables.file"%>
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
<title>客服人員列表</title>
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
				<form method="post" action="deleteSupporterAccounts.do">
					<fieldset>
						<legend>客服人員列表</legend>
						<div>
							<table id="table" class="display" cellspacing="0" width="100%">
								<thead>
									<tr>
										<td>客服帳號</td>
										<td>客服員工編號</td>
										<td>姓</td>
										<td>名</td>
										<td>編輯</td>
										<td>刪除帳號</td>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${supporterlist}" var="item">
										<tr>
											<td>${item.supportername}</td>
											<td>${item.employeeid}</td>
											<td>${item.lastname}</td>
											<td>${item.firstname}</td>
											<td><a
												href="${pageContext.request.contextPath}/admin/manage/modifySupporter.do?supportername=${item.supportername}">修改</a></td>
											<td><input type="checkbox" name="supporterChecked" value="${item.supportername}"></td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</fieldset>
					<div style="margin: 5px">
						<span style="float: left"><input type="button" value="重新載入列表" onclick="goToPage()"></span>
						<span style="float: right"><input type="reset" name="reset" value="清除選取"> <input type="submit" name="delete" value="刪除客服帳號"></span>
					</div>
				</form>
			</div>
		</div>
	</div>
	</article>
	<script>
  		var url = "listSupporters.jsp";
  		function goToPage() {
  			window.location = url;
  		}
  	</script>
</body>
</html>