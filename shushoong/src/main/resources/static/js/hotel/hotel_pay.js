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
		} else {
			$('.disagree_radio').prop('checked', true);
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

async function payHandler(){
	const storeId = $('.store_id').val();
	const channelKey = $('.channel_key').val();
	
	const now = new Date();
    const year = now.getFullYear();
    const month = String(now.getMonth() + 1).padStart(2, '0');
    const day = String(now.getDate()).padStart(2, '0');
	const time = now.getSeconds();

    const currentTime = year + month + day + time;	
	var residenceNameKo = $('#name').val(); //투숙객명
	var residenceNameEn = $('#last_name').val() + $('#first_name').val();
	var residenceGender = $('input[name=gender]:checked').val();
	var residenceBirth = $('#birthday').val();
	var residencePhone = $('#phone').val();
	var residenceEmail = $('#email').val();
	
	var requestSum= 0;
	$('.require_check').children().children('input[name="checkbox"]:checked').each(function() {
        // val가 1,2,4,8, 비트 마스킹 값이므로 +더하기하면됨.
        requestSum += ($(this).val() * 1);
        //문자가 숫자로 인식
		});
	var reserveCheckIn = $('#check_in').val();
	var reserveCheckOut = $('#check_out').val();
	var userId = $('.user_id').val();
	var roomCap = $('.room_cap').val();
	var hotelCode = $('.hotel_code').val();
	var roomCat = $('.room_cat').val();
	var roomAtt = $('.room_att').val();
	var roomAttDesc = $('.room_att_desc').val();

	var roomCatDesc = $('.room_cat_desc').val();
	var hotelName = $('.hotel_name').text();
	//var hotelPriceStr = $('.hotel_price').val();
	var hotelPriceStr = '100';
	var hotelPrice = parseInt(hotelPriceStr, 10);
	
	var hotelReserveCode = currentTime + hotelCode + roomCat; //sysdate + hotelCode + room_cat
	var orderName = hotelName + ' ' + roomCatDesc;
	var people = $('.people').val();

	let reservationData = {
		residenceNameKo : residenceNameKo,
		residenceNameEn : residenceNameEn, 
		residenceGender : residenceGender,
		residencePhone : residencePhone,
		residenceEmail : residenceEmail,
		residenceBirth : residenceBirth,
		reserveCheckIn : reserveCheckIn,
		reserveCheckOut : reserveCheckOut,
		userId : userId,
		roomCap : roomCap,
		hotelCode : hotelCode,
		roomAtt : roomAtt,
		roomCat : roomCat,
		hotelReserveCode : hotelReserveCode,
		/*위에는 예약으로, 아래는 예약완료됐을때 띄워주기*/
		people : people,
		requestSum : requestSum		
	}
	
	const receipt = {
		roomAttDesc : roomAttDesc,
		roomCatDesc : roomCatDesc,
		hotelName : hotelName,
		hotelPrice : hotelPrice	
	};

	// reservationData 객체를 JSON 문자열로 변환
	let reservationDataString = JSON.stringify(reservationData);
	
	console.log(residenceBirth);
	
	const response = await PortOne.requestPayment({
			storeId : storeId, 
			paymentId : hotelReserveCode,
			orderName : orderName, //호텔이름 + 방속성이름(상품명 - 고객에게 표시를 위해...)
			totalAmount : hotelPrice,
			currency : "CURRENCY_KRW",
			channelKey : channelKey, // 콘솔 결제 연동 화면에서 채널 연동 시 생성된 채널 키를 입력해주세요.
			payMethod : "CARD",
			customer : {
				fullName : userId	
			}
		});
			if (response.code) {
			// 오류 발생
				console.log('결제 오류');
		} else {
			if(response.paymentId != hotelReserveCode){
				alert("예약번호 다름. 다시 해");
				return;
			}
			
			// 결제 검증 - 위에서 선언한거를 데이터로 보냄(결제 정보 외 것들)
			$.ajax({
				url : `/shushoong/hotel/payment?roomAttDesc=${encodeURIComponent(receipt.roomAttDesc)}&roomCatDesc=${encodeURIComponent(receipt.roomCatDesc)}&hotelName=${encodeURIComponent(receipt.hotelName)}&hotelPrice=${encodeURIComponent(receipt.hotelPrice)}`,
				type : "post",
				contentType: "application/json" ,
			    data: reservationDataString, 
				error:function(request, status, error){
						alert("결제 실패!!!!!!!!!!!!! 돌아가!!!!!!!!!!!");
					},
				dataType: "json",
				success : function(data) {
					if (data == 1) {
						location.href = "/shushoong/hotel/customer/reserve/pay/success?hotelReserveCode="+hotelReserveCode;
						return;
					} else {
						alert("결제 금액과 지불 금액이 일치하지 않거나 알 수 없는 오류가 발생했습니다.");
						return;
					}
				}
			});
		}

}

