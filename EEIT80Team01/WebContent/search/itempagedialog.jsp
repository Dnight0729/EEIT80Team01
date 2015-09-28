<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div id="dialog" title="會員資料">
	        
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
				      height:600,
				      width:800,
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
				      $.post("${pageContext.request.contextPath}/search/member.jsp",{"id":"${item.seller}"},
				    	function(data){
				    	  $( "#dialog" ).html(data);
				    	  $( "#dialog" ).dialog( "open" );
				      }	  
				      )
				    });
				  });
				</script>