function getSelectList() {

	$('#showlist').on('change', function() {
		getSelectList();
	});

	console.log('셀렉트바 active');

	$.ajax({
		url: "/shushoong/airline/list_select_options",
		method: "get",
		data: $('#frm-select').serialize(),
		dataType: "json",
		success: function(response) {
			console.log("정렬 완");
			updateAirlineList(response);
		}

	});

	function updateAirlineList(data) {
		$('.airline-info-container').empty(); // 기존 목록 초기화

		data.forEach(function(air) {
			var airlineInfo = `
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
                                <span class="span-seat">잔여 ${air.seatCount}석</span>
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
                                    <div>${air.ticketPrice}</div>
                                </div>
                                <div class="ticket-btn">
                                    <button class="ticketinfo-btn" type="button">
                                        <img class="price-img-btn" src="/shushoong/images/skyblue_right.png" alt="">
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        `;
			$('.airline-info-container').html(airlineInfo);
		});
	}
}