package www.convenient.store.searchkey.service;

import java.util.List;

import www.convenient.store.searchkey.model.SearchkeyVO;

public interface SearchKeyService {
	
	// 검색키워드 추출(feat.komoran)
	public List<String> extractKeyWord(String keyWordSource);
	
	// 검색키워드  생성
	public void insertSearchKey(SearchkeyVO keyWord);
	
}
