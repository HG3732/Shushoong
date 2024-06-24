
	
	//캐러셀
	function slide(index) {
		document.querySelector('.list-wrapper').style.transform = `translateX(-${index * 330}px)`;
		currentindex = index;
	}
	
	//캐러셀 버튼 클릭
	function leftClickHandler() {
		$('.right.btn2').prop('disable', false);
		if(currentindex > 0) {
			currentindex = currentindex-1;
			slide(currentindex);
		} else {
			$('.left.btn2').prop('disable', true);
		};
	}
	
	function rightClickHandler() {
		$('.left.btn2').prop('disable', false);
		if(currentindex < $('.list-wrapper').children().length-3) {
			currentindex = currentindex+1;
			slide(currentindex);
		} else {
			$('.right.btn2').prop('disable', true);
		}
	}
	
     
       