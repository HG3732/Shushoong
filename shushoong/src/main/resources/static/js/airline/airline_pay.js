	
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
									<div><input type="text" placeholder="이름을 영어로 입력해 주세요" name="passenger_firstName"></div>
									<div>
										<div>영문 성 </div>
										<div>(Last Name)</div>
									</div>
									<div><input type="text" placeholder="성씨를 영어로 입력해 주세요" name="passenger_lastName"></div>
								</div>
								<div>
									<div>
										<div>생년월일 </div>
										<div>(Birth)</div>
									</div>
									<div class="input_type_date">
										<input type="text" placeholder="생년월일" name="passenger_birth">
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
									<div><input type="text" placeholder="여권 번호" name="passport_num"></div>
									<div>
										<div>여권 만료일</div>
										<div>(Expired Date)</div>
									</div>
									<div class="input_type_date">
										<input type="text" placeholder="만료 날짜" name="expiration_date">
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
									<div><input type="text" placeholder="여권에 적힌 이름을 영어로 입력해 주세요" name="passenger_firstName"></div>
									<div>
										<div>영문 성 </div>
										<div>(Last Name)</div>
									</div>
									<div><input type="text" placeholder="여권에 적힌 성씨를 영어로 입력해 주세요" name="passenger_lastName"></div>
								</div>
								<div>
									<div>
										<div>생년월일 </div>
										<div>(Birth)</div>
									</div>
									<div class="input_type_date">
										<input type="text" placeholder="생년월일" name="passenger_birth">
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
									<div><input type="text" placeholder="여권 번호" name="passport_num"></div>
									<div>
										<div>여권 만료일</div>
										<div>(Expired Date)</div>
									</div>
									<div class="input_type_date">
										<input type="text" placeholder="만료 날짜" name="expiration_date">
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
			console.log(baby);
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
									<div><input type="text" placeholder="여권에 적힌 이름을 영어로 입력해 주세요" name="passenger_firstName"></div>
									<div>
										<div>영문 성 </div>
										<div>(Last Name)</div>
									</div>
									<div><input type="text" placeholder="여권에 적힌 성씨를 영어로 입력해 주세요" name="passenger_lastName"></div>
								</div>
								<div>
									<div>
										<div>생년월일 </div>
										<div>(Birth)</div>
									</div>
									<div class="input_type_date">
										<input type="text" placeholder="생년월일" name="passenger_birth">
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
									<div><input type="text" placeholder="여권 번호" name="passport_num"></div>
									<div>
										<div>여권 만료일</div>
										<div>(Expired Date)</div>
									</div>
									<div class="input_type_date">
										<input type="text" placeholder="만료 날짜" name="expiration_date">
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
		
			if(this.checked) {
					$('.agree_radio').prop('checked', true);
				} else {
					$('.disagree_radio').prop('checked', true);
				}
		
		}
		
		/*동의합니다 개별 체크 다 누를 시 전체동의 선택되게 하기*/
		function checkAllEscHandler(){
			
			if(this.checked){
				$('#check1').prop('checked', false);
			}
			
			var checkEsc = document.querySelectorAll('.agree_radio:checked');
			
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
			console.log(basePrice);
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
		if($("#domestic_val").val()=='O'){
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
		
		
		