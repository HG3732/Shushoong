package kh.mclass.shushoong.servicecenter.model.Controller;

import java.io.File;
import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Iterator;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import jakarta.mail.Multipart;
import jakarta.servlet.http.HttpSession;
import kh.mclass.shushoong.servicecenter.model.domain.NoticeDto;
import kh.mclass.shushoong.servicecenter.model.domain.NoticeFileDto;
import kh.mclass.shushoong.servicecenter.model.domain.NoticeFileWriteDto;
import kh.mclass.shushoong.servicecenter.model.domain.OnlineQnADto;
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
		
		model.addAttribute("userGrade",userGrade);
		model.addAttribute("result", service.selectOneQna(faqId));
		return "servicecenter/viewQnA";
	}
	
	@PostMapping("/support/qna/write/answer.ajax")
	public String writeQnAanswer(Model model, String faqId, String ansContent) {
		service.updateAnswer(faqId, ansContent);
		model.addAttribute("result", service.selectOneQna(faqId));
		return "servicecenter/viewQnA";
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
		
		md.addAttribute("userGrade",userGrade);
		System.out.println("유저 등급 : " + userGrade);
		return "servicecenter/writeQna";
	}
	
	@PostMapping("/support/qna/write")
	public String PostQnaWrite (String askTitle, String category,
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

	    int insertQna = service.insertQna(dto);
	    int insertQnaCat = service.insertQnaCat(dto);
	    
	    // 파일 첨부 해야함..
		
		return "redirect:/support/qna/list";
	}
	
	
	
	
	
	
