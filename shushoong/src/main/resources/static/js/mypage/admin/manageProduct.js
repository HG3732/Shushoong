$(loadedHandler);

function loadedHandler() {
	$('.productSearch').on('click', hotelSearchHandler);
}

function hotelSearchHandler() {
	var keyword = $('#productName').val();
	
	$.ajax({
		url:"/shushoong/admin/manager/product/searchHotel.ajax",
		method: "post",
		data: {keyword : keyword},
		error: function(xhr, status, error) {
				console.log('AJAX 실패:', error);
			}
	}).done(function(response) {
		$('#productList').replaceWith(response);
	});
}