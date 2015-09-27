$().ready(function(){
	 $("#reset").click(function() {
	    	$("#form").validate().resetForm();
	    });
	$("#form").validate({
			submitHandler:function(form) {
				form.submit();
			},
			rules:{
				oldPassword:{
					required:true,
					minlength: 5
				},
				password:{
					required:true,
					minlength: 5
				},
				passwordCheck:{
					required:true,
					minlength: 5,
					equalTo:"#password"
				}			
			},
			messages:{
				oldPassword:{
					required:"請輸入舊的密碼",
					minlength:"密碼至少五個字"
				},
				password:{
					required:"請輸入新的密碼",
					minlength:"密碼至少五個字"
				},
				passwordCheck:{
					required:"請輸入新的密碼",
					minlength:"密碼至少五個字",
					equalTo:"兩次密碼不相同，請確認密碼"
				}
				
			}
	});
	
	
		
});