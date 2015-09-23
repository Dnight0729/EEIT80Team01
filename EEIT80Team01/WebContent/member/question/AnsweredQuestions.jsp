<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>客服回覆</title>
<%@include file="/include/include"%>
<%@include file="/include/datatables.file"%>
<style type="text/css">
form {
	width: 100%;
}

.tag {
	display: inline-block;
	width: 115px;
	text-align: right;
}

.navbar {
	margin-bottom: 0px;
}

body {
	padding-top: 50px;
}

#contentPart {
	padding-top: 50px;
}
</style>
</head>
<body>
	<header>
		<%@include file="/include/header"%>
	</header>
	<article>
		<div class="container-fluid">
			<div class="row">
				<%@include file="/include/navPart"%>
				<div class="col-md-7 main" id="contentPart">
					<table id="table" class="display" cellspacing="0" width="100%">
						<thead>
							<tr>
								<td>問題編號</td>
								<td>標題</td>
								<td>發問時間</td>
								<td>回答時間</td>
								<td>詳細內容</td>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${AnsweredQuestions}" var="answered">
								<tr>
									<td>${answered.qno}</td>
									<td>${answered.title}</td>
									<td>
										<jsp:useBean id="myDate" class="java.util.Date"/>  
										<c:set target="${myDate}" property="time" value="${answered.qt}"/>
										<fmt:formatDate value="${myDate}" pattern="yyyy/MM/dd HH:mm:ss" timeZone="Asia/Taipei"/>
									</td>
									<td>
										<jsp:useBean id="myDate2" class="java.util.Date"/>  
										<c:set target="${myDate2}" property="time" value="${answered.at}"/>
										<fmt:formatDate value="${myDate2}" pattern="yyyy/MM/dd HH:mm:ss" timeZone="Asia/Taipei"/>
									</td>
									<td><a
										href="${pageContext.request.contextPath}/member/question/memberAnsweredQuestionDetailServlet.do?qno=${answered.qno}&member=${answered.member}">詳細內容</a>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				<%@include file="/include/blockPart"%>
			</div>
		</div>
	</article>
</body>
</html>