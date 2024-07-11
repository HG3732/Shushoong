function clickedShowReturnBtn() {
    $('.show-comeback-btn').on('click', function() {
        console.log("오는편 항공 정보 눌림");

		var seatPriceStr = $(this).closest('.airline-info').find('.seat-price').text().trim();
		var departDate = $('#select-info-departDate').text().trim();
		var arrivalDate = $('#select-info-arrivalDate').text().trim();
		var departLoc = $('#select-info-departLoc').text().trim();
		var arrivalLoc = $('#select-info-arrivalLoc').text().trim();
		var airlineCode= $(this).closest('.airline-info').find('.select-info-airlineCode').text().trim();
		var flightNo= $(this).closest('.airline-info').find('.select-info-flightNo').text().trim();
		var adult = $('.airline-info-container').find('#adult').val();
		var child = $('.airline-info-container').find('#child').val();
		var baby = $('.airline-info-container').find('#baby').val();
		
		var seatPrice = seatPriceStr.replace(/,/g, '').replace('원', '');
		
		console.log('seatPrice:', seatPrice);
		console.log('departDate', departDate);
		console.log('arrivalDate:', arrivalDate);
		console.log('Depart Loc:', departLoc);
		console.log('Arrival Loc:', arrivalLoc);
		console.log('airlineCode:', airlineCode);
		console.log('flightNo:', flightNo);
		console.log('adult:', adult);
		console.log('child:', child);
		console.log('baby:', baby);
		
		$('#seatPrice').val(seatPrice);
		$('#departDate').val(departDate);
		$('#arrivalDate').val(arrivalDate);
		$('#departLoc').val(arrivalLoc);
		$('#arrivalLoc').val(departLoc);
		$('#airlineCode').val(airlineCode);
		$('#flightNo').val(flightNo);
		
        console.log('doGet Data:', {
            departLoc: $('#departLoc').val(),
            arrivalLoc: $('#arrivalLoc').val(),
            airlineCode: $('#airlineCode').val(),
            flightNo: $('#flightNo').val(),
            seatPrice: $('#seatPrice').val()
        });
		
        $('#frm-airinfo').attr('method', 'GET');
        $('#frm-airinfo').attr('action', '/shushoong/airline/list_return');
        $('#frm-airinfo').submit();
    });
}

