function clickedReserveBtn(){
	$('.reserve-btn').on("click", function(){
		
		console.log("결제하기 버튼 눌림");
		var departDate = $('.airline-info-container').find('#departDate').text().trim();
		var arrivalDate = $('.airline-info-container').find('#arrivalDate').text().trim();
		var airlineCode = $(this).closest('.airline-info').find('.select-info-airlineCode').text().trim();
		console.log('airlineCodeDirect: ', airlineCode);
		console.log('departDate: ', departDate);
		console.log('arrivalDate: ', arrivalDate);
		$('#airlineCode').val(airlineCode);
		
		console.log('항공코드 진짜 값', $('#airlineCode').val());
		
		$('#frm-airinfo').attr('method', 'POST');
        $('#frm-airinfo').attr('action', '/shushoong/airline/customer/reserve/pay');
        $('#frm-airinfo').submit();
    });
}

function clickedReturnReserve() {
		$('.reserve-return-btn').on("click", function(){
		
		console.log("결제하기 버튼 눌림 - 오는 편");
		var airlineCode = $('#select-info-airlineCode-out').text().trim();
		var airlineCodeReturn = $(this).closest('.airline-info.return').find('.select-info-airlineCode-out').text().trim();
		var departLocReturn = $('#select-info-departLoc').text().trim();
		var arrivalLocReturn = $('#select-info-arrivalLoc').text().trim();
		
		console.log('departLocReturn:', departLocReturn);
		console.log('departLocReturn:', departLocReturn);
		console.log('airlineCode:', airlineCode);
		console.log('airlineCodeReturn:', airlineCodeReturn);
		console.log($('#frm-return').serialize());
		
		$('#airlineCode').val(airlineCode);
		$('#airlineCodeReturn').val(airlineCodeReturn);
		$('#departLocReturn').val(arrivalLocReturn);
		$('#arrivalLocReturn').val(departLocReturn);
		
		
		$('#frm-return').attr('method', 'POST');
        $('#frm-return').attr('action', '/shushoong/airline/customer/reserve/pay');
        $('#frm-return').submit();
    });
}