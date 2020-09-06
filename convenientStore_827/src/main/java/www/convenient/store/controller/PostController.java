package www.convenient.store.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import www.convenient.store.model.PostVO;
import www.convenient.store.model.ReviewTargetVO;
import www.convenient.store.model.StoreVO;
import www.convenient.store.paging.Criteria;
import www.convenient.store.paging.PageDTO;
import www.convenient.store.service.PostService;
import www.convenient.store.service.StoreService;

@Controller
@Log4j
@RequestMapping("/post/*")
@AllArgsConstructor
public class PostController {
	
	private PostService postService;
	
	private StoreService storeService;
	
	//ReviewPost 목록조회(페이징 처리포함)
	@GetMapping("/postList")
	public void postList(Criteria cri, Model model) {
		log.info(model);
		model.addAttribute("plist", postService.getPostListWithKeywordPaging(cri));

		//포스트의 총 개수
		int total = postService.getPostTotalWithKeyword(cri);
		// model : view에 보여줄 데이터를 담는 통
		model.addAttribute("pageMaker", new PageDTO(cri, total));
	}

	//입력페이지 Open
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/postWritePage")
	public void openWritePostPage(Model model) {
		//storeService로 상품아이디와 브랜드이름을 호출한다.
		model.addAttribute("storelist", storeService.getStoreList());
		//호출된 값들은 입력페이지에 출력될 거임 예) ${store.storeId}, ${store.storeBrand}
	}
	
	//입력을 수행하고 목록으로 돌아옴
	@PreAuthorize("isAuthenticated()")
	@PostMapping("/postWrite")
	public String postWrite(PostVO post, ReviewTargetVO target, RedirectAttributes rttr) {
		postService.ReviewPostWrite(post, target);
		//뷰에 보낼 내용(등록완료 알림창에 띄울 데이터)
		rttr.addFlashAttribute("ReviewPostId", post.getHierarchyId());
		
		return "redirect:/post/postList";
	}
	
	//조회 페이지로 이동
	@GetMapping({"/getReviewPost"})
	public void getReviewPost(@RequestParam("hierarchyId") String hierarchyId,
			Model model) {
		model.addAttribute("post", postService.findByPostId(hierarchyId));
	}
	
	//수정 페이지로 이동
	@GetMapping({"/postModifyPage"})
	public void getReviewPost(@RequestParam("hierarchyId") String hierarchyId,
			Model model, StoreVO store) {
		model.addAttribute("post", postService.findByPostId(hierarchyId));
		model.addAttribute("storelist", storeService.getStoreList());
	}
	
	//수정
	@PostMapping("/postModify")
	public String postModify(PostVO post, ReviewTargetVO target, RedirectAttributes rttr) {
		
		int success=0;
		success=postService.updateReviewPost(post, target);
		if(success==1) {
			rttr.addFlashAttribute("result", "success");
			return "redirect:/post/postList";
		}else {
			rttr.addFlashAttribute("result", "fail");
			return "redirect:/post/postModifyPage";
		}
	}
	
	//삭제
	@GetMapping("/postDelete")
	public String ReviewPostDelete(@RequestParam("hierarchyId") String hierarchyId) {
		postService.deleteReviewPost(hierarchyId);
		return "redirect:/post/postList";
	}	
	
}
