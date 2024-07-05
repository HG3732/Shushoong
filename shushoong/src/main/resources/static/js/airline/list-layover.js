 function btnClickHandler() {
	    $('#direct-btn').on('click', function () {

			var $blueDirect = $("#blue-direct");
	        var $skyDirect = $("#sky-direct");
	        var $blueLayover = $("#blue-layover");
	        var $skyLayover = $("#sky-layover");
	        
	        // 직항 버튼 클릭 시
		    if ($(this).prop('checked')) {
		        $blueDirect.css("display", "block");
		        $skyDirect.css("display", "none");
		        
		        $blueLayover.css("display", "none");
		        $skyLayover.css("display", "block");
		        
		        console.log('직항 눌림');
		    } else {
		        console.log('체크됏는데 눌림 ㅋ')
		        $blueDirect.css("display", "none");
		        $skyDirect.css("display", "block");
		        
		        $blueLayover.css("display", "none");
		        $skyLayover.css("display", "block");
		        
		        console.log('직항 해제');
			    }
			});
	    $('#layover-btn').on('click', function () {
	        var $blueDirect = $('#blue-direct');
	        var $skyDirect = $('#sky-direct');
	        var $blueLayover = $('#blue-layover');
	        var $skyLayover = $('#sky-layover');

	        // 경유 버튼 클릭 시
		    if ($(this).prop('checked')) {
		        $blueLayover.css("display", "block");
		        $skyLayover.css("display", "none");
		        
		        $blueDirect.css("display", "none");
		        $skyDirect.css("display", "block");
		        
		        console.log('직항 눌림');
		    } else {
		        console.log('체크됏는데 눌림 ㅋ')
		        $blueLayover.css("display", "none");
		        $skyLayover.css("display", "block");
		        
		        $blueDirect.css("display", "none");
		        $skyDirect.css("display", "block");
		        
		        console.log('직항 해제');
			    }
	    });
	}