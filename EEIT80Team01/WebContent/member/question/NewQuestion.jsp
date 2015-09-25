<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>向客服發問</title>
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
<script src="http://cdn.ckeditor.com/4.5.3/basic/ckeditor.js"></script>
<body>
	<header>
		<%@include file="/include/header"%>
	</header>
	<article>
		<div class="container-fluid">
			<div class="row">
				<%@include file="/include/navPart"%>
				<div class="col-md-7 main" id="contentPart">
					<form action="createQuestion.do" method="post">
						<fieldset>
							<legend>檢舉/諮詢</legend>
							<div class="form-group">
								<label>標題：</label><input class="form-control" type="text" name="qtitle" style="width:100%">
								&nbsp;<small><font color="red" size="-3">${errors.errorTitle}</font></small>
							</div>
							<label>內容：</label>
							<textarea name="qmsg" id="editor1" rows="10" cols="60"></textarea>
							&nbsp;<small><font color="red" size="-3">${errors.errorQMsg}</font></small>
							<script>
								CKEDITOR.replace('editor1');
							</script>
							<div class="form-group">
								<input type="submit" id="submit" value="送出"
									class="btn btn-default"> <input type="reset" id="reset"
									value="清除" class="btn btn-danger">
							</div>
						</fieldset>
					</form>
					<c:if test="${!empty questionSuccess || !empty quesstionFailed }">
					<div id="dialog" title="${questionSuccess}${quesstionFailed}">
					<H3><Font color='red'>${questionSuccess}</Font></H3>
					<H3><Font color='red'>${quesstionFailed}</Font></H3>					
					</div>
					<script>
					$(function() {
				    $( "#dialog" ).dialog({
				      hide: {
				        effect: "fade",
				        duration: 500
				      },
				      height:200,
				      width:300,
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
				    })
				  });
					</script>
					</c:if>
				</div>
				<%@include file="/include/blockPart"%>
			</div>
		</div>
	</article>
</body>
<script>
$("#sectionItem4").addClass("active");
</script>
</html>