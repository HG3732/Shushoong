$(loadedHandler);

function loadedHandler() {
    $(".hotel_pic").on("change", previewImage);
}

	//썸네일 미리보기
	function previewImage(event) {
			const files = event.target.files;
			$('.thumbnail-box').empty(); // 기존 썸네일 초기화
			
			Array.from(files).forEach(file => {
				const reader = new FileReader();
				reader.onload = function(e) {
					const thumbnail = $('<div class="thumbnail"></div>');
					thumbnail.css('background-image', 'url(' + e.target.result + ')');
					$('.thumbnail-box').append(thumbnail);
				};
				reader.readAsDataURL(file);
			});
		}