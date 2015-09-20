<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
</head>
<body>
			<div>${questionDetail.qno}</div>
			<div>${questionDetail.title}</div>
			<div>${questionDetail.member}</div>
			<div>${questionDetail.qmsg}</div>
			<div>${questionDetail.qt}</div>
		<form method="post" action="supporterAnswerQuestion.do" id="answerform">
			<input type="submit" value="送出">
			<input type="hidden" name="hiddenqno" value="${questionDetail.qno}">
			<input type="hidden" name="hiddenqmsg" value="${questionDetail.qmsg}">
			<input type="hidden" name="hiddenmember" value="${questionDetail.member}">
			<input type="hidden" name="hiddentitle" value="${questionDetail.title}">
			<input type="hidden" name="hiddenqt" value="${questionDetail.qt}">
		</form>
		<textarea rows="20" cols="100" name="answer" form="answerform"></textarea>
</body>
</html>