function clickedUpdateBtn(){
	$('.update-btn').on('click', function(){
		console.log('수정하기 버튼 클릭됨')
		var noticeId = $('#noticeId').text().trim();
		console.log('글번호', noticeId);
        $('#frm-notice-update').attr('method', 'GET');
        $('#frm-notice-update').attr('action', '/shushoong/support/notice/update/' + noticeId);
        $('#frm-notice-update').submit();
	});
}
function clickedDeleteBtn(){
	$('.delete-btn').on('click', function(){
		console.log('삭제하기 버튼 클릭됨')
		
		$('#modal').css('display', 'block');
		$('.delete-no').on('click', function(){
			$('#modal').css('display', 'none');	
		});
		
		$('.delete-ok').on('click', function(){
			//alert('삭제해줄겡');
		var noticeId = $('#noticeId').text().trim();
		console.log('글번호', noticeId);
        var $form = $('#frm-notice-update');
        $form.empty(); 
        $('<input>').attr({
            type: 'hidden',
            name: 'noticeId',
            value: noticeId
        }).appendTo($form);

        $form.attr('method', 'POST');
        $form.attr('action', '/shushoong/support/notice/delete');
        $form.submit();

		});
	});
}