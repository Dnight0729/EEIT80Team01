	$().ready(function(){
			
		    $("#reset").click(function() {
		    	$("#form").validate().resetForm();
		    });
			
		
			$("#form").validate({
					submitHandler:function(form) {
						form.submit();
					},
					rules:{						
						firstName:{
							required:true
						},
						lastName:{
							required:true
						},
						email:{
							required: true,
						    email: true
						}
					},//end of rules
					messages: {

						firstName:{
							required: "名字未輸入"
						},
						lastName:{
							required: "姓氏未輸入"
						},
						email:{
							required: "請輸入E-mail",
						    email: "請輸入正確的E-mail格式"
						}
					}
			
			});//end of validate
		})