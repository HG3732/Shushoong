$(loadedHanlder);
function loadedHanlder() {
	$(".btn.emailSend").on("click", emailSendHandler);
	$(".btn.emailCheck").on("click", emailCheckHandler);
}

// 이메일 전송 이벤트
function emailSendHandler() {
	var userEmail = $("#userEmail").val()
	console.log(userEmail);

	$.ajax({
		type: "post",
		url: "/shushoong/v2/verify-email",
		contentType: 'application/json;',
		data: JSON.stringify({
			'userEmail': userEmail
		})
	})
}

// 이메일 체크 이벤트
function emailCheckHandler() {
	var code = $("#code").val();
	console.log(userEmail);

	$.ajax({
		type: "post",
		url: "/shushoong/verification-code",
		contentType: 'application/json;',
		data: JSON.stringify({
			'code': code
		})
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

	// 체크된 체크박스 전체개수 
	const query = 'input[name="chk"]:checked'
	const selectedElements = document.querySelectorAll(query);
	const selectedElementsCnt = selectedElements.length;

	// 체크박스 전체개수와 체크된 체박스 전체개수가 같으면 전체 체크박스 체크
	if (allCount == selectedElementsCnt) {
		document.getElementById('allCheckBox').checked = true;
	}

	// 같지 않으면 전체 해제
	else {
		document.getElementById('allCheckBox').checked = false;
	}
}

// 체크박스 전체 체크
function chkAllChecked() {
	document.querySelectorAll(".chk").forEach(function(v, i) {
		v.checked = true;
	});
}

// 체크박스 전체 해제
function chkAllUnChecked() {
	document.querySelectorAll(".chk").forEach(function(v, i) {
		v.checked = false;
	});
}