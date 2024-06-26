$(loadedHandler);

function loadedHandler() {
	$('.idSearch').on('click', memberSearchHandler);
}

/* 회원 아이디 키워드로 검색 */
function memberSearchHandler() {
	var keyword = $('#userId').val();
	
	$.ajax({
		url:"/shushoong/admin/manager/customer/searchMember.ajax",
		method: "get",
		data: {keyword : keyword},
		error: function(xhr, status, error) {
				console.log('AJAX 실패:', error);
			}
	}).done(function(response) {
		console.log(response);
		$('#article-body').replaceWith(response);
	});
}
