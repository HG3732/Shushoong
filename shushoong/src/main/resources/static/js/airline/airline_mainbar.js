
	
	
				/* 편도 왕복 적용하는 펑션 */		
		function ticketType(event,ticketType,ticketTypeBtn){
			var i, tabcontent, tablinks;
			tabcontent=document.getElementsByClassName("tabcontent");
			tablinks = document.getElementsByClassName("tablinks");

			
			for(i = 0 ; i<tabcontent.length; i++){
				tabcontent[i].style.display="none";
			}
		
			for(i=0; i<tablinks.length; i++){
				tablinks[i].style.border= "none";
			}
		
			document.getElementById(ticketType).style.display="block";
			document.getElementById(ticketTypeBtn).style.borderBottom = "2px solid  rgb(0,106,255)";
			$("#one_way_date").children(1).text("날짜 선택");										/*날짜 초기화*/
			$("#round_trip_date").children(1).text("날짜 선택");									/*날짜 초기화*/
			$("#hidden_depart_date").val("");													/*날짜 초기화*/
			$("#hidden_arrival_date").val("");  												/*날짜 초기화*/
			event.currentTarget.className +=" active";
		}														
			
				/* 편도 왕복 value input */
		$("button.tablinks").on("click",function(){
			if($(this).attr("id")=="one_way_btn"){
				$("#hidden_ticket_type").val(1);
			}else if($(this).attr("id")=="round_trip_btn"){
				$("#hidden_ticket_type").val(2);
			}
		});
		
				/* 출입국 지역 날짜 인원 according 버튼 펑션 */		
		function accordingInfo(event,accordingInfo){
		
			var i, according;
			according = document.getElementsByClassName("according");
			if(document.getElementById(accordingInfo).style.display==="block"||document.getElementById(accordingInfo).style.display==="flex"){
				for(i=0 ; i<according.length; i++){
					according[i].style.display="none";
				}
			}else{
				for(i=0 ; i<according.length; i++){
					according[i].style.display="none";
				}
				if(accordingInfo == "depart_accoding" || accordingInfo == "arrival_accoding"){
					document.getElementById(accordingInfo).style.display="flex";
				}else{
					document.getElementById(accordingInfo).style.display="block";
				}
			}
		}
		
				/* 출국 설정하는 vertical Tabs 적용하는 펑션 */
		function departNation(event,nation,nationId){
			var i, tabcontent, tablinks;
			tabcontent=document.getElementsByClassName("according_section_depart_tabcontent");
			tablinks = document.getElementsByClassName("according_section_depart_tablinks");
			
			for(i = 0 ; i<tabcontent.length; i++){
				tabcontent[i].style.display="none";
			}
		
			for(i=0; i<tablinks.length; i++){
				tablinks[i].style.fontWeight ="0";
				tablinks[i].style.backgroundColor = "white";
			}
		
			document.getElementById(nation).style.display="block";
			document.getElementById(nationId).style.fontWeight ="900" ;
			document.getElementById(nationId).style.backgroundColor = "var(--color_blue_2)";
			}
				
				/* 입국 설정하는 vertical Tabs 적용하는 펑션 */
		function arrivalNation(event,nation,nationId){
			var i, tabcontent, tablinks;
			tabcontent=document.getElementsByClassName("according_section_arrival_tabcontent");
			tablinks = document.getElementsByClassName("according_section_arrival_tablinks");
			
			
			for(i = 0 ; i<tabcontent.length; i++){
				tabcontent[i].style.display="none";
			}
		
			for(i=0; i<tablinks.length; i++){
				tablinks[i].style.fontWeight ="0";
				tablinks[i].style.backgroundColor = "white";
			}
		
			document.getElementById(nation).style.display="block";
			document.getElementById(nationId).style.fontWeight ="900" ;
			document.getElementById(nationId).style.backgroundColor = "var(--color_blue_2)";
			
			event.currentTarget.className +=" active";
		}
		
				/* 출국 바 이름 변경 + value input + 입국 according open 펑션 */
		$("button.depart_accoding_value").on("click",function(){
			var depart = $(this).text();
			$("button#depart").each(function(){
				$(this).text(depart);
			})
			$("#hidden_depart_value").val($(this).attr("id"));								/* value input */
			document.getElementById("depart_accoding").style.display="none"; 		/* 출국 according close */
			document.getElementById("arrival_accoding").style.display="flex";		/* 입국 according open */
		});
				
				/* 입국 value input 펑션 */
		$("button.arrival_accoding_value").on("click",function(){
			var arrival = $(this).text();
			$("button#arrival").each(function(){
				$(this).text(arrival);
			})
			$("#hidden_arrival_value").val($(this).attr("id"));								/* value input */
			document.getElementById("arrival_accoding").style.display="none";		/* 입국 according close */
			document.getElementById("date_accoding").style.display="block";			/* 날짜 according open */
		});
				
				
				/* 날짜 설정하는 펑션  */
				
														/* 달력 api */
	    document.addEventListener('DOMContentLoaded', function() {
	    	var date = new Date();
    	    var year = date.getFullYear();
    	    var month = ("0" + (1 + date.getMonth())).slice(-2);
    	    var day = ("0" + date.getDate()).slice(-2);
    	    
    	    
	    	
			var calendarEl = document.getElementById('calendar');
		    var calendar = new FullCalendar.Calendar(calendarEl, {
		   		initialView: 'dayGridMonth',
		   		selectable : true,
		       	dateClick: function(info) {
		       		
					if($("#hidden_ticket_type").val()==1){
						if(year + "-" + month + "-" + day > info.dateStr){
								alert("금일보다 과거의 날짜를 선택하실 수 없습니다. \n다시 입력해 주시길 바랍니다.");
								$("#round_trip_date").children(1).text("날짜 선택");
							}else{
							   	$("#hidden_depart_date").val(info.dateStr);
							   	$("#one_way_date").children(1).text($("#hidden_depart_date").val().substr(5,2)+"."+$("#hidden_depart_date").val().substr(8,2));
							   	document.getElementById("date_accoding").style.display="none";
							   	document.getElementById("people_lineup_sit_accoding").style.display="block";
							}
					}else if($("#hidden_ticket_type").val()==2){
						if($("#depart_date").css("display")=="block"){
							if(year + "-" + month + "-" + day > info.dateStr){
								alert("금일보다 과거의 날짜를 선택하실 수 없습니다. \n다시 입력해 주시길 바랍니다.");
								$("#round_trip_date").children(1).text("날짜 선택");
							}else if(year + "-" + month + "-" + day <= info.dateStr){
								$("#hidden_depart_date").val(info.dateStr);
								$("#round_trip_date").children(1).text($("#hidden_depart_date").val().substr(5,2)+"."+$("#hidden_depart_date").val().substr(8,2)+"/");
								document.getElementById("btn_return_depart_date").style.display="block";
								document.getElementById("depart_date").style.display="none";
								document.getElementById("arrival_date").style.display="block";
							}
						}
						else if($("#arrival_date").css("display")=="block"){
							$("#hidden_arrival_date").val(info.dateStr);
							if($("#hidden_depart_date").val() > $("#hidden_arrival_date").val()){
								alert("출발일보다 과거의 날짜를 선택하실 수 없습니다. \n다시 입력해 주시길 바랍니다.");
								$("#round_trip_date").children(1).text("날짜 선택");
							}
							else if($("#hidden_depart_date").val() <= $("#hidden_arrival_date").val()){
								$("#round_trip_date").children(1).text($("#hidden_depart_date").val().substr(5,2)+"."+$("#hidden_depart_date").val().substr(8,2)+"/"+$("#hidden_arrival_date").val().substr(5,2)+"."+$("#hidden_arrival_date").val().substr(8,2));
								document.getElementById("btn_return_depart_date").style.display="none";
								document.getElementById("arrival_date").style.display="none";
								document.getElementById("depart_date").style.display="block";
								document.getElementById("date_accoding").style.display="none";
								document.getElementById("people_lineup_sit_accoding").style.display="block";
							}
						}
					}
		       	}
		       
		       	  
		    });
		    calendar.render();
	    
	    });
	    
													/* 오는날에서 가는날로 전환 */
		$("#btn_return_depart_date").on("click",function(){
			document.getElementById("arrival_date").style.display="none";
			document.getElementById("btn_return_depart_date").style.display="none";
			document.getElementById("depart_date").style.display="block";
		});		
				
				/* 인원 설정하는 펑션 - plus */
		$("button#plus").on("click",function(){
			if(Number($("div#adult").children("div").text()) == Number($("div#baby").children("div").text())){
				if($(this).parent().attr("id")=="adult"||$(this).parent().attr("id")=="child"){
					$(this).prev().text(Number($(this).prev().text())+1);
				}else if($(this).parent().attr("id")=="baby"){
					alert("유아는 좌석이 따로 배정되지 않으므로, \n성인보다 많이 탑승할 수 없습니다.");
				}
			}else if(Number($("div#adult").children("div").text()) <= Number($("div#baby").children("div").text())){
				alert("유아는 좌석이 따로 배정되지 않으므로, \n성인보다 많이 탑승할 수 없습니다.");
			}else{
				$(this).prev().text(Number($(this).prev().text())+1);
			}
			
		});
			
			/* 인원 설정하는 펑션 - minus */
		$("button#minus").on("click",function(){
			if(Number($(this).next().text()) < 1){
				alert("인원은 0명 이하로 입력하실 수 없습니다.");
			}else if(Number($("div#adult").children("div").text()) == Number($("div#baby").children("div").text())){
				if($(this).parent().attr("id")=="adult"){
					alert("유아는 좌석이 따로 배정되지 않으므로, \n성인보다 많이 탑승할 수 없습니다.");
				}else if($(this).parent().attr("id")=="baby"||$(this).parent().attr("id")=="child"){
					$(this).next().text(Number($(this).next().text())-1);
				}
			}else if(Number($("div#adult").children("div").text()) < Number($("div#baby").children("div").text())){
				alert("유아는 좌석이 따로 배정되지 않으므로, \n성인보다 많이 탑승할 수 없습니다.");
			}else{
			$(this).next().text(Number($(this).next().text())-1);
			}
		});
			
					/* 좌석 설정하는 펑션 */
		$("button.sit_kinds").on("click",function(){
			var kinds = document.getElementsByClassName("sit_kinds");
			for(i = 0 ; i<kinds.length; i++){
				kinds[i].style.background="none";
				kinds[i].style.color="black";
			}
		document.getElementById($(this).attr("id")).style.backgroundColor="var(--color_blue_2)";
		document.getElementById($(this).attr("id")).style.color="black";
		}); 
					
					/* 인원 or 좌석 input value + 바에 띄우기  */
		$("button#plus").on("click",lineupSitFunction);
		$("button#minus").on("click",lineupSitFunction);
		$("button.sit_kinds").on("click",lineupSitFunction);
		function lineupSitFunction(){
		 	if($(this).parent().attr("id")=="adult"){
		 		$("#hidden_adult").val($("#adult_value").text());}
			else if($(this).parent().attr("id")=="child"){
				$("#hidden_child").val($("#child_value").text());			}
			else if($(this).parent().attr("id")=="baby"){
		 		$("#hidden_baby").val($("#baby_value").text());}
			else if($(this).parent().attr("class")=="sit"){
				$("#hidden_seat_grade").val($(this).attr("id"));
			} 
			$("div#people_lineup_text").each(function(){
				$(this).text(
				$("#"+$("#hidden_seat_grade").val()).text() +"/"+
				"성인"+$("#hidden_adult").val()+"/"+
				"소아"+$("#hidden_child").val()+"/"+
				"유아"+$("#hidden_baby").val()
				);
			});
		 	
		}
		
		
		
		
		
	