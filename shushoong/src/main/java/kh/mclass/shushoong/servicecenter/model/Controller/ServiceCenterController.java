package kh.mclass.shushoong.servicecenter.model.Controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import jakarta.mail.Multipart;
import jakarta.servlet.http.HttpSession;
import kh.mclass.shushoong.servicecenter.model.domain.NoticeDto;
import kh.mclass.shushoong.servicecenter.model.domain.NoticeFileDto;
import kh.mclass.shushoong.servicecenter.model.domain.OnlineQnADto;
import kh.mclass.shushoong.servicecenter.model.domain.OnlineQnAFileDto;
import kh.mclass.shushoong.servicecenter.model.service.NoticeService;
import kh.mclass.shushoong.servicecenter.model.service.OnlineQnAService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class ServiceCenterController {
	
	@Autowired
	private NoticeService noticeService;
	
	@Autowired
	private OnlineQnAService service;
	@Autowired
	private Cloudinary cloudinary;
	
	int pageSize = 10;
	int pageBlockSize = 3;
	int currentPageNum = 1;
	
	@GetMapping("/support/qna/list")
	public String qnaList(Model model, String category, String keyword, String questCat) {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		System.out.println("authentication" + authentication);
		String userGrade = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .findFirst()
                .orElse("anonymousUser"); // 기본값 설정
		
		model.addAttribute("userGrade", userGrade);
		
		if(authentication.getAuthorities().toString().contains("admin")) {
			int totalCount = service.selectTotalCount(null, category, keyword, questCat);
			model.addAttribute("result", service.selectAllList(pageSize, pageBlockSize, currentPageNum, null, category, keyword, questCat));
			int totalPageCount = (totalCount%pageSize == 0) ? totalCount/pageSize : totalCount/pageSize + 1;
			int startPageNum = (currentPageNum%pageBlockSize == 0) ? ((currentPageNum/pageBlockSize)-1)*pageBlockSize + 1 : ((currentPageNum/pageBlockSize))*pageBlockSize + 1;
			int endPageNum = (startPageNum+pageBlockSize > totalPageCount) ? totalPageCount : startPageNum + pageBlockSize - 1;
			
			model.addAttribute("currentPageNum", currentPageNum);
			model.addAttribute("totalPageCount", totalPageCount);
			model.addAttribute("startPageNum", startPageNum);
			model.addAttribute("endPageNum", endPageNum);
		} else {
			String loginId = authentication.getName();
			int totalCount = service.selectTotalCount(loginId, category, keyword, questCat);
			model.addAttribute("result", service.selectAllList(pageSize, pageBlockSize, currentPageNum, loginId, category, keyword, questCat));
			int totalPageCount = (totalCount%pageSize == 0) ? totalCount/pageSize : totalCount/pageSize + 1;
			int startPageNum = (currentPageNum%pageBlockSize == 0) ? ((currentPageNum/pageBlockSize)-1)*pageBlockSize + 1 : ((currentPageNum/pageBlockSize))*pageBlockSize + 1;
			int endPageNum = (startPageNum+pageBlockSize > totalPageCount) ? totalPageCount : startPageNum + pageBlockSize - 1;
			
			model.addAttribute("currentPageNum", currentPageNum);
			model.addAttribute("totalPageCount", totalPageCount);
			model.addAttribute("startPageNum", startPageNum);
			model.addAttribute("endPageNum", endPageNum);
		}
		
		return "servicecenter/onlineQnAlist";
	}
	
	@GetMapping("/support/notice/search.ajax")
	public String searchQnA(Model model, String pageNum, String category, String keyword, String questCatCategory) {
		
		currentPageNum = 1;
		if(pageNum != null && !pageNum.equals("")) {
			try {
				currentPageNum = Integer.parseInt(pageNum);
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
		}
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		System.out.println("authentication" + authentication);
		String userGrade = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .findFirst()
                .orElse("anonymousUser"); // 기본값 설정
		
		model.addAttribute("userGrade", userGrade);
		
		if(authentication.getAuthorities().toString().contains("admin")) {
			int totalCount = service.selectTotalCount(null, category, keyword, questCatCategory);
			model.addAttribute("result", service.selectAllList(pageSize, pageBlockSize, currentPageNum, null, category, keyword, questCatCategory));
			int totalPageCount = (totalCount%pageSize == 0) ? totalCount/pageSize : totalCount/pageSize + 1;
			int startPageNum = (currentPageNum%pageBlockSize == 0) ? ((currentPageNum/pageBlockSize)-1)*pageBlockSize + 1 : ((currentPageNum/pageBlockSize))*pageBlockSize + 1;
			int endPageNum = (startPageNum+pageBlockSize > totalPageCount) ? totalPageCount : startPageNum + pageBlockSize - 1;
			
			model.addAttribute("currentPageNum", currentPageNum);
			model.addAttribute("totalPageCount", totalPageCount);
			model.addAttribute("startPageNum", startPageNum);
			model.addAttribute("endPageNum", endPageNum);
		} else {
			String loginId = authentication.getName();
			int totalCount = service.selectTotalCount(loginId, category, keyword, questCatCategory);
			model.addAttribute("result", service.selectAllList(pageSize, pageBlockSize, currentPageNum, loginId, category, keyword, questCatCategory));
			int totalPageCount = (totalCount%pageSize == 0) ? totalCount/pageSize : totalCount/pageSize + 1;
			int startPageNum = (currentPageNum%pageBlockSize == 0) ? ((currentPageNum/pageBlockSize)-1)*pageBlockSize + 1 : ((currentPageNum/pageBlockSize))*pageBlockSize + 1;
			int endPageNum = (startPageNum+pageBlockSize > totalPageCount) ? totalPageCount : startPageNum + pageBlockSize - 1;
			
			model.addAttribute("currentPageNum", currentPageNum);
			model.addAttribute("totalPageCount", totalPageCount);
			model.addAttribute("startPageNum", startPageNum);
			model.addAttribute("endPageNum", endPageNum);
		}
		return "servicecenter/QnAlist";
	}
	
	@GetMapping("/support/qna/view/{faqId}")
	public String viewQnA(Model model, @PathVariable("faqId") String faqId) {
		SecurityContextHolder.getContext().getAuthentication();
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String userId =  authentication.getName();
		String userGrade = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .findFirst()
                .orElse("anonymousUser"); // 기본값 설정
		if (!userGrade.equals("ROLE_ANONYMOUS")) {
		model.addAttribute("userGrade",userGrade);
		model.addAttribute("result", service.selectOneQna(faqId));
		model.addAttribute("qnaFileDto", service.selectOneQnaFile(faqId));
			return "servicecenter/viewQnA";
		} else {
			return "member/login";
		}
		
	}
	
	@PostMapping("/support/qna/write/answer.ajax")
	public String writeQnAanswer(Model model, String faqId, String ansContent) {
		service.updateAnswer(faqId, ansContent);
		model.addAttribute("result", service.selectOneQna(faqId));
		return "servicecenter/qnaContent";
	}
	
	// 공지사항 작성
	@GetMapping("/support/qna/write")
	public String getQnaWrite (Model md) {
		SecurityContextHolder.getContext().getAuthentication();
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String userId =  authentication.getName();
		String userGrade = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .findFirst()
                .orElse("anonymousUser"); // 기본값 설정
		
		if (!userGrade.equals("ROLE_ANONYMOUS")) {
		md.addAttribute("userGrade",userGrade);
		System.out.println("유저 등급 : " + userGrade);
			return "servicecenter/writeQna";
		} else {
			return "member/login";
		}
	}
	
	
	@PostMapping("/support/qna/write")
	public String PostQnaWrite (String askTitle, String category, @RequestParam("qnaFile") MultipartFile[] qnaFile,
			String askContent, 
			Model md
			) {
		SecurityContextHolder.getContext().getAuthentication();
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String userId =  authentication.getName();
		String userGrade = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .findFirst()
                .orElse("anonymousUser"); // 기본값 설정
		
		System.out.println("유저 등급 : " + userGrade);
		System.out.println("문의 작성 포스트 컨트롤러");
		System.out.println("askTitle : " + askTitle);
		System.out.println("category : " + category);
		System.out.println("askContent : " + askContent);
		
	    OnlineQnADto dto = new OnlineQnADto();
	    dto.setUserId(userId);
	    dto.setAskTitle(askTitle);
	    dto.setAskContent(askContent);
	    dto.setQuestCat(category);
	    md.addAttribute("userGrade", userGrade);

	    int faqId = service.insertQna(dto);
	    String faqIdStr = String.valueOf(faqId);
	    System.out.println("문의 번호 : " + faqId);
	    int insertQnaCat = service.insertQnaCat(dto);
	    
	    try {
			for(MultipartFile qnaFile2 : qnaFile) {
				if(qnaFile2 != null && !qnaFile2.isEmpty()) {
					Map<String, Object> uploadResult = cloudinary.uploader().upload(qnaFile2.getBytes(), ObjectUtils.emptyMap());
					String savedFilePathName = uploadResult.get("url").toString();
					
					OnlineQnAFileDto fileDto = new OnlineQnAFileDto();
					
					fileDto.setFaqId(faqIdStr);
					fileDto.setOriginalFilename(qnaFile2.getOriginalFilename());
					fileDto.setSavedFilePathName(savedFilePathName);
					
					System.out.println("문의 글 번호 : " + faqId);
					System.out.println("setOriginalFilename : " + qnaFile2.getOriginalFilename());
					System.out.println("savedFilePathName : " + savedFilePathName);
					service.insertQnaFile(fileDto);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	    
	    return "redirect:/support/qna/list";
	}
	
	@PostMapping("/support/qna/delete")
	public String deleteQna (String faqId) {
		System.out.println("삭제될 문의 글 번호 : " + faqId);
		
		service.deleteQnaFile(faqId);
		service.deleteQnaCat(faqId);
		service.deleteQna(faqId);
		return "redirect:/support/qna/list";
	}
	
	
	
	
	
	
// ===================================================	
	
	
	
	
	
	
	// 마이페이지 공지사항
	@GetMapping("/support/notice/list")
	public String noticeList (Model md, 
			String pageNum) {
			
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			String userId =  authentication.getName();
			String userGrade = authentication.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .findFirst()
                    .orElse("anonymousUser"); // 기본값 설정
			
			System.out.println("userGrade : " + userGrade);
                    
//			String userId = authentication.getName();
			// 로그인 검사
			if (!userGrade.equals("ROLE_ANONYMOUS")) {
			
			System.out.println("리스트 컨트롤러 pageNum : " + pageNum);
			if (pageNum != null && !pageNum.equals("")) {
				try {
					currentPageNum = Integer.parseInt(pageNum);
				} catch (NumberFormatException e) {
					e.printStackTrace();
				}
			}
			String noticeCategory = null;
//			System.out.println("리스트 컨트롤러 noticeCategory : " + noticeCategory);
			int totalCount = noticeService.selectTotalCount(userGrade,noticeCategory);
			int totalPageCount = (totalCount%pageSize == 0) ? totalCount/pageSize : totalCount/pageSize + 1;
			int startPageNum = (currentPageNum%pageBlockSize == 0) ? ((currentPageNum/pageBlockSize)-1)*pageBlockSize + 1 : ((currentPageNum/pageBlockSize))*pageBlockSize + 1;
			int endPageNum = (startPageNum+pageBlockSize > totalPageCount) ? totalPageCount : startPageNum + pageBlockSize - 1;
			List<NoticeDto> noticeDto =  noticeService.selectNoticeAllList(pageSize,pageBlockSize,currentPageNum,userGrade);
			md.addAttribute("currentPageNum", currentPageNum);
			md.addAttribute("totalPageCount", totalPageCount);
			md.addAttribute("startPageNum", startPageNum);
			md.addAttribute("endPageNum", endPageNum);
			md.addAttribute("noticeDto", noticeDto);
			md.addAttribute("userGrade", userGrade);
			
			System.out.println("리스트 컨트롤러 currentPageNum : " + currentPageNum);

			return "servicecenter/notice";
		} else {
			return "member/login";
		}
	}
	
	@GetMapping("/support/notice/list.ajax")
	public String noticeListAjax (Model md, String noticeCategory,
	String pageNum) {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String userId =  authentication.getName();
		String userGrade = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .findFirst()
                .orElse("anonymousUser"); // 기본값 설정
		
		System.out.println("userGrade : " + userGrade);
		System.out.println("noticeCategory : " + noticeCategory);
		System.out.println("ajax 컨트롤러 pageNum : " + pageNum);
		if (pageNum != null && !pageNum.equals("")) {
			try {
				currentPageNum = Integer.parseInt(pageNum);
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println("리스트 컨트롤러 noticeCategory : " + noticeCategory);
		int totalCount = noticeService.selectTotalCount(userGrade,noticeCategory);
		int totalPageCount = (totalCount%pageSize == 0) ? totalCount/pageSize : totalCount/pageSize + 1;
		
		int startPageNum = (currentPageNum%pageBlockSize == 0) ? ((currentPageNum/pageBlockSize)-1)*pageBlockSize + 1 : ((currentPageNum/pageBlockSize))*pageBlockSize + 1;
		int endPageNum = (startPageNum+pageBlockSize > totalPageCount) ? totalPageCount : startPageNum + pageBlockSize - 1;
		List<NoticeDto> noticeDto =  noticeService.selectNoticeAllListAjax(pageSize,pageBlockSize,currentPageNum,userGrade, noticeCategory);
		md.addAttribute("currentPageNum", currentPageNum);
		md.addAttribute("totalPageCount", totalPageCount);
		md.addAttribute("startPageNum", startPageNum);
		md.addAttribute("endPageNum", endPageNum);
		md.addAttribute("noticeDto", noticeDto);
		md.addAttribute("userGrade", userGrade);
		
		return "servicecenter/notice_section";
	}
	
	// 공지사항 작성
	@GetMapping("/support/notice/write")
	public String getNoticeWrite (Model md) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String userId =  authentication.getName();
		String userGrade = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .findFirst()
                .orElse("anonymousUser"); // 기본값 설정
		
		System.out.println("유저 등급 : " + userGrade);
		if (!userGrade.equals("ROLE_ANONYMOUS")) {
		return "servicecenter/notice_write";
	} else {
		return "member/login";
	}

}
	
	@PostMapping("/support/notice/write")
	public String PostNoticeWrite (@RequestParam("noticeFile") MultipartFile[] noticeFile, 
			String noticeTitle, String noticeContent,  String noticeCategory,
			Model md
			) {
		SecurityContextHolder.getContext().getAuthentication();
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String userId =  authentication.getName();
		String userGrade = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .findFirst()
                .orElse("anonymousUser"); // 기본값 설정
		
	    NoticeDto dto = new NoticeDto();
	    dto.setNoticeTitle(noticeTitle);
	    dto.setNoticeContent(noticeContent);
	    dto.setNoticeCategory(noticeCategory);
	    dto.setUserId(userId); 
	    md.addAttribute("userGrade", userGrade);
	    
	    if (userGrade.equals("admin")) {
	    int noticeId = noticeService.insertNotice(dto);
	    String noticeIdStr = String.valueOf(noticeId);
	    System.out.println("공지사항 글번호 : " + noticeId);
	    
	    try {
	        for (MultipartFile noticeFile2 : noticeFile) {
	            if (noticeFile2 != null && !noticeFile2.isEmpty()) {
	                Map<String, Object> uploadResult = cloudinary.uploader().upload(noticeFile2.getBytes(), ObjectUtils.emptyMap());
	                String savedFilePathName = uploadResult.get("url").toString();

	                NoticeFileDto noticeFileDto = new NoticeFileDto();
	                noticeFileDto.setNoticeId(noticeIdStr);
	                noticeFileDto.setNoticeCategory(noticeCategory);
	                noticeFileDto.setOriginalFilename(noticeFile2.getOriginalFilename());
	                noticeFileDto.setSavedFilePathName(savedFilePathName);
	                System.out.println("공지사항 번호 : " + dto.getNoticeId());
	                System.out.println("파일 카테고리 : " + noticeCategory);
	                System.out.println("파일 오리지널 명 : " + noticeFile2.getOriginalFilename());
	                System.out.println("파일 패스 명 : " + savedFilePathName);

	                noticeService.insertNoticeFile(noticeFileDto);
	            }
	        }

	    } catch (IOException e) {
	        e.printStackTrace();
	        System.out.println("파일 첨부 에러");
	    }
			return "redirect:/support/notice/list";
	    }else {
	    	return "memeber/login";
	    }
	}
	
	// 공지사항 수정
	@GetMapping("/support/notice/update/{noticeId}")
	public String getNoticeUpdate (Model md, @PathVariable("noticeId") String noticeId, String noticeCategory, String noticeTime) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String userId =  authentication.getName();
		String userGrade = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .findFirst()
                .orElse("anonymousUser"); // 기본값 설정
		System.out.println("유저 등급 : " + userGrade);
		
		if (!userGrade.equals("ROLE_ANONYMOUS")) {
		
		List<NoticeFileDto> fileDtoList = noticeService.selectOneNoticeFile(noticeId);
		md.addAttribute("noticeDto", noticeService.selectOneNotice(noticeId));
		md.addAttribute("noticeFileDto", fileDtoList);
		System.out.println("noticeCategory : " + noticeCategory);
		System.out.println("파일 개수 : " + fileDtoList.size());
			
		return "servicecenter/notice_update";
		} else {
			return "member/login";
		}
	}
	
	@PostMapping("/support/notice/update")
	public String postNoticeUpdate (// RedirectAttributes rd, 
			String noticeTitle, 
			String noticeContent, 
			@RequestParam("noticeFile") MultipartFile[] noticeFile,
			String noticeCategory,
			String noticeTime,
			int noticeId, Model md
			) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String userId =  authentication.getName();
		String userGrade = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .findFirst()
                .orElse("anonymousUser"); // 기본값 설정
		
		System.out.println("유저 등급 : " + userGrade);
		System.out.println("공지 업데이트 포스트 컨트롤러");
		System.out.println("noticeId : " + noticeId);
		System.out.println("noticeTitle : " + noticeTitle);
		System.out.println("noticeContent : " + noticeContent);
		System.out.println("noticeFile : " + noticeFile);
		System.out.println("noticeCategory : " + noticeCategory);
		System.out.println("noticeTime : " + noticeCategory);
		
//		md.addAttribute("noticeDto", noticeService.selectOneNotice(noticeId));
		
	    NoticeDto dto = new NoticeDto();
	    dto.setNoticeTime(noticeTime);
	    dto.setNoticeTitle(noticeTitle);
	    dto.setNoticeContent(noticeContent);
	    dto.setNoticeId(noticeId);	    // noticeFile과 noticeCategory가 NoticeDto에 있는 경우 설정;
	    dto.setNoticeCategory(noticeCategory);
//	    dto.setUserId(userId); 
//	    dto.setNoticeCategory("defaultUserGrade"); 
	    md.addAttribute("userGrade", userGrade);
	    md.addAttribute("noticeFileDto", noticeService.selectOneNoticeFile(String.valueOf(noticeId)));
        int updateNotice = noticeService.updateNotice(dto);
        // 파일 첨부 해야함..
        System.out.println("파일 리스트 개수 : " + noticeFile.length);
        try {
//	        List<NoticeFileDto> noticeFileDtos = new ArrayList<>();
        	noticeService.deleteNoticeFile(String.valueOf(noticeId));
            for (MultipartFile noticeFile2 : noticeFile) {
                if (noticeFile2 != null && !noticeFile2.isEmpty()) {
                    // 클라우드 서비스를 통해 파일 업로드 처리
                    Map<String, Object> uploadResult = cloudinary.uploader().upload(noticeFile2.getBytes(), ObjectUtils.emptyMap());
                    String savedFilePathName = uploadResult.get("url").toString();

                    NoticeFileDto noticeFileDto = new NoticeFileDto();
//    	                noticeFileDto.setNoticeId(noticeCategory)
                    // 공지번허 앞글자만 버리기 (시퀀스 앞에 숫자로 구분 지어야함)
                    String noticeIdStr = String.valueOf(noticeId);
                    if (noticeIdStr.length() > 1) {
                        noticeIdStr = noticeIdStr.substring(1);
                    }
                    noticeFileDto.setNoticeId(String.valueOf(noticeIdStr));
                    noticeFileDto.setNoticeCategory(noticeCategory);
                    noticeFileDto.setOriginalFilename(noticeFile2.getOriginalFilename());
                    noticeFileDto.setSavedFilePathName(savedFilePathName);
                    System.out.println("공지사항 번호 : " + dto.getNoticeId());
                    System.out.println("파일 카테고리 : " + noticeCategory);
                    System.out.println("파일 오리지널 명 : " + noticeFile2.getOriginalFilename());
                    System.out.println("파일 패스 명 : " + savedFilePathName);
                    
                    noticeService.insertNoticeFile(noticeFileDto);
	            }
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	        System.out.println("파일 첨부 에러");
	    }
		return "redirect:/support/notice/list";
	}
	@GetMapping("/support/notice/view/{noticeId}")
	public String viewNotice(Model md, @PathVariable("noticeId") String noticeId, String noticeCategory) {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String userId =  authentication.getName();
		String userGrade = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .findFirst()
                .orElse("anonymousUser"); // 기본값 설정
		
		System.out.println("유저 등급 : " + userGrade);
		if (!userGrade.equals("ROLE_ANONYMOUS")) {
				
			System.out.println("noticeCategory : " + noticeCategory);

	        // 공지사항 정보 가져오기
	        NoticeDto noticeDto = noticeService.selectOneNotice(noticeId);
	        
	        List<NoticeFileDto> noticeFileDtoList = noticeService.selectOneNoticeFile(noticeId);
	        for(NoticeFileDto noticefileDto : noticeFileDtoList) {
	        	System.out.println("파일 번호 : " + noticefileDto.getFileId());
	        	System.out.println("파일 오리지널명 : " + noticefileDto.getOriginalFilename());
	        	System.out.println("파일 저장 경로 : " + noticefileDto.getSavedFilePathName());
	        }
	        System.out.println("파일 리스트 개수 : " + noticeFileDtoList.size());
	        
	        md.addAttribute("noticeFileDto", noticeFileDtoList);
			md.addAttribute("noticeDto", noticeDto);
			md.addAttribute("userGrade", userGrade);
			return "servicecenter/notice_view";
		}else {
			return "member/login";
		}
	}
	
	@PostMapping("/support/notice/delete")
	public String deleteNotice(@RequestParam String noticeId) {
		System.out.println("삭제될 할 글 번호 : " + noticeId);
		noticeService.deleteNoticeFile(noticeId);
		noticeService.deleteNotice(noticeId);
		
		return "redirect:/support/notice/list";
	}
	
	
}
