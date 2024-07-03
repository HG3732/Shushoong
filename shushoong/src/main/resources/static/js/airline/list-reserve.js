function clickedReserveBtn(){
	$('.reserve-btn').on("click", function(){
		
		console.log("결제하기 버튼 눌림");
		//var adult = $('.passenger-info').find('.adult').text().trim();
		//var child = $('.passenger-info').find('.child').text().trim();
		//var baby = $('.passenger-info').find('.baby').text().trim();
		var airlineCode = $(this).closest('.airline-info').find('.select-info-airlineCode').text().trim();
		console.log('airlineCodeDirect: ', airlineCode);
		//console.log('adult: ', adult);
		//console.log('child: ', child);
		//console.log('baby: ', baby);
		//var seatPrice = $(this).closest('.airline-info').find('.select-info-airlineCode').text().trim();
		//console.log('seatPriceDirect : ', seatPrice);
		$('#airlineCode').val(airlineCode);
		//$('#seatPrice').val(seatPrice);
		//$('#adult').val(adult);
		//$('#child').val(child);
		//$('#baby').val(baby); 
		
		console.log('항공코드 진짜 값', $('#airlineCode').val());
		//console.log('좌석 값 진짜 값' , $('#seatPrice').val());
		//console.log('어른 진짜 값', $('#adult').val());
		//console.log('child 진짜 값', $('#child').val());
		//console.log('baby 진짜 값' , $('#baby').val());
		
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