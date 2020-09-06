package www.convenient.store.searchkey.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import www.convenient.store.searchkey.model.SearchkeyVO;

public interface SearchKeyMapper {

	// 검색키워드 생성
	public void insertSearchKey(SearchkeyVO keyWord);
	
	// 검색키워드 탐색 및 반환
	public List<SearchkeyVO> findSearchKeyword(List<SearchkeyVO> keyWordlist);

	// post에 검색키워드를 매칭
	public void insertKeyMatch(@Param("postId") String hierarchyId, @Param("keyId") int keywordId);
	
}
