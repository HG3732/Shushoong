$(loadedHanlder);
function loadedHanlder() {
	$(".btn.emailSend").on("click", emailSendHandler);
	$(".btn.emailCheck").on("click", emailCheckHandler);
	$(".btn.findId").on("click", findIdValidation);
	$(".btn.findPwd").on("click",findPwdValidation);
}

var code_success = '';

function findIdCheckHandler() {
	var userName = $("#userName").val();
	var userEmail = $("#userEmail").val();
	var userGrade = "business";

	$.ajax({
		url: '/shushoong/find/business/id.ajax',
		type: 'post',
		data: { userName: userName, userEmail: userEmail, userGrade: userGrade },
		success: function(result) {
			if (result == 0) {
				alert("등록하신 정보와 일치하는지 다시 한 번 확인해 주세요.");
			} else {
				alert("설정하신 아이디는 " + result + " 입니다.");
			}
		}
	})
}

function findIdValidation() {

	if ($("#userName").val() == "") {
		alert("이름을 입력해 주세요.");
		return false;
	}

	if ($("#userEmail").val() == "") {
		alert("이메일을 입력해 주세요.");
		return false;
	}

	if (code_success != 1) {
		alert("이메일 인증을 해 주세요.");
		return false;
	}
	
	findIdCheckHandler();
}

function findPwdValidation() {
	if ($("#userId").val() == "") {
		alert("이름을 입력해 주세요.");
		return false;
	}

	if ($("#userEmail").val() == "") {
		alert("이메일을 입력해 주세요.");
		return false;
	}

	if (code_success != 1) {
		alert("이메일 인증을 해 주세요.");
		return false;
	}
	resetPwdCheckHandler();
}

function resetPwdCheckHandler() {
	var userId = $("#userId").val();
	var userEmail = $("#userEmail").val();
	var userGrade = "business";
	
	$.ajax({
		url: '/shushoong/find/business/id.ajax',
		type: 'post',
		data: { userName: userName, userEmail: userEmail, userGrade: userGrade },
		success: function(result) {
			if (result == 0) {
				alert("등록하신 정보와 일치하는지 다시 한 번 확인해 주세요.");
			} else {
				window.open('/shushoong/reset/password', '비밀번호 초기화','width=500px height=500px, loaction=no, status=no scrollbars=none');
			}
		}
	})
}

// 이메일 전송 이벤트
function emailSendHandler() {
	var userEmail = $("#userEmail").val();

	$.ajax({
		type: "post",
		url: "/shushoong/v2/verify-email",
		contentType: 'application/json;',
		data: JSON.stringify({
			'userEmail': userEmail
		}),
		success: function(request, status, error) {
			alert(userEmail + "로 인증번호가 전송되었습니다.");
		},
		error: function(request, status, error) {
			alert("잘못된 이메일 형식입니다.");
		}
	})
}

// 이메일 체크 이벤트
function emailCheckHandler() {
	var code = $("#code").val();

	$.ajax({
		type: "post",
		url: "/shushoong/verification-code",
		contentType: 'application/json;',
		data: JSON.stringify({
			'code': code
		}),
		success: function(request, status, error) {
			alert("인증이 완료되었습니다.");
			code_success = 1;

		},
		error: function(request, status, error) {
			alert("유효한 코드가 아닙니다.");
			code_success = 0;
		}
	})
}

