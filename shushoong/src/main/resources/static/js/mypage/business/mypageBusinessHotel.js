$(loadedHandler);

function loadedHandler() {
	/*호텔 등록하기*/
	$(".btn_register").on("click", hotelRegisterHandler);
	
}

function hotelRegisterHandler(){
	window.location.href = 'hotel/register';
}

