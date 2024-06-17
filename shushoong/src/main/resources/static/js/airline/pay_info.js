	
	function adultNum(){
			const urlParams = new URL(location.href).searchParams;
			const adult = urlParams.get('adult');
			var htmlVal = '';
			
			console.log(adult)
			
			for(var  i = 0; i < adult; i++){
				console.log("adult");
				htmlVal+=`
					<section class="passenger_wrap">
						<div>탑승자 : 성인 : ${i+1}</div>
						<div class="passenger_info">
							<div class="passenger_info_flex">
								<div>
									<div>성별</div>
									<div>
										<input type="radio" name="passenger_gender" id="male" value="M"><label for="male" >남성</label>
										<input type="radio" name="passenger_gender" id="female" value="F"><label for="female">여성</label>
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
										<input type="text" placeholder="생년월일" name="passenger_lastName">
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
								<div>
									<div>
										<div>여권번호</div>
										<div>(Passport Num)</div>
									</div>
									<div><input type="text" placeholder="여권 번호"></div>
									<div>
										<div>여권 만료일</div>
										<div>(Expired Date)</div>
									</div>
									<div class="input_type_date">
										<input type="text" placeholder="만료 날짜" name="passenger_lastName">
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
										<input id="adult_10kg${i}" type="radio" name="adult_baggage_size${i}" value="1" checked="checked">
									</label>
									<label for="adult_20kg${i}">
										<div class="kg20"></div>
										<div><div>KRW :</div><div>48,000</div></div>
										<input id="adult_20kg${i}" type="radio" name="adult_baggage_size${i}" value="2">
									</label>
									<label for="adult_30kg${i}">
										<div class="kg30"></div>
										<div><div>KRW :</div><div>64,000</div></div>
										<input id="adult_30kg${i}" type="radio" name="adult_baggage_size${i}" value="3">
									</label>
									<label for="adult_40kg${i}">
										<div class="kg40"></div>
										<div><div>KRW :</div><div>90,000</div></div>
										<input id="adult_40kg${i}" type="radio" name="adult_baggage_size${i}" value="4">
									</label>
								</div>
							</div>
						</div>
					</section>
				`;
				
				$(".adult_list_wrap").html(htmlVal)
			}
		}
		
		
		
		
		
		function childNum(){
			const urlParams = new URL(location.href).searchParams;
			const child = urlParams.get('child');
			var htmlVal = '';
			
			console.log(child)
			
			for(var  i = 0; i < child; i++){
				console.log("child");
				htmlVal+=`
					<section class="passenger_wrap">
						<div>탑승자 : 소아 : ${i+1}</div>
						<div class="passenger_info">
							<div class="passenger_info_flex">
								<div>
									<div>성별</div>
									<div>
										<input type="radio" name="passenger_gender" value="M">남성
										<input type="radio" name="passenger_gender" value="F">여성
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
										<input type="text" placeholder="생년월일" name="passenger_lastName">
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
								<div>
									<div>
										<div>여권번호</div>
										<div>(Passport Num)</div>
									</div>
									<div><input type="text" placeholder="여권 번호"></div>
									<div>
										<div>여권 만료일</div>
										<div>(Expired Date)</div>
									</div>
									<div class="input_type_date">
										<input type="text" placeholder="만료 날짜" name="passenger_lastName">
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
										<input id="child_10kg${i}" type="radio" name="child_baggage_size${i}" value="1" checked="checked">
									</label>
									<label for="child_20kg${i}">
										<div class="kg20"></div>
										<div><div>KRW :</div><div>48,000</div></div>
										<input id="child_20kg${i}" type="radio" name="child_baggage_size${i}" value="2">
									</label>
									<label for="child_30kg${i}">
										<div class="kg30"></div>
										<div><div>KRW :</div><div>64,000</div></div>
										<input id="child_30kg${i}" type="radio" name="child_baggage_size${i}" value="3">
									</label>
									<label for="child_40kg${i}">
										<div class="kg40"></div>
										<div><div>KRW :</div><div>90,000</div></div>
										<input id="child_40kg${i}" type="radio" name="child_baggage_size${i}" value="4">
									</label>
								</div>
							</div>
						</div>
					</section>
				`;
				
				$(".child_list_wrap").html(htmlVal)
			}
		}
		
		function babyNum(){
			const urlParams = new URL(location.href).searchParams;
			const baby = urlParams.get('baby');
			var htmlVal = '';
			
			console.log(baby)
			
			for(var  i = 0; i < baby; i++){
				console.log("baby");
				htmlVal+=`
					<section class="passenger_wrap">
						<div>탑승자 : 유아 : ${i+1}</div>
						<div class="passenger_info">
							<div class="passenger_info_flex">
								<div>
									<div>성별</div>
									<div>
										<input type="radio" name="passenger_gender" value="M">남성
										<input type="radio" name="passenger_gender" value="F">여성
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
										<input type="text" placeholder="생년월일" name="passenger_lastName">
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
								<div>
									<div>
										<div>여권번호</div>
										<div>(Passport Num)</div>
									</div>
									<div><input type="text" placeholder="여권 번호"></div>
									<div>
										<div>여권 만료일</div>
										<div>(Expired Date)</div>
									</div>
									<div class="input_type_date">
										<input type="text" placeholder="만료 날짜" name="passenger_lastName">
									</div>
								</div>
							</div>
						</div>
						
						<div class="select_baggage">
							<input id="baby_30kg${i}" type="hidden" name="child_baggage_size${i}" value="1">
						</div>
					</section>
				`;
				
				$(".baby_list_wrap").html(htmlVal)
			}
		}
	