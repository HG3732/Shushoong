$(loadedHandler);

function loadedHandler() {
	$('.btn-write-answer').on('click', writeAnswer);
	$('.btn-submit-answer').on('click', submitAnswer);
	$('.ans-update-btn').on('click', updateAnswer);
	$('.ans-delete-btn').on('click', deleteQna);
}

function writeAnswer(event) {
	event.preventDefault();
	$('.no-content').css('display', 'none');
	$('.write-answer').css('display', 'block');
	$('.btn-write-answer').css('display', 'none');
	$('.btn-submit-answer').css('display', 'block');
	console.log($('.write-answer').text());
	/*if($('.write-answer').val() != null || $('.write-answer').val() != '') {
		$('.btn-write-answer').attr('type', 'submit');
	} else {
		alert("답변을 제대로 작성해주세요.");
		event.preventDefault();
	}*/
	event.preventDefault();
}

function updateAnswer() {
	console.log('수정하기 버튼 눌림');
	$('.wrap-comment.update').css('display', 'none');
	$(this).css('display', 'none');
	$('#update-box').css('display', 'block');
	$('.no-content').css('display', 'none');
	$('.write-answer').css('display', 'block');
	$('.btn-write-answer').css('display', 'none');
	$('.btn-submit-answer').css('display', 'block');
	$('#frm-delete').find('.ans-delete-btn').css('display',  'none');
	
	var content = $('.ask-content').text();
	console.log('기존 내용', content)
	$('.write-answer').text(content);
}

function deleteQna() {
	console.log('삭제하기 버튼 눌림');
	
	var id = $('#faqId');
	console.log('문의 글 번호 : ', id);	
	$('#frm-delete').attr('method', 'POST');
	$('#frm-delete').attr('action', '/shushoong/support/qna/delete');
	$('#frm-delete').submit();
}

function submitAnswer() {
	var id = $(this).data("faqid");
	console.log(id);
	var answer = $('.write-answer').val();
	if(answer == null || answer == '') {
		alert("답변을 제대로 작성해주세요.");
	} else {
		$.ajax({
		url: "/shushoong/support/qna/write/answer.ajax",
		method: "post",
		data: { faqId : id,
				ansContent : answer },
		error: function(xhr, status, error) {
				console.log('AJAX 실패:', error);
				}
		})
		//success함수 대체
		.done(function(response){
			$('.wrapper').replaceWith(response);
		});
	}
}