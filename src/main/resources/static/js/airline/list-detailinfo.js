function clickedTicketInfo() {
	$(".ticketinfo-btn").on("click", function() {
		console.log("티켓 상세 정보 눌림");
		var ticketDetailInfo = $(this).parents().find(".contain-select-info");
		var airlineInfoBox = $(this).parents().find(".airline-info");

		ticketDetailInfo.slideToggle(function() {

			if (ticketDetailInfo.is(":visible")) {
				airlineInfoBox.css({
//					"border-width": "1px 1px 0 1px",
					// "border-bottom-color" : "transparent",
					// "border-radius": "10px 10px 0 0",
					// "z-index" : 0
				});
			} else {
				console.log("토글 닫힘");
				 airlineInfoBox.css({
					//"border": "1px solid black",
					//"border-radius": "10px",
					//"z-index" : 3
				});

			}
		});
	});
}