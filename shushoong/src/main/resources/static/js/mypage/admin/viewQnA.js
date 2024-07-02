$(loadedHandler);

function loadedHandler() {
	$('.btn-write-answer').on('click', writeAnswer);
}

function writeAnswer(event) {
	event.preventDefault();
	$('.no-content').css('display', 'none');
	$('.write-answer').css('display', 'block');
	$('.btn-write-answer').off('click', writeAnswer);
	$('.btn-write-answer').text('작성완료');
	$('.btn-write-answer').attr('type', 'submit');
	event.preventDefault();
}