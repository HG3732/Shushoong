$(loadedHanlder);
		function loadedHanlder() {
			$(".btn.emailSend").on("click", emailSendHandler);
			$(".btn.emailCheck").on("click", emailCheckHandler);
			$(".btn.idCheck").on("click", idCheckHandler);
		}
		
		// 아이디 체크 이벤트
		function idCheckHandler() {
			var userId = $('#userId').val();
			$.ajax({
				url : '/shushoong/idcheck.ajax',
				type : 'post',
				data : {userId:userId},
				success:function(cnt) {
					if(cnt == 0) {
						$('.test_Idok').css("display","inline-block");
					} else {
						$('.test_IDexception').css("display","inline-block");
	                    $('#userId').val('');
					}
				}, 
				error:function(request,status,error) {
					alert("시스템 에러. 관리자에게 문의해 주세요.");
				}
			})
		}
		
		// 이메일 전송 이벤트
		function emailSendHandler() {	
			var userEmail = $("#userEmail").val();
			
			$.ajax({
				type: "post",
				url: "/shushoong/v2/verify-email",
				contentType : 'application/json;',
				data: JSON.stringify({
					'userEmail' : userEmail
				}), 
				success:function(request,status,error) {
					$('.test_Emailok').css("display","inline-block");
				}, 
				error:function(request,status,error) {
					$('.test_Emailexception').css("display","inline-block");
				}
			})
		}
		
		// 이메일 체크 이벤트
		function emailCheckHandler() {	
			var code = $("#code").val();
			
			$.ajax({
				type: "post",
				url: "/shushoong/verification-code",
				contentType : 'application/json;',
				data: JSON.stringify({
					'code' : code
				}),
				success:function(request,status,error) {
					$('.test_Echkok').css("display","inline-block");
				}, 
				error:function(request,status,error) {
					$('.test_Echkexception').css("display","inline-block");
				}
			})
		}
		
		function allChecked(target) {
			// 전체 체크박스 버튼
			const checkbox = document.getElementById('allCheckBox');
			
			// 전체 체크박스 체크 여부
			const is_checked = checkbox.checked;
			
			// 전체 체크박스를 제외한 모든 체크박스
			if(is_checked) {
				//체크박스 전부 체크
				chkAllChecked();
			}
			
			else {
				// 체크박스 전부 해제
				chkAllUnChecked();
			}
		}
		
		// 자식 체크박스 클릭 이벤트
		function chkClicked() {
			// 체크박스 전체개수
			const allCount = document.querySelectorAll(".chk").length;
			const allMarketingCount = document.querySelectorAll(".marketing").length;
			
			// 체크된 체크박스 전체개수 
			const query = 'input[name="usingAgree"]:checked';
			const marketingquery = 'input[name="marketing"]:checked';
			
			const selectedElements = document.querySelectorAll(query);
			const selectedElementsCnt = selectedElements.length;
			
			const selectMarketingElements = document.querySelectorAll(marketingquery);
			const selectedMarketingElementsCnt = selectMarketingElements.length;
			
			// 체크박스 전체개수와 체크된 체박스 전체개수가 같으면 전체 체크박스 체크
			if(allCount == selectedElementsCnt && allMarketingCount == selectedMarketingElementsCnt) {
				document.getElementById('allCheckBox').checked = true;
				document.getElementById('receiveMarketing').checked = true;
			}
			
			else if(allMarketingCount == selectedMarketingElementsCnt) { 
				document.getElementById('receiveMarketing').checked = true;
			}
			
			// 같지 않으면 전체 해제
			else {
				document.getElementById('allCheckBox').checked = false;
				document.getElementById('receiveMarketing').checked = false;
			}
		}
		
		// 체크박스 전체 체크
		function chkAllChecked() {
			document.querySelectorAll(".chk,.receiveMarketing,.marketing").forEach(function(v,i) {
				v.checked = true;
			});
		}
		
		// 체크박스 전체 해제
		function chkAllUnChecked() {
			document.querySelectorAll(".chk,.receiveMarketing,.marketing").forEach(function(v,i) {
				v.checked = false;
			});
		}
		
		// 마케팅 allChecked 이벤트
		function checkMarketing(target) {
			// 전체 체크박스 버튼
			const checkbox = document.getElementById('receiveMarketing');
			
			// 전체 체크박스 체크 여부
			const is_checked = checkbox.checked;
			
			// 전체 체크박스를 제외한 모든 체크박스
			if(is_checked) {
				//체크박스 전부 체크
				chkAllMarketing();
			}
			
			else {
				// 체크박스 전부 해제
				chkAllUnMarketing();
			}
		}
		
		// 체크박스 전체 체크
		function chkAllMarketing() {
			document.querySelectorAll('.marketing').forEach(function(v,i) {
				v.checked = true;
			});
		}
		
		// 체크박스 전체 해제
		function chkAllUnMarketing() {
			document.querySelectorAll('.marketing').forEach(function(v,i) {
				v.checked = false;
			});
		}