package www.convenient.store.searchkey.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import kr.co.shineware.nlp.komoran.constant.DEFAULT_MODEL;
import kr.co.shineware.nlp.komoran.core.Komoran;
import kr.co.shineware.nlp.komoran.model.KomoranResult;
import kr.co.shineware.nlp.komoran.model.Token;
import lombok.AllArgsConstructor;
import www.convenient.store.searchkey.model.SearchkeyVO;
import www.convenient.store.searchkey.persistence.SearchKeyMapper;

@Service
@AllArgsConstructor
public class SearchKeyServiceImp implements SearchKeyService {
	
	private static Komoran komoran = new Komoran(DEFAULT_MODEL.FULL);
	
	private SearchKeyMapper searchMapper;

	@Override
	public void insertSearchKey(SearchkeyVO keyWord) {
		searchMapper.insertSearchKey(keyWord);
	}

	@Override
	public List<String> extractKeyWord(String keyWordSource) {
		List<String> ret = new ArrayList<>();
		
		//분석기에 넣어서 그 결과를 받는다.
		KomoranResult analyzeResultList = komoran.analyze(keyWordSource);
		
		//분석결과를 TokenList()로 변환하여 List<Token>에 넣는다.
		List<Token> tokenList = analyzeResultList.getTokenList();
		for (Token token : tokenList) {
			//tokenList에서 명사NN 숫자SN만을 뽑아 ret에 넣는다.
			if (token.getPos().startsWith("NN") || token.getPos().equals("SL")) {
				ret.add(token.getMorph());
			}
		}
		return ret;
	}
}
