	//정규 표현식
	var firstNameJ = /^[A-Z]{1,15}$/;
	var lastNameJ = /^[a-zA-Z]{1,20}$/;
	var phoneNumJ = /^\d{10,11}$/;
	var dateJ = /^[0-9]{8}$/;
	var emailJ =  /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;
	var passportJ = /^[0-9A-Z]{9}$/;
	var changedDateJ = /^\d{4}-\d{2}-\d{2}$/;
	var changedPhoneNumJ = /^\d{3}-\d{3,4}-\d{4}$/;


	function regexTestFunction(){
			
//			 checkpoint : 정규표현식 - 예약자 정보
			
			$("[name=reserver_phone_number]").blur(function(){ //휴대전화번호
				if(phoneNumJ.test($(this).val())){
					if(($(this).val()).length==11){
						var phoneNum = $('[name=reserver_phone_number]').val();
						var phoneNum2 = phoneNum.substring(0,3)+"-"+phoneNum.substring(3, 7)+ "-"+ phoneNum.substring(7, 11);
						$('[name=reserver_phone_number]').val(phoneNum2);
					}else if(($(this).val()).length==10){
						var phoneNum = $('[name=reserver_phone_number]').val();
						var phoneNum2 = phoneNum.substring(0,3)+"-"+phoneNum.substring(3, 6)+ "-"+ phoneNum.substring(6, 10);
						$('[name=reserver_phone_number]').val(phoneNum2);
					}
					$(this).parent().parent().css("height","60px")
					$(this).parent().children(".errorJ").css("display","none")
				}else if(changedPhoneNumJ.test($(this).val())){
					console.log("changed ok")
					$(this).parent().parent().css("height","60px")
					$(this).parent().children(".errorJ").css("display","none")
				}else{
					
					$(this).parent().parent().css("height","70px")
					$(this).parent().children(".errorJ").css("display","block")
				}
			})
			$("[name=reserver_email]").blur(function(){ //이메일 주소
				if(emailJ.test($(this).val())){
					
					$(this).parent().parent().css("height","60px")
					$(this).parent().children(".errorJ").css("display","none")
				}else{
					
					$(this).parent().parent().css("height","70px")
					$(this).parent().children(".errorJ").css("display","block")
				}
			});
			
	
//			 checkpoint : 정규표현식 - 탑승자 정보
			
			
			$("input[name=passenger_firstName]").blur(function(){ //영문 이름
				$(this).val($(this).val().toUpperCase());
				if(firstNameJ.test($(this).val())){
					if(firstNameJ.test($(this).parent().parent().children().children("input[name=passenger_lastName]").val())){
						$(this).parent().parent().css("height","60px")
					}
					$(this).parent().children(".errorJ").css("display","none")
				}else{
					
					$(this).parent().parent().css("height","70px")
					$(this).parent().children(".errorJ").css("display","block")
				}
			})
			$("input[name=passenger_lastName]").blur(function(){ //영문 성
				$(this).val($(this).val().toUpperCase());
				if(lastNameJ.test($(this).val())){
					if(firstNameJ.test($(this).parent().parent().children().children("input[name=passenger_firstName]").val())){
						$(this).parent().parent().css("height","60px")
					}
					$(this).parent().children(".errorJ").css("display","none")
				}else{
					
					$(this).parent().parent().css("height","70px")
					$(this).parent().children(".errorJ").css("display","block")
				}
			})
		
		
			$("input[name=passenger_birth]").blur(function(){ //생년월일
				
				if(dateJ.test($(this).val())){
					console.log($(this).val());
					var birth = $(this).val();
					var birth2 = birth.substring(0,4)+"-"+birth.substring(4, 6)+ "-"+ birth.substring(6, 8);
					$(this).val(birth2);
					
					$(this).parent().parent().css("height","60px")
					$(this).parent().children(".errorJ").css("display","none")
				}else if(changedDateJ.test($(this).val())){
					console.log("changed ok")
					$(this).parent().parent().css("height","60px")
					$(this).parent().children(".errorJ").css("display","none")
				}else{
					$(this).parent().parent().css("height","70px")
					$(this).parent().children(".errorJ").css("display","block")
				}
			})
			$("input[name=passport_num]").blur(function(){ //여권번호

				$(this).val($(this).val().toUpperCase());
				if(passportJ.test($(this).val())){
					if(changedDateJ.test($(this).parent().parent().children().children("input[name=expiration_date]").val())){
						$(this).parent().parent().css("height","60px")
					}
					$(this).parent().children(".errorJ").css("display","none")
					
					
				}else{
					$(this).parent().parent().css("height","70px")
					$(this).parent().children(".errorJ").css("display","block")
				}
			})
			$("input[name=expiration_date]").blur(function(){ //여권 만료일
				
				if(dateJ.test($(this).val())){
					var expiredDate = $(this).val();
					var expiredDate2 = expiredDate.substring(0,4)+"-"+expiredDate.substring(4, 6)+ "-"+ expiredDate.substring(6, 8);
					$(this).val(expiredDate2);
					if(passportJ.test($(this).parent().parent().children().children("input[name=passport_num]").val())){
						$(this).parent().parent().css("height","60px")
					}
					$(this).parent().children(".errorJ").css("display","none")
				}else if(changedDateJ.test($(this).val())){
					if(passportJ.test($(this).parent().parent().children().children("input[name=passport_num]").val())){
						$(this).parent().parent().css("height","60px")
					}
					$(this).parent().children(".errorJ").css("display","none")
				}else{
					
					$(this).parent().parent().css("height","70px")
					$(this).parent().children(".errorJ").css("display","block")
				}
			})
			
			
			
		
	}
	
	function submitBtnCheckFunction(){
		var domestic = $("#domestic_val").val();
		var list = Number($("#adult_val").val())+Number($("#child_val").val())+Number($("#baby_val").val());
		console.log(list);
		var i,firC , lasC , birC , natC , pasC , expC;
		
		$("input[type=text]").blur();
		if(domestic == "O"){
			
		}
		
		for(i=0,firC=0,lasC=0,birC=0,natC=0,pasC=0,expC=0 ;i<list;i++){
			console.log($("input[name=passenger_firstName]").eq(i).val());
			console.log(firstNameJ.test( $("input[name=passenger_firstName]").eq(i).val()));
			if(firstNameJ.test( $("input[name=passenger_firstName]").eq(i).val())){
				firC++
			}else{
				$("input[name=passenger_firstName]")[i].scrollIntoView({
					behavior : "smooth",
					block : "center"
				});
			}
			
			if(lastNameJ.test( $("input[name=passenger_lastName]").eq(i).val())){
				lasC++
			}else{
				$("input[name=passenger_lastName]")[i].scrollIntoView({
					behavior : "smooth",
					block : "center"
				});
			}
			
			if(changedDateJ.test( $("input[name=passenger_birth]").eq(i).val())){
				birC++
			}else{
				$("input[name=passenger_birth]")[i].scrollIntoView({
					behavior : "smooth",
					block : "center"
				});
			}
			
			if($("select[name=passenger_nation]").eq(i).val() != 0){
				natC++
			}else{
				$("select[name=passenger_nation]")[i].scrollIntoView({
					behavior : "smooth",
					block : "center"
				});
			}
			
			if(passportJ.test( $("input[name=passport_num]").eq(i).val())){
				pasC++
			}else{
				$("input[name=passport_num]")[i].scrollIntoView({
					behavior : "smooth",
					block : "center"
				});
			}
			
			if(changedDateJ.test( $("input[name=expiration_date]").eq(i).val())){
				expC++
			}else{
				$("input[name=expiration_date]")[i].scrollIntoView({
					behavior : "smooth",
					block : "center"
				});
			}
			
			
		}
		
		
		
		
		
			console.log("firC"+firC);
			console.log("lasC"+lasC);
			console.log("birC"+birC);
			console.log("natC"+natC);
			console.log("pasC"+pasC);
			console.log("expC"+expC);
			
//			결제창으로 이동
			if(changedPhoneNumJ.test($('[name=reserver_phone_number]').val())){	//휴대전화번호
				if(emailJ.test($("[name=reserver_email]").val())){//이메일 주소
						
//			국제선
					if(domestic == "I"){
						if(firC == list && lasC == list && birC == list && natC == list && pasC == list &&  expC == list){
							reserverInfoInsertFunction();
						}else{
							alert("기입을 부탁드립니다.");
						}
//			국내선
					}else if(domestic == "D"){
						if(firC == list && lasC == list && birC == list && natC == list){
							reserverInfoInsertFunction();
						}else{
							alert("기입을 부탁드립니다.");
						}
					}else{
						alert("죄송합니다. 국내/국제 인식 기능이 오류났습니다.");
					}
					
				}
			}
		
		
		
		
	}
				
		
		
//		reserverInfoInsertFunction();
