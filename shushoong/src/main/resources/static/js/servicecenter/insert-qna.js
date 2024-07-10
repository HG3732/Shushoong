function clickedWriteOkBtn(){
	$('.notice-btn').on('click', function(){
		var askTitle = $('.titleVal').val();
		var category = $('.catVal').val();
		var qnaFile = $('.fileVal').val();
		var askContent = $('.content-textarea').val();
		var userGrade = $('#userGrade').text();
		
		console.log('userGrade : ', userGrade);
		console.log('askTitle : ', askTitle);
		console.log('category : ', category);
		console.log('qnaFile : ', qnaFile);
		console.log('askContent : ', askContent);
		
		$('#askTitle').val(askTitle);
		$('#category').val(category);
		//$('#qnaFile').val(qnaFile);
		$('#askContent').val(askContent);
		
		$('#frm-write').attr('method', 'POST');
        $('#frm-write').attr('action', '/shushoong/support/qna/write');
        $('#frm-write').submit();
	});
}

function selectFile() {
	var fileInput = document.getElementById('qnaFile');
	var fileListContainer = document.createElement('ul');
	fileListContainer.className = 'file-list';

	fileInput.addEventListener('change', function() {
		var fileList = fileInput.files;
		console.log('Selected files:');
		fileListContainer.innerHTML = ''; // 기존 목록 초기화

		for (var i = 0; i < fileList.length; i++) {
			console.log(fileList[i].name);

			var fileItem = document.createElement('li');
			fileItem.textContent = fileList[i].name;
			fileListContainer.appendChild(fileItem);
		}
		// Display the file list
		var existingFileList = document.querySelector('.file-list');
		if (!existingFileList) {
			fileInput.parentNode.appendChild(fileListContainer);
		}
	});
}