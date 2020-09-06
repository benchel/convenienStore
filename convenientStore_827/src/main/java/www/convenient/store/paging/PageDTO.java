package www.convenient.store.paging;

import lombok.Data;

@Data
public class PageDTO {

	private int startPage; // 화면에 보여지는 페이지의 시작 번호
	private int endPage; // 화면에 보여지는 페이지의 끝 번호
	private boolean prev, next; // 이전, 다음
	
	private int total; // 테이블(게시판)이 가지고 있는 전체 데이터의 수
	private String keyword; // 검색키워드 
	private Criteria cri; // Criteria는 현재 페이지 번호, 페이지당 보여질 데이터의 수를 가지고 있음.
	
	public PageDTO(Criteria cri, int total) { 
		this.cri = cri;
		this.total = total; // 테이블이 가지고 있는 전체 데이터의 수

		// 페이징의 끝 번호 계산
		this.endPage = (int) (Math.ceil(cri.getPageNum()/10.0))*10;
		// 페이징의 시작 번호 계산
		this.startPage = this.endPage-9;
		
		// 테이블이 가지고 있는 전체 데이터의 수 * 1.0 / 페이지당 보여질 데이터의 수
		int realEnd = (int) (Math.ceil((total * 1.0)/cri.getAmount()));
		
		// 페이지 끝 번호가 테이블이 가지고 있는 전체 데이터의 수를 초과하지 않도록 방지
		if(realEnd < this.endPage) {
			this.endPage = realEnd;
		}
		// 이전 계산
		this.prev = this.startPage > 1;
		// 다음 계산
		this.next = this.endPage < realEnd;
	}
}
