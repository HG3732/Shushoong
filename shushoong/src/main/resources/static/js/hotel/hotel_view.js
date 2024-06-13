/*===========방 전체부기 눌렀을 때 나머지 방들 출력하는 함수============*/
function showAllRoomHandler(){
	$(".wrap_room .room:gt(2)").show();
	$(".more_room_btn").hide();
	$('.price_explain').css('margin-top', '10px')
	
}

/* ============================지도 모달창================================ */

function showMapHandler() {
    var modal = $('.mapModal');
    var address = $(this).data('address'); // 버튼에 설정된 데이터 속성(주소)
    //클릭된 버튼의 data-address 속성에서 주소 값을 가져와 address 변수에 할당
    console.log("주소값 받기========" + $(this).data('address'));
    
    $('#address').val(address); // input type=text에 주소 입력
    modal.show();
    initialize(); //Google 지도를 초기화하는 initialize 함수를 호출
    setTimeout(() => {
		//1밀리초 후에 실행
        google.maps.event.trigger(map, "resize");
        //Google 지도의 크기가 변경되었음을 API에 알려주고 모달 창이 뜨기 전에 이 작업을 수행해야 함
        codeAddress(); // 자동으로 encode라고 써있는 button 누르기
    }, 1);
}

//window를 눌렀을 때 함수 설정
$(window).on('click', function(event) {	
    var modal = $('.mapModal');
    console.log("모달창========" + $(event.target).is(modal));
    console.log("window===============" + $(window));
    
    if ($(event.target).is(modal)) {
		//window를 click 했을 때 modal창이니? 라고 물었을 때 false 라면 모달창을 숨겨줘
		// --> 모달창 외 다른 곳을 눌렀을 떄 모달창 닫히는 것
		//$(event.target).is(modal) 이거 console창에 찍어보면 false 나옴
        modal.hide();
    }
});


var geocoder;
var map;
function initialize() {
  geocoder = new google.maps.Geocoder();

  /* 서울을 기준으로 잡기 */
/*var latlng = new google.maps.LatLng(37.566535, 126.9779692);*/
  var mapOptions = {
    zoom: 12,
/*    center: latlng*/
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
