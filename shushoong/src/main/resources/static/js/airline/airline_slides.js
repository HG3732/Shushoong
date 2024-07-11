	
		
										/* 저렴한 가격 캐러셀 */
	var cheapIndex = 0;
	var cheapSlideSize =document.getElementsByClassName("airline-recommend");
	$(".right.btn2").on("click", function(){
		if(cheapIndex > cheapSlideSize.length-4){
			
		}else{
			cheapIndex++;
			cheapSlide(cheapIndex);
		}
	});
	
	$(".left.btn2").on("click", function(){
		if(cheapIndex <  1){
			
		}else{
			cheapIndex--;
			cheapSlide(cheapIndex);
		}
	});
	
	
	function cheapSlide(cheapIndex){
		$(".airline-recommend").each(function(){
			var transval = -330*cheapIndex;
			$(this).css("transform","translateX("+transval+"px)")
		});
	};
							
							
							
							
							
							
		