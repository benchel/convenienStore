package www.convenient.store.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import www.mydream.com.KeyWordTarget;

@Data
@NoArgsConstructor
public class NoticeVO extends ReplyVO{
	// 게시글 : P, 댓글 : R, 공지사항 : N
	private char objType;
	
	//게시물 제목(해쉬태그 사용) 제목내용으로 검색가능
	private String title;
	
	private int boardId;
	
	public NoticeVO(String hierarchyId) {
		super(hierarchyId);
	}
	
	@Override
	public String getHierarchyId() {
		return super.getHierarchyId();
	}
}
