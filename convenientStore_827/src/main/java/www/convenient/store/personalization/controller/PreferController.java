package www.convenient.store.personalization.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import www.convenient.store.model.ReviewTargetVO;
import www.convenient.store.personalization.model.PreferVO;
import www.convenient.store.personalization.service.PreferService;

@RestController
@RequestMapping("/prefer/*")
@AllArgsConstructor
public class PreferController {
	
	private PreferService preferService;
	
	// 선호상품 목록 조회
	@GetMapping(value="preferList/{userId}",
			produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
	public ResponseEntity<List<PreferVO>> getPreferList(@PathVariable("userId") String userId) {
		
		List<PreferVO> list =preferService.getPreferList(userId);
	    return ResponseEntity
	    		.status(HttpStatus.OK)
	    		.body(list);
	}
	
	// 선호상품 등록
	@PostMapping(value = "reg",
			consumes = "application/json",
			produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
	public ResponseEntity<PreferVO> registerPrefer(@RequestBody PreferVO prefer) {
		
		int cnt = preferService.registerPrefer(prefer);
		
		return cnt == 1 ? new ResponseEntity(prefer, HttpStatus.OK)
				: new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	// 선호상품 목록 조회
	@GetMapping(value="recommendList/{userId}/{prdName}/{prdAssort}",
			produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
	public ResponseEntity<List<PreferVO>> getRecommendList(@PathVariable("userId") String userId, 
			@PathVariable("prdName") String prdName, @PathVariable("prdAssort") String prdAssort) {
		PreferVO preferForRecmmd = new PreferVO();
		preferForRecmmd.setUserId(userId);
		preferForRecmmd.setPreferProdc(new ReviewTargetVO(prdAssort, prdName, 0));
		
		List<PreferVO> list=preferService.recommendPreferlist(preferForRecmmd);
	    return ResponseEntity
	    		.status(HttpStatus.OK)
	    		.body(list);
	}
}
