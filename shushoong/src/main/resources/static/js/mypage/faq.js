let precategory = null;
let preQuestCatCategory = null;
let preKeyword = null;

$(loadedHandler);

function loadedHandler() {
	$('.searchQnAList').on('click', searchHandler);
	$('.category').on('change', toggleCategory);
}

//문의 검색
function searchHandler() {
	precategory = $('.category option:selected').val();
	if(precategory == 'questCat'){
		preQuestCatCategory = $('.questCat-category option:selected').val();
	} else {
		preKeyword = $('.searchKeyword').val();		
	}
	
	$.ajax({
		url:"/shushoong/support/notice/search.ajax",
		method: "get",
		data: { category : precategory,
				keyword : preKeyword,
				questCatCategory : preQuestCatCategory},
		error: function(xhr, status, error) {
				console.log('AJAX 실패:', error);
			}
	}).done(function(response) {
		$('#wrap-QnAlist').replaceWith(response);
	})
}

//페이지 이동
function goPage(thisElement) {
	var currentPageNum = $(thisElement).data('targetpage');
	$.ajax({
		url:"/shushoong/support/notice/search.ajax",
		method: "get",
		data: { category : precategory,
				keyword : preKeyword,
				questCatCategory : preQuestCatCategory,
				pageNum : currentPageNum},
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
		$('.search-bar').css('display', 'none');
	} else {
		$('.questCat-category').css('display', 'none');
		$('.search-bar').css('display', 'block');
	}
}

