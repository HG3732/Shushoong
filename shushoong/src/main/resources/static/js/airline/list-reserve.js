function clickedReserveBtn(){
	$('.reserve-btn').on("click", function(){
		
		console.log("결제하기 버튼 눌림");
		var airlineCode = $(this).closest('.airline-info').find('.select-info-airlineCode').text().trim();
		console.log('airlineCodeDirect: ', airlineCode);
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
		
		var airlineCode= $(this).closest('.airline-info.return').find('.select-info-airlineCode-out').text().trim();
		console.log('airlineCodeReturn:', airlineCode);
		console.log($('#airlineCodeReturn').val(airlineCode));
		console.log($('#frm-return').serialize());
		$('#airlineCodeReturn').val(airlineCode);
		
		$('#frm-return').attr('method', 'POST');
        $('#frm-return').attr('action', '/shushoong/airline/customer/reserve/pay');
        $('#frm-return').submit();
    });
}