var keyword = null;

$(loadedHandler);

function loadedHandler() {
	$('.search1').on('click', memberSearchHandler);
	$('.search2').on('click', noSalerSearchHandler);
	$('.search3').on('click', sleeperSearchHandler);
	$('.allstopBtn').on('click', allLockHandler);
}

/* 회원 아이디 키워드로 검색 */
function memberSearchHandler() {
	keyword = $('#userId1').val();
	console.log(keyword);
	
	$.ajax({
		url:"/shushoong/admin/manager/business/searchMember.ajax",
		method: "get",
		data: {keyword : keyword},
		error: function(xhr, status, error) {
				console.log('AJAX 실패:', error);
			}
	}).done(function(response) {
		$('#memberlist').replaceWith(response);
	});
}

/* 회원 아이디 클릭 시 세부 정보 확인 + 문의내역 3개 조회 */
function memberViewHandler(thisElement) {
	var id = $(thisElement).data("userid");
	
	$.ajax({
		url: "/shushoong/admin/manager/business/viewMember.ajax",
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
	
	$.ajax({
		url: "/shushoong/admin/manager/customer/viewQna.ajax",
		method: "get",
		data: { id : id },
		error: function(xhr, status, error) {
				console.log('AJAX 실패:', error);
			}
	})
	//success함수 대체
	.done(function(response){
		$("#userqna").replaceWith(response);
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

/* 상품 미등록 회원 아이디 키워드로 검색 */
function noSalerSearchHandler() {
	keyword = $('#userId2').val();
	
	$.ajax({
		url:"/shushoong/admin/manager/business/searchNosale.ajax",
		method: "post",
		data: {keyword : keyword},
		error: function(xhr, status, error) {
				console.log('AJAX 실패:', error);
			}
	}).done(function(response) {
		$('.secessionUserList').eq(0).replaceWith(response);
	});
}

/* 장기 미접속 회원 아이디 키워드로 검색 */
function sleeperSearchHandler() {
	keyword = $('#userId3').val();
	
	$.ajax({
		url:"/shushoong/admin/manager/business/searchSleeper.ajax",
		method: "post",
		data: {keyword : keyword},
		error: function(xhr, status, error) {
				console.log('AJAX 실패:', error);
			}
	}).done(function(response) {
		$('.secessionUserList').eq(1).replaceWith(response);
	});
}

/* 장기 미사용 회원 아이디 클릭 시 세부 정보 확인 */
function sleeperViewHandler(thisElement) {
	var id = $(thisElement).data("userid");
	
	$.ajax({
		url: "/shushoong/admin/manager/customer/viewSleeper.ajax",
		method: "get",
		data: { id : id },
		error: function(xhr, status, error) {
				console.log('AJAX 실패:', error);
			}
	})
	//success함수 대체
	.done(function(response){
		$("#viewsleeper").replaceWith(response);
	});
}

//sleeper 회원 정지
function lockAccountHandler2(thisElement) {
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
			url: "/shushoong/admin/manager/customer/viewSleeper.ajax",
			method: "get",
			data: { id : id },
			error: function(xhr, status, error) {
					console.log('AJAX 실패:', error);
				}
			})
		//success함수 대체
			.done(function(response){
				$("#viewsleeper").replaceWith(response);
			});
	});
}

//sleeper 회원 정지 해제
function unlockAccountHandler2(thisElement) {
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
			url: "/shushoong/admin/manager/customer/viewSleeper.ajax",
			method: "get",
			data: { id : id },
			error: function(xhr, status, error) {
					console.log('AJAX 실패:', error);
				}
			})
		//success함수 대체
			.done(function(response){
				$("#viewsleeper").replaceWith(response);
			});
	});
}

//sleeper 일괄 정지
function allLockHandler() {
	var sleeperList = []; 
	$('.sleeperUser').each(function() {
		sleeperList.push($(this).data('userid'));
	});
	
	$.ajax({
		url: "/shushoong/admin/manager/customer/allLock.ajax",
		type: 'post',
		contentType: 'application/json',
		data: JSON.stringify(sleeperList),
		success: function(response) {
			alert("정지에 성공하였습니다.");
		},
		error: function(xhr, status, error) {
				console.log('AJAX 실패:', error);
			}
	})
}