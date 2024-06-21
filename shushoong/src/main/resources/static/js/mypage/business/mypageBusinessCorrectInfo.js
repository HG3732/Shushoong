$(loadedHandler);

function loadedHandler() {
	$(".btn.secession").on("click", deleteAccHandler);
	$(".pop-btn.cancle").on("click", cancleHandler);
	$(".close_btn").on("click", cancleHandler)
}

function deleteAccHandler() {
	$("#popup").hide().fadeIn();
}

function cancleHandler() {
	$("#popup").fadeOut();
}