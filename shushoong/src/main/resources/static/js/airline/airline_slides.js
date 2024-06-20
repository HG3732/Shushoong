	
		
										/* 저렴한 가격 캐러셀 */
	cheapIndex = 0;
	var cheapSlideSize =document.getElementsByClassName("cheap_item");
	$(".cheap_next_btn").on("click", function(){
		if(cheapIndex > cheapSlideSize.length-4){
			
		}else{
			cheapIndex++;
			cheapSlide(cheapIndex);
			console.log("cheapIndex : " + cheapIndex);
		}
	});
	
	$(".cheap_prev_btn").on("click", function(){
		if(cheapIndex <  1){
			
		}else{
			cheapIndex--;
			cheapSlide(cheapIndex);
			console.log("cheapIndex : " + cheapIndex);
		}
	});
	
	
	function cheapSlide(cheapIndex){
			console.log("asdfasdf");
		$(".cheap_item").each(function(){
			var transval = -320*cheapIndex;
			console.log("transval : " + transval);
			console.log("cheapIndex : " + cheapIndex);
			console.log($(this).css("transform"));
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
			console.log("fastIndex : " + fastIndex);
		}
	});
	
	$(".fast_prev_btn").on("click", function(){
		if(fastIndex <  1){
			
		}else{
			fastIndex--;
			fastSlide(fastIndex);
			console.log("fastIndex : " + fastIndex);
		}
	});
	
	
	function fastSlide(fastIndex){
			console.log("asdfasdf");
		$(".fast_item").each(function(){
			var transval = -320*fastIndex;
			console.log("transval : " + transval);
			console.log("fastIndex : " + fastIndex);
			console.log($(this).css("transform"));
			$(this).css("transform","translateX("+transval+"px)")
		});
	};
	
		
