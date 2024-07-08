function clickedReserveBtn(){
	$('.reserve-btn').on("click", function(){
		
		console.log("결제하기 버튼 눌림");
		var seatPriceStr = $(this).closest('.airline-info').find('.seat-price').text().trim();
		var departDate = $('.airline-info-container').find('#departDate').text().trim();
		var arrivalDate = $('.airline-info-container').find('#arrivalDate').text().trim();
		var airlineCode = $(this).closest('.airline-info').find('.select-info-airlineCode').text().trim();
		var flightNo= $(this).closest('.airline-info').find('.select-info-flightNo').text().trim();
		var adult = $('.airline-info-container').find('#adult').val();
		var child = $('.airline-info-container').find('#child').val();
		var baby = $('.airline-info-container').find('#baby').val();
		var seatPrice = seatPriceStr.replace(/,/g, '').replace('원', '');
		var seatGrade = $('#frm-seatGrade');
		
		console.log('airlineCodeDirect: ', airlineCode);
		console.log('flightNo:', flightNo);
		console.log('seatPrice: ', seatPrice);
		console.log('departDate: ', departDate);
		console.log('arrivalDate: ', arrivalDate);
		console.log('frm-seatGrade: ', seatGrade);
		console.log('adult:', adult);
		console.log('child:', child);
		console.log('baby:', baby);
		$('#airlineCode').val(airlineCode);
		$('#seatPrice').val(seatPrice);
		$('#flightNo').val(flightNo);
		
		console.log('항공코드 진짜 값', $('#airlineCode').val());
		console.log('티켓 값', $('#seatPrice').val());
		
		$('#frm-airinfo').attr('method', 'POST');
        $('#frm-airinfo').attr('action', '/shushoong/airline/customer/reserve/pay');
        $('#frm-airinfo').submit();
    });
}

function clickedReturnReserve() {
		$('.reserve-return-btn').on("click", function(){
		
		console.log("결제하기 버튼 눌림 - 오는 편");
		var seatPriceStr = $('.airline-info').find('.seat-price').text().trim();
		var seatPriceReturnStr = $(this).closest('.airline-info.return').find('.seat-price-return').text().trim();
		var airlineCode = $('#select-info-airlineCode-out').text().trim();
		var airlineCodeReturn = $(this).closest('.airline-info.return').find('.select-info-airlineCode-out').text().trim();
		
		var flightNo = $('#select-info-flightNo-out').text().trim();
		var flightNoReturn = $(this).closest('.airline-info.return').find('.select-info-flightNo-out').text().trim();
		var departLocReturn = $('#select-info-departLoc').text().trim();
		var arrivalLocReturn = $('#select-info-arrivalLoc').text().trim();
		var adult = $('.airlineReturn-container').find('#adult').val();
		var child = $('.airlineReturn-container').find('#child').val();
		var baby = $('.airlineReturn-container').find('#baby').val();
		var seatGradeReturn = $('#frm-seatGrade');
		
		var seatPrice = seatPriceStr.replace(/,/g, '').replace('원', '');
		var seatPriceReturn = seatPriceReturnStr.replace(/,/g, '').replace('원', '');
		
		console.log('seatPrice:', seatPrice);
		console.log('seatPriceReturn:', seatPriceReturn);
		console.log('departLocReturn:', departLocReturn);
		console.log('arrivalLocReturn:', arrivalLocReturn);
		console.log('airlineCode:', airlineCode);
		console.log('airlineCodeReturn:', airlineCodeReturn);
		console.log('flightNo:', flightNo);
		console.log('flightNoReturn:', flightNoReturn);
		console.log('adult:', adult);
		console.log('child:', child);
		console.log('baby:', baby);
		console.log('seatGradeReturn:', seatGradeReturn);
		console.log($('#frm-return').serialize());
		
		$('#airlineCode').val(airlineCode);
		$('#airlineCodeReturn').val(airlineCodeReturn);
		$('#departLocReturn').val(arrivalLocReturn);
		$('#arrivalLocReturn').val(departLocReturn);
		$('#seatPrice').val(seatPrice);
		$('#seatPriceReturn').val(seatPriceReturn);
		$('#flightNo').val(flightNo);
		$('#flightNoReturn').val(flightNoReturn);
		
		
		$('#frm-return').attr('method', 'POST');
        $('#frm-return').attr('action', '/shushoong/airline/customer/reserve/pay');
        $('#frm-return').submit();
    });
}