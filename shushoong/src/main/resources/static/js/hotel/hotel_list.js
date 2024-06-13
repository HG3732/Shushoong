//좋아요 누를 시 색깔 변함
function likeHandler(thisElement){
	console.log(thisElement);	//이벤트 함수가 걸린 태그
	console.log(event.target);	//마우스 클릭 시 제일 가까운 요소 (하트 사진 누르면 하트 사진 뜨는....)
	
	var currentSrc = $(thisElement).children().attr('src');
	
	console.log("현재 소스값" + currentSrc);
	/* 이 경로를 토대로 이미지 바뀌는 경우의 수 적어주기 */
	
	if( currentSrc === '/shushoong/images/heart.png'){
		$(thisElement).children().attr("src", "/shushoong/images/heart_color.png");
		/* console 창에 결과나온거 보면 흰색  하트 img src 경로 보이는데 그 경로 토대로 작성 */
	} else {
		$(thisElement).children().attr("src", "/shushoong/images/heart.png");
	}
}
	

/* const inputLeft = document.getElementById("input-left"); */
const inputRight = document.getElementById("input-right");
/* const circleLeft = document.querySelector(".slider > .circle.left"); */
const circleRight = document.querySelector(".slider > .circle.right");
const range = document.querySelector(".slider > .range");

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
    const _this = inputRight;
    const [min, max] = [parseInt(_this.min), parseInt(_this.max)];

  	//오른쪽값을 기준으로 왼쪽은 오른쪽에게 최대 20까지 차이날 수 있게끔함 (이 이상 오지 못함)
    // -> 겹치지 않게 이렇게 설정
/*     _this.value = Math.max(parseInt(_this.value), parseInt(inputLeft.value) + 20); */

    // 오른쪽 thumb의 위치를 계산하여 슬라이더의 오른쪽 값을 표시
    const rightValue = parseInt(_this.value);	//현재 바가 있는 곳의 위치 값
    const rightVal = document.querySelector(".price2");	//우측 숫자 변수지정
    rightVal.innerHTML = `${rightValue}`;	//지정한 우측 숫자를 현재 바가 있는 곳의 값으로 갱신
    const percent = ((_this.value - min) / (max - min)) * 100;
    circleRight.style.right = 100 - percent + "%";	// 바의 위치값
    range.style.right = 100 - percent + "%";	//바의 길이값
};


//왼쪽 입력 값이 변경될 때와 오른쪽 입력 값이 변경될 때 해당 함수들을 호출하도록 이벤트 리스너를 설정
/* inputLeft.addEventListener("input", setLeftValue); */
inputRight.addEventListener("input", setRightValue);


// 페이지가 로드될 때 초기값을 설정하는 함수
const setInitialValues = () => {
    // 초기 왼쪽 값과 오른쪽 값
/*     const initialLeftValue = 0; */
    const initialRightValue = rightVal;
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
		console.log('Ajax Success');
		$("#hotellistsection").replaceWith(response);
		//updateHotelList(response);
	});
}

//호텔 이름 키워드 검색
function searchHandler() {
	prekeyword = $('.type_hotel').val();
	$.ajax({
		url: "/shushoong/hotel/list/search.ajax",
		method: "get",
		data: {
			loccode : preloccode,
			people : prepeople,
			keyword : prekeyword,
			maxPrice : maxPrice,
			sortBy : presortBy,
			sortTo : presortTo
		},
		dataType: 'json',
		success: function(response) {
				console.log('Ajax Success');
				updateHotelList(response);
				},
		error: function(xhr, status, error) {
				console.log('AJAX 실패:', error);
			}
		
	});
}

// 호텔 가격(슬라이드바) 검색
function priceHandler() {
	maxPrice = $('#input-right').val();
	console.log(maxPrice);
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
		dataType: 'json',
		success: function(response) {
				console.log('Ajax Success');
				updateHotelList(response);
				},
		error: function(xhr, status, error) {
				console.log('AJAX 실패:', error);
			}
		
	});
}

function updateHotelList(data) {
	//기존 리스트 제거
	$('.hotel').empty();
	
	let newList = '';
	console.log(data);
	if(data == ''){
		newList += `<div class="isEmpty">
				조건에 맞는 호텔이 없습니다.
			</div>`
	} else {
	data.forEach(function(list){
		newList += `<div class="hotel_list">
							<div class="hotel_pic" style="background-image: url('${list.hotelPic}'); background-size: cover;'">
								<div class="hotel_like">
									<img src="/shushoong/images/heart.png" alt="좋아요" style="width:40px;" class="heart">
								</div>
							</div>
							<div class="hotel_attr">
								<div class="hotel_kr">${list.hotelName}</div>
								<div class="hotel_eng">${list.hotelEng}</div>
								<div class="hotel_location">${list.hotelAddress}</div>
								<div class="hotel_review">
									<span><img src="/shushoong/images/star_line.png" alt="별" style="width:20px;" class="heart"></span>
									<span>${list.hotelScore}/5</span>
								</div>
							</div>
							<div class="hotel_price">${list.hotelPrice}원</div>
						</div>`
		});
	}
	$('.hotel').html(newList);
}
