package www.convenient.store.paging;

import lombok.Data;

@Data
public class Criteria { //criterion의 복수형 1. 기준, 2. 평가
	
	private int pageNum; //현재 페이지 번호
	private int amount; // 한 페이지당 출력할 데이터의 수
	
	private String keyword; // 검색키워드
	
	public Criteria() {
		this(1,10, "");
	}

	public Criteria(int pageNum, int amount, String keyword) {
		this.pageNum=pageNum;
		this.amount=amount;
		this.keyword=keyword;
	}
}