// ===================================================	
	
	
	
	
	
	
	// 마이페이지 공지사항
	@GetMapping("/support/notice/list")
	public String noticeList (Model md, 
			String pageNum) {
			
			SecurityContextHolder.getContext().getAuthentication();
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
		
		SecurityContextHolder.getContext().getAuthentication();
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
		SecurityContextHolder.getContext().getAuthentication();
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
		
		System.out.println("유저 등급 : " + userGrade);
		System.out.println("공지 작성 포스트 컨트롤러");
		System.out.println("noticeTitle : " + noticeTitle);
		System.out.println("noticeContent : " + noticeContent);
		System.out.println("noticeFile : " + noticeFile);
		System.out.println("noticeCategory : " + noticeCategory);
		
	    NoticeDto dto = new NoticeDto();
	    dto.setNoticeTitle(noticeTitle);
	    dto.setNoticeContent(noticeContent);
	    dto.setNoticeCategory(noticeCategory);
	    dto.setUserId(userId); 
	    md.addAttribute("userGrade", userGrade);
	    
//	    int insertNotice = noticeService.insertNotice(dto);
	    int noticeId = noticeService.insertNotice(dto);
	    String noticeIdStr = String.valueOf(noticeId);
	    System.out.println("공지사항 글번호 : " + noticeId);
//	    NoticeFileDto noticeFileDto = new NoticeFileDto();
	    
	    try {
//	        List<NoticeFileDto> noticeFileDtos = new ArrayList<>();
	        for (MultipartFile noticeFile2 : noticeFile) {
	            if (noticeFile2 != null && !noticeFile2.isEmpty()) {
	                // 클라우드 서비스를 통해 파일 업로드 처리
	                Map<String, Object> uploadResult = cloudinary.uploader().upload(noticeFile2.getBytes(), ObjectUtils.emptyMap());
	                String savedFilePathName = uploadResult.get("url").toString();

	                NoticeFileDto noticeFileDto = new NoticeFileDto();
//	                noticeFileDto.setNoticeId(noticeCategory)
	                noticeFileDto.setNoticeId(noticeIdStr);
	                noticeFileDto.setNoticeCategory(noticeCategory);
	                noticeFileDto.setOriginalFilename(noticeFile2.getOriginalFilename());
	                noticeFileDto.setSavedFilePathName(savedFilePathName);
	                System.out.println("공지사항 번호 : " + dto.getNoticeId());
	                System.out.println("파일 카테고리 : " + noticeCategory);
	                System.out.println("파일 오리지널 명 : " + noticeFile2.getOriginalFilename());
	                System.out.println("파일 패스 명 : " + savedFilePathName);

//	                noticeFileDtos.add(noticeFileDto);
	                noticeService.insertNoticeFile(noticeFileDto);
	            }
	        }


	    } catch (IOException e) {
	        e.printStackTrace();
	        System.out.println("파일 첨부 에러");
	    }
	    
	    // 파일 첨부 해야함..
//	    if (noticeFile.length != 0) {
//	        try {
//	            // 파일 저장 경로 설정
//	        	Map<String, Object> uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
//	        	String saveFile = uploadResult.get("url").toString();
//	        	System.out.println("파일 패스 명 : " + saveFile);
//	            
//	        	
//	            List<NoticeFileDto> noticeFiles = new ArrayList<>();
//	            NoticeFileDto noticeFileDto = new NoticeFileDto();
//	            noticeFileDto.setNoticeCategory(noticeCategory);
//	            noticeFileDto.setOriginalFilename(noticeFile.getOriginalFilename());
//	            noticeFileDto.setSavedFilePathName(saveFile);
//	            System.out.println("파일 카테고리 : " + noticeCategory);
//	            System.out.println("파일 오리지널 네임 : " + noticeFile.getOriginalFilename());
//	            System.out.println("파일 패스 명 : " + saveFile);
//	            
//	            noticeFiles.add(noticeFileDto);
//	            
//	            // 파일 정보 데이터베이스에 저장
//	            noticeService.insertNoticeFile(noticeFiles);
//	            
//	        } catch (IOException e) {
//	            e.printStackTrace();
//	            System.out.println("파일 첨부 에러");
//	        }
//		}
		
		return "redirect:/support/notice/list";
	}
	
	// 공지사항 수정
	@GetMapping("/support/notice/update/{noticeId}")
	public String getNoticeUpdate (Model md, @PathVariable("noticeId") String noticeId, String noticeCategory) {
		SecurityContextHolder.getContext().getAuthentication();
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String userId =  authentication.getName();
		String userGrade = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .findFirst()
                .orElse("anonymousUser"); // 기본값 설정
		
		System.out.println("유저 등급 : " + userGrade);
		md.addAttribute("noticeDto", noticeService.selectOneNotice(noticeId));
		System.out.println("noticeCategory : " + noticeCategory);
		if (!userGrade.equals("ROLE_ANONYMOUS")) {
			return "servicecenter/notice_update";
	} else {
		return "member/login";
	}
	}
	
	@PostMapping("/support/notice/update")
	public String postNoticeUpdate (// RedirectAttributes rd, 
			String noticeTitle, 
			String noticeContent, 
			String noticeFile,
			String noticeCategory,
			int noticeId, Model md
			) {
		SecurityContextHolder.getContext().getAuthentication();
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
		
//		md.addAttribute("noticeDto", noticeService.selectOneNotice(noticeId));
		
	    NoticeDto dto = new NoticeDto();
	    dto.setNoticeTitle(noticeTitle);
	    dto.setNoticeContent(noticeContent);
	    dto.setNoticeId(noticeId);	    // noticeFile과 noticeCategory가 NoticeDto에 있는 경우 설정;
	    dto.setNoticeCategory(noticeCategory);
//	    dto.setUserId(userId); 
//	    dto.setNoticeCategory("defaultUserGrade"); 
	    md.addAttribute("userGrade", userGrade);
	    
        int updateNotice = noticeService.updateNotice(dto);
        // 파일 첨부 해야함..
		
		return "redirect:/support/notice/list";
	}
	
	@GetMapping("/support/notice/view/{noticeId}")
	public String viewNotice(Model md, @PathVariable("noticeId") String noticeId, String noticeCategory) {
		
		SecurityContextHolder.getContext().getAuthentication();
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
