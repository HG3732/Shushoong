/*  모달창 */
function reserveCancelHandler(){
	$('#modal').css("display", 'block');
	
}

function escHandler(){
	$('#modal').css("display", 'none');
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