 function btnClickHandler() {
	    $('#direct-btn').on('click', function () {
	        var $blueDirect = $(this).children("#blue-direct");
	        var $skyDirect = $(this).children("#sky-direct");
	        var $blueLayover = $(this).parent().parent().find(".direct-btn.one").children("#blue-layover");
	        var $skyLayover = $(this).parent().parent().find(".direct-btn.one").children("#sky-layover");

	        // 직항 버튼 클릭 시
	        $blueDirect.css("display", "block");
	        $skyDirect.css("display", "none");

	        $blueLayover.css("display", "none");
	        $skyLayover.css("display", "block");
	        
	        console.log('직항 눌림');
	    });

	    $('#layover-btn').on('click', function () {
	        var $blueDirect = $(this).parent().parent().find('.direct-btn.zero').children('#blue-direct');
	        var $skyDirect = $(this).parent().parent().find('.direct-btn.zero').children('#sky-direct');
	        var $blueLayover = $(this).children('#blue-layover');
	        var $skyLayover = $(this).children('#sky-layover');

	        // 경유 버튼 클릭 시
	        $blueLayover.css("display", "block");
	        $skyLayover.css("display", "none");

	        $blueDirect.css("display", "none");
	        $skyDirect.css("display", "block");

	        console.log('경유 눌림');
	    });
	}