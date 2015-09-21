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
<title>未回答問題列表(客服)</title>
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
											<td>問題編號</td>
											<td>提問會員帳號</td>
											<td>標題</td>
											<td>提問時間</td>
											<td>詳細內容</td>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${unansweredQuestions}" var="item">
											<tr>
												<td>${item.qno}</td>
												<td>${item.member}</td>
												<td>${item.title}</td>
												<td>${item.qt}</td>
												<td><a
													href="${pageContext.request.contextPath}/support/manage/question/supportQuestionDetail.do?qno=${item.qno}">內容</a></td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
						</fieldset>
						<div style="margin: 5px">
							<span style="float: left"><input type="button"
								value="重新載入列表" onclick="goToPage()"></span>
						</div>
					</form>
				</div>
			</div>
		</div>
	</article>
	<script>
		var url = "listUnansweredQuestionsServlet.jsp";
		function goToPage() {
			window.location = url;
		}
	</script>
</body>
</html>