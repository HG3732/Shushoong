var keyword = null;
let currentPageNum = 1;

$(loadedHandler);

function loadedHandler() {
	$('.idSearch1').on('click', memberSearchHandler);
	$('.idSearch2').on('click', sleeperSearchHandler);
	$('.allstopBtn').on('click', allLockHandler);
}

/* 회원 아이디 키워드로 검색 */
function memberSearchHandler() {
	keyword = $('#userId1').val();
	
	$.ajax({
		url:"/shushoong/admin/manager/customer/searchMember.ajax",
		method: "get",
		data: {keyword : keyword},
		error: ajaxErrorHandler
	}).done(function(response) {
		$('#memberlist').replaceWith(response);
	});
}

/* 회원 아이디 클릭 시 세부 정보 확인 + 문의내역 3개 조회 */
function memberViewHandler(thisElement) {
	var id = $(thisElement).data("userid");
	currentPageNum = 1;
	$.ajax({
		url: "/shushoong/admin/manager/customer/viewMember.ajax",
		method: "get",
		data: { id : id,
			currentPageNum : currentPageNum },
		error: ajaxErrorHandler
	})
	//success함수 대체
	.done(function(response){
		$("#viewmember").replaceWith(response);
	});
	
	$.ajax({
		url: "/shushoong/admin/manager/customer/viewQna.ajax",
		method: "get",
		data: { id : id },
		error: ajaxErrorHandler
	})
	//success함수 대체
	.done(function(response){
		$("#userqna").replaceWith(response);
	});
}

//결제내역 조회 클릭 시 모달
function payList(thisElement) {
	$('.modal-background').css('display', 'flex');
}

//모달 닫기
function closeModal() {
	$('.modal-background').css('display', 'none');
}

//모달 탭 전환
function tabToHotel() {
	$('.modal-box').eq(1).css('display','none');
	$('.modal-box').eq(0).css('display','flex');
}
function tabToFly() {
	$('.modal-box').eq(0).css('display','none');
	$('.modal-box').eq(1).css('display','flex');
}

//결제내역 페이징
function goPage(thisElement) {
	currentPageNum = $(thisElement).data('targetpage');
	var id = $('.user-id').val();
	
	$.ajax({
		url: "/shushoong/admin/manager/customer/viewpay.ajax",
		method: "post",
		data: { id : id,
			currentPageNum : currentPageNum },
		error: ajaxErrorHandler
	})
	//success함수 대체
	.done(function(response){
		$("#modal").replaceWith(response);
	});
}

//회원 정지
function lockAccountHandler(thisElement) {
	var id = $(thisElement).parent('.btn.container').data("targetid");
	$.ajax({
		url: "/shushoong/admin/manager/customer/lockAccount.ajax",
		method: "get",
		data: { id : id },
		error: ajaxErrorHandler
	})
	//success함수 대체
	.done(function(response1){
		$.ajax({
			url: "/shushoong/admin/manager/customer/viewMember.ajax",
			method: "get",
			data: { id : id },
			error: ajaxErrorHandler
			})
		//success함수 대체
			.done(function(response){
				$(".modal-box").replaceWith(response);
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
		error: ajaxErrorHandler
	})
	//success함수 대체
	.done(function(response1){
		$.ajax({
			url: "/shushoong/admin/manager/customer/viewMember.ajax",
			method: "get",
			data: { id : id },
			error: ajaxErrorHandler
			})
		//success함수 대체
			.done(function(response){
				$("#viewmember").replaceWith(response);
			});
	});
}

/* 장기 미접속 회원 아이디 키워드로 검색 */
function sleeperSearchHandler() {
	keyword = $('#userId2').val();
	
	$.ajax({
		url:"/shushoong/admin/manager/customer/searchSleeper.ajax",
		method: "post",
		data: {keyword : keyword},
		error: ajaxErrorHandler
	}).done(function(response) {
		$('.secessionUserList').replaceWith(response);
	});
}

/* 장기 미사용 회원 아이디 클릭 시 세부 정보 확인 */
function sleeperViewHandler(thisElement) {
	var id = $(thisElement).data("userid");
	
	$.ajax({
		url: "/shushoong/admin/manager/customer/viewSleeper.ajax",
		method: "get",
		data: { id : id },
		error: ajaxErrorHandler
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
		error: ajaxErrorHandler
	})
	//success함수 대체
	.done(function(response1){
		$.ajax({
			url: "/shushoong/admin/manager/customer/viewSleeper.ajax",
			method: "get",
			data: { id : id },
			error: ajaxErrorHandler
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
		error: ajaxErrorHandler
	})
	//success함수 대체
	.done(function(response1){
		$.ajax({
			url: "/shushoong/admin/manager/customer/viewSleeper.ajax",
			method: "get",
			data: { id : id },
			error: ajaxErrorHandler
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
		error: ajaxErrorHandler
	})
}