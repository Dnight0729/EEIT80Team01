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
<title>封鎖中會員列表</title>
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
					<form method="post" action="unbanMember.do">
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
											<td>解除封鎖</td>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${memberlist}" var="item">
											<c:if test="${item.access eq 1}">
												<tr>
													<td>${item.userName}</td>
													<td>${item.lastName}</td>
													<td>${item.firstName}</td>
													<td>${item.email}</td>
													<td>
														<c:if test="${item.gender eq 1}">男</c:if>
														<c:if test="${item.gender eq 0}">女</c:if>
													</td>
													<td>
														<fmt:formatDate value="${item.birthDay}" pattern="yyyy/MM/dd"/>
													</td>
													<td><input type="checkbox" name="bannedMemberChecked" value="${item.userName}"></td>
												</tr>
											</c:if>
										</c:forEach>
									</tbody>
								</table>
							</div>
						</fieldset>
						<div style="margin: 5px">
							<span style="float: left"><input type="button" value="重新載入列表" onclick="goToPage()"></span>
							<span style="float: right"><input type="reset" name="reset" value="清除選取">
							<input type="submit" name="delete" value="解除封鎖已選取會員"></span>
						</div>
					</form>
					<c:if test="${!empty unbannedNumber}">
						<div><span style="color:red">成功解除封鎖 ${unbannedNumber} 位會員</span></div>
					</c:if>
				</div>
			</div>
		</div>
	</article>
	<script>
		var url = "listBannedMembers.jsp";
		function goToPage() {
			window.location = url;
		}
	</script>
</body>
</html>