	//정규 표현식
	var fistNameJ = /^[A-Z]{1,15}$/;
	var lastNameJ = /^[a-zA-Z]{1,20}$/;
	var phoneNumJ = /^\d{10,11}$/;
	var dateJ = /^[0-9]{8}$/;
	var emailJ =  /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;
	var passportJ = /^[0-9A-Z]{9}$/;
	var changedDateJ = /^\d{4}$-\d{2}$-\d{2}$/;


	function regexTestFunction(){
			
			
			
			
			
			$("input[name=passenger_firstName]").blur(function(){
				$(this).val($(this).val().toUpperCase());
				if(fistNameJ.test($(this).val())){
					console.log("ok");
					$(this).parent().parent().css("height","60px")
					$(this).parent().children(".errorJ").css("display","none")
				}else{
					console.log("nooo");
					$(this).parent().parent().css("height","70px")
					$(this).parent().children(".errorJ").css("display","block")
				}
			})
			$("input[name=passenger_lastName]").blur(function(){
				$(this).val($(this).val().toUpperCase());
				if(lastNameJ.test($(this).val())){
					console.log("ok");
					$(this).parent().parent().css("height","60px")
					$(this).parent().children(".errorJ").css("display","none")
				}else{
					console.log("nooo");
					$(this).parent().parent().css("height","70px")
					$(this).parent().children(".errorJ").css("display","block")
				}
			})
		
			$("input[name=passport_num]").blur(function(){
				$(this).val($(this).val().toUpperCase());
				if(passportJ.test($(this).val())){
					console.log("ok");
					$(this).parent().parent().css("height","60px")
					$(this).parent().children(".errorJ").css("display","none")
				}else{
					console.log("nooo");
					$(this).parent().parent().css("height","70px")
					$(this).parent().children(".errorJ").css("display","block")
				}
			})
			$("input[name=passenger_birth]").blur(function(){
				
				if(dateJ.test($(this).val())){
					var birth = $('[name=passenger_birth]').val();
					var birth2 = birth.substring(0,4)+"-"+birth.substring(4, 6)+ "-"+ birth.substring(6, 8);
					$('[name=passenger_birth]').val(birth2);
					console.log("ok");
					$(this).parent().parent().css("height","60px")
					$(this).parent().children(".errorJ").css("display","none")
				}else{
					console.log("nooo");
					$(this).parent().parent().css("height","70px")
					$(this).parent().children(".errorJ").css("display","block")
				}
			})
			$("input[name=expiration_date]").blur(function(){
				
				if(dateJ.test($(this).val())){
					var expiredDate = $('[name=expiration_date]').val();
					var expiredDate2 = expiredDate.substring(0,4)+"-"+expiredDate.substring(4, 6)+ "-"+ expiredDate.substring(6, 8);
					$('[name=expiration_date]').val(expiredDate2);
					console.log("ok");
					$(this).parent().parent().css("height","60px")
					$(this).parent().children(".errorJ").css("display","none")
				}else{
					console.log("nooo");
					$(this).parent().parent().css("height","70px")
					$(this).parent().children(".errorJ").css("display","block")
				}
			})
			$("[name=reserver_phone_number]").blur(function(){
				if(phoneNumJ.test($(this).val())){
					console.log("ok");
				}else{
					console.log("no");
				}
			})
			$("[name=reserver_email]").blur(function(){
				if(emailJ.test($(this).val())){
					console.log("ok");
				}else{
					console.log("no");
				}
			});
	
	
	}