let calendar;
function createCalendar(){
	var calendarEl = document.getElementById('calendar');
	calendar = new FullCalendar.Calendar(calendarEl, {
	 initialView: 'dayGridMonth',
	 height: 'auto',
	 locale:'ko',
	 headerToolbar:{
			left:'prevYear prev',
			center:'title' ,
			right:'next nextYear' 				
		},
		selectable : true,
		droppable : true,
		editable : true,
		dateClick : function (info) {
			//클릭한 날짜 == info.dateStr
			console.log(info.dateStr);
			
			var year = info.dateStr.substr(0, 4);
			var month = info.dateStr.substr(5, 2);
			var day = info.dateStr.substr(8, 2);
			if(today_year > year || today_month > month || today_day > day) {	//현재 날짜 이전의 시간을 선택한다면
				alert("현재 날짜 이전은 선택하실 수 없습니다.");
			} else {
				if(select_status == 0){	//체크인 날짜 선택
					var startdate = $('.check-in-date').text(year + "년" + month + "월" + day + "일");
					date1 = new Date(info.dateStr);
					$('.check-in').val(year + "년" + month + "월" + day + "일");
					select_status = 1;
				} else {
					date2 = new Date(info.dateStr); 
					console.log(date1 < date2);
					if(date1 <= date2) {
						$('.check-out-date').text(year + "년" + month + "월" + day + "일");
						$('.check-out').val(year + "년" + month + "월" + day + "일");
						select_status = 0;
					} else {
						$('.check-in-date').text(year + "년" + month + "월" + day + "일");
						$('.check-out-date').text("");
						$('.check-in').val();
						$('.check-out').val();
						select_status = 0;
					}
				}
			}
		}
	});
}
function renderCalendar(){
	if(!calendar){	//달력이 아직 render가 안되있다면
		createCalendar();
	}
	calendar.render();
}