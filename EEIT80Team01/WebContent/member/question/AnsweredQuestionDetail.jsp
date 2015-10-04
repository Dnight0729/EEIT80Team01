<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>問題編號：${AnsweredQuestion.qno}的回覆</title>
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
					<div class="form-group">
						<label>問題編號：</label>
						<pre>${AnsweredQuestion.qno}</pre>
					</div>
					<div class="form-group">
						<label>標題：</label>
						<pre>${AnsweredQuestion.title}</pre>
					</div>
					<div class="form-group">
						<label>客服&nbsp;${AnsweredQuestion.supporter}&nbsp;回覆時間：</label>
						<div class="form-control">
							<jsp:useBean id="atime" class="java.util.Date" />
							<c:set target="${atime}" property="time" value="${AnsweredQuestion.at}" />
							<fmt:formatDate value="${atime}" pattern="yyyy/MM/dd HH:mm:ss" timeZone="Asia/Taipei" />
						</div>
					</div>
					<div class="form-group">
						<label>客服&nbsp;${AnsweredQuestion.supporter}&nbsp;回覆內容：</label>
						<pre>${AnsweredQuestion.amsg}</pre>
					</div>
					<hr/>
					<div class="form-group">
						<label>會員&nbsp;${AnsweredQuestion.member}&nbsp;發問時間：</label>
						<div class="form-control">
							<jsp:useBean id="qtime" class="java.util.Date" />
							<c:set target="${qtime}" property="time" value="${AnsweredQuestion.qt}" />
							<fmt:formatDate value="${qtime}" pattern="yyyy/MM/dd HH:mm:ss" timeZone="Asia/Taipei" />
						</div>
					</div>
					<div class="form-group">
						<label>會員&nbsp;${AnsweredQuestion.member}&nbsp;發問內容：</label>
						<pre>${AnsweredQuestion.qmsg}</pre>
					</div>
				</div>
				<%@include file="/include/blockPart"%>
			</div>
		</div>
	</article>
	<footer>
	<%@include file="/include/footer" %>
	</footer>










	<%-- 	<div>問題編號：${AnsweredQuestion.qno}</div> --%>
	<%-- 	<div>標題：${AnsweredQuestion.title}</div> --%>
	<!-- 	<div> -->
	<%-- 		發問時間：<jsp:useBean id="dateObject" class="java.util.Date" /> --%>
	<%-- 		<jsp:setProperty name="dateObject" property="time" --%>
	<%-- 			value="${AnsweredQuestion.qt}" /> --%>
	<%-- 		<fmt:formatDate value="${dateObject}" pattern="yyyy/MM/dd hh:mm:ss" /> --%>
	<!-- 	</div> -->
	<%-- 	<div>提問內容：${AnsweredQuestion.qmsg}</div> --%>
	<%-- 	<div>回覆客服：${AnsweredQuestion.supporter}</div> --%>
	<!-- 	<div> -->
	<%-- 		回覆時間：<jsp:useBean id="dateObject2" class="java.util.Date" /> --%>
	<%-- 		<jsp:setProperty name="dateObject" property="time" --%>
	<%-- 			value="${AnsweredQuestion.at}" /> --%>
	<%-- 		<fmt:formatDate value="${dateObject2}" pattern="yyyy/MM/dd hh:mm:ss" /> --%>
	<!-- 	</div> -->
	<%-- 	<div>回覆內容：${AnsweredQuestion.amsg}</div> --%>
</body>
</html>