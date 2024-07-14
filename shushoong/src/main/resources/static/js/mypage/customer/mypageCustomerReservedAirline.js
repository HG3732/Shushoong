/*  모달창 */
function reserveCancelHandler(){
	$('#modal_esc').css("display", 'block');
}

function escHandler(){
	$('#modal_esc').css("display", 'none');
}

/* 결제 취소하기 */
function cancelHandler(){

	var paymentId = $('.code_num').first().text();
	console.log(paymentId);
	
	$.ajax({
		url : "/shushoong/customer/mypage/reserved/airline/cancel",
		type : "post",
	    data: {paymentId : paymentId}, 
		error:function(request, status, error){
				alert("취소 실패!!!!!!!!!!!!!!!!!!!!");
			},
		dataType: "json",
		success : function(data) {
			if (data == 1) {
				location.href = "shushoong/customer/mypage/reserved/airline/list";
				return;
			} else {
				alert("결제 취소 도중 오류가 발생했습니다. 다시 시도해주세요.");
				return;
			}
		}
	});
	
}