

//국가 검색 클릭 시 국가 선택 창 표시
	function locationClickHandler() {
		$('.wrap-date-box').css('display', 'none');
		$('.wrap-room-box').css('display', 'none');
		$('.wrap-loc-box').toggle();
	}
	
	//국가 클릭 시 해당 국가의 주요 지역 표시
	function localClickHandler() {
		var index = $(this).index();
		$('.local-box').css('display', 'none');
		$('.country').css('font-weight', '100');
		$('.country').css('background-color', 'white');
		$('.local-box').eq(index).css('display', 'grid');
		$(this).css('background-color', 'var(--color_blue_2)');
		$(this).css('font-weight', '900');
	}
	
	//지역 클릭 시 검색란에 자동 입력
	function selectLocHandler() {
		var loc = $(this).text();
		var loc_nation = $(this).data('nation');
		var loc_code = $(this).data('loccode');
		console.log(loc_code);
		$('.selected-loc').val(loc);
		$('input[name=loc]').val(loc_nation + loc_code);
	}
	
	//날짜 검색 클릭 시 달력 표시
	function timeClickHandler() {
		$('.wrap-loc-box').css('display', 'none');
		$('.wrap-room-box').css('display', 'none');
		$('.wrap-date-box').toggle();
/*		console.log($('.wrap-date-box').css('display'));
		if($('.wrap-date-box').css('display') == 'block'){
			calendar.render();
		} else {
			$('#calendar').html();
		}*/
	}
	
	//객실 수 클릭 시 객실, 인원 수 선택 표시
	function roomClickHandler() {
		$('.wrap-loc-box').css('display', 'none');
		$('.wrap-date-box').css('display', 'none');
		$('.wrap-room-box').toggle();
	}
	
	//방, 인원 수량 증감
	function plusClickHandler() {
		var numStr = $(this).prev().text();
		var num = Number(numStr);
		if($(this).parent().prev().children().text() != "객실" || num < 8){	//객실은 8개까지만 예약 가능
			$(this).prev().text(++num);
		}
		var cat = $(this).parent().prev().children().eq(0).text();
		if(cat == '객실'){
			$('input[name=room]').val(num);
			$('.room-num').text(num);
		} else if (cat == '성인 '){
			$('input[name=adult]').val(num);
			$('.adult-num').text(num);
		} else if (cat == '아동 '){
			$('input[name=child]').val(num);
			$('.child-num').text(num);
		}
	}
	
	//방, 인원 수량 증감
	function minusClickHandler() {
		var num = $(this).next().text();
		if(($(this).parent().prev().children().eq(0).text() == "성인 " && num > 1)
			|| ($(this).parent().prev().children().eq(0).text() == "아동 " && num > 0)		//성인은 최소 한명 이상, 아동은 0명 선택 가능
			|| $(this).parent().prev().text() == "객실" && num > 1){	//객실도 1개 이상...
			$(this).next().text(--num);
		}
		
		var cat = $(this).parent().prev().children().eq(0).text();
		if(cat == '객실'){
			$('input[name=room]').val(num);
			$('.room-num').text(num);
		} else if (cat == '성인 '){
			$('input[name=adult]').val(num);
			$('.adult-num').text(num);
		} else if (cat == '아동 '){
			$('input[name=child]').val(num);
			$('.child-num').text(num);
		}
	}