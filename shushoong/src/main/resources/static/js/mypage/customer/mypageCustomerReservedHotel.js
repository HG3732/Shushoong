/*  모달창 */
function reserveCancelHandler(){
	$('#modal').css("display", 'block');
}

function escHandler(){
	$('#modal').css("display", 'none');
}

/* 결제 취소하기 */
function refundHandler(){
	console.log($(this));
}