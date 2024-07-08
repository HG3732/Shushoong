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
	console.log(paymentId);
	
	$.ajax({
		url : "/shushoong/customer/mypage/reserved/hotel/cancel",
		type : "post",
	    data: {paymentId : paymentId}, 
		error:function(request, status, error){
				alert("취소 실패!!!!!!!!!!!!!!!!!!!!");
			},
		dataType: "json",
		success : function(data) {
			if (data == 1) {
				location.href = "shushoong/customer/mypage/reserved/hotel/list";
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