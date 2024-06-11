function clickedReturnTicketInfo() {
	$(".ticketinfo-btn").on("click", function() {
		console.log("티켓 상세 정보 눌림");
        // 클릭한 버튼이 속한 .airline-info 요소 찾기
        var airlineInfoElement = $(this).closest('.airline-info.return');

        // .airline-info 다음 형제 요소 중 .contain-select-info 선택
        var ticketDetailInfo = airlineInfoElement.find('.contain-select-info');
        
//        ticketDetailInfo.slideToggle("slow");
        
        // 토글 처리
        if (ticketDetailInfo.is(":visible")) {
            ticketDetailInfo.slideUp('slow', function() {
                console.log("토글 닫힘");
                airlineInfoElement.children().find('.ticketinfo-btn').css({
					"transform": "rotate(90deg)"
					// "transform": "rotate(270deg)"
                    // 필요한 스타일 추가
                    // 예시: "border": "1px solid black",
                    // "border-radius": "10px",
                    // "z-index": 3
                });
            });
        } else {
            // 숨겨져 있으면 보이게 함
            ticketDetailInfo.slideDown('slow', function() {
                console.log("토글 열림");
                    // airlineInfoElement.children().find('.ticketinfo-btn').addClass('.open');
                    airlineInfoElement.children().find('.ticketinfo-btn').css({
					"transform": "rotate(270deg)"
	                    // 필요한 스타일 추가
	                    // 예시: "border-width": "1px 1px 0 1px",
	                    // "border-bottom-color": "transparent",
	                    // "border-radius": "10px 10px 0 0",
	                    // "z-index": 0
                });
            });
        }
    });
}