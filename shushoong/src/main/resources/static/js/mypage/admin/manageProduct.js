let precategory = null;
let prekeyword = null;

$(loadedHandler);

function loadedHandler() {
	$('.productSearch').on('click', hotelSearchHandler);
}

function hotelSearchHandler() {
	precategory = $('.category option:selected').val();
	prekeyword = $('#productName').val();
	
	$.ajax({
		url:"/shushoong/admin/manager/product/searchProduct.ajax",
		method: "post",
		data: { category : precategory,
				keyword : prekeyword},
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
		url:"/shushoong/admin/manager/product/searchProduct.ajax",
		method: "post",
		data: { category : precategory,
				keyword : prekeyword,
				currentPage : currentPageNum},
		error: function(xhr, status, error) {
				console.log('AJAX 실패:', error);
			}
	}).done(function(response) {
		$('#productList').replaceWith(response);
	})
}