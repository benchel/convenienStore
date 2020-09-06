package www.convenient.store.personalization.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import www.convenient.store.personalization.model.PreferVO;

public interface PreferService {
	
	// 선호상품 목록 조회 
	public List<PreferVO> getPreferList(String userId);
	
	// 선호상품 등록
	public int registerPrefer(PreferVO prefer);
	
	// 추천목록 조회
	public List<PreferVO> recommendPreferlist(PreferVO prefer);
}
