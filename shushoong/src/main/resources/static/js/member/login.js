$(loadedHandler);

function loadedHandler() {
	$(".btn.customerLogin").on("click", loginHandler);
}

function loginHandler() {
	if ($("#userId1").val() == "") {
		alert("아이디를 입력해 주세요.");
		return false;
	}
	
	if ($("#userPwd1").val() == "") {
		alert("비밀번호를 입력해 주세요.");
		return false;
	}

}