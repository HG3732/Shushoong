$.ajax({
	error: function() {
		alert("입력하신 정보를 다시 확인해 주세요.");
		$("#userPwd").val('');
		$("#userId").val('');
		return false;
	}
})