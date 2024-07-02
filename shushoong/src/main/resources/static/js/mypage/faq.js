$(loadedHandler);

function loadedHandler() {
	$('.searchQnAList').on('click', searchHandler);
	$('.category').on('change', toggleCategory);
}

function searchHandler() {
	var category = $('.category option:selected').val();
	var questCatCategory = null;
	var keyword = null;
	if(category == 'questCat'){
		questCatCategory = $('.questCat-category option:selected').val();
	} else {
		keyword = $('.searchKeyword').val();		
	}
	console.log(questCatCategory);
	$.ajax({
		url:"/shushoong/support/notice/search.ajax",
		method: "get",
		data: { category : category,
			keyword : keyword,
			questCatCategory : questCatCategory},
		error: function(xhr, status, error) {
				console.log('AJAX 실패:', error);
			}
	}).done(function(response) {
		$('#wrap-QnAlist').replaceWith(response);
	})
}

function toggleCategory() {
	if($('.category option:selected').val() == 'questCat') {
		$('.questCat-category').css('display', 'block');
		$('.searchKeyword').css('display', 'none');
	} else {
		$('.questCat-category').css('display', 'none');
		$('.searchKeyword').css('display', 'block');
	}
}

