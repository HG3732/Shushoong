function getSelectOptions() {
	// var viaType = "direct";
	var departDate = $('#select-info-departDate').text().trim();
	var arrivalDate = $('#select-info-arrivalDate').text().trim();
	var departLoc = $('#select-info-departLoc').text();
	var arrivalLoc = $('#select-info-arrivalLoc').text();
	var ticketType = $('#select-info-ticketType').text();
	var adult = $('.airline-info-container').find('#adult').val();
	var child = $('.airline-info-container').find('#child').val();
	var baby = $('.airline-info-container').find('#baby').val();
	
	console.log('티켓타입 : ', ticketType);

	$('#showlist').on('change', updateTimeRange);
	$('#price-input-right').on('click', updateTimeRange); // 가격 사이드 바
	
    $('.direct-btn').on('change', function() {
        if (this.id === 'direct-btn' && this.checked) {
            $('#layover-btn').prop('checked', false);
            $('#viaType').val($(this).val());
        } else if (this.id === 'layover-btn' && this.checked) {
            $('#direct-btn').prop('checked', false);
            $('#viaType').val($(this).val());
        } else {
            $('#viaType').val('');
        }
        console.log('Selected value: ', $('#viaType').val());
        updateTimeRange();
	});

    var seatGrade  = $('#select-info-seatGrade').text().trim(); // 요소의 텍스트 값을 가져옵니다
	console.log('좌석 등급' , $('#select-info-seatGrade').text().trim());
	console.log('좌석 등급 숫자로 변환' , seatGrade);
	// 시간대 
	function updateTimeRange() {
	
		var selectType = $('#showlist').val();
		console.log('셀렉트바 active');
		console.log('정렬 순서', selectType);

		var maxPrice = $('#maxPrice').text(); // 가격대
		console.log('가격 최댓 값 : ', maxPrice);
		
		console.log('사이드바 출발 시간대');
		// 출발
		var departTimeLeftVal = $('#depart-left-timeval').text()/*.split(':')[0]*/;
		var departTimeRightVal = $('#depart-right-timeval').text()/*.split(':')[0]*/;
		// 도착
		var arrivalTimeLeftVal = $('#arrival-left-timeval').text()/*.split(':')[0]*/;
		var arrivalTimeRightVal = $('#arrival-right-timeval').text()/*.split(':')[0]*/;
		// 가격대
		
		// 직항/경유
        var viaType = $('#viaType').val();
	
		
		$('#departDate').val(departDate);
		$('#arrivalDate').val(arrivalDate);
		
		console.log('출발지 : ', departLoc);
		console.log('도착지 : ', arrivalLoc);
		console.log('정렬 순서', selectType);
		console.log('LEFT 출발 시간대 : ', departTimeLeftVal);
		console.log('RIGHT 출발 시간대 : ', departTimeRightVal);
		console.log('LEFT 도착 시간대 : ', arrivalTimeLeftVal);
		console.log('RIGHT 도착 시간대 : ', arrivalTimeRightVal);
		console.log('가격 최댓 값 : ' , maxPrice);
		console.log('편도/왕복 : ' , ticketType)
		console.log('좌석등급 : ' , seatGrade)

		$.ajax({
			url: "/shushoong/airline/list_select_options/ajax",
			method: "get",
			data: {
				adult: adult,
				child: child,
				baby: baby,
				departLoc: departLoc,
				arrivalLoc: arrivalLoc,
				departTimeLeft: departTimeLeftVal,
				deaprtTimeRight: departTimeRightVal,
				arrivalTimeLeft: arrivalTimeLeftVal,
				arrivalTimeRight: arrivalTimeRightVal,
				selectType: selectType,
				viaType: viaType,
				maxPrice: maxPrice,
				ticketType : ticketType,
				seatGrade: seatGrade,
				departDate: departDate
			},

			error: function(xhr, status, error) {
				console.log('AJAX 실패:', error);
			}
		}).done(function(response){
			$("#airline-info-box").replaceWith(response);
		});
	}

	// 양방향 레인지 바 이벤트 핸들러 설정
	$('#depart-input-left').on('click', updateTimeRange);
	$('#depart-input-right').on('click', updateTimeRange);
	$('#arr-input-left').on('click', updateTimeRange);
	$('#arr-input-right').on('click', updateTimeRange);
}
/*
	clickedTicketInfo();
	clickedShowReturnBtn();
*/
