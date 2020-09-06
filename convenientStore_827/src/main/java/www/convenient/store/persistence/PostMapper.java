package www.convenient.store.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import www.convenient.store.model.PostVO;
import www.convenient.store.model.ReviewTargetVO;
import www.convenient.store.paging.Criteria;

public interface PostMapper {
	// CRUD순으로 작성
	
	// id로 특정 리뷰포스트 찾기
	public PostVO findByPostId(String hierarchyId);
	
	// id로 특정 리뷰타겟 찾기(선호상품 등록에 사용)
	public ReviewTargetVO findByReviewTargetId(String hierarchyId);
	
	// 리뷰포스트 작성
	public void insertPost(@Param("post") PostVO post, @Param("target") ReviewTargetVO target);
	
	//리뷰포스트 목록 조회(현재 보고 있는 페이지 수와 페이지에 출력되는 데이터 수)
	public List<PostVO> getPostListWithPaging(Criteria cri);
	
	// 키워드를 포함하는 리뷰포스트의 총 개수를 구하는 쿼리
	public int getPostTotalWithKeyword(Criteria cri);
	
	// 키워드를 포함하는 리뷰포스트 목록 조회
	public List<PostVO> getPostListWithKeywordPaging(Criteria cri);
	
	//리뷰포스트 총 개수
	public int getPostTotal(Criteria cri);
	
	// 리뷰포스트 수정(포스트)
	public int updatePost(@Param("post") PostVO post);
	
	// 리뷰포스트 수정(리뷰타겟)
	public int updateReviewTarget(@Param("post") PostVO post, @Param("target") ReviewTargetVO target);
	
	// 리뷰포스트 삭제(포스트)
	public int deletePost(String hierarchyId);
	
	//리뷰포스트 삭제(리뷰타겟)
	public int deleteReviewTarget(String hierarchyId);

}
