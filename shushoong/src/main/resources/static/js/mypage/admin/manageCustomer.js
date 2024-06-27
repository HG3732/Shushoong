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
		$('#memberlist').replaceWith(response);
	});
}

/* 회원 아이디 클릭 시 세부 정보 확인 */
function memberViewHandler(thisElement) {
	var id = $(thisElement).data("userid");
	
	$.ajax({
		url: "/shushoong/admin/manager/customer/viewMember.ajax",
		method: "get",
		data: { id : id },
		error: function(xhr, status, error) {
				console.log('AJAX 실패:', error);
			}
	})
	//success함수 대체
	.done(function(response){
		$("#viewmember").replaceWith(response);
	});
}

//회원 정지
function lockAccountHandler(thisElement) {
	var id = $(thisElement).parent('.btn.container').data("targetid");
	$.ajax({
		url: "/shushoong/admin/manager/customer/lockAccount.ajax",
		method: "get",
		data: { id : id },
		error: function(xhr, status, error) {
				console.log('AJAX 실패:', error);
			}
	})
	//success함수 대체
	.done(function(response1){
		$.ajax({
			url: "/shushoong/admin/manager/customer/viewMember.ajax",
			method: "get",
			data: { id : id },
			error: function(xhr, status, error) {
					console.log('AJAX 실패:', error);
				}
			})
		//success함수 대체
			.done(function(response){
				$("#viewmember").replaceWith(response);
			});
	});
}

//회원 정지 해제
function unlockAccountHandler(thisElement) {
	var id = $(thisElement).parent('.btn.container').data("targetid");
	$.ajax({
		url: "/shushoong/admin/manager/customer/unlockAccount.ajax",
		method: "get",
		data: { id : id },
		error: function(xhr, status, error) {
				console.log('AJAX 실패:', error);
			}
	})
	//success함수 대체
	.done(function(response1){
		$.ajax({
			url: "/shushoong/admin/manager/customer/viewMember.ajax",
			method: "get",
			data: { id : id },
			error: function(xhr, status, error) {
					console.log('AJAX 실패:', error);
				}
			})
		//success함수 대체
			.done(function(response){
				$("#viewmember").replaceWith(response);
			});
	});
}