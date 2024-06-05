	// 사이드 바
	
	// 시간대---------------------------
	// 출발 
	const inputDepartLeft = document.getElementById("depart-input-left");
	const inputDepartRight = document.getElementById("depart-input-right");
	const thumbDepartLeft = document.querySelector(".slider.depart > .thumb.left.depart");
	const thumbDepartRight = document.querySelector(".slider.depart > .thumb.right.depart");
	const departRange = document.querySelector(".slider.depart > .range");

	// 왼쪽 입력 값이 변경될 때 실행되는 함수
	function setDepartLeftValue() {
	    // 현재 입력 값(_this)과 입력 요소의 최소값과 최대값을 가져옴
	    const _this = inputDepartLeft;
	    const [min, max] = [parseInt(_this.min), parseInt(_this.max)];

	    // 왼쪽과 오른쪽의 최소 거리 결정
	    _this.value = Math.min(parseInt(_this.value), parseInt(inputDepartRight.value) - 3);

	    // 왼쪽 thumb의 위치를 계산하여 슬라이더의 왼쪽 값을 표시
	    const leftValue = parseInt(_this.value);
	    console.log("출발 시간 left.val : " + _this.value);
	    
	    const leftVal = document.querySelector(".middle-range.depart .div1");
	    leftVal.innerHTML = `${leftValue}:00`;
	    
	    const percent = ((_this.value - min) / (max - min)) * 100;
	    thumbDepartLeft.style.left = percent + "%";
	    departRange.style.left = percent + "%";
	};

	// 오른쪽 입력 값이 변경될 때 실행되는 함수
	function setDepartRightValue() {
	    // 현재 입력 값(_this)과 입력 요소의 최소값과 최대값을 가져옴
	    const _this = inputDepartRight;
	    const [min, max] = [parseInt(_this.min), parseInt(_this.max)];

	    // 왼쪽과 오른쪽의 최소 거리 결정
	    _this.value = Math.max(parseInt(_this.value), parseInt(inputDepartLeft.value) + 3);

	    // 오른쪽 thumb의 위치를 계산하여 슬라이더의 오른쪽 값을 표시
	    const rightValue = parseInt(_this.value);
	    console.log("출발 시간 right.val : " + _this.value);
	    
	    const rightVal = document.querySelector(".middle-range.depart .div2");
	    rightVal.innerHTML = `${rightValue}:00`;
	    
	    const percent = ((_this.value - min) / (max - min)) * 100;
	    thumbDepartRight.style.right = 100 - percent + "%";
	    departRange.style.right = 100 - percent + "%";
	};

	// 왼쪽 입력 값이 변경될 때와 오른쪽 입력 값이 변경될 때 해당 함수들을 호출하도록
	// 이벤트 리스너를 설정
	inputDepartLeft.addEventListener("input", setDepartLeftValue);
	inputDepartRight.addEventListener("input", setDepartRightValue);

	// 페이지가 로드될 때 초기값을 설정하는 함수
	function setDepartInitialValues() {
	    // 초기 왼쪽 값과 오른쪽 값
	    const initialDepartLeftValue = 0;
	    const initialDepartRightValue = 24;
	    // 초기값을 입력 요소에 설정하고, 왼쪽과 오른쪽 thumb의 위치를 조정
	    inputDepartLeft.value = initialDepartLeftValue;
	    inputDepartRight.value = initialDepartRightValue;
	    setDepartLeftValue();
	    setDepartRightValue();
	};

	// 도착
	const inputArrLeft = document.getElementById("arr-input-left");
	const inputArrRight = document.getElementById("arr-input-right");
	const thumbArrLeft = document.querySelector(".slider.arr > .thumb.left.arr");
	const thumbArrRight = document.querySelector(".slider.arr > .thumb.right.arr");
	const arrRange = document.querySelector(".slider.arr > .range");

	function setArrLeftValue() {
	    const _this = inputArrLeft;
	    const [min, max] = [parseInt(_this.min), parseInt(_this.max)];

	    _this.value = Math.min(parseInt(_this.value), parseInt(inputArrRight.value) - 3);

	    const leftValue = parseInt(_this.value);
	    
	    const leftVal = document.querySelector(".middle-range.arr .div1");
	    leftVal.innerHTML = `${leftValue}:00`;
	    console.log("도착 시간 left.val : " + _this.value);
	    
	    const percent = ((_this.value - min) / (max - min)) * 100;
	    thumbArrLeft.style.left = percent + "%";
	    arrRange.style.left = percent + "%";
	};

	function setArrRightValue() {
	    const _this = inputArrRight;
	    const [min, max] = [parseInt(_this.min), parseInt(_this.max)];

	    _this.value = Math.max(parseInt(_this.value), parseInt(inputArrLeft.value) + 3);

	    const rightValue = parseInt(_this.value);
	    
	    const rightVal = document.querySelector(".middle-range.arr .div2");
	    rightVal.innerHTML = `${rightValue}:00`;
	    console.log("도착 시간 right.val : " + _this.value);
	    
	    const percent = ((_this.value - min) / (max - min)) * 100;
	    thumbArrRight.style.right = 100 - percent + "%";
	    arrRange.style.right = 100 - percent + "%";
	};

	inputArrLeft.addEventListener("input", setArrLeftValue);
	inputArrRight.addEventListener("input", setArrRightValue);

	function setArrInitialValues() {
	    const initialArrLeftValue = 0;
	    const initialArrRightValue = 24;
	    inputArrLeft.value = initialArrLeftValue;
	    inputArrRight.value = initialArrRightValue;
	    setArrLeftValue();
	    setArrRightValue();
	};

	
	// 가격 -------------------------------------------------------
	const inputPriceLeft = document.getElementById("price-input-left");
	const inputPriceRight = document.getElementById("price-input-right");
	const thumbPriceLeft = document.querySelector(".slider.price > .thumb.left.price");
	const thumbPriceRight = document.querySelector(".slider.price > .thumb.right.price");
	const priceRange = document.querySelector(".slider.price > .range");

	function setPriceLeftValue() {
	    const _this = inputPriceLeft;
	    const [min, max] = [parseInt(_this.min), parseInt(_this.max)];

	    _this.value = Math.min(parseInt(_this.value), parseInt(inputPriceRight.value) - 3);

	    const leftValue = parseInt(_this.value);
	    
	    const leftVal = document.querySelector(".middle-range.price .div1");
	    leftVal.innerHTML = `${leftValue}(최솟값)원 ~`;
	    console.log("최솟값 : " + _this.value);
	    
	    const percent = ((_this.value - min) / (max - min)) * 100;
	    thumbPriceLeft.style.left = percent + "%";
	    priceRange.style.left = percent + "%";
	};

	function setPriceRightValue() {
	    const _this = inputPriceRight;
	    const [min, max] = [parseInt(_this.min), parseInt(_this.max)];

	    _this.value = Math.max(parseInt(_this.value), parseInt(inputPriceLeft.value) + 3);

	    const rightValue = parseInt(_this.value);
	    
	    const rightVal = document.querySelector(".middle-range.price .div2");
	    rightVal.innerHTML = `${rightValue}(최댓값)원`;
	    console.log("최댓값 : " + _this.value);
	    
	    const percent = ((_this.value - min) / (max - min)) * 100;
	    thumbPriceRight.style.right = 100 - percent + "%";
	    priceRange.style.right = 100 - percent + "%";
	};

	inputPriceLeft.addEventListener("input", setPriceLeftValue);
	inputPriceRight.addEventListener("input", setPriceRightValue);

	function setPriceInitialValues() {
	    const initialPriceLeftValue = 0;
	    const initialPriceRightValue = 24;
	    inputPriceLeft.value = initialPriceLeftValue;
	    inputPriceRight.value = initialPriceRightValue;
	    setPriceLeftValue();
	    setPriceRightValue();
	};
	