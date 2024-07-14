/*  모달창 */
function reserveCancelHandler(){
	$('#modal_esc').css("display", 'block');
}

function escHandler(){
	$('#modal_esc').css("display", 'none');
}

/* 결제 취소하기 */
function cancelHandler(){

	var paymentId = $('.code_num').text();
	var room = $('.room_count').val();
	var roomAtt = $('.room_att').val();
	var roomCap = $('.room_cap').val();
	var roomCat = $('.room_cat').val();
	var hotelCode = $('.hotel_c').val();
	
	console.log(paymentId);
	
	$.ajax({
		url : "/shushoong/customer/mypage/reserved/hotel/cancel",
		type : "post",
		data: {
			paymentId : paymentId,
			room : room,
			roomAtt : roomAtt,
			roomCap : roomCap,
			roomCat : roomCat,
			hotelCode : hotelCode
		},
		/*contentType: "application/json; charset=utf-8",*/
		error:function(request, status, error){
				alert("취소 실패!!!!!!!!!!!!!!!!!!!!");
			},
		/*dataType: "json",*/
		success : function(data) {
			if (data == 1) {
				location.href = "/shushoong/customer/mypage/reserved/hotel/list/${userId}";
				location.href = `/shushoong/customer/mypage/reserved/hotel/list`;
				return;
			} else {
				alert("결제 취소 도중 오류가 발생했습니다. 다시 시도해주세요.");
				return;
			}
		}
	});
	
}

/* 리뷰 작성 */
function reviewWriteHandler(){
	$('#modal_review').css("display", 'block');
		
}

function reviewEscHandler(){
	$('#modal_review').css("display", 'none');
 	$('.score').prevAll().children().attr("src", "/shushoong/images/star.png").css('width', '15px;');
 	$('.score').next().children().attr("src", "/shushoong/images/star.png").css('width', '15px;');
	
}

function checkScoreHandler() {
	
	console.log(this);
	console.log($(this).children());
	
	/* 한번 별을 초기화 */
 	$(this).siblings().children().attr("src", "/shushoong/images/star.png").css('width', '15px;');
 	
 	$(this).prevAll().children().attr("src", "/shushoong/images/star_line.png").css('width', '15px;');
  	$(this).next().children().attr("src", "/shushoong/images/star_line.png").css('width', '15px;');
 }
 
 function reviewSubmitHandler() {
	var facScore = $('input[name=facility_score]').index($('input[name=facility_score]:checked')) + 1;
	var sanScore = $('input[name=sanity_score]').index($('input[name=sanity_score]:checked')) + 1;
	var conScore = $('input[name=conven_score]').index($('input[name=conven_score]:checked')) + 1; 
	var serScore = $('input[name=service_score]').index($('input[name=service_score]:checked')) + 1;
	var touristCat = $('select[name=tourist_cat]').val();
	var title = $('.review_title').val();
	var content = $('.review_content').val();
	var approveNo = $('.approve_no').val();
	var hotelReserveCode = $('.code_num').text();
	var id = $('.user_id').val();

	var dto = {
		approveNo : approveNo,
		reviewTitle : title,
		reviewComment : content,
		hotelFacility : facScore,
		hotelClean : sanScore,
		hotelConven : conScore,
		hotelKind : serScore,
		hotelReserveCode : hotelReserveCode,
		tripperCat : touristCat
	};	
	
	$.ajax({
		    success: ajaxErrorHandler,
			url: contextRoot+"customer/mypage/submit/review.ajax",
		    type : "POST",
		    data: JSON.stringify(dto),
		    contentType: "application/json; charset=utf-8",
		    success: function(response){
				if (response > 0) {
					location.href="/shushoong/customer/mypage/reserved/hotel/list";
				} else{
					alert("리뷰 작성에 실패하였습니다. 다시 시도해주세요.");
				}
			},
		    error : ajaxErrorHandler
	    });
 }