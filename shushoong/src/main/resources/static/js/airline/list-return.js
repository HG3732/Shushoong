function clickedShowReturnBtn() {
    $('.show-comeback-btn').on('click', function() {
        console.log("오는편 항공 정보 눌림");

		//var departLoc = $(this).closest('.airline-info').find('.select-info-departLoc').text().trim();
		//var arrivalLoc = $(this).closest('.airline-info').find('.select-info-arrivalLoc').text().trim();
		var airlineCode= $(this).closest('.airline-info').find('.select-info-airlineCode').text().trim();

		//console.log('Depart Loc:', departLoc);
		//console.log('Arrival Loc:', arrivalLoc);
		console.log('airlineCode:', airlineCode);
		
		//$('#departLoc').val(arrivalLoc);
		//$('#arrivalLoc').val(departLoc);
		$('#airlineCode').val(airlineCode);
		
		// 설정된 폼 데이터
        console.log('doGet Data:', {
            //departLoc: $('#departLoc').val(),
            //arrivalLoc: $('#arrivalLoc').val(),
            airlineCode: $('#airlineCode').val()
        });
		
        $('#frm-airinfo').attr('method', 'POST');
        $('#frm-airinfo').attr('action', '/shushoong/airline/list_return');
        $('#frm-airinfo').submit();
    });
}

