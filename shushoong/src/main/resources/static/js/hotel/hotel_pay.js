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

    const currentTime = year + month + day;	
	var residenceNameKo = $('#name').val(); //투숙객명
	var residenceNameEng = $('#last_name').val() + $('#first_name').val();
	var residenceGender = $('input[name=gender]:checked').val();
	var residencePhone = $('#phone').val();
	var residenceEmail = $('#email').val();
	
	var requestItems= [];
	var request = $('.require_check').children().children('input[name="checkbox"]:checked').each(function() {
                    // 값을 배열에 추가합니다.
                    requestItems.push($(this).val());
					});
					
	var reserveCheckIn = $('#check_in').val();
	var reserveCheckOut = $('#check_out').val();
	var userId = $('.user_id').val();
	var roomCap = $('.room_cap').val();
	var hotelCode = $('.hotel_code').val();
	var roomCat = $('.room_cat').val();
	var roomAtt = $('.room_att').val();

	var roomCatDesc = $('.room_cat_desc').val();
	var hotelName = $('.hotel_name').text();
	var hotelPriceStr = $('.hotel_price').val();
	var hotelPrice = parseInt(hotelPriceStr, 10);
	
	var hotelReserveCode = currentTime + hotelCode + roomCat; //sysdate + hotelCode + room_cat
	var orderName = hotelName + ' ' + roomCatDesc;

	console.log(hotelPrice);

	let reservationData = {
		residenceNameKo : residenceNameKo,
		residenceNameEng : residenceNameEng, 
		residenceGender : residenceGender,
		residencePhone : residencePhone,
		residenceEmail : residenceEmail,
		request : requestItems,
		reserveCheckIn : reserveCheckIn,
		reserveCheckOut : reserveCheckOut,
		userId : userId,
		roomCap : roomCap,
		hotelCode : hotelCode,
		roomCat : roomCat,
		roomAtt : roomAtt,
		/*위에는 예약으로, 아래는 예약완료됐을때 띄워주기*/
		roomCatDesc : roomCatDesc,
		hotelName : hotelName,
		hotelPrice : hotelPrice,
		hotelReserveCode : hotelReserveCode,
	}

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
			// 결제 검증 - 위에서 선언한거를 데이터로 보냄(결제 정보 외 것들)
			$.ajax({
				url : contextPath + "hotel/payment",
				type : "post",
				data : { //이건 내가 마음ㅇ대로 이름 지을 수 있음 - 호텔정보들 여기로 보내기
					paymentId : response.paymentId,
					totalAmount : totalAmount,
					reservationData : reservationData
				},
				error : ajaxErrorHandler,
				success : async function(data) {
					if (data == 1) {
						location.href = contextPath + "hotel/reserve/complete";
						return;
					} else {
						alert("결제에 실패하셨습니다. 메인페이지로 돌아갑니다.");
						location.href = contextPath + "hotel/main";
						return;
					}
				}
			});
		}

}

