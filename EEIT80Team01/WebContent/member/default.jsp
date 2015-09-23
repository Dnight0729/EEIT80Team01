<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="member.model.*,items.model.*" %>
<!DOCTYPE html>
<c:choose>
  	<c:when test="${!empty param.id}">
  		<%
  			MemberService service = new MemberService();
			String id = request.getParameter("id");
			MemberBean mb = service.findMemberData(id);
			List<ItemsBean> list = service.selectMyItems(mb.getUserName());
			pageContext.setAttribute("member",mb);
			pageContext.setAttribute("myItems",list);
		%>
	</c:when>
	<c:otherwise>
		<%
			MemberBean mb = (MemberBean)session.getAttribute("LoginOK");
			MemberService service = new MemberService();
			List<ItemsBean> list = service.selectMyItems(mb.getUserName());
			pageContext.setAttribute("member",mb);
			pageContext.setAttribute("myItems",list);
		%>
	</c:otherwise>
</c:choose>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<%@include file="/include/include" %>
<style>
.navbar{ 
 	margin-bottom: 0px;
}
body { padding-top: 50px; }
#contentPart { padding-top: 50px; }
</style>
<title>會員中心</title>
</head>
<body>
	<header>
		<%@include file="/include/header" %>
	</header>	
		
	
	<article>
		<div class="container-fluid">
	      <div class="row">
			<%@include file="/include/navPart" %>
	        <div class="col-md-7 main" id="contentPart">
	        <div class="col-md-offset-2 col-md-8 ">
	        	<div class="form-group">
	         	 <form action="default.jsp" method="get">
	         	 <div class="input-group">	
	         	 	 <span class="input-group-addon">會員搜尋</span>
	       			<input type="text" class="form-control" name="id" id="input" autocomplete="off">
	       			 <div class="input-group-btn">
	       			 <button type="submit" class="btn btn-primary">搜尋</button>
	       			 </div>  	 
	         	 </div>
	         	 </form>
	         	 </div>
	    		</div>
	        	<c:choose>
  				<c:when test="${!empty member}">
  				<div class="col-md-12 ">
	        	<h2>會員基本資料</h2>	        	
				<table class="table table-bordered">
					<tbody>
						<tr>
							<td>會員帳號</td>
							<td>${member.userName}</td>
						</tr>
						<tr>
							<td>E-mail</td>
							<td>${member.email}</td>
						</tr>
						<tr>
							<td>生日</td>
							<fmt:formatDate value="${member.birthDay}" var="formatDate" pattern="yyyy年MM月dd日" />	
							<td>${formatDate}</td>
						</tr>
						<tr>
							<td>性別</td>
							<c:choose>
  					  		<c:when test="${member.gender==1}">
   	     						<td>男</td>     			
    						</c:when>    
    						<c:otherwise>
								<td>女</td>
    						</c:otherwise>
							</c:choose>
						</tr>
					</tbody>				
				</table>

				</div>
					<h2>最近的商品</h2>
						<table class="table table-bordered">
							<thead>
								<tr>
									<th>商品標題</th>
									<th>起標價格</th>
									<th>直購價格</th>
									<th>結標時間</th>
								</tr>
							</thead>
							<tbody>
					<c:forEach items="${myItems}" var="item">
						<tr>
							<td>${item.title}</td>
							<td>${item.startPrice}</td>
							<td>${item.directPrice}</td>
							<fmt:formatDate value="${item.endTime}" var="formatTime" pattern="yyyy年MM月dd日HH時mm分" />
							<td>${formatTime}</td>
						</tr>							
					</c:forEach>
							</tbody>
						</table>
					<c:if test="${!member.userName.equals(LoginOK.userName)}">
						<a href="message/sendmessage.jsp?id=${member.userName}">寄信給他</a>
					</c:if>	
				</c:when>
				<c:otherwise>
					<h2>查無此會員</h2>
				</c:otherwise>
				</c:choose>
	        </div>
			<%@include file="/include/blockPart" %>
         </div>
        </div>
	</article>
	<footer>
	
	</footer>
	<%@include file="/include/modal" %>


</body>
<script>
$("#sectionItem1").addClass("active");
</script>
</html>