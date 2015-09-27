<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>收到的的信件</title>
<%@include file="/include/include" %>
<%@include file="/include/datatables.file" %>
<%@page import="javax.servlet.http.*,global.GlobalService,member.model.*" %>

<style>
.navbar{ 
 	margin-bottom: 0px;
}
body { padding-top: 50px; }
#contentPart { padding-top: 50px; }
</style>
</head>
<body>
	<header>
		<%@include file="/include/header" %>
	</header>
	<%	
	MemberBean mb = (MemberBean)session.getAttribute(GlobalService.LOGIN_TOKEN);
	String receiver = mb.getUserName();
	MessageService serv = new MessageService();
	List<MessageBean> mblist = serv.findByReceiver(receiver);
	pageContext.setAttribute("list", mblist);
	%>
	<article>
	<div class="container-fluid">
	      <div class="row">
			<%@include file="/include/navPart" %>
<div class="col-md-7 main" id="contentPart">
			 <script type="text/javascript">
	function tdUrl(num){
		window.location="msg.jsp?t="+num;
	}
</script>
<form method="post" action="receivedelete">
	<fieldset>
		<legend>收件夾</legend>
		<table id="table" class="display" cellspacing="0" width="100%">
			<thead>
				<tr>
					<td></td>
					<td>發件者</td>
					<td>收件者</td>
					<td>信件主旨</td>
					<td>發件時間</td>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${list}" var="item">
					<tr>
						<td><input type="checkbox" name="messageNumber" value="${item.messageNumber}"></td>						
						<td onclick="tdUrl(${item.messageNumber})">${item.sender}</td>
						<td onclick="tdUrl(${item.messageNumber})">${item.receiver}</td>
						<td onclick="tdUrl(${item.messageNumber})">${item.messageTitle}</td>
						<fmt:formatDate value="${item.messageTime}" var="formattedDate" pattern="yyyy年MM月dd日HH時mm分" />				
						<td onclick="tdUrl(${item.messageNumber})">${formattedDate}</td>									
					</tr>						
				</c:forEach>
			</tbody>
		</table>
		<input type="reset" name="reset" value="清除">
		<input type="submit" name="delete" value="刪除信件">
	</fieldset>
</form>


	</div>
		<%@include file="/include/blockPart" %>
	</div>
	</div>
</article>
</body>
<script>
$("#sectionItem7").addClass("active");
</script>
</html>