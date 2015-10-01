<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="member.model.*,items.model.*,java.util.Map,java.util.List" %>

<c:if test="${!empty param.id}">
  		<%
  			MemberService service = new MemberService();
			String id = request.getParameter("id");
			MemberBean mb = service.findMemberData(id);
			List<Map<String, Object>> list= null;
			if(mb!=null){
				list = service.selectMyItems(mb.getUserName());	
			}			 
			pageContext.setAttribute("member",mb);
			pageContext.setAttribute("myItems",list);
		%>
</c:if>

<div class="col-md-12 main" id="contentPart">

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
<script type="text/javascript">
	function tdUrl(num){
		window.location="${pageContext.request.contextPath}/search/item?itemid="+num;
	}
</script>
				</div>
				<div  class="col-md-12 ">
					<h2>最近上架中商品</h2>
						<table class="table table-bordered">
							<thead>
								<tr>
									<th>商品標題</th>
									<th>目前價格</th>
									<th>直購價格</th>
									<th>結標時間</th>
								</tr>
							</thead>
							<tbody>
					<c:if test="${empty myItems}">
						<tr>
							<td colspan="4">暫無上架中商品</td>
						</tr>
					</c:if>
					<c:forEach items="${myItems}" var="map">
						<tr onclick="tdUrl(${map.item.itemId})">
							<td>${map.item.title}</td>
							<td>${map.price}</td>
							<td>${map.item.directPrice}</td>
							<fmt:formatDate value="${map.item.endTime}" var="formatTime" pattern="yyyy年MM月dd日HH時mm分" />
							<td>${formatTime}</td>
						</tr>							
					</c:forEach>
							</tbody>
						</table>
					</div>
					<div  class="col-md-12 ">
					<c:if test="${!member.userName.equals(LoginOK.userName)}">
						<a href="${pageContext.request.contextPath}/message/sendmessage.jsp?id=${member.userName}" target="_blank">寄信給他</a>
					</c:if>	
					</div>
				</c:when>
				<c:otherwise>
				<div  class="col-md-12 ">
<img style="padding-left:10%" src="${pageContext.request.contextPath}/imgs/member.jpg" width="100%">

				</div>
				</c:otherwise>
				</c:choose>
</div>