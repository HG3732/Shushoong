$(loadedHanlder)

function loadedHandler() {
	$(".btn.login").on("click", loginHandler);
}

function loginHandler() {
	if ($("#userId").val() == "") {
		alert("이름을 입력해 주세요.");
		return false;
	}
	
	if ($("#userPwd").val() == "") {
		alert("비밀번호를 입력해 주세요.");
		return false;
	}
}