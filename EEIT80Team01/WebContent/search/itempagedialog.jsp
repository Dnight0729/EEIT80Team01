<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div id="dialog" title="會員資料">
	        	<c:choose>
  				<c:when test="${!empty member}">
  				<div class="col-md-12 ">
	        	<h2>會員資料</h2>	        	
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
					<c:if test="${!member.userName.equals(LoginOK.userName)}">
						<a href="${pageContext.request.contextPath}/member/message/sendmessage.jsp?id=${member.userName}" target="_blank">寄信給他</a>
					</c:if>	
				</div>		
				</c:when>
				<c:otherwise>
					<h2>查無此會員</h2>
				</c:otherwise>
				</c:choose>
</div>				
				
				<script>
				$(function() {
				    $( "#dialog" ).dialog({
				      autoOpen: false,
				      show: {
				        effect: "fade",
				        duration: 500
				      },
				      hide: {
				        effect: "blind",
				        duration: 500
				      },
				      height:400,
				      width:500,
				      modal: true,
				      closeOnEscape: false,//按ESC不能關閉
				      open: function(event, ui) {
				    	    //隱藏「x」關閉按鈕
				    	    $(this).parent().children().children('.ui-dialog-titlebar-close').hide();
				    	    },
				      buttons: {
				    	  "關閉":function() {
				              $( this ).dialog( "close" );
				          }
				      }
				    });
				 
				    $( "#opener" ).click(function() {
				      $( "#dialog" ).dialog( "open" );
				    });
				  });
				</script>