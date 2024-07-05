$(loadedHanlder);
function loadedHanlder() {
	$(".btn.emailSend").on("click", emailSendHandler);
	$(".btn.emailCheck").on("click", emailCheckHandler);
	$(".btn.idCheck").on("click", idCheckHandler);
	$(".btn.userJoin").on("click", submitJoinHandler);
}

var id_success = '';
var code_success = '';
var id_text = /^[A-Za-z0-9][A-Za-z0-9]{4,15}$/;
var name_text = /^[가-힣a-zA-Z]{2,8}$/;
var pwd_text = /^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,20}$/;

$("#userId").keyup(function(e) {
	var content = $(this).val();

	if (content.length < 2) {
		$('.test_IDexception').css("display", "none");
		$('.test_IDexception2').css("display", "none");
		$('.test_Idok').css("display", "none");
	} else if (id_text.test(content) == false) {
		$('.test_Idok').css("display", "none");
		$('.test_IDexception2').css("display", "inline-block");
		$('.test_IDexception').css("display", "none");
	} else if (id_text.test(content) == true) {
		$('.test_IDexception').css("display", "none");
		$('.test_IDexception2').css("display", "none");
		$('.test_Idok').css("display", "none");
	}
});

$("#userName").keyup(function(e) {
	var content = $(this).val();
	if (content == '') {
		$('.test_Nameexception').css("display", "none");
	} else if (name_text.test(content) == false) {
		$('.test_Nameexception').css("display", "inline-block");
	} else if (name_text.test(content) == true) {
		$('.test_Nameexception').css("display", "none");
	}
});

$("#userPwd").keyup(function(e) {
	var content = $(this).val();
	if (content == '') {
		$('.test_Pwdexception').css("display", "none");
	} else if (pwd_text.test(content) == false) {
		$('.test_Pwdexception').css("display", "inline-block");
	} else if (pwd_text.test(content) == true) {
		$('.test_Pwdexception').css("display", "none");
	} else {
		$('.test_Pwdexception').css("display", "none");
	}
});

// 비밀번호 확인 체크
$('#pwdCheck').keyup(function(e) {
	var checkPwd = $(this).val();
	var lengthPwd = $(this).val().length;
	var inputPwd = $("#userPwd").val();

	if (lengthPwd < 3) {
		$('.test_PwdChkexception').css("display", "none");
	} else if (inputPwd != checkPwd) {
		$('.test_PwdChkexception').css("display", "inline-block");
	} else if (inputPwd == checkPwd) {
		$('.test_PwdChkexception').css("display", "none");
	}
});


