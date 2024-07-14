function adultNum(){
			var adult = $("#adult_val").val();
			
			var htmlVal = '';
			
			for(var  i = 0; i < adult; i++){
				htmlVal+=`
					<section class="passenger_wrap">
						<div>탑승자 : 성인 : ${i+1}</div>
						<div class="passenger_info">
							<div class="passenger_info_flex">
								<div>
									<div>성별</div>
									<div>
										<input type="radio" checked="checked" class="passenger_gender" name="passenger_gender_adult${i}" id="male_adult${i}" value="M"><label for="male_adult${i}" >남성</label>
										<input type="radio" class="passenger_gender" name="passenger_gender_adult${i}" id="female_adult${i}" value="F"><label for="female_adult${i}">여성</label>
										<input type="hidden" name="passenger_gender" value="M">
									</div>
								</div>
								<div>
									<div>
										<div>영문 이름</div> 
										<div>(First Name)</div>
									</div>
									<div>
										<input type="text" placeholder="이름을 영어로 입력해 주세요" name="passenger_firstName">
										<div class="errorJ">1~15 영어만 사용하실 수 있습니다</div>
									</div>
									<div>
										<div>영문 성 </div>
										<div>(Last Name)</div>
									</div>
									<div>
										<input type="text" placeholder="성씨를 영어로 입력해 주세요" name="passenger_lastName">
										<div class="errorJ">1~20 영어만 사용하실 수 있습니다</div>
									</div>
								</div>
								<div>
									<div>
										<div>생년월일</div>
										<div>(Birth)</div>
									</div>
									<div class="input_type_date">
										<input type="text" placeholder="생년월일을 'YYYYMMDD'형태로 입력해 주세요" name="passenger_birth">
										<div class="errorJ">숫자 8자만 사용하실 수 있습니다</div>
									</div>
									<div>
										<div>국적 </div>
										<div>(Nation)</div>
									</div>
									<div>
										<select name="passenger_nation">
											<option selected value="0">선택하기</option>
								            <option value="한국">korean</option>
								            <option value="미국">american</option>
								            <option value="중국">chinese</option>
								            <option value="일본">japanese</option>
										</select>
									</div>
								</div>
								<div class="passport_info_wrap">
									<div>
										<div>여권번호</div>
										<div>(Passport Num)</div>
									</div>
									<div>
										<input type="text" placeholder="여권 번호를 적어주세요" name="passport_num">
										<div class="errorJ">숫자 8자만 사용하실 수 있습니다</div>
									</div>
									<div>
										<div>여권 만료일</div>
										<div>(Expired Date)</div>
									</div>
									<div class="input_type_date">
										<input type="text" placeholder="만료 날짜를 'YYYYMMDD'형태로 입력해 주세요" name="expiration_date">
										<div class="errorJ">숫자 8자만 사용하실 수 있습니다</div>
									</div>
								</div>
							</div>
						</div>
						
						<div class="select_baggage">
							<div>수하물 선택하기</div>
							<div class="select_baggage_flex">
								<div>
									<div>~10kg</div>
									<div>~20kg</div>
									<div>~30kg</div>
									<div>~40kg</div>
								</div>
								<div>
									<label for="adult_10kg${i}">
										<div class="kg10"></div>
										<div><div>KRW :</div><div>0</div></div>
										<input class="baggage" id="adult_10kg${i}" type="radio" name="adult_baggage_size${i}" value="1" checked="checked">
									</label>
									<label for="adult_20kg${i}">
										<div class="kg20"></div>
										<div><div>KRW :</div><div>48,000</div></div>
										<input class="baggage" id="adult_20kg${i}" type="radio" name="adult_baggage_size${i}" value="2">
									</label>
									<label for="adult_30kg${i}">
										<div class="kg30"></div>
										<div><div>KRW :</div><div>64,000</div></div>
										<input class="baggage" id="adult_30kg${i}" type="radio" name="adult_baggage_size${i}" value="3">
									</label>
									<label for="adult_40kg${i}">
										<div class="kg40"></div>
										<div><div>KRW :</div><div>90,000</div></div>
										<input class="baggage" id="adult_40kg${i}" type="radio" name="adult_baggage_size${i}" value="4">
									</label>
									<input type="hidden" name="baggage_size" value="1">
									<input type="hidden" name="reserve_code" >
								</div>
							</div>
						</div>
					</section>
				`;
				
				$(".adult_list_wrap").html(htmlVal);

			}
		}
		
		
		
		
		
		function childNum(){
			var child = $("#child_val").val();
			
			var htmlVal = '';
			
			for(var  i = 0; i < child; i++){
				htmlVal+=`
					<section class="passenger_wrap">
						<div>탑승자 : 소아 : ${i+1}</div>
						<div class="passenger_info">
							<div class="passenger_info_flex">
								<div>
									<div>성별</div>
									<div>
										<input type="radio" checked="checked" class="passenger_gender" name="passenger_gender_child${i}" id="male_child${i}" value="M"><label for="male_child${i}" >남성</label>
										<input type="radio" class="passenger_gender" name="passenger_gender_child${i}" id="female_child${i}" value="F"><label for="female_child${i}">여성</label>
										<input type="hidden" name="passenger_gender" value="M">
									</div>
								</div>
								<div>
									<div>
										<div>영문 이름</div> 
										<div>(First Name)</div>
									</div>
									<div>
										<input type="text" placeholder="이름을 영어로 입력해 주세요" name="passenger_firstName">
										<div class="errorJ">1~15 영어만 사용하실 수 있습니다</div>									
									</div>
									<div>
										<div>영문 성 </div>
										<div>(Last Name)</div>
									</div>
									<div>
										<input type="text" placeholder="성씨를 영어로 입력해 주세요" name="passenger_lastName">
										<div class="errorJ">1~20 영어만 사용하실 수 있습니다</div>
									</div>
								</div>
								<div>
									<div>
										<div>생년월일</div>
										<div>(Birth)</div>
									</div>
									<div class="input_type_date">
										<input type="text" placeholder="생년월일을 'YYYYMMDD'형태로 입력해 주세요" name="passenger_birth">
										<div class="errorJ">숫자 8자만 사용하실 수 있습니다</div>
									</div>
									<div>
										<div>국적 </div>
										<div>(Nation)</div>
									</div>
									<div>
										<select name="passenger_nation">
											<option selected value="0">선택하기</option>
								            <option value="한국">korean</option>
								            <option value="미국">american</option>
								            <option value="중국">chinese</option>
								            <option value="일본">japanese</option>
										</select>
									</div>
								</div>
								<div class="passport_info_wrap">
									<div>
										<div>여권번호</div>
										<div>(Passport Num)</div>
									</div>
									<div>
										<input type="text" placeholder="여권 번호를 적어주세요" name="passport_num">
										<div class="errorJ">숫자 8자만 사용하실 수 있습니다</div>
									</div>
									<div>
										<div>여권 만료일</div>
										<div>(Expired Date)</div>
									</div>
									<div class="input_type_date">
										<input type="text" placeholder="만료 날짜를 'YYYYMMDD'형태로 입력해 주세요" name="expiration_date">
										<div class="errorJ">숫자 8자만 사용하실 수 있습니다</div>
									</div>
								</div>
							</div>
						</div>
						
						<div class="select_baggage">
							<div>수하물 선택하기</div>
							<div class="select_baggage_flex">
								<div>
									<div>~10kg</div>
									<div>~20kg</div>
									<div>~30kg</div>
									<div>~40kg</div>
								</div>
								<div>
									<label for="child_10kg${i}">
										<div class="kg10"></div>
										<div><div>KRW :</div><div>0</div></div>
										<input class="baggage" id="child_10kg${i}" type="radio" name="child_baggage_size${i}" value="1" checked="checked">
									</label>
									<label for="child_20kg${i}">
										<div class="kg20"></div>
										<div><div>KRW :</div><div>48,000</div></div>
										<input  class="baggage"id="child_20kg${i}" type="radio" name="child_baggage_size${i}" value="2">
									</label>
									<label for="child_30kg${i}">
										<div class="kg30"></div>
										<div><div>KRW :</div><div>64,000</div></div>
										<input class="baggage" id="child_30kg${i}" type="radio" name="child_baggage_size${i}" value="3">
									</label>
									<label for="child_40kg${i}">
										<div class="kg40"></div>
										<div><div>KRW :</div><div>90,000</div></div>
										<input class="baggage" id="child_40kg${i}" type="radio" name="child_baggage_size${i}" value="4">
									</label>
									<input type="hidden" name="baggage_size" value="1">
									<input type="hidden" name="reserve_code" >

								</div>
							</div>
						</div>
					</section>
				`;
				
				$(".child_list_wrap").html(htmlVal)
		
			}
		}
		
		function babyNum(){
			var baby = $("#baby_val").val();
			var htmlVal = '';
			
			$(".babyNum").text(baby);   /*별개의 내용*/
			
			for(var  i = 0; i < baby; i++){
				htmlVal+=`
					<section class="passenger_wrap baby">
						<div>탑승자 : 유아 : ${i+1}</div>
						<div class="passenger_info">
							<div class="passenger_info_flex">
								<div>
									<div>성별</div>
									<div>
										<input type="radio" checked="checked" class="passenger_gender" name="passenger_gender_baby${i}" id="male_baby${i}" value="M"><label for="male_baby${i}" >남성</label>
										<input type="radio" class="passenger_gender" name="passenger_gender_baby${i}" id="female_baby${i}" value="F"><label for="female_baby${i}">여성</label>
										<input type="hidden" name="passenger_gender" value="M">
									</div>
								</div>
								<div>
									<div>
										<div>영문 이름</div> 
										<div>(First Name)</div>
									</div>
									<div>
										<input type="text" placeholder="이름을 영어로 입력해 주세요" name="passenger_firstName">
										<div class="errorJ">1~15 영어만 사용하실 수 있습니다</div>
									</div>
									<div>
										<div>영문 성 </div>
										<div>(Last Name)</div>
									</div>
									<div>
										<input type="text" placeholder="성씨를 영어로 입력해 주세요" name="passenger_lastName">
										<div class="errorJ">1~20 영어만 사용하실 수 있습니다</div>									
									</div>
								</div>
								<div>
									<div>
										<div>생년월일</div>
										<div>(Birth)</div>
									</div>
									<div class="input_type_date">
										<input type="text" placeholder="생년월일을 'YYYYMMDD'형태로 입력해 주세요" name="passenger_birth">
										<div class="errorJ">숫자 8자만 사용하실 수 있습니다</div>
									</div>
									<div>
										<div>국적 </div>
										<div>(Nation)</div>
									</div>
									<div>
										<select name="passenger_nation">
											<option selected value="0">선택하기</option>
								            <option value="한국">korean</option>
								            <option value="미국">american</option>
								            <option value="중국">chinese</option>
								            <option value="일본">japanese</option>
										</select>
									</div>
								</div>
								<div class="passport_info_wrap">
									<div>
										<div>여권번호</div>
										<div>(Passport Num)</div>
									</div>
									<div>
										<input type="text" placeholder="여권 번호를 적어주세요" name="passport_num">
										<div class="errorJ">숫자 8자만 사용하실 수 있습니다</div>
									</div>
									<div>
										<div>여권 만료일</div>
										<div>(Expired Date)</div>
									</div>
									<div class="input_type_date">
										<input type="text" placeholder="만료 날짜를 'YYYYMMDD'형태로 입력해 주세요" name="expiration_date">
										<div class="errorJ">숫자 8자만 사용하실 수 있습니다</div>
									</div>
									<input type="hidden" name="baggage_size" value="1">
									<input type="hidden" name="reserve_code" >
								</div>
							</div>
						</div>
						
					</section>
				`;
				
				$(".baby_list_wrap").html(htmlVal)
			}
		}
		
	
		
