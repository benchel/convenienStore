package www.convenient.store.controller;


import java.util.List;
import org.springframework.http.MediaType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import www.convenient.store.model.ReplyVO;
import www.convenient.store.service.ReplyService;

/**
 * RequestMappingHandlerMapping(DefaultAnnotationHandlerMapping이 deprecated되면서 대체됨)
 * pring MVC(3.1이후버전) 제공하는 주요 HandlerMapping 구현 클래스 중 하나이다.
 * 
 * @RequestMapping 은 RequestMappingHandlerMapping을 사용하는 어노테이션이다.
 * url에 하나의 컨트롤러가 매핑되던 다른 핸들러 매핑과 달리 메서드 단위까지 세분화하여 적용할 수 있으며,
 * url뿐만 아니라 파라미터, 헤더 등 더욱 넓은 범위를 적용할 수 있다.
 *
 * 클래스와 메서드에 붙은 어노테이션 정보를 결합해 최종 매핑정보를 생성한다.
 * 기본적인 결합 방법은 클래스 레벨의 @RequestMapping을 기준으로 삼고,
 * 메서드 레벨의 @RequestMapping 으로 세분화하는 방식으로 사용된다.
 */
@RequestMapping("/replies/*")
@RestController
@AllArgsConstructor
public class ReplyController {
	
	private ReplyService replyService;
	
	/** GetMapping란?
	 * 기존에 존재하던 '@RequestMapping'를 method 별로 분리하여 간소화시켜놓은 것 중 하나이다.
	 * '@RequestMapping(value="/url", method=RequestMethod.GET)' -> '@GetMapping(value ="")'
	 * consumes : RequestBody에 담는 타입을 제한한다.
	 * 
	 * public class ResponseEntity<T> extends HttpEntity<T>
	 * HttpEntity를 상속받음으로써 HttpHeader와 body를 갖는다.
	 * 개발자는 이를 이용하여 HTTP상태코드와 데이터를 담는다.
	 * 
	 * '@PathVariable' 
	 * URL경로에  파라미터를 담는다.
	 * 담고자 하는 파라미터를 { }로 감싸서 url에 작성하면 
	 * '@PathVariable'이 자동으로 매핑시켜준다.
	 */
	@GetMapping(value="rlist/{postId}",
			produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
	public ResponseEntity<List<ReplyVO>> getReplyList(@PathVariable("postId") String postId) {
		ReplyVO reply = new ReplyVO();
		reply.setHierarchyId(postId);
		
		return ResponseEntity
				.status(HttpStatus.OK) // 전달할 HTTP 상태 코드
				.body(replyService.getReplyList(reply)); // 전달할 데이터
	}
	
	/**
	 * contentType() :  클라이언트에 데이터를 전송할 때 해당 데이터의 타입. 
	 * consumes : RequestBody에 담는 타입을 제한한다.
	 * produces : 지정한 데이터 타입으로만 클라이언트에 응답한다.
	 */
	@PreAuthorize("isAuthenticated()")
	@PostMapping(value = "new",
			consumes = "application/json",
			produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
	public ResponseEntity<ReplyVO> insertReply(@RequestBody ReplyVO reply) {
		ReplyVO parent = new ReplyVO();
		//getReviewPost.jsp에서 가져온 부모의 id를 이식한다. (코드 169 참고)
		parent.setHierarchyId(reply.getHierarchyId());
		
		int cnt = replyService.insertReReply(parent, reply);
		return cnt == 1 ? new ResponseEntity(reply, HttpStatus.OK)
				: new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
