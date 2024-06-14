function getSelectOptions() {
	var viaType;
	var departLoc = $('#select-info-departLoc-out').text();
	var arrivalLoc = $('#select-info-arrivalLoc-out').text();

	$('#showlist').on('change', updateTimeRange);
	$('#price-input-right').on('click', updateTimeRange); // 가격 사이드 바
	$('.direct-btn').on('click', function() {
		viaType = $(this).val();
		$('#viaType').val(viaType);
		console.log('direct-btn.val: ', $('#viaType').val());
		updateTimeRange();
	});

	$('.layover-btn').on('click', function() {
		viaType = $(this).val();
		$('#viaType').val(viaType);
		console.log('direct-btn.val: ', $('#viaType').val());
		updateTimeRange();
	});
	// 시간대 
	function updateTimeRange() {
	
		// var departLoc = $('#select-info-departLoc-out').text();
		// var arrivalLoc = $('#select-info-arrivalLoc-out').text();
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

		console.log('출발지 : ', departLoc);
		console.log('도착지 : ', arrivalLoc);
		console.log('도착지', arrivalLoc);
		console.log('정렬 순서', selectType);
		console.log('LEFT 출발 시간대 : ', departTimeLeftVal);
		console.log('RIGHT 출발 시간대 : ', departTimeRightVal);
		console.log('LEFT 도착 시간대 : ', arrivalTimeLeftVal);
		console.log('RIGHT 도착 시간대 : ', arrivalTimeRightVal);
		console.log('가격 최댓 값 : ' , maxPrice);

		$.ajax({
			url: "/shushoong/airline/list_select_options/ajax",
			method: "get",
			data: {
				departLoc: departLoc,
				arrivalLoc: arrivalLoc,
				departTimeLeft: departTimeLeftVal,
				deaprtTimeRight: departTimeRightVal,
				arrivalTimeLeft: arrivalTimeLeftVal,
				arrivalTimeRight: arrivalTimeRightVal,
				selectType: selectType,
				viaType: viaType,
				maxPrice: maxPrice
			},
			dataType: 'json',
			success: function(response) {
				console.log('Ajax Success', response);
				if (response.length == 0) {
					// alert('해당 조건을 일치하는 항공권이 없습니다.')
					// location.reload(true); 새로고침
					noAirlineList(response);
				} else {
					updateAirlineList(response);
				}
			},
			error: function(xhr, status, error) {
				console.log('AJAX 실패:', error);
			}
		});
	}

	// 양방향 레인지 바 이벤트 핸들러 설정
	$('#depart-input-left').on('click', updateTimeRange);
	$('#depart-input-right').on('click', updateTimeRange);
	$('#arr-input-left').on('click', updateTimeRange);
	$('#arr-input-right').on('click', updateTimeRange);
}

function noAirlineList() {
	$('.airline-info-container').empty(); // 기존 목록 초기화
	var airlineEmpty = `
	<div class="empty-list">해당 조건에 일치하는 항공권이 없습니다.</div>
	`;
	$('.airline-info-container').html(airlineEmpty);
}

function updateAirlineList(data) {
	// 항공 목록을 업데이트
	$('.airline-info-container').empty(); // 기존 목록 초기화

	let airlineInfo = '';
	data.forEach(function(air) {
		airlineInfo += `
			<form id="frm-return" hidden>
				<input type="hidden" id="departLoc" name="departLoc">
				<input type="hidden" id="arrivalLoc" name="arrivalLoc">
				<input type="hidden" id="airlineCode" name="airlineCode">
			</form>
            <div class="airline-info">
                <div hidden="">
                    <span class="select-info-departLoc" name="departLoc">${air.departLoc}</span>
                    <span class="select-info-arrivalLoc" name="arrivalLoc">${air.arrivalLoc}</span>
                    <span class="select-info-airlineCode" name="airlineCode">${air.airlineCode}</span>
                </div>
                <div class="seat-count">
                    <div class="airline-flex">
                        <div class="airline-logo">
                            <div class="span-seat-div">
                                <span class="span-seat">잔여9석${air.seatCount}</span>
                            </div>
                            <img src="${air.airlineImg}" alt="">
                        </div>
                        <div class="airline-name">
                            <span>${air.airlineName}</span>
                        </div>
                        <div class="airinfo depart-info">
                            <div class="info date">${air.departDate}</div>
                            <div class="info time">${air.departTime}</div>
                            <div class="info loc depart">${air.departLoc}</div>
                        </div>
                        <div class="arrow">
                            <div class="via-count">
                                <span>${air.viaCount}</span>
                            </div>
                            <img src="/shushoong/images/airline_via.png" alt="">
                            <div class="flytime">${air.flightTime}</div>
                            <div hidden>${air.flightNo}</div>
                        </div>
                        <div class="airinfo arr-info">
                            <div class="info date">${air.arrivalDate}</div>
                            <div class="info time">${air.arrivalTime}</div>
                            <div class="info loc arrival">${air.arrivalLoc}</div>
                        </div>
                        <div class="nop">
                            <div class="sero">
                                <img src="/shushoong/images/airline_line.png" alt="">
                            </div>
                            <div class="ticket-adult">성인 1</div>
                            <div class="airticket">
                                <div class="ticket-price">
                                    <div>${air.seatPrice}</div>
                                </div>
                                <div class="ticket-btn">
                                    <button class="ticketinfo-btn" type="button">
                                        <img class="price-img-btn" src="/shushoong/images/skyblue_right.png" alt="">
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>
                 <div hidden="" class="contain-select-info">
										<div class="flex-select-info">
											<div class="date-select-info">
												<div>
													<div>
														<span>${air.departDate}</span>
													</div>
													<div>
														<span>${air.departTime}</span>
													</div>
												</div>
												<div>
													<span${air.flightTime}>비행 시간</span>
												</div>
												<div>
													<div>
														<span>${air.arrivalDate}</span>
													</div>
													<div>
														<span>${air.departTime}10:20</span>
													</div>
												</div>
											</div>
											<div class="loc-select-info">
												<span>${air.departLoc}</span>
												<div>
													<span>${air.airlineName}항공사 명</span>
												</div>
												<div>
													<span>좌석등급 항공편명</span>
												</div>
												<span>${air.arrivalLoc}도착지</span>
											</div>
											<div class="btn-select-info">
												<button id="show-comeback-btn" type="button">선택하기</button>
											</div>
										</div>
									</div>
                </div>
            </div>
        `;
	});
	$('.airline-info-container').html(airlineInfo);
	// $('.airline-info-container').append(airlineInfo);
	// $(".ticketinfo-btn").on("click", clickedTicketInfo());
	clickedTicketInfo();
	clickedShowReturnBtn();
}

