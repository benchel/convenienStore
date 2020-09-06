package www.convenient.store.service;

import java.util.List;

import www.convenient.store.model.PostVO;
import www.convenient.store.model.ReviewTargetVO;
import www.convenient.store.paging.Criteria;
import www.convenient.store.searchkey.model.SearchkeyVO;

public interface PostService {
	
	//리뷰포스트 작성(post, reviewTarget)
	public void ReviewPostWrite(PostVO post, ReviewTargetVO target);
	
	//리뷰포스트의 총 개수
	public int getPostTotal(Criteria cri);
	
	//리뷰포스트 목록 조회(현재 보고 있는 페이지 수와 페이지에 출력되는 데이터 수)
	public List<PostVO> getPostListWithPaging(Criteria cri);
	
	// 키워드 결과를 포함하는 리뷰포스트의 총 개수를 구하는 쿼리
	public int getPostTotalWithKeyword(Criteria cri);
	
	// 키워드 검색 결과를 포함하는 리뷰포스트 목록 조회
	public List<PostVO> getPostListWithKeywordPaging(Criteria cri);
	
	// id로 특정 리뷰포스트 찾기
	public PostVO findByPostId(String hierarchyId);
	
	// id로 특정 리뷰타겟 찾기(선호상품 등록에 사용)
	public ReviewTargetVO findByReviewTargetId(String hierarchyId);
	
	//리뷰포스트 수정
	public int updateReviewPost(PostVO post, ReviewTargetVO target);
	
	//리뷰포스트 삭제
	public int deleteReviewPost(String hierarchyId);
}
