let prekeyword = null;

$(loadedHandler);

function loadedHandler() {
	$('.productSearch').on('click', hotelSearchHandler);
}

function hotelSearchHandler() {
	prekeyword = $('#productName').val();
	
	$.ajax({
		url:"/shushoong/business/my/hotel/searchHotel.ajax",
		method: "post",
		data: { keyword : prekeyword},
		error: function(xhr, status, error) {
				console.log('AJAX 실패:', error);
			}
	}).done(function(response) {
		$('#productList').replaceWith(response);
	});
}

//페이지 이동
function goPage(thisElement) {
	var currentPageNum = $(thisElement).data('targetpage');
	
	$.ajax({
		url:"/shushoong/business/my/hotel/searchHotel.ajax",
		method: "post",
		data: { keyword : prekeyword,
				currentPage : currentPageNum},
		error: function(xhr, status, error) {
				console.log('AJAX 실패:', error);
			}
	}).done(function(response) {
		$('#productList').replaceWith(response);
	})
}

//호텔 삭제 확인
function checkDeleteHotelHandler(thisElement){
	event.preventDefault();
	event.stopPropagation();
	var hotelCode = $(thisElement).data("hotelcode");
	if(confirm("정말 이 사업장을 삭제하시겠습니까?")) {
		deleteHotelHandler(hotelCode);	
	}
	
}

//호텔 삭제
function deleteHotelHandler(hotelCode) {
	$.ajax({
		url:"/shushoong/business/my/hotel/deleteHotel.ajax",
		method: "post",
		data: { hotelCode : hotelCode },
		error: function(xhr, status, error) {
				console.log('AJAX 실패:', error);
			}
	}).done(function(response) {
		$('#productList').replaceWith(response);
	})
}