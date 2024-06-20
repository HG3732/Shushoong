	
		
										/* 저렴한 가격 캐러셀 */
	cheapIndex = 0;
	var cheapSlideSize =document.getElementsByClassName("cheap_item");
	$(".cheap_next_btn").on("click", function(){
		if(cheapIndex > cheapSlideSize.length-4){
			
		}else{
			cheapIndex++;
			cheapSlide(cheapIndex);
		}
	});
	
	$(".cheap_prev_btn").on("click", function(){
		if(cheapIndex <  1){
			
		}else{
			cheapIndex--;
			cheapSlide(cheapIndex);
		}
	});
	
	
	function cheapSlide(cheapIndex){
		$(".cheap_item").each(function(){
			var transval = -320*cheapIndex;
			$(this).css("transform","translateX("+transval+"px)")
		});
	};
							
							
							
							
							
							
							
							/* 빠른 도착시간 캐러셀 */
	
		fastIndex = 0;
	var fastSlideSize =document.getElementsByClassName("fast_item");
	$(".fast_next_btn").on("click", function(){
		if(fastIndex > fastSlideSize.length-4){
			
		}else{
			fastIndex++;
			fastSlide(fastIndex);
		}
	});
	
	$(".fast_prev_btn").on("click", function(){
		if(fastIndex <  1){
			
		}else{
			fastIndex--;
			fastSlide(fastIndex);
		}
	});
	
	
	function fastSlide(fastIndex){
		$(".fast_item").each(function(){
			var transval = -320*fastIndex;
			$(this).css("transform","translateX("+transval+"px)")
		});
	};
	
		
