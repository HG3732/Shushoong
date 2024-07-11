$(loadedHandler);

function loadedHandler() {
    $(".hotel_pic").on("change", previewImage);
    $(".add_btn").on("click", addRoomInfo);
    $(".delete_btn").on("click", deleteRoomInfo);
    $(".submit_btn").on("click", submitHandler);
    
}
function ajaxErrorHandler(request, status, error) {
	alert("code: " + request.status + "\n" + "message: "
		+ request.responseText + "\n"
		+ "error: " + error);
}
	function submitHandler() {
//		출처: https://devlifetestcase.tistory.com/12 [개발 인생 TestCase:티스토리]
		//console.log(contextRoot);
		console.log($("#frm_register"));
//		var frmHotelData = new FormData( $("#frm_register") );
		var frmHotelData = new FormData(document.getElementById("frm_register") );
		var roomArr = [];
		$(".select_wrap").each(function(){
			var room = {};
			room.roomCat= $(this).find("[name=roomCat").val();
			room.roomPrice=$(this).find("[name=roomPrice").val();
			room.roomCount=$(this).find("[name=roomCount").val();
			room.roomCap=$(this).find("[name=roomCap").val();
			room.roomAtt=$(this).find("[name=roomAtt").val();
			roomArr.push(room);
			//frmHotelData.append("roomList", room);
		});
//		frmHotelData.append("roomList", roomArr);
		//console.log(frmHotelData.getAll("roomList"));
		
		$.ajax({
		    success: ajaxErrorHandler,
			url: contextRoot+"business/my/hotel/register/submit",
		    type : "POST",
		    enctype : "multipart/form-data",
	        cache : false,
		    processData : false,
		    data: frmHotelData,
		    success: function(){},
		    error : ajaxErrorHandler
	    });
		    
//		
//		method="POST" th:action="@{/business/my/hotel/register/submit}"
//		
//		new FormData(frm_register, submitter);
		
	}

	//썸네일 미리보기
	function previewImage(event) {
			const files = event.target.files;
			$('.thumbnail-box').empty(); // 기존 썸네일 초기화
			
			Array.from(files).forEach(file => {
				const reader = new FileReader();
				reader.onload = function(e) {
					const thumbnail = $('<div class="thumbnail"></div>');
					thumbnail.css('background-image', 'url(' + e.target.result + ')');
					$('.thumbnail-box').append(thumbnail);
				};
				reader.readAsDataURL(file);
			});
		}
	
	//방 추가
	function addRoomInfo() {
		var htmlVal = '';
		htmlVal += `
			<div class="select_wrap">
				<select name="roomCat" class="category" style="width:100px; height:30px; border-radius:10px;">
					 <option value="0">스탠다드룸</option>
					 <option value="1">디럭스룸</option>
					 <option value="2">슈페리어룸</option>
					 <option value="3">스위트룸</option>
					 <option value="4">기타</option>
				</select>
				<select name="roomCap" class="capacity" style="width:100px; height:30px; border-radius:10px;">
					 <option value="2">더블룸</option>
					 <option value="3">트리플룸</option>
					 <option value="4">쿼드룸</option>
					 <option value="5">파티룸</option>
				</select>
				<select name="roomAtt" class="attribute" style="width:100px; height:30px; border-radius:10px;">
					 <option value="0">없음</option>
					 <option value="1">오션뷰</option>
					 <option value="2">마운틴뷰</option>
					 <option value="3">시티뷰</option>
					 <option value="4">기타</option>
				</select>
				<input type="text" name="roomCount" value="1" placeholder="방 개수(숫자만 적어주세요)" class="input_roomnum">														
				<input type="text" name="hotelPrice" value="99999999" placeholder="방 가격(숫자만 적어주세요)" class="input_roomprice">														
			</div>
			`;
		$(".select_option").append(htmlVal);
	}
	
	//방 삭제
	function deleteRoomInfo(thisElement) {
		$(".select_wrap:last-child").remove();
	}
	
	//양식 제출
	function submitHandler() {
		
		var frmHotelData = new FormData(document.getElementById("frm_register") );
		$(".select_wrap").each(function(idx){
			frmHotelData.append("roomList[" + idx + "].roomCat", $(this).find("[name=roomCat").val());
			frmHotelData.append("roomList[" + idx + "].hotelPrice", $(this).find("[name=hotelPrice").val());
			frmHotelData.append("roomList[" + idx + "].roomCount", $(this).find("[name=roomCount").val());
			frmHotelData.append("roomList[" + idx + "].roomCap", $(this).find("[name=roomCap").val());
			frmHotelData.append("roomList[" + idx + "].roomAtt", $(this).find("[name=roomAtt").val());
		});
		
		$.ajax({
		    success: ajaxErrorHandler,
			url: contextRoot+"business/my/hotel/register/submit",
		    type: "POST",
		    enctype: "multipart/form-data",
	        cache: false,
		    processData: false,
			contentType: false,
		    data: frmHotelData,
		    success: function(){
				alert("등록에 성공하였습니다.");
				location.href='../hotel';
			},
		    error : ajaxErrorHandler
	    });
	}

