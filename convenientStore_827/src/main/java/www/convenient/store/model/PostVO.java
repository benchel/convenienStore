package www.convenient.store.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;
import www.convenient.store.searchkey.model.SearchkeyVO;
import www.mydream.com.KeyWordTarget;

@Data
@NoArgsConstructor // 기본 생성자를 생성
public class PostVO extends ReplyVO{
	// 게시글 : P, 댓글 : R, 공지사항 : N
	private static final char objType = 'P';
	
	//게시물 제목
	@KeyWordTarget
	private String title;
	//게시판 ID_게시물이 어떤 게시판에 속해 있는지 식별 
	private int boardId;
	
	private ReviewTargetVO reviewTarget;
	
	// 포스트에 의하여 생성되는 SearchKeyWord List
	private List<SearchkeyVO> SearchKeylist = new ArrayList<>();
	
	public PostVO(String hierarchyId) {
		super(hierarchyId);
	}
	
	public PostVO(String hierarchyId, ReviewTargetVO reviewTarget) {
		super(hierarchyId);
		this.reviewTarget=reviewTarget;
	}
	
	@Override
	public String getHierarchyId() {
		// 부모클래스(ReplyVO)의 필드 속성 값을 반환한다.
		return super.getHierarchyId();
	}
	
	@Override
	public String getKeyWordSource() {
		return super.getKeyWordSource()+" "+this.title;
	}
	// 리뷰포스트를 작성하면서 생성된 키워드들을 넣는다.
	public void addKeyword(SearchkeyVO keyword) {
		SearchKeylist.add(keyword);
	}
}
