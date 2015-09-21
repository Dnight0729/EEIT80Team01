<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="/include/include"%>
<%@include file="/include/datatables.file"%>
<title>會員列表</title>
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
	<header><%@include file="/include/header-support"%></header>
	<article>
		<div class="container-fluid">
			<div class="row">
				<div class="col-md-3">
					<%@include file="/support/toolbar" %>
				</div>
				<div class="col-md-9">
					<form method="post" action="還沒寫">
						<fieldset>
							<legend>會員列表</legend>
							<div>
								<table id="table" class="display" cellspacing="0" width="100%">
									<thead>
										<tr>
											<td>會員帳號</td>
											<td>姓</td>
											<td>名</td>
											<td>email</td>
											<td>性別</td>
											<td>生日</td>
											<td>身分驗證</td>
											<td>狀態</td>
											<td>封鎖</td>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${memberlist}" var="item">
											<tr>
												<td>${item.userName}</td>
												<td>${item.lastName}</td>
												<td>${item.firstName}</td>
												<td>${item.email}</td>
												<td>
													<c:if test="${item.gender eq 1}">男</c:if>
													<c:if test="${item.gender eq 0}">女</c:if>
												</td>
												<td>${item.birthDay}</td>
												<td>
													<c:if test="${item.certified eq 0}">未認證</c:if>
													<c:if test="${item.certified eq 1}">已認證</c:if>
												</td>
												<td>
													<c:if test="${item.access eq 0}">正常</c:if>
													<c:if test="${item.access eq 1}">封鎖中</c:if>
												</td>
												<td><input type="checkbox" name="memberChecked" value="${item.userName}"></td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
						</fieldset>
						<div style="margin: 5px">
							<span style="float: left"><input type="button" value="重新載入列表" onclick="goToPage()"></span>
							<span style="float: right"><input type="reset" name="reset" value="清除選取"> <input type="submit" name="delete" value="封鎖已選取會員"></span>
						</div>
					</form>
					<c:if test="${!empty bannedNumber}">
						<span style="color:red">成功封鎖 ${bannedNumber} 位會員</span>
					</c:if>
				</div>
			</div>
		</div>
	</article>
	<script>
		var url = "listMembers.jsp";
		function goToPage() {
			window.location = url;
		}
	</script>
</body>
</html>