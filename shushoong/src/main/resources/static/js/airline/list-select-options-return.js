function getSelectOptionsReturn() {
	// var viaType = "direct";
	var departLoc = $('#select-info-departLoc-out').text();
	var arrivalLoc = $('#select-info-arrivalLoc-out').text();
	var airlineCode = $('#select-info-airlineCode-out').text();
	var adult = $('.airlineReturn-container').find('#adult').val();
	var child = $('.airlineReturn-container').find('#child').val();
	var baby = $('.airlineReturn-container').find('#baby').val();
	
    var seatGrade = $('#select-info-seatGrade').text().trim(); // 요소의 텍스트 값을 가져옵니다
	console.log('좌석 등급' + seatGrade);
    
	$('#showlist').on('change', updateTimeRange);
	// $('#price-input-right').on('click', updateTimeRange);
	
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

	// 시간대
	function updateTimeRange() {
		
		$('#flightNo').val(flightNo);
		$('#flightNoReturn').val(flightNoReturn);
		
		var selectType = $('#showlist').val();

		console.log('셀렉트바 active');
		console.log('정렬 순서', selectType);

		console.log('사이드바 출발 시간대');
		// 출발
		var departTimeLeftVal = $('#depart-left-timeval').text()/*.split(':')[0]*/;
		var departTimeRightVal = $('#depart-right-timeval').text()/*.split(':')[0]*/;
		// 도착
		var arrivalTimeLeftVal = $('#arrival-left-timeval').text()/*.split(':')[0]*/;
		var arrivalTimeRightVal = $('#arrival-right-timeval').text()/*.split(':')[0]*/;
	
		
		// 직항/경유
        var viaType = $('#viaType').val();
		
		console.log('비행기 편명 : ', airlineCode);
		console.log('출발지 : ', departLoc);
		console.log('도착지 : ', arrivalLoc);
		console.log('도착지', arrivalLoc);
		console.log('정렬 순서', selectType);
		console.log('LEFT 출발 시간대 : ', departTimeLeftVal);
		console.log('RIGHT 출발 시간대 : ', departTimeRightVal);
		console.log('LEFT 도착 시간대 : ', arrivalTimeLeftVal);
		console.log('RIGHT 도착 시간대 : ', arrivalTimeRightVal);
		console.log('좌석등급 : ', seatGrade);
		// console.log('가격 최댓 값 : ' , maxPrice);
		

		$.ajax({
			url: "/shushoong/airline/list_select_options_return/ajax",
			method: "get",
			data: {
				airlineCode : airlineCode,
				departLoc: arrivalLoc,
				arrivalLoc: departLoc,
				departTimeLeft: departTimeLeftVal,
				deaprtTimeRight: departTimeRightVal,
				arrivalTimeLeft: arrivalTimeLeftVal,
				arrivalTimeRight: arrivalTimeRightVal,
				selectType: selectType,
				viaType: viaType,
				seatGrade: seatGrade
			},

			error: function(xhr, status, error) {
				console.log('AJAX 실패:', error);
			}
		}).done(function(response){
			$("#airline-return-box").replaceWith(response);
			});
	}

	// 양방향 레인지 바 이벤트 핸들러 설정
	$('#depart-input-left').on('click', updateTimeRange);
	$('#depart-input-right').on('click', updateTimeRange);
	$('#arr-input-left').on('click', updateTimeRange);
	$('#arr-input-right').on('click', updateTimeRange);
}