// 유효성 체크
function submitJoinHandler() {
	if ($("#userId").val() == "") {
		alert("아이디를 입력해 주세요.");
		return false;
	}

	if (id_success != 1) {
		alert("아이디 중복 확인을 해주세요.");
		return false;
	}

	if (id_text.test($("#userId").val()) == false) {
		$('.test_Idok').css("display", "none");
		$('.test_IDexception2').css("display", "inline-block");
		$('.test_IDexception').css("display", "none");
		return false;
	}

	if ($("#userName").val() == "") {
		alert("이름을 입력해 주세요.");
		return false;
	}

	if (name_text.test($("#userName").val()) == false) {
		$('.test_Nameexception').css("display", "inline-block");
		return false;
	}

	if ($("#userPwd").val() == "") {
		alert("비밀번호를 입력해 주세요.");
		return false;
	}

	if (pwd_text.test($("#userPwd").val()) == false) {
		$('.test_Pwdexception').css("display", "inline-block");
		return false;
	}

	if ($("#pwdCheck").val() == "") {
		alert("비밀번호 확인란을 입력해 주세요.");
		return false;
	}

	if ($("#userPwd").val() != $("#pwdCheck").val()) {
		$('.test_PwdChkexception').css("display", "inline-block");
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

	if ($("#usingService").is(':checked') == false && $("#privacy").is(':checked') == false) {
		alert("필수 항목에 동의해주세요");
		return false;
	}
	
	if($("#msgReceive").is(':checked') == true) {
		$("#msgReceive") = 1;
	} else {
		$("#msgReceive") = 0;
	}
	
	if($("#emailReceive").is(':checked') == true) {
		$("#emailReceive") = 1;
	} else {
		$("#emailReceive") = 0;
	}

	joinCustomer();
}

function joinCustomer() {
	$.ajax({
		success: function() {
			alert("회원가입에 성공했습니다.");
		}, error: function() {
			alert("오류로 회원가입에 실패했습니다. 다시 시도해 주세요.");
			return false;
		}
	})
}

// 아이디 체크 이벤트
function idCheckHandler() {
	var userId = $('#userId').val();
	
	if ($("#userId").val() == "") {
		alert("아이디를 입력해 주세요.");
		return false;
	}
	
	if (id_text.test($("#userId").val()) == false) {
		$('.test_Idok').css("display", "none");
		$('.test_IDexception2').css("display", "inline-block");
		$('.test_IDexception').css("display", "none");
		return false;
	}

	$.ajax({
		url: '/shushoong/idcheck.ajax',
		type: 'post',
		data: { userId: userId },
		success: function(cnt) {
			if (cnt == 0) {
				$('.test_IDexception').css("display", "none");
				$('.test_IDexception2').css("display", "none");
				$('.test_Idok').css("display", "inline-block");
				id_success = 1;
			} else {
				$('.test_Idok').css("display", "none");
				$('.test_IDexception2').css("display", "none");
				$('.test_IDexception').css("display", "inline-block");
				$('#userId').val('');
				id_success = 0;
			}
		},
		error: function(request, status, error) {
			alert("시스템 에러. 관리자에게 문의해 주세요.");
			return false;
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
			$('.test_Emailexception').css("display", "none");
			$('.test_Emailok').css("display", "inline-block");
		},
		error: function(request, status, error) {
			$('.test_Emailok').css("display", "none");
			$('.test_Emailexception').css("display", "inline-block");
			return false;
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
			$('.test_Echkexception').css("display", "none");
			$('.test_Echkok').css("display", "inline-block");
			code_success = 1;
		},
		error: function(request, status, error) {
			$('.test_Echkok').css("display", "none");
			$('.test_Echkexception').css("display", "inline-block");
			return false;
			code_success = 0;
		}
	})
}

function allChecked(target) {
	// 전체 체크박스 버튼
	const checkbox = document.getElementById('allCheckBox');

	// 전체 체크박스 체크 여부
	const is_checked = checkbox.checked;

	// 전체 체크박스를 제외한 모든 체크박스
	if (is_checked) {
		//체크박스 전부 체크
		chkAllChecked();
	}

	else {
		// 체크박스 전부 해제
		chkAllUnChecked();
	}
}

// 자식 체크박스 클릭 이벤트
function chkClicked() {
	// 체크박스 전체개수
	const allCount = document.querySelectorAll(".chk").length;
	const allMarketingCount = document.querySelectorAll(".marketing").length;

	// 체크된 체크박스 전체개수 
	const query = 'input[name="usingAgree"]:checked';
	const marketingquery = 'input[name="marketing"]:checked';

	const selectedElements = document.querySelectorAll(query);
	const selectedElementsCnt = selectedElements.length;

	const selectMarketingElements = document.querySelectorAll(marketingquery);
	const selectedMarketingElementsCnt = selectMarketingElements.length;

	// 체크박스 전체개수와 체크된 체박스 전체개수가 같으면 전체 체크박스 체크
	if (allCount == selectedElementsCnt && allMarketingCount == selectedMarketingElementsCnt) {
		document.getElementById('allCheckBox').checked = true;
		document.getElementById('receiveMarketing').checked = true;
	}

	else if (allMarketingCount == selectedMarketingElementsCnt) {
		document.getElementById('receiveMarketing').checked = true;
	}

	// 같지 않으면 전체 해제
	else {
		document.getElementById('allCheckBox').checked = false;
		document.getElementById('receiveMarketing').checked = false;
	}
}

// 체크박스 전체 체크
function chkAllChecked() {
	document.querySelectorAll(".chk,.receiveMarketing,.marketing").forEach(function(v, i) {
		v.checked = true;
	});
}

// 체크박스 전체 해제
function chkAllUnChecked() {
	document.querySelectorAll(".chk,.receiveMarketing,.marketing").forEach(function(v, i) {
		v.checked = false;
	});
}

// 마케팅 allChecked 이벤트
function checkMarketing(target) {
	// 전체 체크박스 버튼
	const checkbox = document.getElementById('receiveMarketing');

	// 전체 체크박스 체크 여부
	const is_checked = checkbox.checked;

	// 전체 체크박스를 제외한 모든 체크박스
	if (is_checked) {
		//체크박스 전부 체크
		chkAllMarketing();
	}

	else {
		// 체크박스 전부 해제
		chkAllUnMarketing();
	}
}

// 체크박스 전체 체크
function chkAllMarketing() {
	document.querySelectorAll('.marketing').forEach(function(v, i) {
		v.checked = true;
	});
}

// 체크박스 전체 해제
function chkAllUnMarketing() {
	document.querySelectorAll('.marketing').forEach(function(v, i) {
		v.checked = false;
	});
}
