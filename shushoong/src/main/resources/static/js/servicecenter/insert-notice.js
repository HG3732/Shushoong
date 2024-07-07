function clickedWriteOkBtn(){
	$('.notice-btn').on('click', function(){
		
		
		
		var noticeTitle = $('.titleVal').val();
		var noticeCategory = $('.catVal').val();
		var noticeFile = $('.fileVal').val();
		var noticeContent = $('.content-textarea').val();
		
		if (noticeTitle.trim() === "" || noticeContent.trim() === "") {
		    alert('제목과 내용을 입력해주세요.');
		    return;
		}
		
		console.log('noticeTitle : ', noticeTitle);
		console.log('noticeCategory : ', noticeCategory);
		console.log('noticeFile : ', noticeFile);
		console.log('noticeContent : ', noticeContent);
		
		$('#noticeTitle').val(noticeTitle);
		$('#noticeCategory').val(noticeCategory);
		$('#noticeFile').val(noticeFile);
		$('#noticeContent').val(noticeContent);
		
		$('#frm-write').attr('method', 'POST');
        $('#frm-write').attr('action', '/shushoong/support/notice/write');
        $('#frm-write').submit();
	});
}