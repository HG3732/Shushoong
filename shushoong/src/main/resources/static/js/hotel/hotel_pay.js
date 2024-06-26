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

	const now = new Date();
    const year = now.getFullYear();
    const month = String(now.getMonth() + 1).padStart(2, '0');
    const day = String(now.getDate()).padStart(2, '0');
    
    const currentTime = year + month + day;	
	var reserveName = $('#name').val(); //예약자 --> 회원명
	var reserveEmail = $('#email').val();
	var residenceNameKo = $('#name').val(); //투숙객명
	var residenceNameEng = $('#last_name').val() + $('#first_name').val();
	var residenceGender = $('input[name=gender]:checked').val();
	var residencePhone = $('#phone').val();
	var request = $('.require_check').children().children('input[type=checkbox]:checked').val();
	var reserveCheckIn = $('#check_in').val();
	var reserveCheckOut = $('#check_out').val();
	var userId = $('.user_id').val();
	var roomCap = $('.room_cap').val();
	var hotelCode = $('.hotel_code').val();
	var roomCat = $('.room_cat').val();
	var roomAtt = $('.room_att').val();
	var hotelPrice = $('.final_pay').val();
	var hotelReserveCode = currentTime + hotelCode + roomCat; //sysdate + hotelCode + room_cat
	
	console.log(hotelReserveCode);

	/*	const response = await PortOne.requestPayment({
			storeId : storeId, // 
			paymentId : buyId,
			orderName : orderName,
			totalAmount : totalAmount,
			currency : "CURRENCY_KRW",
			channelKey : channelKey, // 콘솔 결제 연동 화면에서 채널 연동 시 생성된 채널 키를 입력해주세요.
			payMethod : "CARD",
			customer : {
				fullName : memNick,
				phoneNumber : memTel,
				email : memEmail,
			}
		});
			if (response.code) {
			// 오류 발생
				console.log('결제 오류');
		} else {
			// 결제 검증
			$.ajax({
				url : contextPath + "store/payment",
				type : "post",
				data : {
					paymentId : response.paymentId,
					totalAmount : totalAmount,
					items : items,
					buyId : buyId
				},
				error : ajaxErrorHandler,
				success : async function(data) {
					if (data == 1) {
						Swal.fire({
							title: "결제가 완료되었습니다.\n구매내역으로 이동하시겠습니까?",
							icon: "success",
							showCancelButton: true,
							confirmButtonText: "이동하기",
							confirmButtonColor: "#000000",
							cancelButtonText: "돌아가기",
							cancelButtonColor: "#ff0000"
						}).then((swal) => {
							if(swal.isConfirmed){
								location.href = contextPath + "store/buy";
							}
						});
						return;
					} else {
						Swal.fire({
							title: "결제 금액과 지불 금액이 일치하지 않거나 알 수 없는 오류가 발생했습니다.",
							icon: "error",
							confirmButtonText: "확인",
							confirmButtonColor: "#000000",
						});
						return;
					}
				}
			});
		}
*/
}