/*약관 상세보기*/
function arrowDownClickHandler(){
	console.log($(this).parents().eq(1).next());
	
	var downArrow = $(this).attr('src');
	
	if(downArrow == '/shushoong/images/down_arrow.png'){
		$(this).attr("src", "/shushoong/images/up_arrow.png");
		$(this).parents().eq(1).next().show();
		$(this).parents().eq(1).next().css('margin-bottom', '20px')
		
	} else {
		$(this).attr("src", "/shushoong/images/down_arrow.png");
		$(this).parents().eq(1).next().hide();
	}

}

/*전체동의*/
function allAgreeCheckHandler(){
	console.log($(this).parent().siblings().eq(12).children('div:even').children());

	if(this.checked) {
			$('.agree_radio').prop('checked', true);
			checkAgreeFunction();
		} else {
			$('.disagree_radio').prop('checked', true);
			checkAgreeFunction();
		}
}

/*동의합니다 개별 체크 다 누를 시 전체동의 선택되게 하기*/
function checkAllEscHandler(){
	console.log(this);
	
	if(this.checked){
		$('#check1').prop('checked', false);
	}
	
	var checkEsc = document.querySelectorAll('.agree_radio:checked');
	console.log(checkEsc.length);
	
	if(checkEsc.length == 5) {
		$('#check1').prop('checked', true);
	} else {
		$('#check1').prop('checked', false);
	}

}


		
		
		function totalValueFunction(){
			
			var result = 0;
			var adult = $("#adult_val").val();
			var child = $("#child_val").val();
			var baby = $("#baby_val").val();
			var seatPrice = $("#seatPrice_val").val();
			var seatPriceRetrun = $("#seatPriceReturn_val").val();
			var sum;
			var basePrice = Number(seatPrice*(Number(adult)+Number(child*0.75)+Number(baby*0.1)));
			var basePriceReturn = Number(seatPriceRetrun*(Number(adult)+Number(child*0.75)+Number(baby*0.1)));
			$(".baggage:checked").each(function(){
				if($(this).val()==1){
					
				}else if($(this).val()==2){
					result += 48000
				}else if($(this).val()==3){
					result += 64000
				}else if($(this).val()==4){
					result += 90000
				}else{
					console.log("total value function on error")
				}
				
				$(this).val();
			});
			sum = Number(result)+Number(basePrice)+Number(basePriceReturn);
			
			$(".total_value").val(sum);
			refreshNumberCommaHandler();
		}
		
		function radioGenderInputValueFunction(){
			$(".passenger_gender:checked").each(function(){
				$(this).parent().children("[name='passenger_gender']").val($(this).val());
			});
		}
		function raidoBaggageInputValueFunction(){
			$(".baggage:checked").each(function(){
				$(this).parent().parent().children("[name='baggage_size']").val($(this).val());
			});
		}
		
		
