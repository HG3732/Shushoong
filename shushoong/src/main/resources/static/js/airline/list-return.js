function clickedShowReturnBtn() {
    $('.show-comeback-btn').on('click', function() {
        console.log("오는편 항공 정보 눌림");

		var seatPriceStr = $(this).closest('.airline-info').find('.seat-price').text().trim();
		var departLoc = $('#select-info-departLoc').text().trim();
		var arrivalLoc = $('#select-info-arrivalLoc').text().trim();
		var airlineCode= $(this).closest('.airline-info').find('.select-info-airlineCode').text().trim();
		
		var seatPrice = seatPriceStr.replace(/,/g, '').replace('원', '');
		
		console.log('seatPrice:', seatPrice);
		console.log('Depart Loc:', departLoc);
		console.log('Arrival Loc:', arrivalLoc);
		console.log('airlineCode:', airlineCode);
		
		$('#seatPrice').val(seatPrice);
		$('#departLoc').val(arrivalLoc);
		$('#arrivalLoc').val(departLoc);
		$('#airlineCode').val(airlineCode);
		
        console.log('doGet Data:', {
            departLoc: $('#departLoc').val(),
            arrivalLoc: $('#arrivalLoc').val(),
            airlineCode: $('#airlineCode').val(),
            seatPrice: $('#seatPrice').val()
        });
		
        $('#frm-airinfo').attr('method', 'GET');
        $('#frm-airinfo').attr('action', '/shushoong/airline/list_return');
        $('#frm-airinfo').submit();
    });
}

