function getSelectList() {
	
	$.ajax({
		url: "/shushoong/airline/list_select_options",
		method: "get",
		data: $('#frm-select').serialize(),
		dataType: "json",
		succsess: function(response){
			console.log("정렬 완");
			updateAirlineList(response);
		}
		
	});
	
	function updateAirlineList() {
		$('.airline-info-container').empty(); // 기존 목록 초기화
	}
	
}