//좋아요 누를 시 색깔 변함
function likeHandler(thisElement){
	//console.log(thisElement);	//이벤트 함수가 걸린 태그
	//console.log(event.target);	//마우스 클릭 시 제일 가까운 요소 (하트 사진 누르면 하트 사진 뜨는....)
	
	var currentSrc = $(thisElement).attr('src');
	/* 이 경로를 토대로 이미지 바뀌는 경우의 수 적어주기 */
	
	//좋아요 클릭 시 해당 호텔 코드 출력
	var hotelcode = $(thisElement).parents('.hotel').data('hotelcode');
	//console.log(hotelcode);
	
	if( currentSrc === '/shushoong/images/heart.png'){
		$.ajax({
		url: "/shushoong/hotel/like/insert.ajax",
		method: "get",
		data: {hotelCode : hotelcode},
		success: function(result) {
			if(result < 1) {alert("좋아요 작동 에러, 다시 시도해주세요.");}
			else {$(thisElement).attr("src", "/shushoong/images/heart_color.png");}
		},
		error: function(xhr, status, error) {
				console.log('AJAX 실패:', error);
			}
		})
		/* console 창에 결과나온거 보면 흰색  하트 img src 경로 보이는데 그 경로 토대로 작성 */
	} else {
		$.ajax({
		url: "/shushoong/hotel/like/delete.ajax",
		method: "get",
		data: {hotelCode : hotelcode},
		success: function(result) {
			if(result < 1) {alert("좋아요 작동 에러, 다시 시도해주세요.");}
			else {$(thisElement).attr("src", "/shushoong/images/heart.png");}
		},
		error: function(xhr, status, error) {
				console.log('AJAX 실패:', error);
			}
		})
	}
}

/* const inputLeft = document.getElementById("input-left"); */
let inputRight = document.getElementById("input-right");
/* const circleLeft = document.querySelector(".slider > .circle.left"); */
let circleRight = document.querySelector(".slider > .circle.right");
let range = document.querySelector(".slider > .range");

// 왼쪽이 움직일떼(= 왼쪽 값이 변할 때) 변경될 때 실행되는 함수
/* const setLeftValue = () => {
    // 현재 입력 값(_this)과 입력 요소의 최소값과 최대값을 가져옴
    const _this = inputLeft;
    const [min, max] = [parseInt(_this.min), parseInt(_this.max)];
    
    //왼쪽값을 기준으로 으론쪽은 왼쪽에게 최대 20까지 차이날 수 있게끔함 (이 이상 오지 못함)
    // -> 겹치지 않게 이렇게 설정
    _this.value = Math.min(parseInt(_this.value), parseInt(inputRight.value) - 20);

    // 왼쪽 thumb의 위치를 계산하여 슬라이더의 왼쪽 값을 표시
    const leftValue = parseInt(_this.value);
    const leftVal = document.querySelector(".price1");
    leftVal.innerHTML = `${leftValue}`;
    const percent = ((_this.value - min) / (max - min)) * 100;
    circleLeft.style.left = percent + "%";
    range.style.left = percent + "%";
};
 */

// 오른쪽이 움직일떼(= 오른쪽 값이 변할 때) 변경될 때 실행되는 함수
const setRightValue = () => {
    // 현재 입력 값(_this)과 입력 요소의 최소값과 최대값을 가져옴
    const _this = $("#input-right").get(0);
    const [min, max] = [parseInt(_this.min), parseInt(_this.max)];

  	//오른쪽값을 기준으로 왼쪽은 오른쪽에게 최대 20까지 차이날 수 있게끔함 (이 이상 오지 못함)
    // -> 겹치지 않게 이렇게 설정
/*     _this.value = Math.max(parseInt(_this.value), parseInt(inputLeft.value) + 20); */

    // 오른쪽 thumb의 위치를 계산하여 슬라이더의 오른쪽 값을 표시
    const rightValue = parseInt(_this.value);	//현재 바가 있는 곳의 위치 값
    document.querySelector(".price2").innerText = rightValue;	//지정한 우측 숫자를 현재 바가 있는 곳의 값으로 갱신
    const percent = ((_this.value - min) / (max - min)) * 100;
    document.querySelector(".slider > .circle.right").style.right = 100 - percent + "%";	// 바의 위치값
    range.style.right = 100 - percent + "%";	//바의 길이값
};


//왼쪽 입력 값이 변경될 때와 오른쪽 입력 값이 변경될 때 해당 함수들을 호출하도록 이벤트 리스너를 설정
/* inputLeft.addEventListener("input", setLeftValue); */
inputRight.addEventListener("input", setRightValue);


