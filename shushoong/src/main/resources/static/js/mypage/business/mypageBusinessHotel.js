$(loadedHandler);

function loadedHandler() {
	/*호텔 등록하기*/
	$(".btn_register").on("click", hotelRegisterHandler);
	
	/*호텔 삭제하기*/
	$('.trash').on("click", deleteHotelHandler);
}

function hotelRegisterHandler(){
	console.log($(this));
	window.location.href = 'hotel/register';
	
}

function deleteHotelHandler(){
	console.log($(this).parent());
	
}
