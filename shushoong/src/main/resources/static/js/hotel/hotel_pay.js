/*유효성 체크*/
function nameHandler(){
	var name = $(this).val().trim();
	var regName = /^[가-힣]+$/
		if (!regName.test(name)) {
			$('#nameError').text('한글 이름만 입력하세요.');
		} else{
			reserveBtnStatus();
			$('#nameError').text('');
			}
	}

function birthdayHandler(){
	var birthday = $(this).val().trim();
    var regBirthday = /^[0-9]{6}$/;
	    if (!regBirthday.test(birthday)) {
			$('#birthdayError').text('6자리 숫자만 입력가능합니다.');
	    } else {
			reserveBtnStatus();
			$('#birthdayError').text('');
	    }
	   
}

function lastNameHandler(){
	var last_name  = $(this).val().trim();
     var regLastName = /^[a-zA-Z]+$/;
	    if (!regLastName.test(last_name)) {
			$('#last_name_Error').text('알파벳만 입력하세요.');
	    } else {
			reserveBtnStatus();
			$('#last_name_Error').text('');
	    }
}

function firstNameHandler(){
	var first_name  = $(this).val().trim();
    var regFirstName = /^[a-zA-Z]+$/;
	    if (!regFirstName.test(first_name)) {
			$('#first_name_Error').text('알파벳만 입력하세요.');
	    } else {
			reserveBtnStatus();
			$('#first_name_Error').text('');
	    }

}

function phoneHandler(){
	var phone  = $(this).val().trim();
    var regPhone = /^[0-9]*$/;
    var regPhone2 = /^[0-9]{11}$/; 
	    if (!regPhone.test(phone) ||!regPhone2.test(phone)) {
			$('#phoneError').text('"-"제외 11자, 번호만 입력.');
	     } else {
			reserveBtnStatus();
			$('#phoneError').text('');
	    }	
}