//		국내선일경우 passport 관련 정보 display 시키는 옵션
		function passportDisplayFunction(){
			if($("#domestic_val").val()=='D'){
				$(".passport_info_wrap").each(function (){
					$(this).css("display","none");
				});
			}else if($("#domestic_val").val()=='I'){
				$(".passport_info_wrap").each(function (){
					$(this).css("display","flex");
				});
			}else{
				console.log("표시는 되어 있지만 passport 가 오류나서 보이고 있습니다.")
			}
			
		}
		
		
		function refreshNumberCommaHandler(){
			var money = $('input#number').val();
			var money2 = money.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
			$('input#number').val(money2);
			
		}
		
function reserverInfoInsertFunction(){
		
		const now = new Date();
		const year = now.getFullYear();
		const month = String(now.getMonth() + 1).padStart(2, '0');
		const day = String(now.getDate()).padStart(2, '0');
		const currentTime = year + month + day;    

		var airline_reserve_code_str = $('.flightNo').text();

		// 문자열의 길이 구하기
		var length = airline_reserve_code_str.length;

		// 마지막 4자리를 추출
		var airlineReserveCode = currentTime + airline_reserve_code_str.slice(length - 4);

		var userId = $("[name=reserver_name]").val();
		var reservePhone = $(".reserve_phone").val();
		var reserveEmail = $(".reserve_email").val();
		var departDate = $('#one_way_data').val();
		var arrivalDate = $('#around_data').val();
		
		var reserverInfo = {
		    airlineReserveCode: airlineReserveCode,
		    userId: userId,
		    reservePhone: reservePhone,
		    reserveEmail: reserveEmail,
		    departDate : departDate,
		    arrivalDate : arrivalDate
		};

		var reserverInfoString = JSON.stringify(reserverInfo);

		//예약자 정보 먼저 저장	    
	    $.ajax({
	        url: "/shushoong/airline/input/reserverInfo",
	        type: "POST",
	        contentType: "application/json",
	        data: reserverInfoString,
	        success: function(response) {
	            console.log(response);
	            if(response.result == "1") {
	                console.log("성공성공성공");	                
	                const airlineReserveCode = response.airlineReserveCode;
	                passengerInfoInsertFunction(airlineReserveCode); // airlineReserveCode를 함수로 전달
	            } else {
	                alert("데이터 리스트 값 insert 실패");
	            }
	        },
			error:function (request, status, error){
				alert("code: "+request.status + "\n" + "message: " 
						+ request.responseText + "\n"
						+ "error: "+error);
			}
	    })
	}
		
	//승객 정보
