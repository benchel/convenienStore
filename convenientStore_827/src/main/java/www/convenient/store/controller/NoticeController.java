package www.convenient.store.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import www.convenient.store.model.NoticeVO;
import www.convenient.store.paging.Criteria;
import www.convenient.store.paging.PageDTO;
import www.convenient.store.service.NoticeService;

@Controller
@Log4j
@RequestMapping("/notice/*")
@AllArgsConstructor
public class NoticeController {
	
	private NoticeService noticeService;
	
	//공지사항 목록조회
	@GetMapping("/noticeList")
	public void noticeList(Criteria cri, Model model) {
		log.info(model);
		model.addAttribute("nlist", noticeService.getNoticeListWithPaging(cri));
		
		//공지사항 총 개수
		int total = noticeService.getNoticeTotal(cri);
		
		model.addAttribute("pageMaker", new PageDTO(cri, total));
	}
	
	//공지사항 작성
	@PostMapping("/noticeWrite")
	public String noticeWrite(NoticeVO notice) {
		noticeService.insertNotice(notice);
		return "redirect:/notice/noticeList";
	}
	
	//공지사항 수정
	public String noticeModify(@RequestParam("hierarchyId") String hierarchyId) {
		noticeService.updateNotice(hierarchyId);
		return "redirect:/notice/noticeList";
	}
	
	//공지사항 삭제
	public String noticeDelete(@RequestParam("hierarchyId") String hierarchyId) {
		noticeService.deleteNotice(hierarchyId);
		return "redirect:/notice/noticeList";
	}
	
}