function emailHandler(){
	var email  = $(this).val().trim();
    var regEmail =  /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/i;
	    if (!regEmail.test(email)) {
	  		$('#emailError').text('이메일 형식에 맞춰주세요.');     
	    } else {
			reserveBtnStatus();
			$('#emailError').text('');
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
			reserveBtnStatus();
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


// 결제 버튼 비활성화/활성화
function reserveBtnStatus(){
	console.log('버튼 함수 발도오옹');
	var genderChecked = $('#radio_man').is(':checked') || $('#radio_woman').is(':checked');
	var name = $('#name').val().trim();
	var birthday = $('#birthday').val().trim();
	var last_name  = $('#last_name').val().trim();
	var first_name  = $('#first_name').val().trim();
	var phone  = $('#phone').val();
	var email  = $('#email').val();
	
	//var agreeRadios = document.querySelectorAll('.agree_radio');
	var reserveBtn = $('.final_pay');
	var allAgreed = $('.agree_radio').toArray().every(radio => radio.checked && radio.value === 'agree');
	
	console.log(
		'name: ', name, ' birthday: ', birthday, ' last_name: ', last_name, ' first_name', first_name
		,'phone: ', phone, 'email: ', email, ' allAgreed', allAgreed, 'gender', genderChecked);
	
	if(name != null && birthday != null && last_name != null && 
	first_name != null && phone != null && email != null && allAgreed && genderChecked){
		reserveBtn.prop('disabled', false).addClass('active');
	}else{
		reserveBtn.prop('disabled', true).removeClass('active');;
	}
}


/*==================결제 관련 ==================== */

var residenceNameKo = ''; //투숙객명
var residenceNameEn = '';
var residenceGender = ''; 
var residenceBirth = '';
var residencePhone = '';
var residenceEmail = '';


let requestSum= 0;
let reserveCheckIn = '';
let reserveCheckOut = '';
let userId = '';
let roomCap = '';
let hotelCode = '';
let roomCat = '';
let roomAtt = '';
let roomAttDesc = '';
let room = '';

let roomCatDesc = '';
let hotelName = '';
//var hotelPriceStr = $('.hotel_price').val();
let hotelPriceStr = '100';
let hotelPrice = parseInt(hotelPriceStr, 10);

let hotelReserveCode = '';

let orderName = '';
let people = '';


function reserveInfoInsertHandler(){

	const now = new Date();
	const year = now.getFullYear();
	const month = String(now.getMonth() + 1).padStart(2, '0');
	const day = String(now.getDate()).padStart(2, '0');
	const time = now.getSeconds();
	
	const currentTime = year + month + day + time;	
	 residenceNameKo = $('#name').val(); //투숙객명
	 residenceNameEn = $('#last_name').val() + $('#first_name').val();
	 residenceGender = $('input[name=gender]:checked').val();
	 residenceBirth = $('#birthday').val();
	 residencePhone = $('#phone').val();
	 residenceEmail = $('#email').val();
	
	
	 requestSum= 0;
	$('.require_check').children().children('input[name="checkbox"]:checked').each(function() {
	    // val가 1,2,4,8, 비트 마스킹 값이므로 +더하기하면됨.
	    requestSum += ($(this).val() * 1);
	    //문자가 숫자로 인식
		});
	 reserveCheckIn = $('#check_in').val();
	 reserveCheckOut = $('#check_out').val();
	 userId = $('.user_id').val();
	 roomCap = $('.room_cap').val();
	 hotelCode = $('.hotel_code').val();
	 roomCat = $('.room_cat').val();
	 roomAtt = $('.room_att').val();
	 roomAttDesc = $('.room_att_desc').val();
	 room= $('.room_count').val();
	
	 roomCatDesc = $('.room_cat_desc').val();
	 hotelName = $('.hotel_name').text();
	//var hotelPriceStr = $('.hotel_price').val();
	 hotelPriceStr = '100';
	 hotelPrice = parseInt(hotelPriceStr, 10);
	
	 hotelReserveCode = currentTime + hotelCode + roomCat + roomAtt; //sysdate + hotelCode + room_cat
	
	 orderName = hotelName + ' ' + roomCatDesc;
	 people = $('.people').val();


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
		requestSum : requestSum,
		room : room	
	}
		
	// reservationData 객체를 JSON 문자열로 변환
	let reservationDataString = JSON.stringify(reservationData);


		//예약자 정보 먼저 저장	    
	    $.ajax({
	        url: "/shushoong/hotel/input/reserveInfo",
	        type: "POST",
	        contentType: "application/json",
	        data: reservationDataString,
	        success: function(response) {
	            console.log(response);
	            if(response.result == "1") {
	                console.log("성공성공성공");	                
	                const hotelReserveCode = response.hotelReserveCode;
	                payHandler(hotelReserveCode); // hotelReserveCode를 함수로 전달
	            } else {
	                alert("데이터 리스트 값 insert 실패");
	            }
	        },
			error:function (request, status, error){
				alert("code: "+request.status + "\n" + "message: " 
						+ request.responseText + "\n"
						+ "error: "+error);
			}
	    });
}

async function payHandler(hotelReserveCode){

	const storeId = $('.store_id').val();
	const channelKey = $('.channel_key').val();
	
	const reserveCompletedto = {
			roomAttDesc : roomAttDesc,
			roomCatDesc : roomCatDesc,
			hotelName : hotelName,
			hotelPrice : hotelPrice	
		};

	console.log(hotelReserveCode);
	
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
				url : "/shushoong/hotel/payment",
				type : "post",
				data: JSON.stringify(reserveCompletedto),
				contentType: "application/json; charset=utf-8",
				error:function (request, status, error){
						alert("code: "+request.status + "\n" + "message: " 
								+ request.responseText + "\n"
								+ "error: "+error);
					},
				dataType: "json",
				success : function(data) {
					if (Number(data) === 0) {
						alert("결제 금액과 지불 금액이 일치하지 않거나 알 수 없는 오류가 발생했습니다.");
						return;
					} else if (data){
						location.href = "/shushoong/hotel/customer/reserve/pay/success";
						return;
					}
				}
			});
		}

}

