package www.convenient.store.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import www.convenient.store.model.PostVO;
import www.convenient.store.model.ReviewTargetVO;
import www.convenient.store.paging.Criteria;
import www.convenient.store.persistence.PostMapper;
import www.convenient.store.searchkey.model.SearchkeyVO;
import www.convenient.store.searchkey.persistence.SearchKeyMapper;
import www.convenient.store.searchkey.service.SearchKeyService;

@Log4j
@Service
@AllArgsConstructor
public class PostServiceImpl implements PostService {
	
	private PostMapper postMapper;
	
	private SearchKeyService searchService;
	
	private SearchKeyMapper searchKeyMapper;

	@Override
	public void ReviewPostWrite(PostVO post, ReviewTargetVO target) {
		//리뷰포스트 작성
		postMapper.insertPost(post, target);
		// 작성한 리뷰포스트를 바탕으로 SearchKeyWord 생성하여 keyword 테이블에 집어넣는다.
		SearchKeyCreate(post, target);
	}
	
	@Override
	public int getPostTotal(Criteria cri) {
		return postMapper.getPostTotal(cri);
	}
	
	@Override
	public List<PostVO> getPostListWithPaging(Criteria cri) {
		return postMapper.getPostListWithPaging(cri);
	}
	
	/**
	 * 키워드를 포함하는 리뷰포스트의 총 개수를 구하는 쿼리
	 */
	@Override
	public int getPostTotalWithKeyword(Criteria cri) {
		return postMapper.getPostTotalWithKeyword(cri);
	}

	@Override
	public List<PostVO> getPostListWithKeywordPaging(Criteria cri) {
		// TODO Auto-generated method stub
		return postMapper.getPostListWithKeywordPaging(cri);
	}
	
	@Override
	public PostVO findByPostId(String hierarchyId) {
		return postMapper.findByPostId(hierarchyId);
	}

	@Override
	public ReviewTargetVO findByReviewTargetId(String hierarchyId) {
		return postMapper.findByReviewTargetId(hierarchyId);		
	}

	@Transactional
	@Override
	public int updateReviewPost(PostVO post, ReviewTargetVO target) {
		postMapper.updatePost(post); // 리뷰포스트 제목,내용 업데이트
		postMapper.updateReviewTarget(post, target); //리뷰포스트 타겟 업데이트
		
		// 수정한 리뷰포스트를 바탕으로 SearchKeyWord 생성하여 keyword 테이블에 집어넣는다.
		SearchKeyCreate(post, target);
		
		return 1;
	}

	@Transactional
	@Override
	public int deleteReviewPost(String hierarchyId) {
		postMapper.deletePost(hierarchyId);
		postMapper.deleteReviewTarget(hierarchyId);
		return 1;
	}
	
	/**
	 * SearchKeyWord Create
	 */
	private void SearchKeyCreate(PostVO post, ReviewTargetVO target) {
		// SearchKeyWord의 대상이 될 소스들을 가져온다.(title, content, prdName)
		String keyWordSorces = post.getKeyWordSource()+" "+target.getPrdName();
		// 소스들을 형태소로 만든다.
		for(String str : searchService.extractKeyWord(keyWordSorces)) {
			// 형태소로 쪼개진 소스들을 keyword로 넣는다.
			SearchkeyVO keyword = new SearchkeyVO();
			keyword.setKeywordName(str);
			keyword.setPostId(post.getHierarchyId());
			// 리뷰포스트에 키워드목록으로 넣는다.
			post.addKeyword(keyword);
		}
		
		// 중복 배제 작업
		// 기존에 존재하는 검색키워드 테이블을 가져온다.
		List<SearchkeyVO> searchKeylist = 
				searchKeyMapper.findSearchKeyword(post.getSearchKeylist());
		
		// 새롭게 생성된 keyword 목록과 기존에 존재하는 keyword 목록을 비교한다.
		// 만약 중복되는 키워드가 있다면 제거한다.
		post.getSearchKeylist().removeAll(searchKeylist);
		
		// 중복을 제거한 keyword를 테이블에 넣는다. (키워드 테이블 갱신)
		for (SearchkeyVO createKeyword : post.getSearchKeylist()) {
			//keyword를 테이블에 넣는다.
			searchKeyMapper.insertSearchKey(createKeyword);
		}
	}
}
