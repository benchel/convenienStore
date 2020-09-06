package www.convenient.store.personalization.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import www.convenient.store.personalization.model.PreferVO;

public interface PreferMapper {
	
	// 선호상품 등록
	public int registerPrefer(PreferVO prefer);
	
	// 선호상품 목록 조회
	public List<PreferVO> getPreferList(@Param("userId") String userId);
	
	// 선택한 상품을 바탕으로 생성되는 추천리스트 조회
	public List<PreferVO> recommendPreferlist(PreferVO prefer);
	
}
