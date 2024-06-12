function showAllRoomHandler(){
	console.log(this);
	console.log($(this).siblings());

	htmlVal = "";
	htmlVal += `
				 <form>
			        <div class="wrap_room">
			            <div th:each="roomlist: \${roomlist}" class="room">
			                <div th:utext="\${roomlist.roomCat} + ', ' + \${roomlist.roomAtt}" class="room_cat"></div>
			                <div class="amount_num">
			                    <strong th:text="${roomlist.hotelPrice}" style="font-family: 'SOYOMapleBoldTTF';font-size: var(--font1);">1,834,119</strong>
			                    원
			                </div>
			                <button type="button" class="btn_reserve">예약하기</button>
			                <div style="grid-column: 1/9">
			                    <hr style="border: 1px solid #F1F1F1;">
			                </div>
			            </div>
			        </div>
		    	</form>
			`;
			
			$(this).before(htmlVal);
			$(this).hide();
}

/* ============================지도 모달창================================ */
var geocoder;
var map;
function initialize() {
  geocoder = new google.maps.Geocoder();
  
  /* 서울을 기준으로 잡기 */
  var latlng = new google.maps.LatLng(37.566535, 126.9779692);
  var mapOptions = {
    zoom: 8,
    center: latlng
  }
  map = new google.maps.Map(document.getElementById('map'), mapOptions);
}

function codeAddress() {
  var address = document.getElementById('address').value;
  geocoder.geocode( { 'address': address}, function(results, status) {
    if (status == 'OK') {
      map.setCenter(results[0].geometry.location);
      var marker = new google.maps.Marker({
          map: map,
          position: results[0].geometry.location
      });
    } else {
      alert('Geocode was not successful for the following reason: ' + status);
    }
  });
}




/*=================== 리뷰 막대그래프 관련 =================== */
let reviewRate = [2, 4, 3, 4.6];
let reviewDataList = ["청결 상태", "편의시설/서비스", "직원 및 서비스", "숙박시설 상태 및 시설"];

var ctx = document.getElementById('myChart').getContext('2d');
var myChart3 = new Chart(ctx, {
    type: 'bar',
    data: {
        labels: reviewDataList,
        datasets: [{
            data: reviewRate,
            backgroundColor: '#D9D9D9',
            hoverOffset: 4,
            barThickness: 30
        }]
    },
    options: {
        responsive: true, // 차트 크기를 반응형으로 조절
        maintainAspectRatio: false, // 차트 비율 유지하지 않음
        layout: {
            padding: 20
        },
        scales: {
            x: {
                grid: {
                    display: false
                },
                ticks: {
                    autoSkip: false,
                    maxRotation: 0,
                    minRotation: 0
                }
            },
            y: {
                grid: {
                    display: false
                },
                axis: 'y',
                display: false
            }
        },
        plugins: {
            legend: {
                display: false,
            },
        }
    },
    plugins: [{
        afterDraw: function(chart) {
            var ctx = chart.ctx;
            var xAxis = chart.scales['x'];

            xAxis.ticks.forEach((tick, index) => {
                let x = xAxis.getPixelForTick(index);
                let y = chart.chartArea.bottom + 30; /* 레이블의 아래쪽에 위치 */

                // "4.5/5" 추가
                let additionalLabel = '4.5/5';

                ctx.textAlign = 'center';
                ctx.textBaseline = 'top';
                ctx.fillStyle = '#000';
                ctx.fillText(additionalLabel, x, y); // 추가 레이블 그리기
            });
        }
    }]
});


/*===================== 리뷰 모달창 관련 ==========================  */
function checkScoreHandler() {
	
	console.log(this);
	console.log($(this).children());
	
	/* 한번 별을 초기화 */
 	$(this).siblings().children().attr("src", "/shushoong/images/star.png").css('width', '15px;');
 	
 	$(this).prevAll().children().attr("src", "/shushoong/images/star_line.png").css('width', '15px;');
  	$(this).next().children().attr("src", "/shushoong/images/star_line.png").css('width', '15px;');

	

 }

function reviewWriteHandler(){
	$('#modal').css("display", 'block');
		
}

function reviewEscHandler(){
	$('#modal').css("display", 'none');
 	$('.score').prevAll().children().attr("src", "/shushoong/images/star.png").css('width', '15px;');
 	$('.score').next().children().attr("src", "/shushoong/images/star.png").css('width', '15px;');
	
}
