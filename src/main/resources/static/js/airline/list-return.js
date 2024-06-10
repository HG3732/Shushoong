function clickedShowReturnBtn() {
	$("#show-comeback-btn").on('click', function() {
		$('.select-airplane.return').css('display', 'block');
		// $('.airline-info.return').css('display', 'block');

		console.log('왕복 항공편 눌림');

		var departLoc = $(this).closest('.airline-info').find('.info.loc#info-departLoc').text().trim();
		var arrivalLoc = $(this).closest('.airline-info').find('.info.loc#info-arrivalLoc').text().trim();

		console.log('Depart Loc:', departLoc);
		console.log('Arrival Loc:', arrivalLoc);

		$.ajax({
			url: "/shushoong/airline/list_return/ajax",
			method: "GET",
			data: { departLoc: arrivalLoc, arrivalLoc: departLoc },
			dataType: "json",
			success: function(response) {

				console.log('왕복 항공편 js:', response);
				if (Array.isArray(response)) { // 응답이 배열인지 확인

					var returnAirlineInfo = '';

					response.forEach(function(air) {
						returnAirlineInfo +=
							`
					 <div class="airline-info return">
                        <div class="seat-count">
                            <div class="airline-flex">
                                <div class="airline-logo">
                                    <div class="span-seat-div">
                                        <span class="span-seat">잔여 9석</span>
                                    </div>
                                    <img src="${air.airlineImg}" alt="">
                                </div>
                                <div class="airline-name">
                                    <span>${air.airlineName}</span>
                                </div>
                                <div class="airinfo depart-info">
                                    <div class="info date">${air.departDate}</div>
                                    <div class="info time">${air.departTime}</div>
                                    <div class="info loc">${air.departLoc}</div>
                                </div>
                                <div class="arrow">
                                    <div class="via-count">
                                        <span>${air.viaCount}</span>
                                    </div>
                                    <img src="/shushoong/images/airline_via.png" alt="">
                                    <div class="flytime">${air.flightTime}</div>
                                </div>
                                <div class="airinfo arr-info">
                                    <div class="info date">${air.arrivalDate}</div>
                                    <div class="info time">${air.arrivalTime}</div>
                                    <div class="info loc">${air.arrivalLoc}</div>
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
                            <div hidden class="contain-select-info">
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
                                            <span>${air.flightTime}</span>
                                        </div>
                                        <div>
                                            <div>
                                                <span>${air.arrivalDate}</span>
                                            </div>
                                            <div>
                                                <span>${air.departTime}</span>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="loc-select-info">
                                        <span>${air.departLoc}</span>
                                        <div>
                                            <span>${air.airlineName}</span>
                                        </div>
                                        <div>
                                            <span>좌석등급 항공편명</span>
                                        </div>
                                        <span>${air.arrivalLoc}</span>
                                    </div>
                                    <div class="btn-select-info">
                                        <button id="show-comeback-btn" type="button">선택하기</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>`;
					});
					$('.airlineReturn-container').html(returnAirlineInfo);
				}
			}
		});
	});
}