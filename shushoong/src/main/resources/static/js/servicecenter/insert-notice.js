function clickedWriteOkBtn() {
	$('.notice-ok').on('click', function() {
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
		//$('#noticeFile').val(noticeFile);
		$('#noticeContent').val(noticeContent);

		$('#frm-write').attr('method', 'POST');
		$('#frm-write').attr('action', '/shushoong/support/notice/write');
		$('#frm-write').submit();
	});
}

function selectFile() {
	var fileInput = document.getElementById('noticeFile');
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