$(loadedHandler);

function loadedHandler() {
	$(".btn.save").on("click", saveInfoHandler);
	$(".btn.secession").on("click", deleteAccHandler);
	$(".pop-btn.cancle").on("click", cancleHandler);
	$(".close_btn").on("click", cancleHandler);
}

// url 직접접근 차단
$(document).ready(function() {
	if (document.referrer == '' || document.referrer == null) {
		document.location.href = '/shushoong/error/redirect';
	}
})

function saveInfoHandler() {
	var userPwd = $("#userPwd").val();
	var passwordChk = $("#passwordChk").val();
	var pwd_text = /^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,20}$/;
	
	if(pwd_text.test(userPwd) == false) {
		alert("형식에 맞지 않은 비밀번호입니다.");
		$("#userPwd").val('');
		$("#passwordChk").val('');
		return false;
	} else if (userPwd != passwordChk) {
		alert("비밀번호와 비밀번호 확인란이 일치하지 않습니다. 다시 확인해 주세요.");
		$("#userPwd").val('');
		$("#passwordChk").val('');
		return false;
	} else {
		$.ajax({
			type: "post",
			url: "/shushoong/business/changePwd.ajax",
			data: {userPwd:userPwd},
			success:function() {
				alert("비밀번호 변경에 성공했습니다.");
				$("#userPwd").val('');
				$("#passwordChk").val('');
			}, error:function() {
				alert("비밀번호 변경에 실패했습니다.");
				$("#userPwd").val('');
				$("#passwordChk").val('');
				return false;
			}
		})
	}
}

function deleteAccHandler() {
	$("#popup").hide().fadeIn();
}

function cancleHandler() {
	$("#popup").fadeOut();
}