package www.convenient.store.personalization.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import www.convenient.store.model.ReviewTargetVO;
import www.convenient.store.model.StoreVO;

// 선호상품VO
@Data
@NoArgsConstructor // 기본 생성자를 생성
public class PreferVO {
	private String userId; // 사용자 ID
	private StoreVO store;
	private ReviewTargetVO preferProdc; // 선호상품
	
	public PreferVO(String userId, StoreVO store, ReviewTargetVO preferProdc) {
		super();
		this.userId = userId;
		this.store = store;
		this.preferProdc = preferProdc;
	}
}