async function passengerInfoInsertFunction(airlineReserveCode){

		const storeId = $('.store_id').val();
		const channelKey = $('.channel_key').val();
		
		var airlineName = $('#airline_name_val').val();
		var departLoc = $('#depart_loc_val').val();
		var arrivalLoc = $('#arrival_loc_val').val();
		
		var userId = $('.user_id').val();
		
		var orderName = airlineName + departLoc + ", "+ arrivalLoc;
//		var airlinePrice = $('.total_value').val();
		var airlinePrice = '100';
		
		var passengerInfo = [];
		var i;
		for(i = 0 ; i< $("[name=passenger_firstName]").length; i++){
			var infoObj = new Object();
				infoObj.airlineReserveCode = airlineReserveCode;
			infoObj.gender = $("[name=passenger_gender]").eq(i).val();
			infoObj.firstName = $("[name=passenger_firstName]").eq(i).val();
			infoObj.lastName = $("[name=passenger_lastName]").eq(i).val();
			infoObj.birth = $("[name=passenger_birth]").eq(i).val();
			infoObj.nation = $("[name=passenger_nation]").eq(i).val();
			infoObj.baggage = $("[name=baggage_size]").eq(i).val();
			infoObj.passportCode = $("[name=passport_num]").eq(i).val();
				infoObj.passportDate = $("[name=expiration_date]").eq(i).val();
	
			passengerInfo.push(infoObj);
		}
		
	const response = await PortOne.requestPayment({
			storeId : storeId, 
			paymentId : airlineReserveCode,
			orderName : orderName, // 항공사 + 출발지 + 목적지
			totalAmount : airlinePrice,
			currency : "CURRENCY_KRW",
			channelKey : channelKey,
			payMethod : "CARD",
			customer : {
				fullName : userId	
			}
		});
			if (response.code) {
			// 오류 발생
				console.log('결제 오류');
		} else {
			if(response.paymentId != airlineReserveCode){
				alert("예약번호 다름. 다시 해");
				return;
			}
			
			// 결제 검증 - 위에서 선언한거를 데이터로 보냄(결제 정보 외 것들)
			$.ajax({
				url:"/shushoong/airline/input/passengerInfo",
				type : "POST",
				data: JSON.stringify(passengerInfo),
				contentType: "application/json; charset=utf-8",
				success: function(result){
					if(result == i){
						location.href = "/shushoong/airline/customer/reserve/pay/success";
						return;
					}else{
						alert("데이터 리스트 값 insert 실패");
						return;
					}
				},
				error:function (request, status, error){
					alert("code: "+request.status + "\n" + "message: " 
							+ request.responseText + "\n"
							+ "error: "+error);
				}
		       });
		}
	
		


}
		
function checkAgreeFunction(){
		var allAgreed = $('.agree_radio').toArray().every(radio => radio.checked && radio.value === 'agree');

		if(allAgreed){
			$("button#reserver_info_insert").attr("disabled",false);
			console.log('이제 눌림 ㅋ')
		}
		else{
			$("button#reserver_info_insert").attr("disabled",true);
			console.log('버튼 못누름 ㅋ');
		}
		
	}