// 페이지가 로드될 때 초기값을 설정하는 함수
const setInitialValues = () => {
	inputRight = document.getElementById("input-right");
	circleRight = document.querySelector(".slider > .circle.right");
	range = document.querySelector(".slider > .range");
    // 초기 왼쪽 값과 오른쪽 값
/*     const initialLeftValue = 0; */
	//우측 숫자 변수 =  max 최초값
	const initialRightValue = document.querySelector(".price2").innerText; //우측 숫자 변수지정
    // 초기값을 입력 요소에 설정하고, 왼쪽과 오른쪽 thumb의 위치를 조정
/*     inputLeft.value = initialLeftValue; */
    inputRight.value = initialRightValue;
/*     setLeftValue(); */
    setRightValue();
};

// 페이지가 로드될 때 초기값 설정 함수(setInitialValues)를 호출
window.onload = setInitialValues;

//정렬 기준에 맞게 정렬
function sortHandler() {
	start = 1;
	presortBy = $('.sort-by option:selected').val();
	presortTo = $('.sort-to option:selected').val();
	
	$.ajax({
		url: "/shushoong/hotel/list/sort.ajax",
		method: "get",
		data: {
			loccode : preloccode,
			people : prepeople,
			keyword : prekeyword,
			maxPrice : maxPrice,
			sortBy : presortBy,
			sortTo : presortTo
		},
//		dataType: 'json', <-- 받아오는 데이터타입
//		success: function () {},
		error: function(xhr, status, error) {
				console.log('AJAX 실패:', error);
			}
	})
	//success함수 대체
	.done(function(response){
		$("#realHotelList").replaceWith(response);
		//updateHotelList(response);
	});
	
}

//호텔 이름 키워드 검색
function searchHandler() {
	start = 1;
	end = 3;
	prekeyword = $('.type_hotel').val();
	presortBy = null;
	presortTo = null;
	maxPrice = null;
	
	$.ajax({
		url: "/shushoong/hotel/list/sort.ajax",
		method: "get",
		data: {
			loccode : preloccode,
			people : prepeople,
			keyword : prekeyword,
			maxPrice : maxPrice,
			sortBy : presortBy,
			sortTo : presortTo
		},
		error: function(xhr, status, error) {
				console.log('AJAX 실패:', error);
			}
	}).done(function(response){
//		$("#hotellistsection").replaceWith(response);
		$("#realHotelList").replaceWith(response);
		$('.hotel_list').children('.hotel:gt(2)').css('display', 'none');
	
		$.ajax({
		url: "/shushoong/hotel/list/price.ajax",
		method: "get",
		data: {
			loccode : preloccode,
			people : prepeople,
			keyword : prekeyword,
			maxPrice : maxPrice,
			sortBy : presortBy,
			sortTo : presortTo
		},
		error: function(xhr, status, error) {
				console.log('AJAX 실패:', error);
			}
		}).done(function(resp){
			$(".sidebar").replaceWith(resp);
			//inputRight = document.getElementById("input-right");
			//inputRight.addEventListener("input", setRightValue);
			
			//슬라이드바 셋팅
			//슬라이드 바 Max수치 설정
			$("#input-right").on("input",setRightValue);
			//슬라이드바 동작 재설정
			setInitialValues();	
		});
	});
	
	
}
//엔터키 눌렀을 때 키워드 검색버튼 클릭
function enterkey() {
        if(event.keyCode == 13) {
             searchHandler();
        }
}

// 호텔 가격(슬라이드바) 검색
function priceHandler() {
	start = 1;
	maxPrice = $('#input-right').val();
	$.ajax({
		url: "/shushoong/hotel/list/sort.ajax",
		method: "get",
		data: {
			loccode : preloccode,
			people : prepeople,
			keyword : prekeyword,
			maxPrice : maxPrice,
			sortBy : presortBy,
			sortTo : presortTo
		},
		error: function(xhr, status, error) {
				console.log('AJAX 실패:', error);
			}
	}).done(function(response){
		$("#realHotelList").replaceWith(response);
	});
}

//스크롤 내리면 데이터 추가 로드
function loadHotelListHandler() {
	var end = Number(2);
	
	//스크롤이 화면의 일정높이까지 내려가면
	if($(document).height() - $(this).scrollTop() < 900) {
			$('.hotel_list').children('.hotel:gt(' + end + '):lt(' + (end+3) + ')').css('display', 'grid');
			end += 3;
	}
}

