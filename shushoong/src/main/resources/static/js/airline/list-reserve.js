function clickedReserveBtn(){
	$('.reserve-btn').on("click", function(){
		
		console.log("결제하기 버튼 눌림");
		
		var airlineCode= $(this).closest('.airline-info').find('.select-info-airlineCode').text().trim();
		console.log('airlineCode:', airlineCode);
		
		$('#airlineCode').val(airlineCode);
		
		 $('#frm-return').attr('method', 'GET');
         $('#frm-return').attr('action', '/shushoong/airline/customer/reserve/pay');
         $('#frm-return').submit();
    });
}