package www.convenient.store.model;
// 어떤 게시글에서 리뷰된 상품인지는 hierarchy_id, obj_type로 식별한다.

import lombok.Data;
import lombok.NoArgsConstructor;
import www.mydream.com.KeyWordTarget;

@Data
@NoArgsConstructor // 기본 생성자를 생성
public class ReviewTargetVO {
	
	private String hierarchyId;
	private char objType; // 게시글 : P
	private int storeId;
	private String prdAssort; // 상품분류
	@KeyWordTarget
	private String prdName; // 상품이름
	private int prdPrice; // 상품가격
	private int prdRate; // 상품평 5점 만점

	public ReviewTargetVO(char objType, int storeId, String prdAssort, String prdName, int prdPrice, int prdRate) {
		this.objType = objType;
		this.storeId = storeId;
		this.prdAssort = prdAssort;
		this.prdName = prdName;
		this.prdPrice = prdPrice;
		this.prdRate = prdRate;
	}
	
	public ReviewTargetVO(String prdAssort, String prdName, int prdPrice) {
		this.prdAssort = prdAssort;
		this.prdName = prdName;
		this.prdPrice = prdPrice;
	}	
}
