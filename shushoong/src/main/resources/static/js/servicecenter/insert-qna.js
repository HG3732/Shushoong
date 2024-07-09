/*function goWriteBtn(){
	$('.qna-write-btn').on('click', function(){
		
	});
}
*/
function clickedWriteOkBtn(){
	$('.notice-btn').on('click', function(){
		var askTitle = $('.titleVal').val();
		var category = $('.catVal').val();
	//	var categoryDesc = null;
		var qnaFile = $('.fileVal').val();
		var askContent = $('.content-textarea').val();
		var userGrade = $('#userGrade').text();
/*		
		if (askTitle.trim() === "" || askTitle.trim() === "") {
		    alert('제목과 내용을 입력해주세요.');
		    return;
		}
		
		if(userGrade == 'customer'){
			switch (category){
				case "1": {
					categoryDesc = '회원탈퇴';
					break;
				}
				case "2": {
					categoryDesc = '상품정보 변경/환불';
					break;
				}
				case "3": {
					categoryDesc = '결제';
					break;
				}
			}
		}else if(userGrade == 'business'){
			switch (category){
				case "1": {
					categoryDesc = '개인정보';
					break;
				}
				case "2": {
					categoryDesc = '상품';
					break;
				}
				case "3": {
					categoryDesc = '결제';
					break;
				}
			}
		}
		*/
		console.log('userGrade : ', userGrade);
		console.log('askTitle : ', askTitle);
		console.log('category : ', category);
		//console.log('categoryDesc : ', categoryDesc);
		console.log('qnaFile : ', qnaFile);
		console.log('askContent : ', askContent);
		
		$('#askTitle').val(askTitle);
		$('#category').val(category);
		//$('#categoryDesc').val(categoryDesc);
		$('#qnaFile').val(qnaFile);
		$('#askContent').val(askContent);
		
		$('#frm-write').attr('method', 'POST');
        $('#frm-write').attr('action', '/shushoong/support/qna/write');
        $('#frm-write').submit();
	});
}