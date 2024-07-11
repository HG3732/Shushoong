$(loadedHandler);

function loadedHandler() {
	$(".btn.customerLogin").on("click", customerLoginHandler);
	$(".btn.businessLogin").on("click", businessLoginHandler);
}

function customerLoginHandler() {
	if ($("#userId1").val() == "") {
		alert("아이디를 입력해 주세요.");
		return false;
	}
	
	if ($("#userPwd1").val() == "") {
		alert("비밀번호를 입력해 주세요.");
		return false;
	}

}

function businessLoginHandler() {
	if ($("#userId2").val() == "") {
		alert("아이디를 입력해 주세요.");
		return false;
	}
	
	if ($("#userPwd2").val() == "") {
		alert("비밀번호를 입력해 주세요.");
		return false;
	}

}